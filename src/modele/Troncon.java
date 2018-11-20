package modele;

public class Troncon {

    private Noeud origine;
    private Noeud destination;
    private double longueur;
    private String nomRue;

    public Troncon(Noeud origine, Noeud destination, double longueur, String nomRue){
        this.origine = origine;
        this.destination = destination;
        this.longueur = longueur;
        this.nomRue = nomRue;
    }

    public Noeud getOrigine() {
        return origine;
    }
    public void setOrigine(Noeud origine) {
        this.origine = origine;
    }
    public Noeud getDestination() {
        return destination;
    }
    public void setDestination(Noeud destination) {
        this.destination = destination;
    }
    public double getLongueur() {
        return longueur;
    }
    public void setLongueur(double longueur) {
        this.longueur = longueur;
    }
    public String getNomRue() {
        return nomRue;
    }
    public void setNomRue(String nomRue) {
        this.nomRue = nomRue;
    }



}
