package net.jimthescientist.maskinator.outputmachine;

import net.jimthescientist.maskinator.Main;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class BlueprintEditorMachine extends OutputMachine {
    private int height = 10;
    private int width = 10;
    private File file;

    public BlueprintEditorMachine(File saveFile) {
        this.file = saveFile;
    }

    @Override
    public int getMaxHeight() {
        return height;
    }

    @Override
    public int getMaxWidth() {
        return width;
    }

    @Override
    public double getPixelSize() {
        return 0.1;
    }

    @Override
    public JPanel getPanel() {

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JPanel hwPanel = new JPanel();
        JLabel heightText = new JLabel("Height: ");
        hwPanel.setLayout(new GridBagLayout());
        JTextField heightField = new JTextField(Integer.toString(this.height), 4);
        heightField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                onChange();
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                onChange();
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                onChange();
            }
            public void onChange() {
                try {
                    height = Integer.parseInt(heightField.getText());
                } catch (Exception _) {

                }
            }
        });
        JTextField widthField = new JTextField(Integer.toString(this.width), 4);
        JLabel widthText = new JLabel("Width: ");
        widthField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                onChange();
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                onChange();
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                onChange();
            }
            public void onChange() {
                try {
                    width = Integer.parseInt(widthField.getText());
                } catch (Exception _) {

                }
            }
        });

        GridBagConstraints hCon = new GridBagConstraints();
        hCon.gridy = 1;
        GridBagConstraints wCon = new GridBagConstraints();
        wCon.gridy = 0;
        hwPanel.add(heightText, hCon);
        hwPanel.add(heightField, hCon);
        hwPanel.add(widthText, wCon);
        hwPanel.add(widthField, wCon);
        panel.add(hwPanel, BorderLayout.NORTH);
        panel.setBackground(Color.white);
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.LOGGER.info("Saving Blueprint Data");
                if (file == null) {
                    JFileChooser jFileChooser = new JFileChooser();
                    jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    if (jFileChooser.showSaveDialog(panel) == JFileChooser.APPROVE_OPTION) {
                        file = jFileChooser.getSelectedFile();
                        String extension = file.getName().substring(file.getName().lastIndexOf('.') + 1);
                        if (file.exists()) {
                            JDialog overwriteDialog = new JDialog();
                            overwriteDialog.getContentPane().add(new JLabel("Overwrite File?"));
                            overwriteDialog.setVisible(true);
                        } else {
                            if (!extension.equals("mskblp")) {
                                file = new File(file.getName() + ".mskblp");
                                Main.LOGGER.info("Adding file extension .mskblp");
                            }
                        }
                    }

                }
                Main.LOGGER.info("Height: {}", getMaxHeight());
                Main.LOGGER.info("Width: {}", getMaxWidth());
                Main.LOGGER.info("Saving to file {}", file.getAbsolutePath());
                ((JFrame) SwingUtilities.windowForComponent(panel)).setTitle("Blueprint Editor - " + file.getName());
            }
        });
        panel.add(saveButton, BorderLayout.SOUTH);
        return panel;
    }
}
