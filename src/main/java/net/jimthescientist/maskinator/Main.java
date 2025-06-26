package net.jimthescientist.maskinator;

import net.jimthescientist.maskinator.mask.BlueprintWindow;
import net.jimthescientist.maskinator.mask.PlannerWindow;
import net.jimthescientist.maskinator.panel.MFileTree;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.config.Configurator;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

public class Main {
    public static final Logger LOGGER = LogManager.getLogger("Maskinator");
    public static MFileTree mFileTree = new MFileTree("./");
    public static JScrollPane fileScrollPane = new JScrollPane(mFileTree);
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

        openFolder.addActionListener(actionEvent -> {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
             if (jFileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                 frame.getContentPane().remove(fileScrollPane);
                 File file = jFileChooser.getSelectedFile();
                 LOGGER.info("Opening folder {}", file.getAbsolutePath());
                 mFileTree = new MFileTree(file.getAbsolutePath());
                 FileSelected fs = new FileSelected();
                 mFileTree.addTreeSelectionListener(fs);
                 mFileTree.addMouseListener(fs);
                 fileScrollPane = new JScrollPane(mFileTree);
                 frame.getContentPane().add(fileScrollPane);
                 frame.getContentPane().revalidate();
                 frame.getContentPane().repaint();
             }
        });

        fileMenu.add(openFolder);

        JMenu windowMenu = new JMenu("Window");

        JMenuItem plannerItem = new JMenuItem("Open Planner");
        windowMenu.add(plannerItem);

        JMenuItem blueprintItem = new JMenuItem("Open Blueprint Editor");

        windowMenu.add(blueprintItem);

        plannerItem.addActionListener(actionEvent -> new PlannerWindow());

        blueprintItem.addActionListener(actionEvent -> new BlueprintWindow(null));

        menuBar.add(fileMenu);
        menuBar.add(windowMenu);
        FileSelected fs = new FileSelected();
        mFileTree.addTreeSelectionListener(fs);
        mFileTree.addMouseListener(fs);

        frame.getContentPane().add(fileScrollPane);

        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    static class FileSelected implements TreeSelectionListener, MouseListener {
        private TreePath path;
        @Override
        public void valueChanged(TreeSelectionEvent treeSelectionEvent) {
            path = treeSelectionEvent.getPath();
            LOGGER.info("Opening " + treeSelectionEvent.getPath());
        }

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            if (mouseEvent.getClickCount() == 2) {
                LOGGER.info("Double click on " + path);
            }
            LOGGER.info("Mouse clicked");
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {

        }
    }
}