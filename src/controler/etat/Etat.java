package controler.etat;

import modele.Noeud;
import modele.Plan;
import vue.PopUpMenu;
import controler.Controler;



public abstract class Etat {
    protected String label;
    protected Controler  controler;

    Etat(Controler c){this.controler = c;}
    public abstract PopUpMenu getPopUpMenu(Plan plan, Noeud n);

    public String getLabel() {
        return label;
    }
}
