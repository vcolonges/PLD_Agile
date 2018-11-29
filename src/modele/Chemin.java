package modele;

import java.util.ArrayList;

public class Chemin {

    /*
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
    */

    private Noeud origin;
    private Noeud destination;
    private int longueur;
    private ArrayList<Troncon> troncons;

    public Chemin ()
    {
        troncons = new ArrayList<>();
    }

    public void setTroncon(Troncon troncon){this.troncons.add(troncon);}
    public void setTroncon(int index, Troncon troncon){this.troncons.add(index, troncon);}
    public void setOrigin(Noeud noeud){this.origin = noeud;}
    public void setDestination(Noeud noeud){this.destination=noeud;}
    public void setLongueur(int pLong){this.longueur=pLong;}

    public Noeud getOrigin() {
        return origin;
    }

    public Noeud getDestination() {
        return destination;
    }

    public int getLongueur() {
        return longueur;
    }

    public ArrayList<Troncon> getTroncons() {
        return troncons;
    }
}
