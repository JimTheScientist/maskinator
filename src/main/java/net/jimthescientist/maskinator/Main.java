package net.jimthescientist.maskinator;


import net.jimthescientist.maskinator.mask.Grid;
import net.jimthescientist.maskinator.mask.Planner;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.config.Configurator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static final Logger LOGGER = LogManager.getLogger("Maskinator");
    public static void main(String[] args) {
        Configurator.setLevel("Maskinator", Level.DEBUG);
        LOGGER.info("Starting Maskinator...");

        JFrame frame = new JFrame("Maskinator 1.0");
        frame.setSize(800, 600);
        frame.getContentPane().setLayout(new BorderLayout());
        //frame.getContentPane().add(new ResinPrinter().getPanel(), BorderLayout.WEST);
        //frame.getContentPane().add(new ResinPrinter().getPanel(), BorderLayout.EAST);
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu windowMenu = new JMenu("Window");

        JMenuItem plannerItem = new JMenuItem("Open Planner");
        windowMenu.add(plannerItem);

        plannerItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Planner.CreatePlannerWindow();
            }
        });

        menuBar.add(fileMenu);
        menuBar.add(windowMenu);
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}