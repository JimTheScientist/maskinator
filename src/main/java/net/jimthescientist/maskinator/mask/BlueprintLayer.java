package net.jimthescientist.maskinator.mask;

import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;

public class BlueprintLayer {
    public ArrayList<Byte> pixels;
    private int width;
    private int height;

    private Integer offsetX;
    private Integer offsetY;

    public BlueprintLayer(Integer offsetX, Integer offsetY) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public int getHeight() {
        return this.height;
    }
    public int getWidth() {
        return this.width;
    }
}
