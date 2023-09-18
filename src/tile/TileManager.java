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
    public ArrayList<Tile> mapTiles;
    Graphics2D g2;
    UtilityTool uTool = new UtilityTool();
    public TileManager(MapPanel mp) {
        this.mp = mp;
        tiles = new ArrayList<>();
        mapTiles = new ArrayList<>();
        getTileImage();
    }

    public void setGraphics2D(Graphics2D g2) {
        this.g2 = g2;
    }

    public void getTileImage() {
        for (int i = 0; i <=10; i++)
            setup(i, "grass00" );
        // PLACEHOLDER
        setup(11, "grass01");
        setup(12, "water00");
        setup(13, "water01");
        setup(14, "water02");
        setup(15, "water03");
        setup(16, "water04" );
        setup(17, "water05" );
        setup(18, "water06" );
        setup(19, "water07" );
        setup(20, "water08" );
        setup(21, "water09" );
        setup(22, "water10" );
        setup(23, "water11" );
        setup(24, "water12" );
        setup(25, "water13" );
        setup(26, "road00" );
        setup(27, "road01" );
        setup(28, "road02" );
        setup(29, "road03" );
        setup(30, "road04" );
        setup(31, "road05" );
        setup(32, "road06" );
        setup(33, "road07" );
        setup(34, "road08" );
        setup(35, "road09" );
        setup(36, "road10" );
        setup(37, "road11" );
        setup(38, "road12" );
        setup(39, "earth" );
        setup(40, "wall" );
        setup(41, "tree" );

    }
    public void mapSetup(int index, String imageName){
        try {

            mapTiles.add(new Tile());
            mapTiles.get(index).image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/" + imageName + ".png")));
            mapTiles.get(index).image = uTool.scaleImage(mapTiles.get(index).image, mp.map.getCut(), mp.map.getCut());

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void setup(int index, String imageName){
        try {

            tiles.add(new Tile());
            tiles.get(index).image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/" + imageName + ".png")));
            tiles.get(index).image = uTool.scaleImage(tiles.get(index).image, mp.box.getCut()-1, mp.box.getCut()-1);

        }catch (IOException e){
            e.printStackTrace();
        }
        mapSetup(index, imageName);
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
