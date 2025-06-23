package net.jimthescientist.maskinator.mask;

import net.jimthescientist.maskinator.Main;
import net.jimthescientist.maskinator.outputmachine.BlueprintEditorMachine;

import javax.swing.*;
import java.awt.*;

public class BlueprintWindow {
    public Blueprint blueprint;
    public Planner planner = new Planner();
    public BlueprintEditorMachine blueprintEditorMachine = new BlueprintEditorMachine();

    public BlueprintWindow() {
        Main.LOGGER.info("Opening Blueprint Editor window");
        JFrame blueprintFrame = new JFrame("Blueprint Editor");

        blueprintFrame.getContentPane().setLayout(new BorderLayout());

        blueprintFrame.add(planner, BorderLayout.CENTER);
        planner.getGrid().setOutputMachine(blueprintEditorMachine);
        blueprintFrame.add(blueprintEditorMachine.getPanel(), BorderLayout.EAST);

        blueprintFrame.setSize(800, 600);
        blueprintFrame.setVisible(true);
    }
}
