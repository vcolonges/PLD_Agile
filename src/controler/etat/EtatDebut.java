package controler.etat;

import modele.Noeud;
import modele.Plan;
import vue.PopUpMenu;

public class EtatDebut extends Etat {

    public EtatDebut() {
        label = "Début";
    }

    @Override
    public PopUpMenu getPopUpMenu(Plan plan, Noeud n) {
        return null;
    }
}
