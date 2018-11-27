package vue;

import controler.EcouteurDeBoutons;

import controler.Controler;
import controler.EcouteurDeComposant;
import controler.EcouteurDeSouris;
import modele.Noeud;

import javax.swing.*;
import java.awt.*;

public class MainVue extends JFrame {

    // Intitules des boutons de la fenetre
    public final static String CHARGER_PLAN = "Charger un plan";
    public static final String CHARGER_LIVRAISON = "Charger les livraisons";

    private EcouteurDeBoutons ecouteurDeBoutons;
    private EcouteurDeSouris ecouteurDeSouris;
    private EcouteurDeComposant ecouteurDeComposant;
    private JMenuBar menuBar;
    private MapVue mapPanel;
    private JLabel XPosition;
    private JLabel YPosition;
    private JLabel selectedNode;

    private Controler controler;

    public MainVue(){

        super("Application");

        // Init map et controleur
        mapPanel = new MapVue();
        controler = new Controler(this);
        mapPanel.setControler(controler);

        // Création de la menubar
        menuBar = new JMenuBar();

        // Crétion des listener
        ecouteurDeBoutons = new EcouteurDeBoutons(controler);
        ecouteurDeSouris = new EcouteurDeSouris(controler);
        ecouteurDeComposant = new EcouteurDeComposant(controler);
        mapPanel.addMouseListener(ecouteurDeSouris);
        mapPanel.addMouseMotionListener(ecouteurDeSouris);
        mapPanel.addComponentListener(ecouteurDeComposant);


        //Poptlation de la menubar
        JMenuItem chargerPlanXML = new JMenuItem(CHARGER_PLAN);
        JMenuItem chargerLivraisonXML = new JMenuItem(CHARGER_LIVRAISON);
        chargerPlanXML.addActionListener(ecouteurDeBoutons);
        chargerLivraisonXML.addActionListener(ecouteurDeBoutons);

        JMenu fileMenu = new JMenu("Fichier");
        fileMenu.add(chargerPlanXML);
        fileMenu.add(chargerLivraisonXML);
        menuBar.add(fileMenu);

        this.setJMenuBar(menuBar);


        // Layout de la fenetre
        BorderLayout mainLayout = new BorderLayout();
        this.setLayout(mainLayout);

        mapPanel.setBackground(Color.BLUE);

        // Crétion toolPanel
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

        // Création mousePosition Panel
        JPanel mousePositionPanel = new JPanel(new FlowLayout());
        mousePositionPanel.add(new JLabel("X:"));
        XPosition = new JLabel();
        mousePositionPanel.add(XPosition);
        mousePositionPanel.add(new JLabel("Y:"));
        YPosition = new JLabel();
        mousePositionPanel.add(YPosition);
        mousePositionPanel.add(new JLabel("Selected node : "));
        selectedNode = new JLabel();
        mousePositionPanel.add(selectedNode);


        // Placement des panels sur la fenetre
        toolPanel.add(nbPersonPanel);
        toolPanel.add(startStimePanel);
        this.add(mousePositionPanel,BorderLayout.SOUTH);
        this.add(mapPanel,BorderLayout.CENTER);
        this.add(toolPanel,BorderLayout.EAST);

        this.setSize(1200,900);
        this.setVisible(true);

    }

    public MapVue getMapPanel() {
        return mapPanel;
    }

    public void updateMousePosition(Point point) {
        XPosition.setText(""+point.x);
        YPosition.setText(""+point.y);
        mapPanel.onMouseMove(point);
    }

    public void setSelectedNode(Noeud n)
    {
        selectedNode.setText(n.toString());
    }

    public void resizeMap() {
        mapPanel.loadPlan(controler.getPlan());
    }
}
