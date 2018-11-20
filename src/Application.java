
import xml_manager.XMLParser;

public class Application {

    public static void main(String[] args) {
        try {
            XMLParser xmlParser = new XMLParser();
            xmlParser.parse("src/petitPlan.xml");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }



}
