package bataille_navale;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yovli
 */
public class Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*Jeu J1=new Jeu("J1");
        Jeu J2= new Jeu("J2");
        J2.VerifCaselibre(4, 6);
        Bateau B1=new Bateau("porte-avions");
        Bateau B2 =new Bateau("croiseur");
        J2.placerManuel(B1, 3, 3, 5, 9);
        J2.placerManuel(B2, 2, 5,6, 6);
        //J2.tirManuel(3, 6);
        J2.getCase(3, 6).affiche();
        J2.tirManuel(3, 6);
        //J2.getCase(5, 6).affiche();
*/Jeu J1 = new Jeu("Ma Grille") ;
  Jeu J2 = new Jeu("Grille adverse") ;
    
    //boolean b2 = J1.placerManuel(B1,3,4,6,4);
    //boolean b1 = J1.placerManuel(B1,6,2,6,5);
    //J1.Placeralea(B1);
    Bateau B1 = new Bateau("torpilleur") ;
    Bateau B2 = new Bateau("sous-marin");
    Bateau B3 = new Bateau("contre-torpilleur");
    Bateau B4 = new Bateau("croiseur");
    Bateau B5 = new Bateau("porte-avions");
    
    Jeu J3 = new Jeu("Ma Grille") ;
    J3.placerManuel(B1,0,0,1,0) ;
    J3.placerManuel(B2, 5, 9, 7, 9);
    J3.placerManuel(B3, 4, 9, 6, 9);
    J3.placerManuel(B4, 9, 0, 9, 3);
    J3.placerManuel(B5, 3, 0, 7, 0);
    
    J1.CreationJeu();
    J2.CreationJeu();
    /*J3.tirManuel(2, 3);
    System.out.println(J3.GetmesCases()[2][3].GetBateau().getCptImpact());
    J3.tirManuel(2, 4);
    System.out.println(J3.GetmesCases()[2][3].GetBateau().getCptImpact());
    J3.tirManuel(2, 5);
    System.out.println(J3.GetmesCases()[2][3].GetBateau().getCptImpact());
    System.out.println(J2.GetmesCases()[2][3].GetBateau());
    System.out.println(J2.GetmesCases()[2][4].GetBateau());
    System.out.println(J2.GetmesCases()[2][5].GetBateau());
    
//System.ut.println(b1);
    //System.out.println(b2) ;
    System.out.println(((J1.GetmesCases())[5][2]).getSigle()) ;
    System.out.println(((J1.GetmesCases())[6][2]).getSigle()) ;
    System.out.println(((J1.GetmesCases())[7][2]).getSigle()) ;
    System.out.println(((J1.GetmesCases())[8][2]).getSigle()) ;
    System.out.println(((J1.GetmesCases())[4][4]).getSigle()) ;
    System.out.println(((J1.GetmesCases())[5][4]).getSigle()) ;
    System.out.println(((J1.GetmesCases())[6][4]).getSigle()) ;
    System.out.println(((J1.GetmesCases())[3][4]).getSigle()) ;*/
    
     J1.Partie(J2);/*
     SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                Interface il = new Interface();
                JFrame f=new JFrame();
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.getContentPane().setLayout(new BorderLayout());
                f.getContentPane().add(il,BorderLayout.CENTER);
                f.pack();
                f.setVisible(true);
                f.setSize(800,800);
                f.setTitle("Bataille Navale");
                f.setLocationRelativeTo(null);
            }
        });
*/}
    
}
