package controleur.etat;

import controleur.Controler;
import modele.Noeud;
import modele.Plan;
import vue.PopUpMenu;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EtatTournesGeneres extends Etat{
    public EtatTournesGeneres(Controler c) {
        super(c);
        label = "Tournés générés";
    }

    @Override
    public PopUpMenu getPopUpMenu(Plan plan, Noeud n) {
        PopUpMenu popUpMenu = new PopUpMenu();

        if(plan.getLivraisons().containsKey(n.getId()))
        {
            JMenuItem menuItem = new JMenuItem("Changer de livreur");
            popUpMenu.add(menuItem);
            menuItem.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                }
            });
            return popUpMenu;
        }

        return null;
    }
}
