
import modele.Noeud;
import modele.Plan;
import xml_manager.XMLParser;

import java.util.Map;

public class Application {

    public static void main(String[] args) {
        try {
            Plan plan;
            XMLParser xmlParser = new XMLParser();
            plan = xmlParser.parsePlan("src/grandPlan.xml");

            //plan = xmlParser.parseTrajets("src/dl-petit-6.xml", plan);

            for(Map.Entry<Long, Noeud> entry : plan.getNoeuds().entrySet()) {
                System.out.println(entry.getValue());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }



}
