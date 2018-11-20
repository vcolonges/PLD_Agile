package modele;

import java.util.HashSet;

public class Noeud {

    private long id;
    private double latitude;
    private double longitude;
    //private HashSet<Troncon> tronconsAdjacents;

    public Noeud(long id, double latitude, double longitude){
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }



    @Override
    public String toString() {
        return "Noeud [id=" + id + ", latitude=" + latitude + ", longitude=" + longitude + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Noeud other = (Noeud) obj;
        if (id != other.id)
            return false;
        return true;
    }

}
