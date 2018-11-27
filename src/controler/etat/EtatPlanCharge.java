package controler.etat;

import modele.Noeud;
import modele.Plan;
import vue.PopUpMenu;

public class EtatPlanCharge extends Etat {

    public EtatPlanCharge() {
        label = "Plan chargé";
    }

    @Override
    public PopUpMenu getPopUpMenu(Plan plan, Noeud n) {
        return null;
    }
}
