
import TSP.AlgoParcour;
import modele.Livraison;
import modele.Noeud;
import modele.Plan;
import xml_manager.XMLParser;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class Application {

    public static void main(String[] args) {
        try {
            Plan plan;
            XMLParser xmlParser = new XMLParser();
            plan = xmlParser.parsePlan("src/grandPlan.xml");

            plan = xmlParser.parseTrajets("src/dl-grand-20.xml", plan);

            AlgoParcour test = new AlgoParcour();
            ArrayList<ArrayList<Livraison>> testCluster = test.getLivraisons(plan.getLivraisons(), 3);
            for(int i=0; i<testCluster.size(); i++)
            {
                for(Livraison l : testCluster.get(i))
                {
                    System.out.println(l.toString());
                }
                System.out.println(testCluster.get(i).size());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }



}
