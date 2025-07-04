package net.jimthescientist.maskinator.panel;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import java.io.File;

public class MFileTree extends JTree {
    public MFileTree(String path) {
        super(scan(new File(path)));
    }

    private static MutableTreeNode scan(File node) {
        DefaultMutableTreeNode retNode = new DefaultMutableTreeNode(node.getName());
        if (node.isDirectory()) {
            for (File file : node.listFiles()) {
                String extension = file.getName().substring(file.getName().lastIndexOf('.') + 1);
                if (extension.equals("mskblp") || extension.equals("mskpln")) retNode.add(scan(file));
            }
        }
        return retNode;
    }
}
