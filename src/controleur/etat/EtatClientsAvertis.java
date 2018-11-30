package controleur.etat;

import controleur.Controler;
import modele.Noeud;
import modele.Plan;
import vue.PopUpMenu;

import javax.swing.*;

public class EtatClientsAvertis extends Etat {
    public EtatClientsAvertis(Controler c) {
        super(c);
        label = "Clients avertis";
    }

    @Override
    public PopUpMenu getPopUpMenu(Plan plan, Noeud n) {

        PopUpMenu popUpMenu = new PopUpMenu();
        if(!plan.getLivraisons().containsKey(n.getId()))
        {
            JMenuItem menuItem = new JMenuItem("Ajouter une livraison");
            popUpMenu.add(menuItem);
            menuItem.addActionListener(e -> ajouterLivraisonApresLancement(n));
        }
        else
        {
            JMenuItem menuItem = new JMenuItem("Supprimer une livraison");
            popUpMenu.add(menuItem);
            menuItem.addActionListener(e -> supprimerLivraisonApresLancement(n));
        }
        return popUpMenu;
    }

    private void supprimerLivraisonApresLancement(Noeud n) {
        controler.supprimerLivraison(n);
    }

    public void ajouterLivraisonApresLancement(Noeud n){
        System.out.println("Ajout du noeud "+n.getId()+" au trajets.");
        System.out.println("test");
    }
}
