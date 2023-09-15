package tile;

import main.MapPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class TileManager {
    MapPanel mp;
    public ArrayList<Tile> tiles;
    Graphics2D g2;
    public TileManager(MapPanel mp) {
        this.mp = mp;
        tiles = new ArrayList<>();
        getTileImage();
    }

    public void setGraphics2D(Graphics2D g2) {
        this.g2 = g2;
    }

    public void getTileImage() {
        for (int i = 0; i <=10; i++)
            setup(i, "grass00", false);
        // PLACEHOLDER
        setup(11, "grass01", false);
        setup(12, "water00", false);
        setup(13, "water01", false);
        setup(14, "water02", false);
        setup(15, "water03", false);
        setup(16, "water04", false);
        setup(17, "water05", false);
        setup(18, "water06", false);
        setup(19, "water07", false);
        setup(20, "water08", false);
        setup(21, "water09", false);
        setup(22, "water10", false);
        setup(23, "water11", false);
        setup(24, "water12", false);
        setup(25, "water13", false);
        setup(26, "road00", false);
        setup(27, "road01", false);
        setup(28, "road02", false);
        setup(29, "road03", false);
        setup(30, "road04", false);
        setup(31, "road05", false);
        setup(32, "road06", false);
        setup(33, "road07", false);
        setup(34, "road08", false);
        setup(35, "road09", false);
        setup(36, "road10", false);
        setup(37, "road11", false);
        setup(38, "road12", false);
        setup(39, "earth", false);
        setup(40, "wall", false);
        setup(41, "tree", false);

    }
    public void setup(int index, String imageName, boolean collision){
        UtilityTool uTool = new UtilityTool();
        try {

            tiles.add(new Tile());
            tiles.get(index).image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/" + imageName + ".png")));
            tiles.get(index).image = uTool.scaleImage(tiles.get(index).image, mp.box.getCut()-1, mp.box.getCut()-1);
            tiles.get(index).collision = collision;

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void drawBox() {
        int boxCol = 0;
        int boxRow = 0;
        int boxNum;

        while (boxRow < mp.box.getCutHeight() && boxCol < mp.box.getCutWidth()) {

            boxNum = mp.box.tileNumbers[boxRow][boxCol];

            int boxX = boxCol *  mp.box.getCut() + mp.box.getX();
            int boxY = boxRow * mp.box.getCut() + mp.box.getY();

            if (boxNum >= tiles.size()) break;

            g2.drawImage(tiles.get(boxNum).image, boxX, boxY, null);

            boxCol++;
            if (boxCol == mp.box.getCutWidth()) {
                boxCol = 0;
                boxRow++;
            }
        }
        //System.out.println("drawBox");
    }
}
