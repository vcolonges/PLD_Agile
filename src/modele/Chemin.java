package modele;

import java.util.ArrayList;

public class Chemin {

    private Noeud origine;
    private Noeud destination;
    private double longueur;
    ArrayList<Troncon> troncons;

    public Chemin(Noeud origine, Noeud destination, double longueur){
        this.origine = origine;
        this.destination = destination;
        this.longueur = longueur;
        this.troncons = new ArrayList<>();
    }

}
