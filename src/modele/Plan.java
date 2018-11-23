package modele;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public class Plan {

    private HashMap<Long, Noeud> noeuds;
    private HashSet<Troncon> troncons;
    private Noeud entrepot;
    private Date heureDepart;

    public Plan(){
        this.noeuds = new HashMap<>();
        this.troncons = new HashSet<>();
    }

    public HashMap<Long, Noeud> getNoeuds(){
        return this.noeuds;
    }

    public boolean addNoeud(Noeud noeud){
        if(this.noeuds.containsKey(noeud.getId())){
            return false;
        }
        this.noeuds.put(noeud.getId(), noeud);
        return true;
    }

    public HashSet<Troncon> getTroncons() {
        return troncons;
    }

    public boolean addTroncon(Troncon troncon) {
        return this.troncons.add(troncon);
    }

    public Noeud getEntrepot() {
        return entrepot;
    }

    public void setEntrepot(Noeud entrepot) {
        this.entrepot = entrepot;
    }

    public Date getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(Date heureDepart) {
        this.heureDepart = heureDepart;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "noeuds=" + noeuds +
                ", troncons=" + troncons +
                '}';
    }
}
