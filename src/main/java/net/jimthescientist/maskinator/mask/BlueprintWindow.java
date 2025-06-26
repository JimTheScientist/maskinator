package net.jimthescientist.maskinator.mask;

import net.jimthescientist.maskinator.Main;
import net.jimthescientist.maskinator.outputmachine.BlueprintEditorMachine;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class BlueprintWindow {
    public Blueprint blueprint;
    public Planner planner = new Planner();
    public BlueprintEditorMachine blueprintEditorMachine;

    public BlueprintWindow(File fileToOpen) {
        this.blueprintEditorMachine = new BlueprintEditorMachine(fileToOpen);
        Main.LOGGER.info("Opening Blueprint Editor window");
        JFrame blueprintFrame;
        if (fileToOpen == null) {
            blueprintFrame = new JFrame("Blueprint Editor - Untitled");
        } else {
            blueprintFrame = new JFrame("Blueprint Editor - " + fileToOpen.getName());
        }

        blueprintFrame.getContentPane().setLayout(new BorderLayout());

        blueprintFrame.add(planner, BorderLayout.CENTER);
        planner.getGrid().setOutputMachine(blueprintEditorMachine);
        blueprintFrame.add(blueprintEditorMachine.getPanel(), BorderLayout.EAST);

        blueprintFrame.setSize(800, 600);
        blueprintFrame.setVisible(true);
    }
}
