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

        int[][] boxVolume = new int[getCutHeight()][getCutWidth()];

        for ( int row = 0; row < getCutHeight(); row++ )
            for ( int col = 0; col < getCutWidth(); col++ ) {
                boxVolume[row][col] = valueUp;
                valueUp ++;
            }
        return boxVolume;
    }

    protected void clickBox(int x, int y) {
        if (contains(x, y)) {
            int findX =(x - getX()) / getCut(); // Calculate valueUp
            int findY =(y - getY()) / getCut(); // Calculate valueUp

            if ((findX >= 0 && findX <= getCutWidth())
                    && (findY >= 0 && findY <= getCutHeight())) {

                setFindX(findX);
                setFindY(findY);

                check = mp.box.tileNumbers[findY][findX] < mp.tileManager.tiles.size();
            } else {
                check = false;
            }
            mp.map.check = false;
        }
    }

    protected void paintBox() {
        drawSelf(Color.BLUE);

        if (check) {
            selected();
        }
    }
}
