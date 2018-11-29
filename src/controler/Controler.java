package controler;

import TSP.AlgoParcour;
import TSP.TSP;
import controler.etat.*;
import exceptions.XMLException;
import modele.*;
import vue.MainVue;
import xml_manager.XMLParser;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Controler {

    private Plan plan;
    private MainVue mainvue;
    private Etat etat;
    private AlgoParcour algo;

    /**
     * Cree le controleur de l'application
     */
    public Controler(MainVue vue) {
        this.mainvue = vue;
        etat = new EtatDebut();
        mainvue.setEtat(etat);
        algo = new AlgoParcour();
    }

    public void chargerPlan(String lienPlan){
        try {
            if(plan != null)
                plan.getNoeuds().clear();
            plan = XMLParser.parsePlan(lienPlan);
            mainvue.getMapPanel().loadPlan(plan);
            etat = new EtatPlanCharge();
            mainvue.setEtat(etat);
        } catch (XMLException e) {
            e.printStackTrace();
            mainvue.errorMessage(e.getMessage());
        }
    }

    public void chargerLivraison(String lienLivraisons){
        if(plan == null)
            mainvue.errorMessage("Veuillez charger un plan avant de charger des livraisons.");
        else{
            try {
                plan.getLivraisons().clear();
                plan = XMLParser.parseTrajets(lienLivraisons, plan);
                mainvue.getMapPanel().loadPlan(plan);
                etat = new EtatLivraisonsCharges();
                mainvue.setEtat(etat);
            } catch (XMLException e) {
                e.printStackTrace();
                mainvue.errorMessage(e.getMessage());
            }
        }
    }

    public void mouseMoved(Point point) {
        mainvue.updateMousePosition(point);
    }

    public void onHoverNode(Noeud n)
    {
        mainvue.setSelectedNode(n);
    }

    public void onPressNode(Noeud n,MouseEvent e) {
        mainvue.displayMenuNode(n,e,etat.getPopUpMenu(plan,n));
    }

    public void resizeMap() {
        mainvue.resizeMap();
    }

    public Plan getPlan() {
        return plan;
    }

    public void mousePressed(Point point, MouseEvent e) {
        mainvue.mousePressed(point,e);
    }

    public void genererTournees() {
        etat = new EtatTournesGeneres();
        mainvue.setEtat(etat);
        ArrayList<Livraison> livraisons = new ArrayList<>();
        livraisons.addAll(plan.getLivraisons().values());
        ArrayList<Tournee> tournee = TSP.calculerLesTournees(livraisons,2,plan.getEntrepot());
        for(Tournee t : tournee){
            for(Chemin c : t.getChemins()){
                System.out.println(c);
            }
        }
        plan.setTournees(tournee);
        mainvue.getMapPanel().tracerTournees(tournee);
    }

    public void demarrerTournees() {
        etat = new EtatClientsAvertis();
        mainvue.setEtat(etat);
    }
}
