package main;

import java.awt.*;

public class Map extends MyRectangle {

    boolean drawCheck = false;
    public Map(int x, int y, int width, int height, int cut, MapPanel mp) {
        super(x, y, width, height, cut, mp);
    }

    protected void clickMap(int x, int y) {
        if (contains(x, y)) {
            int findX = (x -getX()) / getCut();
            int findY = (y - getY()) / getCut();

            if ((findX >= 0 && findX <= getCutWidth()) &&
                    (findY >= 0 && findY <= getCutHeight())) {

                setFindX(findX);
                setFindY(findY);

                check = true;

            } else {
               check = false;
            }
        }
    }

    protected void paintMap() {
        drawSelf(Color.GREEN);
        drawCol(Color.GREEN, 2);
        drawRow(Color.GREEN, 2);
        drawText("Tile Screen", Color.GREEN, 32);

        int startRow = mp.world.findY * getCutHeight();
        int startCol = mp.world.findX * getCutWidth();
        if (check) {
            selected();

            int totalMapRow = startRow + findY;
            int totalMapCol = startCol + findX;

            mp.world.totalMap[totalMapRow][totalMapCol] = mp.box.tileNumbers[mp.box.findY][mp.box.findX];
        }
        if (drawCheck)
            drawMap(startCol, startRow);
    }

    protected void drawMap(int startCol, int startRow) {
        int mapCol = startCol;
        int mapRow = startRow;
        int mapNum;

        while ( mapRow < super.getCutHeight() + startRow && mapCol < super.getCutWidth() + startCol) {

            if (mapCol < 0 || mapRow < 0) break;
            mapNum = mp.world.totalMap[mapRow][mapCol];

            int mapX = mapCol * getCut() + getX();
            int mapY = mapRow * getCut() + getY();

            mapCol++;

            if (mapNum != -1)
                g2.drawImage(mp.tileManager.tiles.get(mapNum).image, mapX, mapY, null);

            if (mapCol == getCutWidth() + startCol ) {
                mapCol = 0;
                mapRow++;
            }
        }
        System.out.println("the end");
        //mp.repaint();
    }
}
