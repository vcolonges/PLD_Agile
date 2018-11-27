package controler.etat;

import modele.Noeud;
import modele.Plan;
import vue.PopUpMenu;

public class EtatLivraisonsCharges extends Etat {

    public EtatLivraisonsCharges() {
        label = "Livraisons chargées";
    }

    @Override
    public PopUpMenu getPopUpMenu(Plan plan, Noeud n) {
        return null;
    }
}
