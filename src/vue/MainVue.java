package vue;

import modele.Noeud;
import modele.Plan;
import modele.Troncon;

import controler.Controler;
import modele.Plan;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainVue extends JFrame {

    // Intitules des boutons de la fenetre
    protected final static String CHARGER_PLAN = "Charger un plan";
    protected static final String CHARGER_LIVRAISON = "Charger les livraisons";

    private EcouteurDeBoutons ecouteurDeBoutons;
    private JMenuBar menuBar;
    private MapVue mapPanel;

    private Controler controler;

    public MainVue(){

        super("Application");
        controler = new Controler(mapPanel.getPlan());

        menuBar = new JMenuBar();

        ecouteurDeBoutons = new EcouteurDeBoutons(controler);
        JMenuItem chargerPlanXML = new JMenuItem(CHARGER_PLAN);
        JMenuItem chargerLivraisonXML = new JMenuItem(CHARGER_LIVRAISON);
        chargerPlanXML.addActionListener(ecouteurDeBoutons);
        chargerLivraisonXML.addActionListener(ecouteurDeBoutons);

        JMenu fileMenu = new JMenu("Fichier");
        fileMenu.add(chargerPlanXML);
        fileMenu.add(chargerLivraisonXML);
        menuBar.add(fileMenu);

        this.setJMenuBar(menuBar);

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
        nbPersonPanel.setLayout(new FlowLayout());
        nbPersonPanel.add(new JLabel("Nombre de livreurs"));

        JComboBox nbLivreurs = new JComboBox();
        for(int i = 1; i < 15; i++)
            nbLivreurs.addItem(""+i);
        nbLivreurs.setMaximumSize( nbLivreurs.getPreferredSize() );
        nbPersonPanel.add(nbLivreurs);

        JPanel startStimePanel = new JPanel();
        startStimePanel.setLayout(new FlowLayout());

        JComboBox heureDebut = new JComboBox();
        for(int i = 1; i < 25; i++)
            heureDebut.addItem(""+i);
        heureDebut.setMaximumSize( heureDebut.getPreferredSize() );
        startStimePanel.add(new JLabel("Heure de début"));
        startStimePanel.add(heureDebut);

        JComboBox minuteDebut = new JComboBox();
        for(int i = 1; i < 61; i++)
            minuteDebut.addItem(""+i);
        minuteDebut.setMaximumSize( minuteDebut.getPreferredSize() );
        startStimePanel.add(new JLabel("h"));
        startStimePanel.add(minuteDebut);
        startStimePanel.add(new JLabel("min"));



        toolPanel.add(nbPersonPanel);
        toolPanel.add(startStimePanel);
        this.add(mapPanel,BorderLayout.CENTER);
        this.add(toolPanel,BorderLayout.EAST);

        this.setSize(1200,900);
        this.setVisible(true);

    }
}
