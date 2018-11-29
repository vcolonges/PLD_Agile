package modele;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public class Plan {

    private HashMap<Long, Noeud> noeuds;
    private HashSet<Troncon> troncons;
    private HashMap<Long, Livraison> livraisons;
    private Livraison entrepot;
    private Date heureDepart;

    public Plan(){
        this.noeuds = new HashMap<>();
        this.troncons = new HashSet<>();
        this.livraisons = new HashMap<>();
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

    public double getMaxLat(){
        double max = -180;
        for(Noeud n : noeuds.values())
        {
            if(max<n.getLatitude()) max = n.getLatitude();
        }
        return max;
    }

    public double getMaxLong(){
        double max = -180;
        for(Noeud n : noeuds.values())
        {
            if(max<n.getLongitude()) max = n.getLongitude();
        }
        return max;
    }

    public double getMinLat(){
        double min = 180;
        for(Noeud n : noeuds.values())
        {
            if(min>n.getLatitude()) min = n.getLatitude();
        }
        return min;
    }

    public double getMinLong(){
        double min = 180;
        for(Noeud n : noeuds.values())
        {
            if(min>n.getLongitude()) min = n.getLongitude();
        }
        return min;
    }
    public HashMap<Long, Livraison> getLivraisons() {
        return livraisons;
    }

    public boolean addLivraison(Livraison livraison) {
        if(this.livraisons.containsKey(livraison.getNoeud().getId())){
            return false;
        }
        this.livraisons.put(livraison.getNoeud().getId(), livraison);
        return true;
    }

    public Livraison getEntrepot() {
        return entrepot;
    }

    public void setEntrepot(Livraison entrepot) {
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
                ", livraisons=" + livraisons +
                ", entrepot=" + entrepot +
                ", heureDepart=" + heureDepart +
                '}';
    }
}
