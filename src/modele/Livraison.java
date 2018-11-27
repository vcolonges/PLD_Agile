package modele;

import java.util.HashSet;

public class Livraison {

    private Noeud noeud;
    private int duree;
    private HashSet<Chemin> chemins;

    public Livraison(Noeud noeud, int duree){
        this.noeud = noeud;
        this.duree = duree;
        chemins = new HashSet<>();
    }

    public Noeud getNoeud() {
        return noeud;
    }

    public void setNoeud(Noeud noeud) {
        this.noeud = noeud;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public HashSet<Chemin> getChemins() {
        return chemins;
    }

    public Chemin getCheminVers(Livraison destination){
        for (Chemin item: chemins) {
            if(item.getDestination() == destination){
                return item;
            }
        }
        return  null;
    }
}
