package modele;

import java.util.HashMap;
import java.util.HashSet;

public class Plan {

    private HashMap<Long, Noeud> noeuds;
    private HashSet<Troncon> troncons;

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



}
