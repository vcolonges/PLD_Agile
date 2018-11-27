package vue;

import javax.swing.*;

public class PopUpMenu extends JPopupMenu {

    JMenuItem supprimer;
    JMenuItem modifier;
    JMenuItem ajouter;
    public PopUpMenu(){
        supprimer = new JMenuItem("supprimer");
        modifier = new JMenuItem("modifier");
        ajouter = new JMenuItem("ajouter");

        add(supprimer);
        add(modifier);
        add(ajouter);
    }
}
