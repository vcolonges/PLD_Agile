package modele;

import java.util.*;

public class Plan {

    private HashMap<Long, Noeud> noeuds;
    private HashSet<Troncon> troncons;
    private ArrayList<Livraison> livraisons;
    private Livraison entrepot;
    private Date heureDepart;

    public Plan(){
        this.noeuds = new HashMap<>();
        this.troncons = new HashSet<>();
        this.livraisons = new ArrayList<>();
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

    public ArrayList<Livraison> getLivraisons() {
        return livraisons;
    }

    public boolean addLivraison(Livraison livraison) {
        return this.livraisons.add(livraison);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plan plan = (Plan) o;
        return Objects.equals(noeuds, plan.noeuds) &&
                Objects.equals(troncons, plan.troncons) &&
                Objects.equals(livraisons, plan.livraisons) &&
                Objects.equals(entrepot, plan.entrepot) &&
                Objects.equals(heureDepart, plan.heureDepart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noeuds, troncons, livraisons, entrepot, heureDepart);
    }
}
