/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bataille_navale;

import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Yovli
 */
public class Interface extends Canvas implements ActionListener, ItemListener, MouseListener {

    //Attributs
    private Jeu J1;
    private Jeu J2;
    private boolean jeuEnCours;
    private Points memoire;
    private int compteurBateaux;
    private Dimension echelle;

    //Constructeur
    public Interface() {
        addMouseListener(this);
        jeuEnCours = false;
        memoire = new Points(0, 0);
        compteurBateaux = 0;
        J1 = new Jeu("Ma Grille");
        J2 = new Jeu("Grille Adverse");
       // J1.CreationJeu();
        J2.CreationJeu();
        echelle = new Dimension(10, 10);
    }

    public void choisirEchelle() { //on definit l'echelle de notre fenetre 
        echelle.width = getWidth() / ((J1.GetmesCases()[0].length+3)*2);
        echelle.height = getHeight() / ((J1.GetmesCases().length+3)*2);
    }
    

    

    public void paint(Graphics g) {
        choisirEchelle();
        //Permet à chaque case de se dessiner
        for (int h = 0; h < J1.GetmesCases().length; h++) {
            for (int l = 0; l < J1.GetmesCases()[h].length; l++) {
                J1.GetmesCases()[l][h].dessineToi(this, g, echelle, h, l);
                J2.GetmesCases()[l][h].dessineToi2(this, g, echelle, h, l+12);
                
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("hoho");
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {    // action a effectuer lorsque l'on effectue un clic
        if (!jeuEnCours) {
            int x = (int) (e.getPoint().x / echelle.getHeight());
            int y = (int) (e.getPoint().y / echelle.getWidth());
            memoire.SetX(x);
            memoire.SetY(y);
        } else {
            int x = (int) (e.getPoint().x / echelle.getHeight());
            int y = (int) (e.getPoint().y / echelle.getWidth());
            J2.tirManuel(x, y);
            J1.tirOrdi(0);
            this.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) { //actions a effectuer lorsque l'on relâche un clic
        if (!jeuEnCours) {
            if (compteurBateaux == 0) { //gerer le placement des bateaux via l'interface graphique
                J1.placerManuel(new Bateau("torpilleur"), memoire.GetX(), memoire.GetY(), (int) (e.getPoint().x / echelle.getHeight()), (int) (e.getPoint().y / echelle.getWidth()));
                System.out.println("hey");
            }

            else if (compteurBateaux == 1) {
                J1.placerManuel(new Bateau("sous-marin"), memoire.GetX(), memoire.GetY(), (int) (e.getPoint().x / echelle.getHeight()), (int) (e.getPoint().y / echelle.getWidth()));
            }
            else if (compteurBateaux == 2) {
                J1.placerManuel(new Bateau("contre-torpilleur"), memoire.GetX(), memoire.GetY(), (int) (e.getPoint().x / echelle.getHeight()), (int) (e.getPoint().y / echelle.getWidth()));
            }
            else if (compteurBateaux == 3) {
                J1.placerManuel(new Bateau("croiseur"), memoire.GetX(), memoire.GetY(), (int) (e.getPoint().x / echelle.getHeight()), (int) (e.getPoint().y / echelle.getWidth()));
            }
            else if (compteurBateaux == 4) {
                J1.placerManuel(new Bateau("porte-avions"), memoire.GetX(), memoire.GetY(), (int) (e.getPoint().x / echelle.getHeight()), (int) (e.getPoint().y / echelle.getWidth()));
            }
            compteurBateaux += 1;

            if (compteurBateaux > 4) {
                jeuEnCours = true;
            }
            this.repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
