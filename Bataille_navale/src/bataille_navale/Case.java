/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bataille_navale;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author Yovli
 */
public class Case {

    //Attributs
    private char sigle;
    
    //Associations
    private Jeu unJeu;
    private Bateau unBateau;
    
    //Constructeur
    public Case() {
        unJeu = null;
        unBateau = null;
        sigle = '-';
    }

    //Lire la case pour savoir si il y a un bateau
    public boolean VerifCase() {
        if (sigle == '-') return true;
        return false;
    }

    //Rajouter un Bateau dans la case
    public void addBateau(Bateau b) {
        unBateau = b;
        sigle = 'B';

    }

    //Afficher l etat de la case
    public void affiche() {
        System.out.println(sigle);
    }

    //Changer le sigle lorsque l'on touche un bateau
    public void toucheCase() {
        sigle = 'O';
    }

    //Changer le sigle lorsque l'on a ne touche pas de bateau
    public void touchePasCase() {
        sigle = 'X';
    }

    //Lire le sigle de la case
    public char getSigle() {
        return sigle;
    }
    public Bateau GetBateau(){
        return unBateau;  
    }
public void dessineToi(Interface i1, Graphics g, Dimension echelle, int h, int l) { // fonction qui permet à chaque case de se dessiner sur l'interface graphique
       // l += 1;
       // h += 1;
        g.drawRect(l * echelle.width, h * echelle.height, echelle.width, echelle.height); //dessin du contour
        Font font = new Font("Courier", Font.BOLD, 30);
        g.setFont(font);
        if (sigle == 'B' ) {                                               //écriture du bon sigle
            g.drawString("B", l * echelle.width, h * echelle.height);
        }
        if (sigle == 'O') {
            g.drawString("O", l * echelle.width, h * echelle.height);
        }
        if (sigle == 'X') {
            g.drawString("X", l * echelle.width, h * echelle.height);
        }
        else{
        }
    }
    
        public void dessineToi2(Interface i1, Graphics g, Dimension echelle, int h, int l) { // fonction qui permet à chaque case de se dessiner sur l'interface graphique
       // l += 1;
       // h += 1;
        g.drawRect(l * echelle.width, h * echelle.height, echelle.width, echelle.height); //dessin du contour
        Font font = new Font("Courier", Font.BOLD, 30);
        g.setFont(font);
        //if (sigle == 'B' ) {                                               //écriture du bon sigle
        //    g.drawString("B", l * echelle.width, h * echelle.height);
       // }
        if (sigle == 'O') {
            g.drawString("O", l * echelle.width, h * echelle.height);
        }
        if (sigle == 'X') {
            g.drawString("X", l * echelle.width, h * echelle.height);
        }
        else{
        }
    }
}
