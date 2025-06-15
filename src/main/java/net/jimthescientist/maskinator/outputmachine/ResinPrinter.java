package net.jimthescientist.maskinator.outputmachine;

import net.jimthescientist.maskinator.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

public class ResinPrinter extends OutputMachine {
    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public JPanel getPanel() {
        JPanel panel = new JPanel();

        panel.setSize(100, 100);
        panel.setBackground(Color.white);
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        JButton exportButton = new JButton("Export");
        exportButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.LOGGER.info("Export button clicked");
            }
        });
        panel.add(exportButton);
        return panel;
    }
}
