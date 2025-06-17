package net.jimthescientist.maskinator.outputmachine;

import javax.swing.*;

public class Projector extends OutputMachine{
    @Override
    public int getMaxHeight() {
        return 0;
    }

    @Override
    public int getMaxWidth() {
        return 0;
    }

    @Override
    public JPanel getPanel() {
        return null;
    }
}
