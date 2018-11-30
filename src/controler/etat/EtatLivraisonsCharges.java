package controler.etat;

import controler.Controler;
import modele.Noeud;
import modele.Plan;
import vue.PopUpMenu;

public class EtatLivraisonsCharges extends Etat {

    public EtatLivraisonsCharges(Controler c) {
        super(c);
        label = "Livraisons chargées";
    }

    @Override
    public PopUpMenu getPopUpMenu(Plan plan, Noeud n) {
        return null;
    }
}
