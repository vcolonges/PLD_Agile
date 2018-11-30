package controleur;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EcouteurDeSouris extends MouseAdapter {

    private Controler controler;

    public EcouteurDeSouris(Controler controler) {
        this.controler = controler;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        super.mouseClicked(e);
        controler.mousePressed(e.getPoint(),e);
    }


    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
        controler.mouseMoved(e.getPoint());
    }
}
