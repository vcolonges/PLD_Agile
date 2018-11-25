package TSP;

import modele.Livraison;
import modele.Noeud;
import modele.Troncon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

class Chemin
{
   Noeud origin;
   Noeud destination;
   int longueur;
   ArrayList<Troncon> troncons;

   void setTroncon(Troncon troncon){this.troncons.add(troncon);}
   void setOrigin(Noeud noeud){this.origin = noeud;}
   void setDestination(Noeud noeud){this.destination=noeud;}
   void setLongueur(int pLong){this.longueur=pLong;}
}

public class AlgoParcour
{
   //calculer 1e plus court chemin entre 2 livraisons
    // à recoder avec une somme pour eviter des boucles
   Chemin calculchemin(Noeud depart, Noeud fin)
   {
      //initialisation
      Chemin result = null;
      Noeud tmpNoeud=depart;
      Troncon tmpTroncon = null;
      int longueurTot=0;
      double tmpLongueur = 10000000000.0;
      HashSet<Troncon> tmpHashSet = null;
       HashSet<Troncon> tmpHashSetLevel2 = null;

      HashSet<Noeud> sommetNoirs = null;
      //itération sur les noeuds
      while(tmpNoeud!=fin) {
         tmpHashSet = tmpNoeud.getTronconsAdjacents();
         sommetNoirs.add(tmpNoeud);
         //iteration sur les trancons adjacents (patcour en largeur)
         for (Troncon troncon : tmpHashSet) {
             if(!sommetNoirs.contains(troncon.getDestination())) {

                 if (tmpLongueur > troncon.getLongueur()) {
                     tmpTroncon = troncon;
                     tmpLongueur = troncon.getLongueur();
                     longueurTot += tmpLongueur;
                 }
             }
         }
         result.setOrigin(tmpNoeud);
         tmpNoeud=tmpTroncon.getDestination();
         result.setDestination(tmpNoeud);
         result.setLongueur(longueurTot);
         tmpLongueur = 10000000000.0;
         result.setTroncon(tmpTroncon);
      }
      return result;
   }
  //calculer les chemin pour chaque livraison
   HashSet<Livraison> calculCheminLivraison(HashMap<Long, Noeud> noeuds, HashSet<Livraison> livraisons)
   {
      for (Livraison livraison:livraisons) {
         for(Livraison targetLiv:livraisons)
         {
            if(livraison!=targetLiv)
            {
               //livraison.setChemin( calculchemin(livraison.getNoeud(), targetLiv.getNoeud()));
            }
         }
      }
      return livraisons;
   }

  /* HashSet<HashSet<Livraison>> toCircle(HashSet<Livraison> livraisons, int nbrLivreur)
   {
       HashSet<HashSet<Livraison>> result = new HashSet<HashSet<Livraison>>();
       Iterator<Livraison> iterator = livraisons.iterator();
       for(int i = 0; i < nbrLivreur; i++)
       {
           HashSet<Livraison> tmpSet =new HashSet<Livraison>();
           for(int j = 0; j < livraisons.size() / nbrLivreur; j++)
           {
                tmpSet.add(iterator.next());
           }
           result.add(tmpSet);
       }
       Iterator<HashSet<Livraison>> iteratorResult = result.iterator();
       while(iterator.hasNext())
       {
           iteratorResult.next().add(iterator.next());
       }

       return result;
   }*/

  //Calculer la distance entre 2 points.
    Double PointsDistance(double x1, double y1, double x2, double y2)
    {
        return Math.sqrt(Math.pow(2.0,(x2-x1)) + Math.pow(2.0,(y2-y1)));
    }

    //calculer les données d'un cercle
    ArrayList<Double> initCircleData(ArrayList<Double> circleData, ArrayList<Livraison> circle)
    {
        ArrayList<Double> tmpCircleData=null;
        circleData.add(circle.get(0).getNoeud().getLatitude());
        circleData.add(circle.get(0).getNoeud().getLongitude());
        circleData.add(circle.get(1).getNoeud().getLatitude());
        circleData.add(circle.get(1).getNoeud().getLongitude());
        circleData.add((circleData.get(0)+circleData.get(2))/2);
        circleData.add((circleData.get(1)+circleData.get(3))/2);

        for(int indexCircleNoeud = 2; indexCircleNoeud < circle.size(); indexCircleNoeud++)
        {
            Noeud curNoeud = circle.get(indexCircleNoeud).getNoeud();
            double distanceToCenter = PointsDistance( curNoeud.getLatitude(), curNoeud.getLongitude(),circleData.get(4),circleData.get(5));
            double circleRadius = PointsDistance( circleData.get(0),circleData.get(1),circleData.get(4),circleData.get(5));
            //verifier si un noeud n'appartient pas au cercle definit
            if(distanceToCenter > circleRadius)
            {
                tmpCircleData=MAJCircleData(curNoeud, circleData);
            }
        }
        return tmpCircleData;
    }

    //Mettre à jour les données d'un cercle
    ArrayList<Double> MAJCircleData(Noeud curNoeud,  ArrayList<Double> circleData)
    {
        double distanceToPoint1 = PointsDistance( curNoeud.getLatitude(), curNoeud.getLongitude(),circleData.get(0),circleData.get(1));
        double distanceToPoint2 = PointsDistance( curNoeud.getLatitude(), curNoeud.getLongitude(),circleData.get(2),circleData.get(3));
        // verifier si le point exterieur est plus pres du Point1 se trouvant sur le cerlce
        if(distanceToPoint1 < distanceToPoint2)
        {
            //mettre à jour le Point1
            circleData.set(0, curNoeud.getLatitude());
            circleData.set(1, curNoeud.getLongitude());
        }
        else
        {
            //mettre à jour le Point2
            circleData.set(2, curNoeud.getLatitude());
            circleData.set(3, curNoeud.getLongitude());
        }
        //Mettre à jour le centre
        circleData.set(4, (circleData.get(0)+circleData.get(2))/2);
        circleData.set(5, (circleData.get(1)+circleData.get(3))/2);
        return circleData;
    }

    //separer l'ensemble de livraisons en n listes de taille k correspondant au nombre de livraisons
    //chaque liste represente un cercle avec la plus grande densité des k livraison adjacent
    ArrayList<ArrayList<Livraison>> toCircle(ArrayList<Livraison> livraisons, int nbrLivreur)
    {
        //stocker aleatoirement k livraisons en nbrLivreur listes
        ArrayList<ArrayList<Livraison>> result = new ArrayList<ArrayList<Livraison>>();
        ArrayList<ArrayList<Livraison>> tmpResult = new ArrayList<ArrayList<Livraison>>();
        int indexLivraison=0;
        for(int i = 0; i < nbrLivreur; i++)
        {
            ArrayList<Livraison> tmpList =new ArrayList<Livraison>();
            ArrayList<Livraison> tmpResultList =new ArrayList<Livraison>();
            for(int j = 0; j < livraisons.size() / nbrLivreur; j++)
            {
                tmpList.add(livraisons.get(indexLivraison));
                indexLivraison++;
            }
            result.add(tmpList);
            tmpResult.add(tmpResultList);
        }
        //si le nombre de livraisons n'est pas divisible par nbrLivreur, repartir le reste
        // entre les listes
        for(int p = indexLivraison; p<livraisons.size(); p++)
        {
            result.get(livraisons.size()-p).add(livraisons.get(p));
        }

        //à chaque cercle on attribue un centre se composant de deux coordonnées et deux coordonées de deux noeuds les plus eloignés
        // pour chaque element de la liste on a:
        // index 0 : Latitude Noeud 1, index 1 : Longitude Noeud 1
        // index 2 : Latitude Noeud 2, index 3 : Longitude Noeud 2
        // index 4 : Latitude centre, index 5 : Longitude centre
        ArrayList<ArrayList<Double>> circlesData = new ArrayList<>();
        for(int indexCircle = 0; indexCircle < nbrLivreur; indexCircle++)
        {
            ArrayList<Double> tmpCircleData = new ArrayList<>();
            circlesData.add(tmpCircleData);
            //initialiser les données d'un cercle
            circlesData.set(indexCircle, initCircleData( circlesData.get(indexCircle), result.get(indexCircle)));
        }

        //optimiser le contenu d'un cercle

        for(int indexCircle=0; indexCircle<nbrLivreur; indexCircle++)
        {
            //pour chaque cercle on prend chaque noeud
            for(int indexNoeud=0; indexNoeud<result.get(indexCircle).size(); indexNoeud++)
            {
                //pour chaque noeud on récupère sa distance au centre de son cercle
                Noeud curNoeud = result.get(indexCircle).get(indexNoeud).getNoeud();
                double distanceToCenter=PointsDistance(curNoeud.getLatitude(), curNoeud.getLongitude(), circlesData.get(indexCircle).get(4), circlesData.get(indexCircle).get(5));
                //on compare la distance entre noeud et son cercle à la distance du noeud et autres cercles
                int lastCircleIndex = indexCircle;
                double tmpDistance = 0;
                for(int tmpIndexCircle=0; tmpIndexCircle<nbrLivreur; tmpIndexCircle++)
                {
                    tmpDistance = PointsDistance(curNoeud.getLatitude(), curNoeud.getLongitude(), circlesData.get(tmpIndexCircle).get(4), circlesData.get(indexCircle).get(5));
                    if(tmpDistance<distanceToCenter)
                    {
                        distanceToCenter=tmpDistance;
                        lastCircleIndex=tmpIndexCircle;
                    }
                }
                //si le noeud est plus pres du centre d'un autre cercle que du sien, on le transmet à resultat final du cercle ciblé et on redemarre l'algorithme
                //pour le cercle cible
                //possible d'optimiser
                if(lastCircleIndex!=indexCircle)
                {
                    tmpResult.get(lastCircleIndex).add(result.get(indexCircle).remove(indexNoeud));
                    if(distanceToCenter>PointsDistance(circlesData.get(lastCircleIndex).get(0), circlesData.get(lastCircleIndex).get(1), circlesData.get(lastCircleIndex).get(4), circlesData.get(lastCircleIndex).get(5)))
                    {
                        circlesData.set(lastCircleIndex,MAJCircleData(curNoeud, circlesData.get(lastCircleIndex)));
                    }
                    if((curNoeud.getLatitude()==circlesData.get(indexCircle).get(0) && curNoeud.getLongitude()==circlesData.get(indexCircle).get(1)) || (curNoeud.getLatitude()==circlesData.get(indexCircle).get(2) && curNoeud.getLongitude()==circlesData.get(indexCircle).get(3)))
                    {
                        circlesData.set(indexCircle, initCircleData(circlesData.get(indexCircle), result.get(indexCircle)));
                    }
                    indexCircle=lastCircleIndex--;

                    break;
                }
                //sinon on transmet le noeud au resultat du cercle courant
                else
                {
                    tmpResult.get(indexCircle).add(result.get(indexCircle).remove(indexNoeud));
                }

            }
            //si on a transmit l'ensemble de noeuds d'un cercle à resultat du cercle et que le cardinal du resultat
            //est superieur au nombre de livraions / nombre de livreur +1
            // on redistribue le dernier noeud  du resultat potentiellement erroné entre les cercles restant
            if(result.get(indexCircle).size()==0 && tmpResult.get(indexCircle).size()>livraisons.size()/nbrLivreur +1)
            {
                result.get((indexCircle+1)%nbrLivreur).add(tmpResult.get(indexCircle).remove(tmpResult.get(indexCircle).size()-1));
            }
        }

        return result;
    }



  ArrayList<ArrayList<Livraison>> getLivraisons (HashSet<Livraison> livraisons, int nbrLivreur)
  {
    ArrayList<Livraison> listLivraisons = new ArrayList<>(livraisons);

    return toCircle(listLivraisons, nbrLivreur);
  }

}

