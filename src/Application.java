
import TSP.AlgoParcour;
import modele.Plan;
import xml_manager.XMLParser;

public class Application {

    public static void main(String[] args) {
        try {
            Plan plan;
            XMLParser xmlParser = new XMLParser();
            plan = xmlParser.parsePlan("src/petitPlan.xml");

            plan = xmlParser.parseTrajets("src/dl-petit-6.xml", plan);

            AlgoParcour test = new AlgoParcour();
            test.calculChemin(plan.getLivraisons().get(0).getNoeud(), plan.getLivraisons().get(1).getNoeud());
            /*ArrayList<ArrayList<Livraison>> testCluster = test.getLivraisons(plan.getLivraisons(), 3);
            for(int i=0; i<testCluster.size(); i++)
            {
                for(Livraison l : testCluster.get(i))
                {
                    System.out.println(l.toString());
                }
                System.out.println(testCluster.get(i).size());
            }*/
        }catch (Exception e) {
            e.printStackTrace();
        }
    }



}
