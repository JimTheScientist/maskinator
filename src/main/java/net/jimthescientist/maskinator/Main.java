package net.jimthescientist.maskinator;

import net.jimthescientist.maskinator.mask.BlueprintWindow;
import net.jimthescientist.maskinator.mask.PlannerWindow;
import net.jimthescientist.maskinator.panel.MFileTree;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.config.Configurator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Main {
    public static final Logger LOGGER = LogManager.getLogger("Maskinator");
    public static JScrollPane fileScrollPane = new JScrollPane(new MFileTree("./"));
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

        JMenuItem openFolder = new JMenuItem("Open Folder");

        openFolder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                 if (jFileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                     frame.getContentPane().remove(fileScrollPane);
                     File file = jFileChooser.getSelectedFile();
                     LOGGER.info("Opening folder {}", file.getAbsolutePath());
                     fileScrollPane = new JScrollPane(new MFileTree(file.getAbsolutePath()));
                     frame.getContentPane().add(fileScrollPane);
                     frame.getContentPane().revalidate();
                     frame.getContentPane().repaint();
                 }
            }
        });

        fileMenu.add(openFolder);

        JMenu windowMenu = new JMenu("Window");

        JMenuItem plannerItem = new JMenuItem("Open Planner");
        windowMenu.add(plannerItem);

        JMenuItem blueprintItem = new JMenuItem("Open Blueprint Editor");

        windowMenu.add(blueprintItem);

        plannerItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new PlannerWindow();
            }
        });

        blueprintItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new BlueprintWindow(null);
            }
        });

        menuBar.add(fileMenu);
        menuBar.add(windowMenu);
        frame.getContentPane().add(fileScrollPane);

        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}