package net.jimthescientist.maskinator.outputmachine;

import javax.swing.*;

public abstract class OutputMachine {
    public abstract int getMaxHeight();
    public abstract int getMaxWidth();
    public abstract JPanel getPanel();

}
