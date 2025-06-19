package net.jimthescientist.maskinator.mask;

import net.jimthescientist.maskinator.Main;
import net.jimthescientist.maskinator.Wafer;
import net.jimthescientist.maskinator.outputmachine.OutputMachine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;

public class Grid extends JPanel implements ActionListener {

    private OutputMachine outputMachine;

    private double zoomFactor = 10;

    private int panX;
    private int panY;

    public Grid() {
        Timer timer = new Timer(17, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(new Color(0, 255, 0));
        Graphics2D g2 = (Graphics2D) g;


        g2.translate(getWidth()/2,getHeight()/2);

        AffineTransform at = new AffineTransform();
        at.scale(zoomFactor,zoomFactor);
        g2.transform(at);
        g2.translate(-getWidth()/2,-getHeight()/2);
        g2.translate(panX, panY);
        g.setColor(new Color(255,0,0));
        //g.drawLine(2,0,2,10);
        drawWafer(g);
        try {
            at.invert();
        } catch (NoninvertibleTransformException e) {
            throw new RuntimeException(e);
        }
        g2.transform(at);

        drawGridLines(g);
    }

    public void zoom(double factor) {
        this.zoomFactor *= factor;
        this.repaint();
    }

    public double getZoomFactor() {
        return this.zoomFactor;
    }

    public void translateX(int x) {
        panX += x;
        this.repaint();
    }

    public void translateY(int y) {
        panY += y;
        this.repaint();
    }

    public void setOutputMachine(OutputMachine outputMachine) {
        Main.LOGGER.info("setting the output machine");
        this.outputMachine = outputMachine;
        this.repaint();
    }

    private void drawGridLines(Graphics g) {
        if (this.outputMachine == null) return;
        g.setColor(new Color(0,0,0));
        if (zoomFactor >= 10) {
            for (int x = 0; x <= this.outputMachine.getMaxWidth(); x++) {
                g.drawLine((int) (x*zoomFactor), (int) (0*zoomFactor), (int) (x*zoomFactor), (int) (this.outputMachine.getMaxHeight()*zoomFactor));
            }
            for (int y = 0; y <= this.outputMachine.getMaxHeight(); y++) {
                g.drawLine((int) (0*zoomFactor), (int) (y*zoomFactor), (int) (this.outputMachine.getMaxWidth()*zoomFactor), (int) (y*zoomFactor));
            }
        } else {
            g.drawLine((int) (0*zoomFactor), (int) (0*zoomFactor), (int) (0*zoomFactor), (int) (this.outputMachine.getMaxHeight()*zoomFactor));
            g.drawLine((int) (0*zoomFactor), (int) (0*zoomFactor), (int) (this.outputMachine.getMaxWidth()*zoomFactor), (int) (0*zoomFactor));
            g.drawLine((int) (this.outputMachine.getMaxWidth()*zoomFactor), (int) (0*zoomFactor), (int) (this.outputMachine.getMaxWidth()*zoomFactor), (int) (this.outputMachine.getMaxHeight()*zoomFactor));
            g.drawLine((int) (0*zoomFactor), (int) (this.outputMachine.getMaxHeight()*zoomFactor), (int) (this.outputMachine.getMaxWidth()*zoomFactor), (int) (this.outputMachine.getMaxHeight()*zoomFactor));

        }
    }

    private Wafer wafer;

    public void setWafer(Wafer wafer) {
        this.wafer = wafer;
        this.repaint();
    }

    private void drawWafer(Graphics g) {
        if (this.wafer == null) return;
        if (this.outputMachine == null) return;
        int x = (int) ((this.wafer.centerX)/this.outputMachine.getPixelSize() - (this.wafer.diameter/this.outputMachine.getPixelSize())/2);
        int y = (int) ((this.wafer.centerY/this.outputMachine.getPixelSize()) - (this.wafer.diameter/this.outputMachine.getPixelSize())/2);
        g.setColor(new Color(0,0,255));
        g.drawOval(x, y, (int) (this.wafer.diameter/this.outputMachine.getPixelSize()), (int) (this.wafer.diameter/this.outputMachine.getPixelSize()));
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.repaint();
    }
}
