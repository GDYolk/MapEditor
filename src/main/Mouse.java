package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener {
    MapPanel mp;
    public Mouse(MapPanel mp) {
        this.mp = mp;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        mp.boxSelect.clickBoxSelect(x, y);
        if (mp.boxSelect.check)
            mp.box.clickBox(x, y);

        if (mp.box.check && mp.world.check)
            mp.map.clickMap(x, y);
        else mp.map.check = false;

        mp.world.clickWorld(x, y);

        mp.world.totalWorldShow(x, y);
        mp.repaint();
    }


    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //mp.map.check = false;
        //mp.box.check = false;
        //mp.boxSelect.check = false;
        //mp.world.check = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
