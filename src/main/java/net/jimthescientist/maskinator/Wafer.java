package net.jimthescientist.maskinator;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class Wafer {
    public Type type = Type.P_TYPE;

    public double diameter = 50.8; // in millimeters

    public double centerX;
    public double centerY;

    public enum Type {
        P_TYPE,
        N_TYPE
    }

    public Wafer() {}

    public Wafer(Type type) {this.type = type;}

    public Wafer(Type type, double diameter) {this.type = type; this.diameter = diameter;}

    public Wafer(double diameter) {this.diameter = diameter;}

    public JPanel getWaferSettings() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.black));

        JPanel diameterPanel = new JPanel();
        diameterPanel.setLayout(new GridBagLayout());

        JLabel diameterLabel = new JLabel("Diameter (mm): ");
        JTextField diameterField = new JTextField(Double.toString(diameter), 4);
        diameterField.getDocument().addDocumentListener(new DocumentListener() {
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
                try {diameter = Double.valueOf(diameterField.getText());} catch (Exception _) {}
            }
        });

        diameterPanel.add(diameterLabel);
        diameterPanel.add(diameterField);

        JLabel centerXLabel = new JLabel("Center X (mm): ");
        JLabel centerYLabel = new JLabel("Center Y (mm): ");

        JTextField centerXField = new JTextField("0", 3);
        centerXField.getDocument().addDocumentListener(new DocumentListener() {
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
                try {centerX = Double.valueOf(centerXField.getText());} catch (Exception _) {}
            }
        });
        JTextField centerYField = new JTextField("0", 3);
        centerYField.getDocument().addDocumentListener(new DocumentListener() {
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
                try {centerY = Double.valueOf(centerYField.getText());} catch (Exception _) {}
            }
        });
        GridBagConstraints xCon = new GridBagConstraints();
        xCon.gridy = 1;
        diameterPanel.add(centerXLabel, xCon);
        diameterPanel.add(centerXField, xCon);
        GridBagConstraints yCon = new GridBagConstraints();
        yCon.gridy = 2;
        diameterPanel.add(centerYLabel, yCon);
        diameterPanel.add(centerYField, yCon);

        panel.add(diameterPanel);
        return panel;
    }
}
