package main;

import java.awt.*;

abstract class MyRectangle {
    protected final int x, y;
    protected final int width, height;
    protected final int cut;
    protected boolean check = false;
    protected Graphics2D g2;
    protected int findX, findY;
    protected MapPanel mp;

    public MyRectangle(int x, int y, int width, int height, int cut, MapPanel mp) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.cut = cut;
        this.mp = mp;
    }
    protected void setGraphics2D(Graphics2D g2) {
        this.g2 = g2;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public int getCut() {
        return cut;
    }
    // Width cut number return function
    public int getCutWidth() {
        return  width / cut;
    }
    // Height cut number return function
    public int getCutHeight() {
        return height / cut;
    }
    // Coordinate width position return.
    public int getX2() { return x + width; }
    // Coordinate height position return.
    public int getY2() {
        return y + height;
    }

    public void setFindX(int findX) {
        this.findX = findX;
    }

    public void setFindY(int findY) {
        this.findY = findY;
    }

    // draw functions

    protected void drawSelf(Color color) {
        g2.setColor(color);
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(x, y, width, height);
    }

    protected void drawCol(Color color, int stroke) {
        g2.setColor(color);
        g2.setStroke(new BasicStroke(stroke));

        for ( int up = 1; up <= getCutWidth(); up++ )
            g2.drawLine(x + cut * up, y, x + cut * up, getY2());
    }

    protected void drawRow(Color color, int stroke) {
        g2.setColor(color);
        g2.setStroke(new BasicStroke(stroke));

        for (int up = 1; up <= getCutHeight(); up++)
            g2.drawLine(x,  cut * up + y, getX2(), cut * up + y);
        
    }

    protected void drawText(String text, Color color, int size) {
        g2.setColor(color);
        g2.setFont(new Font("Int", Font.BOLD, size));
        g2.drawString(text, x + 32, y - 16);
    }

    //---------------------------

    protected void selected() {
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(5));
        g2.drawRect(findX * cut + x,findY * cut + y, cut, cut);
    }

    protected boolean contains(int X, int Y) {
        int w = this.width;
        int h = this.height;
        if ((w | h) < 0) {
            // At least one of the dimensions is negative...
            return false;
        }
        // Note: if either dimension is zero, tests below must return false...
        int x = this.x;
        int y = this.y;
        if (X < x || Y < y) {
            return false;
        }
        w += x;
        h += y;
        //    overflow || intersect
        return ((w < x || w > X) &&
                (h < y || h > Y));
    }

}
