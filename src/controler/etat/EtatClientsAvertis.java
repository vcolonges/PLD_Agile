package controler.etat;

import modele.Livraison;
import modele.Noeud;
import modele.Plan;
import vue.PopUpMenu;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EtatClientsAvertis extends Etat {
    public EtatClientsAvertis() {
        label = "Clients avertis";
    }

    @Override
    public PopUpMenu getPopUpMenu(Plan plan, Noeud n) {

        PopUpMenu popUpMenu = new PopUpMenu();
        if(!plan.isInLivraisons(n))
        {
            JMenuItem menuItem = new JMenuItem("Ajouter une livraison");
            popUpMenu.add(menuItem);
            menuItem.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //TODO implement
                }
            });
        }
        else
        {
            JMenuItem menuItem = new JMenuItem("Supprimer une livraison");
            popUpMenu.add(menuItem);
            menuItem.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //TODO implement
                }
            });
        }
        return popUpMenu;
    }
}
