package controler.etat;

import controler.Controler;
import modele.Noeud;
import modele.Plan;
import vue.PopUpMenu;

public class EtatPlanCharge extends Etat {

    public EtatPlanCharge(Controler c) {
        super(c);
        label = "Plan chargé";
    }

    @Override
    public PopUpMenu getPopUpMenu(Plan plan, Noeud n) {
        return null;
    }
}
