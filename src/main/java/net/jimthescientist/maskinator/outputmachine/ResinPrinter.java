package net.jimthescientist.maskinator.outputmachine;

import net.jimthescientist.maskinator.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ResinPrinter extends OutputMachine {
    private int maxHeight = 5120;
    private int maxWidth = 9024;
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
