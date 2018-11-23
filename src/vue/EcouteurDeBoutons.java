package vue;

import controler.Controler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class EcouteurDeBoutons implements ActionListener{

    private Controler controler;

    public EcouteurDeBoutons(Controler controleur) {
        this.controler = controleur;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Methode appelee par l'ecouteur de boutons a chaque fois qu'un bouton est clique
        // Envoi au controleur du message correspondant au bouton clique
        switch (e.getActionCommand()) {
            case MainVue.CHARGER_PLAN:
                JFileChooser choix = new JFileChooser();
                choix.setCurrentDirectory(new File("/"));
                choix.changeToParentDirectory();
                int retour = choix.showOpenDialog(null);
                if (retour == JFileChooser.APPROVE_OPTION) {
                    // un fichier a été choisi (sortie par OK)
                    // chemin absolu du fichier choisi
                    String lienPlan = choix.getSelectedFile().getAbsolutePath();
                    controler.chargerPlan(lienPlan);
                }
            case MainVue.CHARGER_LIVRAISON: controler.chargerLivraison(); break;
        }
    }
}
