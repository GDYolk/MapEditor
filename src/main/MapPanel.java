package main;

import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel {
    // Classes
    public Map map = new Map(32, 64, 512, 512, 64, this);
    World world = new World(608, 64, 512, 512, 64, this);
    BoxSelect boxSelect = new BoxSelect(32, 640, 480, 48, 48, this);
    public Box box = new Box(32, 688, 480, 96, 48, this);
    TileManager tileManager = new TileManager(this);
    Mouse mouse = new Mouse(this);

    MapPanel() {
        this.setPreferredSize(new Dimension(1152, 800));
        this.setBackground(Color.black);
        this.addMouseListener(mouse);
        this.setFocusable(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        tileManager.setGraphics2D(g2);
        boxSelect.setGraphics2D(g2);
        box.setGraphics2D(g2);
        world.setGraphics2D(g2);
        map.setGraphics2D(g2);

        boxSelect.paintBoxSelect();
        box.paintBox();
        map.paintMap();
        world.paintWorld();
        world.showTotalMap();

        g2.dispose();
    }

}
