package main;

import java.awt.*;

public class World extends MyRectangle{
    int[][] totalMap = constNumber(mp.map.getCutHeight() * getCutHeight(), mp.map.getCutWidth() * getCutWidth());

    public World(int x, int y, int width, int height, int cut, MapPanel mp) {
        super(x, y, width, height, cut, mp);
    }
    protected int[][] constNumber(int row, int col) {
        int[][] constA = new int[row][col];

        for ( int i = 0; i < row; i++ ) {
            for ( int j = 0; j < col; j++ ) {
                constA[i][j] = -1;
            }
        }
        return constA;
    }

    protected void clickWorld(int x, int y) {
        if (contains(x, y)) {
            int indexX =(x - getX()) / getCut(); // Calculate row
            int indexY =(y - getY()) / getCut(); // Calculate col

            if ((indexX >= 0 && indexX <= getCutWidth())
                    && (indexY >= 0 && indexY <= getCutHeight())) {
                setFindX(indexX);
                setFindY(indexY);

                check = true;

            } else check = false;
            mp.map.check = false;
            mp.map.drawCheck = true;
        }
    }

    protected void totalWorldShow(int x, int y) {
        if (x > 0 && x <= 32 && y > 0 && y <= 32) {
            for (int i = 0; i < mp.map.getCutHeight() * mp.world.getCutHeight(); i++) {
                for (int j = 0; j < mp.map.getCutWidth() * mp.world.getCutWidth(); j++) {
                    System.out.print(totalMap[i][j]);
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
        drawSelf(Color.magenta);
        drawCol(Color.magenta, 3);
        drawRow(Color.magenta, 3);
        drawText("World Screen", Color.magenta, 32);

        if (check) {
            selected();
        }
    }
}
