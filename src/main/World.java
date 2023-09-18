package main;

import java.awt.*;

public class World extends MyRectangle{
    int[][] totalMap = constNumber(
            mp.map.getCutHeight() * super.getCutHeight(),
            mp.map.getCutWidth() * super.getCutWidth());

    public World(int x, int y, int width, int height, int cut, MapPanel mp) {
        super(x, y, width, height, cut, mp);
    }
    private int[][] constNumber(int row, int col) {
        int[][] constA = new int[row][col];

        for ( int i = 0; i < row; i++ ) {
            for ( int j = 0; j < col; j++ ) {
                constA[i][j] = -1;
            }
        }
        return constA;
    }

    protected void clickWorld(int x, int y) {
        if (super.contains(x, y)) {
            int indexX =(x - super.getX()) / super.getCut();
            int indexY =(y - super.getY()) / super.getCut();

            if ((indexX >= 0 && indexX <= super.getCutWidth())
                    && (indexY >= 0 && indexY <= super.getCutHeight())) {
                super.setFindX(indexX);
                super.setFindY(indexY);

                super.check = true;
                mp.map.drawCheck = true;
            } else super.check = false;

            mp.map.check = false;
        }
    }

    protected void totalWorldShow(int x, int y) {
        if (x > 0 && x <= 32 && y > 0 && y <= 32) {
            for (int i = 0; i < mp.map.getCutHeight() * super.getCutHeight(); i++) {
                for (int j = 0; j < mp.map.getCutWidth() * super.getCutWidth(); j++) {
                    System.out.print(this.totalMap[i][j]);
                }
                System.out.println();
            }
        }
    }

    protected void showTotalMap() {
        g2.setColor(Color.magenta);
        g2.setFont(new Font("BOLD", Font.BOLD, 10));
        g2.drawString("Show", 6, 20);
    }

    protected void paintWorld() {
        super.drawSelf(Color.magenta);
        super.drawCol(Color.magenta, 3);
        super.drawRow(Color.magenta, 3);
        super.drawText("World Screen", Color.magenta, 32);

        if (super.check) {
            super.selected();
        }
    }
}
