
import modele.Plan;
import xml_manager.XMLParser;

public class Application {

    public static void main(String[] args) {
        try {
            Plan plan;
            XMLParser xmlParser = new XMLParser();
            plan = xmlParser.parsePlan("src/petitPlan.xml");

            plan = xmlParser.parseTrajets("src/dl-grand-20.xml", plan);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }



}
