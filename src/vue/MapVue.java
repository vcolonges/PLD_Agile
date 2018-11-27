package vue;

import controler.Controler;
import modele.Livraison;
import modele.Noeud;
import modele.Plan;
import modele.Troncon;

import javax.swing.*;
import java.awt.*;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class MapVue extends JPanel {


    private Controler controler;
    private Plan resizePlan;
    private Queue<Noeud> hoveredNodes;

    private final static int WIDTH_DOT = 10;
    private final static int PADDING = 10;

    public MapVue(){
        hoveredNodes = new LinkedBlockingDeque<>();}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        if(resizePlan != null) {
            for (Noeud n : resizePlan.getNoeuds().values()) {
                g.fillOval((int) n.getLongitude() - WIDTH_DOT / 2, (int) n.getLatitude() - WIDTH_DOT / 2, WIDTH_DOT, WIDTH_DOT);
            }
            for (Troncon t : resizePlan.getTroncons()) {
                Noeud start = t.getOrigine();
                Noeud end = t.getDestination();
                g.drawLine((int) start.getLongitude(), (int) start.getLatitude(), (int) end.getLongitude(), (int) end.getLatitude());
            }
            for(Livraison l : resizePlan.getLivraisons()){
                g.setColor(Color.GREEN);
                g.fillOval((int)l.getNoeud().getLongitude()-WIDTH_DOT/2,(int)l.getNoeud().getLatitude()-WIDTH_DOT/2,WIDTH_DOT,WIDTH_DOT);
            }
            if(resizePlan.getEntrepot()!=null){
                g.setColor(Color.YELLOW);
                g.fillOval((int)resizePlan.getEntrepot().getNoeud().getLongitude()-WIDTH_DOT/2,(int)resizePlan.getEntrepot().getNoeud().getLatitude()-WIDTH_DOT/2,WIDTH_DOT,WIDTH_DOT);
            }

            g.setColor(Color.RED);
            while(!hoveredNodes.isEmpty())
            {
                Noeud hoveredNode = hoveredNodes.poll();
                g.fillOval((int)hoveredNode.getLongitude() - WIDTH_DOT/2,(int)hoveredNode.getLatitude()-WIDTH_DOT/2,WIDTH_DOT,WIDTH_DOT);
            }
        }
    }

    public void loadPlan(Plan p)
    {
        if(p == null) return;
        resizePlan = new Plan();
        controler.getPlan().getMaxLat();
        controler.getPlan().getMaxLong();

        int heightMap = this.getSize().height;
        int widthMap = this.getSize().width;

        double minLatPlan = controler.getPlan().getMinLat();
        double minLongPlan = controler.getPlan().getMinLong();
        double maxLatPlan = controler.getPlan().getMaxLat();
        double maxLongPlan = controler.getPlan().getMaxLong();


        for (Noeud n : controler.getPlan().getNoeuds().values()){
            double newlatitude = ((n.getLatitude()-minLatPlan)*(heightMap-2*PADDING)/(maxLatPlan-minLatPlan)) + PADDING;
            double newLongitude = (n.getLongitude()-minLongPlan)*(widthMap-2*PADDING)/(maxLongPlan-minLongPlan) + PADDING;
            this.resizePlan.addNoeud(new Noeud(n.getId(),newlatitude,newLongitude));
        }

        long originID;
        long destinationID;

        Noeud newOriginTroncon = null;
        Noeud newDestinationTroncon = null;

        for(Troncon t : controler.getPlan().getTroncons()){
            originID = t.getOrigine().getId();
            destinationID = t.getDestination().getId();

            newOriginTroncon = this.resizePlan.getNoeuds().get(originID);
            newDestinationTroncon = this.resizePlan.getNoeuds().get(destinationID);

            this.resizePlan.addTroncon(new Troncon(newOriginTroncon,newDestinationTroncon,t.getLongueur(),t.getNomRue()));

        }
        for(Livraison l : controler.getPlan().getLivraisons()){
            this.resizePlan.addLivraison(new Livraison(this.resizePlan.getNoeuds().get(l.getNoeud().getId()),l.getDuree()));
        }
        if(controler.getPlan().getEntrepot()!=null)
            this.resizePlan.setEntrepot(new Livraison(this.resizePlan.getNoeuds().get(controler.getPlan().getEntrepot().getNoeud().getId()),0));
        repaint();

    }

    public void setControler(Controler controler) {
        this.controler = controler;
    }

    public void onMouseMove(Point point) {
        if(resizePlan == null) return;


        for(Noeud n : resizePlan.getNoeuds().values())
        {
            if(point.x <= n.getLongitude()+WIDTH_DOT/2 && point.x >= n.getLongitude()-WIDTH_DOT/2)
            {
                if(point.y <= n.getLatitude()+WIDTH_DOT/2 && point.y >= n.getLatitude()-WIDTH_DOT/2)
                {
                    hoveredNodes.add(n);
                    controler.onHoverNode(n);
                }
            }
        }

        repaint();

    }
}
