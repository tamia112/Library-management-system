package org.netbeans.lib.awtextra;

import java.awt.Dimension;
import java.awt.Point;

public class AbsoluteConstraints implements java.io.Serializable {
    public int x, y, width = -1, height = -1;

    public AbsoluteConstraints(Point pos) { this(pos.x, pos.y); }
    public AbsoluteConstraints(int x, int y) { this.x = x; this.y = y; }
    public AbsoluteConstraints(Point pos, Dimension size) {
        this.x = pos.x; this.y = pos.y; this.width = size.width; this.height = size.height;
    }
    public AbsoluteConstraints(int x, int y, int width, int height) {
        this.x = x; this.y = y; this.width = width; this.height = height;
    }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
