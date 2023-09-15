package main;

import java.awt.*;

public class BoxSelect extends MyRectangle {

    public BoxSelect(int x, int y, int width, int height, int cut, MapPanel mp) {
        super(x, y, width, height, cut, mp);
    }

    protected void countWidth() {
        g2.setColor(Color.magenta);
        g2.setFont(new Font("Int", Font.BOLD, 16));

        for (int count = 1; count <= getCutWidth(); count++) {
            g2.drawString("" + count, x + (count * cut - cut / 2), y + (height / 2));
        }
    }

    protected void clickBoxSelect(int x, int y) {
        if (contains(x, y)) {

            int findX =(x - this.x) / cut;
            if (findX >= 0 && findX <= 9) {

                setFindX(findX);
                setFindY(0);

                int maxTiles = mp.box.getCutWidth() * mp.box.getCutHeight() * findX;

                if (mp.tileManager.tiles.size() > maxTiles) {
                    mp.box.tileNumbers = mp.box.numbers(maxTiles);
                    check = true;
                } else {
                    check = false;
                }
                // another classes check
                mp.box.check = false;
                mp.map.check = false;
            }
        }
    }

    protected void paintBoxSelect() {
        drawSelf(Color.BLUE);
        drawCol(Color.BLUE, 2);
        drawText("Tiles Select Field", Color.BLUE, 30);
        countWidth();

        if (check) {
            selected();

            mp.box.drawCol(Color.white, 2);
            mp.box.drawRow(Color.BLUE, 2);
            mp.tileManager.drawBox();

        } else mp.box.drawHaveNot();
    }
}
