package net.jimthescientist.maskinator.outputmachine;

import javax.swing.*;

public abstract class OutputMachine {
    public abstract int getMaxHeight(); // Pixels
    public abstract int getMaxWidth(); // Pixels
    public abstract double getPixelSize(); // in MM
    public abstract JPanel getPanel();

}
