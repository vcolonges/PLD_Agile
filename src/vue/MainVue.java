package vue;

import modele.Noeud;
import modele.Plan;
import modele.Troncon;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainVue extends JFrame {
    private JMenuBar menuBar;
    private MapVue mapPanel;
    public final static String CHARGER_PLAN = "Charger un plan";
    public final static String CHARGER_LIVRAISON = "Charger des livraisons";

    public MainVue(){

        super("Application");
        menuBar = new JMenuBar();

        JMenuItem chargerPlanXML = new JMenuItem("Charger un plan");
        JMenuItem chargerLivraisonXML = new JMenuItem("Charger des livraisons");

        JMenu fileMenu = new JMenu("Fichier");
        fileMenu.add(chargerPlanXML);
        fileMenu.add(chargerLivraisonXML);
        menuBar.add(fileMenu);

        this.setJMenuBar(menuBar);
        this.setSize(1200,900);
        this.setVisible(true);

        BorderLayout mainLayout = new BorderLayout();

        this.setLayout(mainLayout);
        mapPanel = new MapVue();
        mapPanel.setBackground(Color.BLUE);
        Plan p = new Plan();
        Noeud n1 = new Noeud(1,10,10);
        Noeud n2 = new Noeud(2,50,80);
        Noeud n3 = new Noeud(3,100,50);
        p.addNoeud(n1);
        p.addNoeud(n2);
        p.addNoeud(n3);
        p.addTroncon(new Troncon(n1,n2,4,"Coucou"));
        p.addTroncon(new Troncon(n2,n3,4,"Coucou"));
        mapPanel.loadPlan(p);

        JPanel toolPanel = new JPanel();
        toolPanel.setLayout(new GridLayout(4,1));

        JPanel nbPersonPanel = new JPanel();
        nbPersonPanel.setLayout(new GridLayout(1,2));
        nbPersonPanel.add(new JLabel("Nombre de livreurs"));
        nbPersonPanel.add(new JButton());

        JPanel startStimePanel = new JPanel();
        startStimePanel.setLayout(new GridLayout(1,5));
        startStimePanel.add(new JLabel("Heure de début"));
        startStimePanel.add(new TextField());
        startStimePanel.add(new JLabel("h"));
        startStimePanel.add(new TextField());
        startStimePanel.add(new JLabel("min"));



        toolPanel.add(nbPersonPanel);
        toolPanel.add(startStimePanel);
        this.add(mapPanel,BorderLayout.CENTER);
        this.add(toolPanel,BorderLayout.EAST);

    }
}
