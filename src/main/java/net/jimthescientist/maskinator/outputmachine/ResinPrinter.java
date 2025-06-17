package net.jimthescientist.maskinator.outputmachine;

import net.jimthescientist.maskinator.Main;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResinPrinter extends OutputMachine {
    private int maxHeight = 0;
    private int maxWidth = 0;
    @Override
    public int getMaxHeight() {
        return this.maxHeight;
    }

    @Override
    public int getMaxWidth() {
        return this.maxWidth;
    }

    @Override
    public JPanel getPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JPanel hwPanel = new JPanel();
        JLabel heightText = new JLabel("Height: ");
        hwPanel.setLayout(new GridBagLayout());
        JTextField heightField = new JTextField(Integer.toString(this.maxHeight), 4);
        heightText.setLabelFor(heightField);
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
        GridBagConstraints hCon = new GridBagConstraints();
        hCon.gridy = 0;
        GridBagConstraints wCon = new GridBagConstraints();
        wCon.gridy = 1;
        hwPanel.add(heightText, hCon);
        hwPanel.add(heightField, hCon);
        hwPanel.add(widthText, wCon);
        hwPanel.add(widthField, wCon);
        panel.add(hwPanel, BorderLayout.NORTH);
        panel.setBackground(Color.white);
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        JButton exportButton = new JButton("Export");
        exportButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.LOGGER.info("Exporting Resin Printer Data");
                Main.LOGGER.info("Max Height: {}", getMaxHeight());
                Main.LOGGER.info("Max Width: {}", getMaxWidth());
            }
        });
        panel.add(exportButton, BorderLayout.SOUTH);
        return panel;
    }
}
