package net.jimthescientist.maskinator.mask;

import net.jimthescientist.maskinator.Main;
import net.jimthescientist.maskinator.Wafer;
import net.jimthescientist.maskinator.outputmachine.OutputMachine;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.util.ArrayList;
import java.util.Vector;

public class Grid extends JPanel {

    public ArrayList<Wafer> wafers;

    public OutputMachine outputMachine;

    private double zoomFactor = 10;

    private int panX;
    private int panY;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(new Color(0, 255, 0));
        Graphics2D g2 = (Graphics2D) g;

        g2.translate(panX, panY);

        AffineTransform at = new AffineTransform();
        at.scale(zoomFactor,zoomFactor);
        g2.transform(at);
        g.setColor(new Color(255,0,0));
        g.drawLine(2,0,2,10);

        try {
            at.invert();
        } catch (NoninvertibleTransformException e) {
            throw new RuntimeException(e);
        }
        g2.transform(at);

        g.setColor(new Color(0,0,0));
        g.drawLine((int) (2*zoomFactor), (int) (0*zoomFactor), (int) (2*zoomFactor), (int) (10*zoomFactor));
        g.drawLine((int) (3*zoomFactor), (int) (0*zoomFactor), (int) (3*zoomFactor), (int) (10*zoomFactor));
    }

    public void zoom(double factor) {
        this.zoomFactor *= factor;
        Main.LOGGER.info("Zoom Factor of {}", this.zoomFactor);
        this.repaint();
    }

    public void translateX(int x) {
        panX += x;
        this.repaint();
    }

    public void translateY(int y) {
        panY += y;
        this.repaint();
    }
}
