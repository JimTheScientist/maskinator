package net.jimthescientist.maskinator.mask;

import net.jimthescientist.maskinator.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Planner extends JPanel {
    private final Grid grid = new Grid();
    public Planner() {
        this.setLayout(new BorderLayout());
        this.add(grid, BorderLayout.CENTER);
        this.add(tools(), BorderLayout.NORTH);
    }

    private JPanel tools() {
        JPanel toolPanel = new JPanel();
        JButton zoomIn = new JButton("+");
        zoomIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Main.LOGGER.info("Zoom in");
                grid.zoom(2);
            }
        });


        JButton zoomOut = new JButton("-");
        zoomOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Main.LOGGER.info("Zoom out");
                grid.zoom(0.5);
            }
        });

        JButton panL = new JButton("→");
        panL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                grid.translateX(100);
            }
        });

        JButton panR = new JButton("←");
        panR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                grid.translateX(-100);
            }
        });

        JButton panU = new JButton("↑");
        panU.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                grid.translateY(-100);
            }
        });

        JButton panD = new JButton("↓");
        panD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                grid.translateY(100);
            }
        });


        toolPanel.add(panU);
        toolPanel.add(panD);
        toolPanel.add(panR);
        toolPanel.add(panL);
        toolPanel.add(zoomOut);
        toolPanel.add(zoomIn);
        return toolPanel;
    }
}
