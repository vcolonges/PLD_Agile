package controler;

import modele.Noeud;
import modele.Plan;
import vue.MainVue;
import xml_manager.XMLParser;

import java.awt.*;

public class Controler {

    private Plan plan;
    private MainVue mainvue;
    /**
     * Cree le controleur de l'application
     */
    public Controler(MainVue vue) {
        this.mainvue = vue;
    }

    public void chargerPlan(String lienPlan) throws Exception {
        //System.out.println("Plan : "+lienPlan);
        plan = XMLParser.parsePlan(lienPlan);
        mainvue.getMapPanel().loadPlan(plan);
    }

    public void chargerLivraison(String lienLivraisons) throws Exception {
        //System.out.println("Livraison : " + lienLivraisons);
        if(plan == null)
            throw new Exception();
        else
            plan = XMLParser.parseTrajets(lienLivraisons, plan);
    }

    public void mouseMoved(Point point) {
        mainvue.updateMousePosition(point);
    }

    public void onHoverNode(Noeud n)
    {
        mainvue.setSelectedNode(n);
    }

    public void resizeMap() {
        mainvue.resizeMap();
    }

    public Plan getPlan() {
        return plan;
    }
}
