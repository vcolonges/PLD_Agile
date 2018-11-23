package vue;

import modele.Noeud;
import modele.Plan;
import modele.Troncon;

import javax.swing.*;
import java.awt.*;

public class MapVue extends JPanel {

    private Plan plan;

    private final static int WIDTH_DOT = 10;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        for(Noeud n : plan.getNoeuds().values())
        {
            g.drawOval((int)n.getLongitude()-WIDTH_DOT/2,(int)n.getLatitude()-WIDTH_DOT/2,WIDTH_DOT,WIDTH_DOT);
        }
        for(Troncon t : plan.getTroncons())
        {
            Noeud start = t.getOrigine();
            Noeud end = t.getDestination();
            g.drawLine((int)start.getLongitude(),(int)start.getLatitude(),(int)end.getLongitude(),(int)end.getLatitude());
        }
    }

    public void loadPlan(Plan p)
    {
        plan = p;
    }

}
