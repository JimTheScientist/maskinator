package net.jimthescientist.maskinator.mask;

import net.jimthescientist.maskinator.Main;
import net.jimthescientist.maskinator.outputmachine.ResinPrinter;

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

    public static void CreatePlannerWindow() {
        Main.LOGGER.info("Opening new Planner window");
        JFrame plannerFrame = new JFrame("Planner");
        plannerFrame.getContentPane().setLayout(new BorderLayout());
        JMenuBar menuBar = new JMenuBar();
        JMenu outputDevicesMenu = new JMenu("Output Devices");
        JMenuItem resinPrinter = new JMenuItem("Resin Printer");

        resinPrinter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ResinPrinter resinPrinter = new ResinPrinter();
                plannerFrame.getContentPane().add(resinPrinter.getPanel(), BorderLayout.EAST);
                plannerFrame.getContentPane().revalidate();
            }
        });

        outputDevicesMenu.add(resinPrinter);
        menuBar.add(outputDevicesMenu);

        plannerFrame.setJMenuBar(menuBar);

        JPanel planner = new Planner();
        plannerFrame.getContentPane().add(planner, BorderLayout.CENTER);
        plannerFrame.setSize(800, 600);
        plannerFrame.setVisible(true);
    }
}
