package TSP;

import javafx.scene.shape.Circle;
import javafx.util.Pair;
import modele.Livraison;
import modele.Noeud;
import modele.Troncon;

import java.util.*;

class Chemin
{
   Noeud origin;
   Noeud destination;
   int longueur;
   ArrayList<Troncon> troncons;
    public Chemin ()
    {
        troncons = new ArrayList<>();
    }
   void setTroncon(Troncon troncon){this.troncons.add(troncon);}
   void setTroncon(int index, Troncon troncon){this.troncons.add(index, troncon);}
   void setOrigin(Noeud noeud){this.origin = noeud;}
   void setDestination(Noeud noeud){this.destination=noeud;}
   void setLongueur(int pLong){this.longueur=pLong;}
}

public class AlgoParcour {
    //calculer 1e plus court chemin entre 2 livraisons
    // à recoder avec une somme pour eviter des boucles
    public Chemin calculChemin(Noeud depart, Noeud fin) {
        //initialisation
        Chemin result = new Chemin();
        // ensemble des troncons adjacents à un noeud
        HashSet<Troncon> curNoeudTroncons = null;
        // Noeud dont tous les succeeceurs sont grisés
        ArrayList<Noeud> blackNoeud = new ArrayList<>();
        //Ensemble de noeuds decouverts
        ArrayList<Noeud> greyNoeuds = new ArrayList<>();
        greyNoeuds.add(depart);
        // Collection des noeuds, leur prédécesseur et la distance jusqu'à ce noeud depuis le noeud départ
        HashMap<Long, Pair<Troncon, Double>> successorDistance = new HashMap<Long, Pair<Troncon, Double>>();
        //initialisation de collection avec le noeud départ
        successorDistance.put(depart.getId(), new Pair(null,0.0));
        //on parcourt tous les noeuds gris
       for(int curNoeudIndex=0; curNoeudIndex<greyNoeuds.size(); curNoeudIndex++)
        {   //si le noeud gris est celui de la fin, on ne calcule pas ses successeurs car c'est la fin de chemin
            Noeud curNoeud = greyNoeuds.get(curNoeudIndex);
            if(curNoeud==fin)
            {
                continue;
            }
            Noeud tmpGreyNoeud = null;
            curNoeudTroncons=curNoeud.getTronconsAdjacents();
            //On récupère la distance du depart jusquà noeud prédécesseur forcement définie dans la collection
            double curTravelDistance = successorDistance.get(curNoeud.getId()).getValue();
            //on parcourt les Noeud adjacents via les troncons
            for(Troncon tmpTroncon : curNoeudTroncons)
            {
                //on calcule la distance au noeud seulement s'il n'est pas dans blackNoeud
                if(!blackNoeud.contains(tmpTroncon.getDestination())) //****Complexité O(n), peut être amélioré
                {
                    tmpGreyNoeud=tmpTroncon.getDestination();
                    //On ajoute les noeuds découverts dans greyNoeud seulement si on a pas déjà decouvert le noeud fin (omplexité O(1))
                    if(!successorDistance.containsKey(fin.getId())) {
                        greyNoeuds.add(tmpGreyNoeud);
                    }
                    curTravelDistance+=tmpTroncon.getLongueur();
                    //Si le noeud gris courant (tmpGreyNoeud) possède une distance plus courte à atteindre dépuis son nouveau predecesseur, on met à jour
                    // la collection
                    if(successorDistance.get(tmpGreyNoeud.getId())==null || successorDistance.get(tmpGreyNoeud.getId()).getValue()>curTravelDistance)
                    {
                        successorDistance.put(tmpGreyNoeud.getId(), new Pair(tmpTroncon,curTravelDistance));
                    }
                }
            }
            //une fois tous les noeud adjacents du curNoeud parcouru, on l'ajoute dans blackNoeud
            blackNoeud.add(curNoeud);
            //greyNoeuds.remove(curNoeud); //probablément juste une perte de temps
        }

        Troncon sortTroncon = successorDistance.get(fin.getId()).getKey();
        result.setTroncon(sortTroncon);
        while(sortTroncon.getOrigine()!=depart)
        {
            sortTroncon= successorDistance.get(sortTroncon.getOrigine().getId()).getKey();
            result.setTroncon(0,sortTroncon);
        }

        for(int i=0; i<result.troncons.size(); i++)
        {
            System.out.println(result.troncons.get(i));
        }
        System.out.println(result.troncons.size());
        return result;
    }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Calculer la distance entre 2 points.
    private Double PointsDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow((x2 - x1), 2.0) + Math.pow((y2 - y1),2.0 ));
    }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //calculer les données d'un cercle
    private ArrayList<Double> initCircleData(ArrayList<Livraison> circle) {
        ArrayList<Double> circleData = new ArrayList<>();
        circleData.add(circle.get(0).getNoeud().getLatitude());
        circleData.add(circle.get(0).getNoeud().getLongitude());
        circleData.add(circle.get(1).getNoeud().getLatitude());
        circleData.add(circle.get(1).getNoeud().getLongitude());
        circleData.add((circleData.get(0) + circleData.get(2)) / 2);
        circleData.add((circleData.get(1) + circleData.get(3)) / 2);

        for (int indexCircleNoeud = 2; indexCircleNoeud < circle.size(); indexCircleNoeud++) {
            Noeud curNoeud = circle.get(indexCircleNoeud).getNoeud();
            double distanceToCenter = PointsDistance(curNoeud.getLatitude(), curNoeud.getLongitude(), circleData.get(4), circleData.get(5));
            double circleRadius = PointsDistance(circleData.get(0), circleData.get(1), circleData.get(4), circleData.get(5));
            //verifier si un noeud n'appartient pas au cercle definit
            if (distanceToCenter > circleRadius) {
                circleData = MAJCircleData(curNoeud, circleData);
            }
        }
        return circleData;
    }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Mettre à jour les données d'un cercle
    private ArrayList<Double> MAJCircleData(Noeud curNoeud, ArrayList<Double> circleData) {
        double distanceToPoint1 = PointsDistance(curNoeud.getLatitude(), curNoeud.getLongitude(), circleData.get(0), circleData.get(1));
        double distanceToPoint2 = PointsDistance(curNoeud.getLatitude(), curNoeud.getLongitude(), circleData.get(2), circleData.get(3));
        // verifier si le point exterieur est plus pres du Point1 se trouvant sur le cerlce
        if (distanceToPoint1 < distanceToPoint2) {
            //mettre à jour le Point1
            circleData.set(0, curNoeud.getLatitude());
            circleData.set(1, curNoeud.getLongitude());
        } else {
            //mettre à jour le Point2
            circleData.set(2, curNoeud.getLatitude());
            circleData.set(3, curNoeud.getLongitude());
        }
        //Mettre à jour le centre
        circleData.set(4, (circleData.get(0) + circleData.get(2)) / 2);
        circleData.set(5, (circleData.get(1) + circleData.get(3)) / 2);
        return circleData;
    }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //separer l'ensemble de livraisons en n listes de taille k correspondant au nombre de livraisons
    //chaque liste represente un cercle avec la plus grande densité des k livraison adjacent
    private ArrayList<ArrayList<Livraison>> toCircle(ArrayList<Livraison> livraisons, int nbrLivreur, int nLim) {
        //stocker aleatoirement k livraisons en nbrLivreur listes
        ArrayList<ArrayList<Livraison>> result = new ArrayList<ArrayList<Livraison>>();
        ArrayList<ArrayList<Livraison>> tmpResult = new ArrayList<ArrayList<Livraison>>();
        int indexLivraison = 0;
        for (int i = 0; i < nbrLivreur; i++)
        {
            ArrayList<Livraison> tmpList = new ArrayList<Livraison>();
            ArrayList<Livraison> tmpResultList = new ArrayList<Livraison>();
            for (int j = 0; j < livraisons.size() / nbrLivreur; j++) {
                tmpList.add(livraisons.get(indexLivraison));
                indexLivraison++;
            }
            result.add(tmpList);
            tmpResult.add(tmpResultList);
        }
        //si le nombre de livraisons n'est pas divisible par nbrLivreur, repartir le reste
        // entre les listes
        for (int p = indexLivraison; p < livraisons.size(); p++) {
            result.get(livraisons.size() - p).add(livraisons.get(p));
        }

        //à chaque cercle on attribue un centre se composant de deux coordonnées et deux coordonées de deux noeuds les plus eloignés
        // pour chaque element de la liste on a:
        // index 0 : Latitude Noeud 1, index 1 : Longitude Noeud 1
        // index 2 : Latitude Noeud 2, index 3 : Longitude Noeud 2
        // index 4 : Latitude centre, index 5 : Longitude centre
        ArrayList<ArrayList<Double>> circlesData = new ArrayList<>();
        for (int indexCircle = 0; indexCircle < nbrLivreur; indexCircle++) {
            circlesData.add(initCircleData(result.get(indexCircle)));
            //initialiser les données d'un cercle
        }

        //optimiser le contenu d'un cercle
        for(int j=0; j<nbrLivreur; j++) {
            for (int indexCircle = 0; indexCircle < nbrLivreur; indexCircle++) {
       /* if(result.get(indexCircle).size()==0)
        {
            result.remove(indexCircle);
            indexCircle--;
            break;
        }*/
                int lastCircleIndex = 0;
                if (result.get(indexCircle).size() != 0) {
                    //pour chaque cercle on prend chaque noeud
                    for (int indexNoeud = 0; indexNoeud < result.get(indexCircle).size(); indexNoeud++) {
                        //pour chaque noeud on récupère sa distance au centre de son cercle
                        Noeud curNoeud = result.get(indexCircle).get(indexNoeud).getNoeud();
                        double distanceToCenter = PointsDistance(curNoeud.getLatitude(), curNoeud.getLongitude(), circlesData.get(indexCircle).get(4), circlesData.get(indexCircle).get(5));
                        //on compare la distance entre noeud et son cercle à la distance du noeud et autres cercles
                        lastCircleIndex = indexCircle;
                        double tmpDistance = 0;
                        for (int tmpIndexCircle = 0; tmpIndexCircle < nbrLivreur; tmpIndexCircle++) {
                            //if (result.get(tmpIndexCircle).size() != 0) {//********************************trouver meilleur condition d'arrêt
                                tmpDistance = PointsDistance(curNoeud.getLatitude(), curNoeud.getLongitude(), circlesData.get(tmpIndexCircle).get(4), circlesData.get(tmpIndexCircle).get(5));
                                if (tmpDistance < distanceToCenter) {
                                    distanceToCenter = tmpDistance;
                                    lastCircleIndex = tmpIndexCircle;
                                }
                            //}
                        }
                        //si le noeud est plus pres du centre d'un autre cercle que du sien, on le transmet à resultat final du cercle ciblé et on redemarre l'algorithme
                        //pour le cercle cible
                        //possible d'optimiser
                        if (lastCircleIndex != indexCircle) {
                            result.get(lastCircleIndex).add(result.get(indexCircle).remove(indexNoeud));
                            if (distanceToCenter > PointsDistance(circlesData.get(lastCircleIndex).get(0), circlesData.get(lastCircleIndex).get(1), circlesData.get(lastCircleIndex).get(4), circlesData.get(lastCircleIndex).get(5))) {
                                circlesData.set(lastCircleIndex, MAJCircleData(curNoeud, circlesData.get(lastCircleIndex)));
                            }
                            if (((curNoeud.getLatitude() == circlesData.get(indexCircle).get(0) && curNoeud.getLongitude() == circlesData.get(indexCircle).get(1)) || (curNoeud.getLatitude() == circlesData.get(indexCircle).get(2) && curNoeud.getLongitude() == circlesData.get(indexCircle).get(3))) && result.get(indexCircle).size() > 1) {
                                circlesData.set(indexCircle, initCircleData(result.get(indexCircle)));
                            }
                            indexCircle = lastCircleIndex - 1;

                            break;
                        }
                        //sinon on transmet le noeud au resultat du cercle courant
                        else {
                            tmpResult.get(indexCircle).add(result.get(indexCircle).remove(indexNoeud));
                            indexNoeud--;
                        }
                    }
                    //si on a transmit l'ensemble de noeuds d'un cercle à resultat du cercle et que le cardinal du resultat
                    //est superieur au nombre de livraions / nombre de livreur +1
                    // on redistribue le dernier noeud  du resultat potentiellement erroné entre les cercles restant
                    if (tmpResult.get(lastCircleIndex).size() > nLim) {
                        int indexToRemove = 0;
                        int indexToMove = indexCircle;
                        for (int indexNoeud = 0; indexNoeud < tmpResult.get(lastCircleIndex).size(); indexNoeud++) {
                            double distanceToCenter = 100000000.0;
                            Noeud curNoeud = tmpResult.get(lastCircleIndex).get(indexNoeud).getNoeud();
                            for (int indexCircleTarget = 0; indexCircleTarget < nbrLivreur; indexCircleTarget++) {
                                if (indexCircleTarget != lastCircleIndex && result.get(indexCircleTarget).size() != 0) {
                                    double tmpDistance = PointsDistance(curNoeud.getLatitude(), curNoeud.getLongitude(), circlesData.get(indexCircleTarget).get(4), circlesData.get(indexCircleTarget).get(5));
                                    if (tmpDistance < distanceToCenter) {
                                        indexToRemove = indexNoeud;
                                        indexToMove = indexCircleTarget;
                                    }

                                }
                            }
                        }
                        if(result.get(indexToMove).size()==0 || indexToRemove== tmpResult.get(lastCircleIndex).size()-1)
                        {
                            int circleSize = 1000000;
                            ArrayList<Livraison> tmpListLiv = null;
                            for(ArrayList<Livraison> listLiv : tmpResult)
                            {
                                if(circleSize>listLiv.size())
                                {
                                    circleSize=listLiv.size();
                                    tmpListLiv=listLiv;
                                }
                            }
                            tmpListLiv.add(tmpResult.get(lastCircleIndex).remove(indexToRemove));
                        }
                        else
                        {
                            result.get(indexToMove).add(tmpResult.get(lastCircleIndex).remove(indexToRemove));
                        }
                        indexCircle = --indexToMove;
                    }
                }

            }
            for(int i=0; i<nbrLivreur; i++)
            {
                result.get(i).addAll(tmpResult.get(i));
                tmpResult.get(i).clear();
            }
        }

        return result;

    }



//////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ArrayList<ArrayList<Livraison>> getLivraisons( ArrayList<Livraison> livraisons, int nbrLivreur) {
        int nLim = livraisons.size()/nbrLivreur;
        if(livraisons.size()%nbrLivreur!=0)
        {
            nLim++;
        }
        return toCircle(livraisons, nbrLivreur, nLim);
    }


}

