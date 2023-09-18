package main;

import tile.UtilityTool;

import java.awt.*;

public class Map extends MyRectangle {

    boolean drawCheck = false;
    public Map(int x, int y, int width, int height, int cut, MapPanel mp) {
        super(x, y, width, height, cut, mp);
    }

    protected void clickMap(int x, int y) {
        if (contains(x, y)) {
            int indexX = (x -getX()) / getCut();
            int indexY = (y - getY()) / getCut();

            if ((indexX >= 0 && indexX <= getCutWidth()) &&
                    (indexY >= 0 && indexY <= getCutHeight())) {

                setFindX(indexX);
                setFindY(indexY);

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
        int currentCol = 0;
        int currentRow = 0;

        while ( mapRow < super.getCutHeight() + startRow && mapCol < super.getCutWidth() + startCol) {

            if (mapCol < 0 || mapRow < 0) break;
            int mapNum = mp.world.totalMap[mapRow][mapCol];

            int mapX = currentCol * super.getCut() + super.getX();
            int mapY = currentRow * super.getCut() + super.getY();

            currentCol++;
            mapCol++;

            if (mapNum != -1) {
                g2.drawImage(mp.tileManager.mapTiles.get(mapNum).image, mapX, mapY, null);
            }

            if (mapCol == getCutWidth() + startCol ) {
                mapCol = startCol;
                currentCol = 0;
                currentRow++;
                mapRow++;
            }
        }
        System.out.println("the end");
    }
}
