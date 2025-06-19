package net.jimthescientist.maskinator.mask;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// TODO Fix zooming in and out, as it doens't zoom around where you are looking!
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
                grid.zoom(2);
            }
        });


        JButton zoomOut = new JButton("-");
        zoomOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                grid.zoom(0.5);
            }
        });

        JButton panL = new JButton("→");
        panL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                grid.translateX((int) (-100 / grid.getZoomFactor()));
            }
        });

        JButton panR = new JButton("←");
        panR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                grid.translateX((int) (100 / grid.getZoomFactor()));
            }
        });

        JButton panU = new JButton("↑");
        panU.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                grid.translateY((int) (100 / grid.getZoomFactor()));
            }
        });

        JButton panD = new JButton("↓");
        panD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                grid.translateY((int) (-100 / grid.getZoomFactor()));
            }
        });

        JButton refresh = new JButton("⟳");
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                grid.repaint();
            }
        });

        toolPanel.add(refresh);
        toolPanel.add(panU);
        toolPanel.add(panD);
        toolPanel.add(panR);
        toolPanel.add(panL);
        toolPanel.add(zoomOut);
        toolPanel.add(zoomIn);
        return toolPanel;
    }
    public Grid getGrid() {
        return this.grid;
    }
}
