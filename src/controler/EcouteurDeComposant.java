package controler;

import controler.Controler;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class EcouteurDeComposant extends ComponentAdapter {

    Controler controleur;

    public EcouteurDeComposant(Controler controleur) {
        this.controleur = controleur;
    }

    @Override
    public void componentResized(ComponentEvent e)
    {
        controleur.resizeMap();
    }
}
