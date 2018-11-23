package vue;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainVue extends JFrame {
    private JMenuBar menuBar;
    private MapVue mapPanel;

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
        mapPanel.add(new JButton("blblb"));
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
