package main;

import java.awt.*;

public class Box extends MyRectangle {
    public int [][] tileNumbers;

    public Box(int x, int y, int width, int height, int cut, MapPanel mp) {
        super(x, y, width, height, cut, mp);
    }

    protected void drawHaveNot() {
        g2.setColor(Color.BLUE);
        g2.setFont(new Font("Int", Font.BOLD, 32));
        g2.drawString("HaVe NoT", x + (width / 2) - 50, y + (height / 2));
    }

    public int[][] numbers(int valueUp) {

        int[][] boxVolume = new int[super.getCutHeight()][super.getCutWidth()];

        for ( int row = 0; row < super.getCutHeight(); row++ )
            for ( int col = 0; col < super.getCutWidth(); col++ ) {
                boxVolume[row][col] = valueUp;
                valueUp ++;
            }
        return boxVolume;
    }

    protected void clickBox(int x, int y) {
        if (super.contains(x, y)) {
            int indexX =(x - super.getX()) / super.getCut();
            int indexY =(y - super.getY()) / super.getCut();

            if ((indexX >= 0 && indexX <= super.getCutWidth())
                    && (indexY >= 0 && indexY <= super.getCutHeight())) {

                super.setFindX(indexX);
                super.setFindY(indexY);

                super.check = mp.box.tileNumbers[indexY][indexX] < mp.tileManager.tiles.size();
            } else {
                super.check = false;
            }
            mp.map.check = false;
        }
    }

    protected void paintBox() {
        super.drawSelf(Color.BLUE);

        if (super.check) {
            super.selected();
        }
    }
}
