package xml_manager;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import modele.Noeud;
import modele.Plan;
import modele.Troncon;

public class XMLParser {

    public Plan parse(String lienFichier) throws ParserConfigurationException, IOException, Exception{
        Plan plan = new Plan();

        File file = new File(lienFichier);

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);

        doc.getDocumentElement().normalize();

        NodeList listNoeuds = doc.getElementsByTagName("noeud");

        for (int i = 0; i < listNoeuds.getLength(); i++) {

            Node node = listNoeuds.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element element = (Element) node;
                /*
                 * Si l'attribut "id" n'existe pas OU est <= 0, throw Exception explicite
                 * /!\ Si l'attribut "id" fait plus de 19 caractères, throw NumberFormatException
                 */
                long id;
                if(element.getAttribute("id").equals("")){
                    throw new Exception("Présence d'un noeud sans id.");
                }else if((id = Long.parseLong(element.getAttribute("id"))) <= 0) {
                    throw new Exception("Présence d'un id de noeud inférieur ou égal à 0.");
                }

                /*
                 * Si l'attribut "latitude" n'existe pas, throw Exception explicite
                 * /!\ Si l'attribut "latitude" n'est pas parsable en Double, throw NumberFormatException
                 */
                if(element.getAttribute("latitude").equals("")){
                    throw new Exception("Présence d'un noeud sans latitude.");
                }
                double latitude = Double.parseDouble(element.getAttribute("latitude"));

                /*
                 * Si l'attribut "longitude" n'existe pas, throw Exception explicite
                 * /!\ Si l'attribut "longitude" n'est pas parsable en Double, throw NumberFormatException
                 */
                if(element.getAttribute("longitude").equals("")){
                    throw new Exception("Présence d'un noeud sans longitude.");
                }
                double longitude = Double.parseDouble(element.getAttribute("longitude"));

                Noeud noeud = new Noeud(id,latitude,longitude);
                if(!plan.addNoeud(noeud)){
                    throw new Exception("Présence d'un doublon de noeud.");
                }
            }
        }

        NodeList listTroncons = doc.getElementsByTagName("troncon");

        for (int i = 0; i < listTroncons.getLength(); i++) {

            Node node = listTroncons.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element element = (Element) node;

                if(element.getAttribute("origine").equals("") ){
                    throw new Exception("Présence d'un troncon sans origine.");
                }else if(Long.parseLong(element.getAttribute("origine")) <= 0) {
                    throw new Exception("Présence d'une origine de troncon <= 0.");
                }
                long origine = Long.parseLong(element.getAttribute("origine"));
				/*if(plan.getNoeuds().get(origine) == null){
					throw new Exception("origine");
				}*/
                System.out.println(plan.getNoeuds().get(origine));

                if(element.getAttribute("longueur").equals("")){
                    throw new Exception("Présence d'un troncon sans longueur.");
                }
                double longueur = Double.parseDouble(element.getAttribute("longueur"));


				/*if(element.getAttribute("nomRue").equals("")){
					throw new Exception("Présence d'un troncon sans nom de rue");
				}*/
                String nomRue = element.getAttribute("nomRue");



                //Troncon troncon = new Troncon()
            }
        }
		/*for (Map.Entry<Long, Noeud> entry : plan.getNoeuds().entrySet())
		{
		    System.out.println(entry.getValue());
		}*/
        return plan;
    }

}
