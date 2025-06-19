package net.jimthescientist.maskinator.outputmachine;

import net.jimthescientist.maskinator.Main;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Projector extends OutputMachine{
    private int maxHeight = 1080;
    private int maxWidth = 1920;
    private double pixelSize = 0.1;

    @Override
    public int getMaxHeight() {
        return maxHeight;
    }

    @Override
    public int getMaxWidth() {
        return maxWidth;
    }

    @Override
    public double getPixelSize() {
        return pixelSize;
    }

    @Override
    public JPanel getPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JPanel hwPanel = new JPanel();
        JLabel heightText = new JLabel("Height: ");
        hwPanel.setLayout(new GridBagLayout());
        JTextField heightField = new JTextField(Integer.toString(this.maxHeight), 4);
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
                    maxHeight = Integer.parseInt(heightField.getText());
                } catch (Exception _) {

                }
            }
        });
        JTextField widthField = new JTextField(Integer.toString(this.maxWidth), 4);
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
                    maxWidth = Integer.parseInt(widthField.getText());
                } catch (Exception _) {

                }
            }
        });

        JTextField pixelSizeField = new JTextField("0.1", 4);
        JLabel pixelSizeLabel = new JLabel("Pixel Size (mm): ");
        pixelSizeField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                onUpdate();
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                onUpdate();
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                onUpdate();
            }

            public void onUpdate() {
                try {
                    pixelSize = Double.valueOf(pixelSizeField.getText());
                } catch (Exception _){
                }
            }
        });

        GridBagConstraints hCon = new GridBagConstraints();
        hCon.gridy = 1;
        GridBagConstraints wCon = new GridBagConstraints();
        wCon.gridy = 0;
        GridBagConstraints pCon = new GridBagConstraints();
        pCon.gridy = 2;
        hwPanel.add(heightText, hCon);
        hwPanel.add(heightField, hCon);
        hwPanel.add(widthText, wCon);
        hwPanel.add(widthField, wCon);
        hwPanel.add(pixelSizeLabel, pCon);
        hwPanel.add(pixelSizeField, pCon);
        panel.add(hwPanel, BorderLayout.NORTH);
        panel.setBackground(Color.white);
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        JButton exportButton = new JButton("Export");
        exportButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.LOGGER.info("Exporting Projector Data");
                Main.LOGGER.info("Max Height: {}", getMaxHeight());
                Main.LOGGER.info("Max Width: {}", getMaxWidth());
            }
        });
        panel.add(exportButton, BorderLayout.SOUTH);
        return panel;
    }
}
