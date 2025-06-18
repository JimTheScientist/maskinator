package net.jimthescientist.maskinator;

import javax.swing.*;
import java.awt.*;

public class Wafer {
    public Type type = Type.P_TYPE;

    public double diameter = 50.8; // in millimeters

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

        JLabel diameterLabel = new JLabel("Diameter: ");
        JTextField diameterField = new JTextField(Double.toString(diameter), 4);
        diameterPanel.add(diameterLabel);
        diameterPanel.add(diameterField);
        panel.add(diameterPanel);
        return panel;
    }
}
