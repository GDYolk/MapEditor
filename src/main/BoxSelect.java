package main;

import java.awt.*;

public class BoxSelect extends MyRectangle {

    public BoxSelect(int x, int y, int width, int height, int cut, MapPanel mp) {
        super(x, y, width, height, cut, mp);
    }

    private void countWidth() {
        g2.setColor(Color.magenta);
        g2.setFont(new Font("Int", Font.BOLD, 16));

        for (int count = 1; count <= getCutWidth(); count++) {
            g2.drawString("" + count, x + (count * super.cut - super.cut / 2), super.y + (super.height / 2));
        }
    }

    protected void clickBoxSelect(int x, int y) {
        if (super.contains(x, y)) {

            int indexX =(x - super.x) / super.cut;
            if (indexX >= 0 && indexX <= 9) {

                super.setFindX(indexX);
                super.setFindY(0);

                int maxTiles = mp.box.getCutWidth() * mp.box.getCutHeight() * indexX;

                if (maxTiles < mp.tileManager.tiles.size()) {
                    mp.box.tileNumbers = mp.box.numbers(maxTiles);
                    super.check = true;
                } else {
                    super.check = false;
                }
                // another classes check
                mp.box.check = false;
                mp.map.check = false;
            }
        }
    }

    protected void paintBoxSelect() {
        super.drawSelf(Color.BLUE);
        super.drawCol(Color.BLUE, 2);
        super.drawText("Tiles Select Field", Color.BLUE, 30);
        this.countWidth();

        if (super.check) {
            super.selected();

            mp.box.drawCol(Color.white, 2);
            mp.box.drawRow(Color.BLUE, 2);
            mp.tileManager.drawBox();

        } else mp.box.drawHaveNot();
    }
}
