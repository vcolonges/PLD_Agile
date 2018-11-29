package controler.etat;

import modele.Noeud;
import modele.Plan;
import vue.PopUpMenu;

import javax.swing.*;

public class EtatClientsAvertis extends Etat {
    public EtatClientsAvertis() {
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
            menuItem.addActionListener(e -> supprimerLivraisonApresLancement(plan, n));
        }
        return popUpMenu;
    }

    private void supprimerLivraisonApresLancement(Plan plan, Noeud n) {
        //TODO
        //plan.getLivraisons().remove(n.getId());
    }

    public void ajouterLivraisonApresLancement(Noeud n){
        System.out.println("Ajout du noeud "+n.getId()+" au trajets.");
        System.out.println("test");
    }
}
