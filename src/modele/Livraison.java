package modele;

import java.util.Objects;

public class Livraison {

    private Noeud noeud;
    private int duree;

    public Livraison(Noeud noeud, int duree){
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

    @Override
    public String toString() {
        return "Livraison{" +
                "noeud=" + noeud +
                ", duree=" + duree +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livraison livraison = (Livraison) o;
        return duree == livraison.duree &&
                Objects.equals(noeud, livraison.noeud);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noeud, duree);
    }
}
