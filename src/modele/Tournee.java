package modele;

import java.util.ArrayList;
import java.util.HashSet;

public class Tournee {
    private HashSet<Livraison> livraisons;
    private ArrayList<Chemin> chemins;

    public Tournee(HashSet<Livraison> livraisons, ArrayList<Chemin> chemins)
    {
        this.livraisons = livraisons;
        this.chemins = chemins;
    }

    public ArrayList<Chemin> getChemins(){
        return this.chemins;
    }

    @Override
    public String toString() {
        return "Tournee{" +
                "chemins=" + chemins +
                '}';
    }
}
