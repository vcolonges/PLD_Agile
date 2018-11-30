import TSP.AlgoParcour;
import modele.Livraison;
import modele.Plan;
import vue.MainVue;
import xml_manager.XMLParser;

import java.util.ArrayList;
import java.util.Date;

public class Application {


    public static void main(String[] args) {
        try {

            Plan plan;
            XMLParser xmlParser = new XMLParser();
            plan = xmlParser.parsePlan("src/grandPlan.xml");

            plan = xmlParser.parseTrajets("src/dl-grand-20.xml", plan);

            AlgoParcour test = new AlgoParcour();
            long time1 = new Date().getTime();
            ArrayList<Livraison> testLiv = new ArrayList<Livraison>(plan.getLivraisons().values());
            for(int i=0;i<testLiv.size();i++)
            {


                    test.calculChemin(testLiv.get(i), testLiv);
                    //System.out.println("\n");


                System.out.println("-----------------------------------------"+i+" Livraison--------------");
            }
            //TSP alTest = new TSP() ;
            //TSP.calculerLesTournees(testLiv, 3, plan.getEntrepot());

            long time2 = new Date().getTime();
            System.out.println((time2-time1));



            /*ArrayList<ArrayList<Livraison>> testCluster = test.getLivraisons(testLiv, 3);
            for(int i=0; i<testCluster.size(); i++)
            {
                for(Livraison l : testCluster.get(i))
                {
                    System.out.println(l.toString());
                }
                System.out.println(testCluster.get(i).size());
            }*/

            new MainVue();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }



}
