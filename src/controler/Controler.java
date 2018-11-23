package controler;

import modele.Plan;
import vue.MainVue;
import xml_manager.XMLParser;

public class Controler {

    /**
     * Cree le controleur de l'application
     */
    public Controler() {
    }

    public void chargerPlan(String lienPlan) {
        System.out.println("Plan : "+lienPlan);
       // XMLParser.parse(lienPlan);
    }

    public void chargerLivraison(String lienLivraisons) {
        System.out.println("Livraison : "+lienLivraisons);
        // XMLParser.parse(lienLivraisons);

    }
}
