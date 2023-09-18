package main;

import java.awt.*;

public class Map extends MyRectangle {

    boolean drawCheck = false;

    public Map(int x, int y, int width, int height, int cut, MapPanel mp) {
        super(x, y, width, height, cut, mp);
    }

    protected void clickMap(int x, int y) {
        if (super.contains(x, y)) {
            int indexX = (x - super.getX()) / super.getCut();
            int indexY = (y - super.getY()) / super.getCut();

            if ((indexX >= 0 && indexX <= super.getCutWidth()) &&
                    (indexY >= 0 && indexY <= super.getCutHeight())) {

                super.setFindX(indexX);
                super.setFindY(indexY);

                super.check = true;
            } else {
                super.check = false;
            }
        }
    }

    protected void paintMap() {

        super.drawSelf(Color.GREEN);
        super.drawCol(Color.GREEN, 2);
        super.drawRow(Color.GREEN, 2);
        super.drawText("Tile Screen", Color.GREEN, 32);

        int startRow = mp.world.findY * super.getCutHeight();
        int startCol = mp.world.findX * super.getCutWidth();

        if (super.check) {
            super.selected();

            int totalMapRow = startRow + super.findY;
            int totalMapCol = startCol + super.findX;
            mp.world.totalMap[totalMapRow][totalMapCol] = mp.box.tileNumbers[mp.box.findY][mp.box.findX];
        }
        if (this.drawCheck)
            this.drawMap(startCol, startRow);
    }

    private void drawMap(int startCol, int startRow) {
        int totalMapCol = startCol;
        int totalMapRow = startRow;
        int endCol = super.getCutWidth() + startCol;
        int endRow = super.getCutHeight() + startRow;
        int mapCol = 0;
        int mapRow = 0;

        while (totalMapRow < endRow && totalMapCol < endCol) {

            if (totalMapCol < 0 || totalMapRow < 0) break;

            int tileNumber = mp.world.totalMap[totalMapRow][totalMapCol];

            int mapX = mapCol * super.getCut() + super.getX();
            int mapY = mapRow * super.getCut() + super.getY();

            mapCol++;
            totalMapCol++;

            if (tileNumber != -1) {
                g2.drawImage(mp.tileManager.mapTiles.get(tileNumber).image, mapX, mapY, null);
            }

            if (totalMapCol == endCol) {
                mapCol = 0;
                mapRow++;
                totalMapCol = startCol;
                totalMapRow++;
            }
        }
        System.out.println("Test this function how to call");
    }
}
