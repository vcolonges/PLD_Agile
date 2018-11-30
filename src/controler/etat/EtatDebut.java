package controler.etat;

import controler.Controler;
import modele.Noeud;
import modele.Plan;
import vue.PopUpMenu;

public class EtatDebut extends Etat {

    public EtatDebut(Controler c) {
        super(c);
        label = "Début";
    }

    @Override
    public PopUpMenu getPopUpMenu(Plan plan, Noeud n) {
        return null;
    }
}
