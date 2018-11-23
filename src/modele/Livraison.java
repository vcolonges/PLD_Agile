package modele;

public class Livraison {

    private Noeud noeud;
    private int duree;

    private Livraison(Noeud noeud, int duree){
        this.noeud = noeud;
        this.duree = duree;
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
}
