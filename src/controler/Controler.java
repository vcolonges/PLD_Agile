package controler;

import modele.Plan;
import vue.MainVue;
import xml_manager.XMLParser;

public class Controler {

    private Plan plan;
    /**
     * Cree le controleur de l'application
     */
    public Controler(Plan plan) {
        this.plan = plan;
    }

    public void chargerPlan(String lienPlan) throws Exception {
        //System.out.println("Plan : "+lienPlan);
        XMLParser.parsePlan(lienPlan);
    }

    public void chargerLivraison(String lienLivraisons) throws Exception {
        //System.out.println("Livraison : " + lienLivraisons);
        XMLParser.parseTrajets(lienLivraisons, plan);

    }
}
