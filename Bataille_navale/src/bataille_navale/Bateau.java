/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bataille_navale;

/**
 *
 * @author Yovli
 */
public class Bateau {

    //Attributs
    private int taille;
    private String nom;
    private boolean coule;
    private int cptImpact;
    //Associations
    private Jeu uneGrille;
    
    //Constructeur
    public Bateau(String n) {
        nom = n;
        coule = false;
        uneGrille = null;
        // on donne la taille du bateau en fonction de son nom
        if (nom.equals("torpilleur") == true) {
            taille = 2;
        }
        if (nom.equals("sous-marin") == true) {
            taille = 3;
        }
        if (nom.equals("contre-torpilleur") == true) {
            taille = 3;
        }
        if (nom.equals("croiseur") == true) {
            taille = 4;
        }
        if (nom.equals("porte-avions") == true) {
            taille = 5;
        }
        cptImpact = 0 ;
    }

    // associer mon bateau à une grille
    public void setGrille(Jeu unJeu) {
        uneGrille = unJeu;
    }

    public int getTaille() {
        return taille;
    }
    
    public String getNom() {
        return nom;
    }
    
    public int getCptImpact() {
        return cptImpact ;
    }
    public void Toucher(int i,int j) // Méthode qui est appliqué quand un bateau est touché et qui met a jour diverses informations
    {
    cptImpact+=1;
    System.out.println();
    System.out.println(uneGrille.getNom() + " : Touché !" );
    if (cptImpact==taille)
    {
        
        System.out.println();
        System.out.println(uneGrille.getNom() + " : Coulé !!");
        coule=true;
        uneGrille.DelBat(this,i,j);
        uneGrille.detruitbateau() ;
        //décrémenter le nb bateau
    }       
    }
    
}
