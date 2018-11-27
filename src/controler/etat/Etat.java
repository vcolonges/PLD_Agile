package controler.etat;

import modele.Noeud;
import modele.Plan;
import vue.PopUpMenu;

public abstract class Etat {
    protected String label;

    public abstract PopUpMenu getPopUpMenu(Plan plan, Noeud n);

    public String getLabel() {
        return label;
    }
}
