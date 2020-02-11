/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bataille_navale;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Yovli
 */
public class Jeu {

    //Attributs
    private String nom;
    private int nombreBateaux = 5;
    private ArrayList<int[]> ppotentiels; // Liste des coordonnées des points sur lesquels il est intéréssant de tirer

    //Associations
    private Case[][] mesCases;
    private ArrayList<Bateau> mesBateaux;

    //Constructeur
    public Jeu(String n) {
        nom = n;
        mesCases = new Case[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                mesCases[i][j] = new Case();
            }
        }

        mesBateaux = new ArrayList();
        ppotentiels = new ArrayList<>();

    }

    //Tester si la case est libre
    public boolean VerifCaselibre(int l, int c) { // Verifie si une case est libre 
        return mesCases[l][c].VerifCase();
    }

    public Case getCase(int l, int c) {
        return mesCases[l][c];
    }

    public String getNom() {
        return nom;
    }

    public int getNombreBateaux() {
        return nombreBateaux;
    }

    public boolean placerManuel(Bateau bat, int x1, int y1, int x2, int y2) { // Place manuellement un bateau avec les coordonnées des 2 points a l'extrémité
        boolean b = true;
        if (x1 > x2) {  // 4 cas Differents pour les coordonnées et on supose que l'utilisateur renvoie au moins une des 2 coordonnées constante
            for (int k = 0; k < (bat.getTaille()); k++) {
                b = b && mesCases[x2 + k][y2].VerifCase();
            }
            if (b) {
                {
                    bat.setGrille(this);
                    mesBateaux.add(bat);
                    for (int k = 0; k < (bat.getTaille()); k++) {
                        (mesCases[x2 + k][y2]).addBateau(bat);
                    }
                }
            }
        } else if (x2 > x1) {
            for (int k = 0; k < (bat.getTaille()); k++) {
                b = b && mesCases[x1 + k][y2].VerifCase();
            }
            if (b) {
                {
                    bat.setGrille(this);
                    mesBateaux.add(bat);
                    for (int k = 0; k < (bat.getTaille()); k++) {
                        (mesCases[x1 + k][y2]).addBateau(bat);
                    }
                }
            }
        } else if (y1 > y2) {
            for (int k = 0; k < (bat.getTaille()); k++) {
                b = b && ((mesCases[x1][y2 + k]).VerifCase());
            }
            if (b) {
                {
                    bat.setGrille(this);
                    mesBateaux.add(bat);
                    for (int k = 0; k < (bat.getTaille()); k++) {
                        (mesCases[x1][y2 + k]).addBateau(bat);
                    }
                }
            }
        } else {
            for (int k = 0; k < (bat.getTaille()); k++) {
                b = b && mesCases[x2][y1 + k].VerifCase();
            }
            if (b) {
                {
                    bat.setGrille(this);
                    mesBateaux.add(bat);
                    for (int k = 0; k < (bat.getTaille()); k++) {
                        (mesCases[x2][y1 + k]).addBateau(bat);
                    }
                }
            }
        }

        return b;
    }

    public void Placeralea(Bateau bat) { // Place aléatoirement un bateau sur la grille
        boolean b = true;
        int x1;
        int x2;
        int y1;
        while (b) {  // Boucle tant qu'on n'a pas trouvé un emplacement valide + Attention : boucle qui pourrait etre infini dans certaines applications mais bonne complexité relativement a la méthode qui n'utiliserai pas cette fonction
            x1 = (int) (Math.random() * 10);//coordonnées de case de départ
            y1 = (int) (Math.random() * 10);//coordonnées de case de départ
            x2 = (int) (Math.random() * 2); // permet de choisir la configuration : Soit vers le bas , soit vers la droite    
            if (x2 == 1) {
                if (y1 + bat.getTaille() - 1 < 10) {
                    b = !(placerManuel(bat, x1, y1, x1, y1 + bat.getTaille() - 1));
                }

            } else {
                if (x1 + bat.getTaille() - 1 < 10) {
                    b = !(placerManuel(bat, x1, y1, x1 + bat.getTaille() - 1, y1));
                }
            }
        }
    }

    public void CreationJeu() {  // Methode qui place les 5 bateaux du jeu aléatoirement sur MesCases
        Bateau b1 = new Bateau("torpilleur");
        Bateau b2 = new Bateau("sous-marin");
        Bateau b3 = new Bateau("contre-torpilleur");
        Bateau b4 = new Bateau("croiseur");
        Bateau b5 = new Bateau("porte-avions");
        Placeralea(b1);
        Placeralea(b2);
        Placeralea(b3);
        Placeralea(b4);
        Placeralea(b5);

    }

    public Case[][] GetmesCases() {
        return mesCases;

    }

    //Tirer manuellement   
    public void tirManuel(int l, int c) { // Tir au coordonnées en entrée
        if (this.mesCases[l][c].getSigle() == 'B') //si il y a un bateau sur la case
        {
            int[] p = {l, c};
            this.ppotentiels.add(p);
            this.mesCases[l][c].toucheCase();         // alors on modifie le sigle de la case en mettant O
            (this.mesCases[l][c].GetBateau()).Toucher(l,c);
        } else if (this.mesCases[l][c].getSigle() != 'O') {

            this.mesCases[l][c].touchePasCase();     // sinon on met X
        }
    }

    public void detruitbateau() {
        nombreBateaux -= 1;
    }

    public void Partie(Jeu J2) {
        int comp = (int) (Math.random() * 2);
        while (nombreBateaux > 0 && J2.getNombreBateaux() > 0) {
            boolean b =true;
            int x=0; // peu élégant mais nécéssaire pour utiliser x et y hors de la boucle while;
            int y =0;
            while(b){
            System.out.println("Coordonnées en x");
            Scanner scan = new Scanner(System.in);
            x = scan.nextInt();
            System.out.println("Coordonnées en y");
            Scanner scan2 = new Scanner(System.in);
            y = scan2.nextInt();
            if (x/10 != 0 || y/10 != 0 || x<0 || y<0) 
            {System.out.println("Mauvaise coordonnées");}// Ne gere pas le cas ou l'utilisateur ne renvoie pas un entier
            else {b = false;} 
            }
            J2.tirManuel(x, y);
            //J2.tirOrdi(comp);
            this.tirOrdi(comp);
            //System.out.println(mesBateaux);
            for (int i = 0; i < 10; i++) {
                System.out.println();
                for (int j = 0; j < 10; j++) {
                    //if(((this.GetmesCases())[i][j]).getSigle()!='B')
                    System.out.print(((this.GetmesCases())[i][j]).getSigle() +" ");
                    //else{
                    //  System.out.print("- ");
                }
            }
            System.out.println();
            System.out.println();
            for (int i = 0; i < 10; i++) {
                System.out.println();
                for (int j = 0; j < 10; j++) {
                    if (((J2.GetmesCases())[i][j]).getSigle() != 'B') {
                        System.out.print(((J2.GetmesCases())[i][j]).getSigle() + " ");
                    } else {
                        System.out.print("- ");
                    }
                }
            }
        }
        System.out.println("Gamer Over");

    }

    public ArrayList<int[]> PossibdePlacement(int n) {  // Methode qui renvoie toutes les possibilités de placemement d'un bateau dans une grille deja entamé 
        ArrayList<int[]> placementpot = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 11 - n; j++) {
                boolean b = true;
                for (int k = 0; k < n; k++) {   
                    b = b && mesCases[i][j + k].VerifCase(); 
                    if (b) {
                        int[] a = {i, j, 0};   // O ou 1 correspondent a un placement verticale ou horizontale
                        placementpot.add(a);
                    }
                        
                }
            }

        }
        for (int i = 0; i < 11 - n; i++) {
            for (int j = 0; j < 10; j++) {
                boolean b = true;
                for (int k = 0; k < n; k++) {
                    b = b && mesCases[i + k][j].VerifCase();
                    if (b) {
                        int[] a = {i, j, 1};
                        placementpot.add(a);
                    }

                }
            }

        }

        return placementpot;
    }

    public void tirAleabis() { // Tir aléatoirement sur une case disponible 
        boolean b = true;
        while (b) {
            int x1;
            int y1;
            int x;
            x = (int) (Math.random() * 100);
            x1 = x / 10; //quotient de x
            y1 = x % 10; //reste de x
            if ((((this.GetmesCases())[x1][y1]).getSigle() != 'X' || ((this.GetmesCases())[x1][y1]).getSigle() != 'O' )) {
                this.tirManuel(x1, y1);
                b = false;
            }
        }

    }

// Tir Aléatoire : 2 facon différentes défini avec l'entier i égal a 0 ou 1  
    public void tirAlea(int i) {    // Probleme avec bateau a 2 élément dans un cas très partuculier 
        int k = 0; // Permet de gerer le Probleme du dessus de facon peu élégante mais fonctionnel 
        if (i == 0) {
            boolean b = true;
            while (b && k < 1000) {
                k += 1;
                int x1;
                int y1;
                int x;
                x = (int) (Math.random() * 100);
                x1 = x / 10; //quotient de x
                y1 = x % 10; //reste de x
                if ((((this.GetmesCases())[x1][y1]).getSigle() != 'X') && (((x1 + y1) % 2) == 0)) {
                    this.tirManuel(x1, y1);
                    b = false;
                }
            }
        } else {
            boolean b = true;
            while (b && k < 1000) {
                int x1;
                int y1;
                int x;
                x = (int) (Math.random() * 100);
                x1 = x / 10; //quotient de x
                y1 = x % 10; //reste de x
                if ((((this.GetmesCases())[x1][y1]).getSigle() != 'X') && (((x1 + y1) % 2) == 1)) {
                    this.tirManuel(x1, y1);
                    b = false;
                }

            }
        }
        if (k == 1000) {
            tirAleabis();
        }
    }

    public char[] caseadja(int x, int y) // Renvoie un tableau a 4 éléments , les 4 éléments sont les sigles des cases adjacentes : le 1er étant celui du haut puis les suivant selon le sens horaire
    {
        char[] M = new char[4];
        if (x == 0) {
            M[0] = 'N';
        } else {
            M[0] = (mesCases[x - 1][y]).getSigle();
        }
        if (y == 9) {
            M[1] = 'N';
        } else {
            M[1] = (mesCases[x][y + 1]).getSigle();
        }
        if (x == 9) {
            M[2] = 'N';
        } else {
            M[2] = (mesCases[x + 1][y]).getSigle();
        }
        if (y == 0) {
            M[3] = 'N';
        } else {
            M[3] = (mesCases[x][y - 1]).getSigle();
        }
        return M;

    }
    // Tir plus intelligent de l'ordinateur : Il va détruire les bateaux qu'il a deja touché une fois

    public void tirOrdi(int comp) {
        boolean b2 = true; // Permet de savoir si il existe un tir "intéréssant" a effectué
        if (!ppotentiels.isEmpty()) {
            int[] tirpripot = new int[2]; // On catégorise les tirs par secondaire ou primaire en fonction de leurs change de touche
            ArrayList<int[]> tirsecpot = new ArrayList<>();
            boolean b = false;
            for (int[] m : ppotentiels) { // On test pour chaque point 'O' encore dans ppotentiels si il existe des tirs intéréssant
                int x = m[0];
                int y = m[1];
                char[] cadj = this.caseadja(x, y);
                if ((cadj[2] == '-') || (cadj[2] == 'B')) {  // Si il y a 2 'O' adjacents , tir préférentiel : 
                    if (cadj[0] == 'O') {
                        int[] p = {x + 1, y};
                        tirpripot = p;
                        b = true;
                        break;
                    }
                    int[] n = {x + 1, y};
                    tirsecpot.add(n);
                }
                if ((cadj[3] == '-') || (cadj[3] == 'B')) {
                    if (cadj[1] == 'O') {
                        int[] p = {x, y - 1};
                        tirpripot = p;
                        b = true;
                        break;
                    }
                    int[] n = {x, y - 1};
                    tirsecpot.add(n);
                }
                if ((cadj[0] == '-') || (cadj[0] == 'B')) {
                    if (cadj[2] == 'O') {
                        int[] p = {x - 1, y};
                        tirpripot = p;
                        b = true;
                        break;
                    }
                    int[] n = {x - 1, y};
                    tirsecpot.add(n);
                }
                if ((cadj[1] == '-') || (cadj[1] == 'B')) {
                    if (cadj[3] == 'O') {
                        int[] p = {x, y + 1};
                        tirpripot = p;
                        b = true;
                        break;
                    }
                    int[] n = {x, y + 1};
                    tirsecpot.add(n);
                }

            }
            if (b) { // Si tir prioritaire
                b2 = false;
                tirManuel(tirpripot[0], tirpripot[1]);
            } else { // Si tir Secondaire
                int i;
                i = tirsecpot.size();
                if (i != 0) {
                    b2 = false;
                    int k = (int) (Math.random() * i);
                    tirManuel((tirsecpot.get(k))[0], (tirsecpot.get(k))[1]);
                }
            }
        }
        if (b2) { // Si aucun tir intéréssant
            //tirAlea(comp);
            TirordiSup(1000);
        }

    }

    // DelBat supprime un tout les points de la liste ppotentiels ainsi que le bateau lié 
    public void DelBat(Bateau bat,int i,int j) { // Un probleme (Efficacité de l'IA) si on ne considère pas que l'ordi sait quelle bateau il a coulé sur quelles cases dans de TRES rares cas 
        ArrayList<int[]> listaux = new ArrayList();
        for (int[] m : ppotentiels) {
            if (m[0]!=i && m[1]!=j) {
            
            listaux.add(m);}
        }
         
        ppotentiels = listaux;
        mesBateaux.remove(bat);

    }

    public int indmaxi(int[][] M) {  // renvoie l'indice du maximum dans une matrice 10*10
        int ind = 0;
        int maxi = 0;
        for (int k = 0; k < 100; k++) {
            if (M[k / 10][k % 10] > maxi) {
                maxi = M[k / 10][k % 10];
                ind = k;

            }
        }
        return ind;
    }

    public void TOrdisupaux(int[][] N) {  // Fonction auxiliaire a la fonction de tir intelligente 
        Jeu Jsup = new Jeu("Jeuaux");  // Creation d'une copie du jeu a l'instant présent sans les bateaux non revelés
        for (int w = 0; w < 100; w++) {
            if ((mesCases[w / 10][w % 10]).getSigle() == 'X' || (mesCases[w / 10][w % 10]).getSigle() == 'O') {
                Jsup.GetmesCases()[w / 10][w % 10].touchePasCase(); 
            }
        }
        int tour = 0;  // permet de gerer quand on ne peux pas placer les bateaux correctement car les bateaux sont mal repéré par l'ordinateur dans de très rare cas
        int compt = mesBateaux.size(); 
        while (compt != 0 && tour < 100) { 
            tour += 1;
            compt = mesBateaux.size();
            Jsup = new Jeu("Jeuaux"); // nouvelle copie tant qu'un possibilité de placements de tout les bateaux n'est pas correctes 
            for (int v = 0; v < 100; v++) {
                if ((mesCases[v / 10][v % 10]).getSigle() == 'X' || (mesCases[v / 10][v % 10]).getSigle() == 'O') {
                    Jsup.GetmesCases()[v / 10][v % 10].touchePasCase();
                }
            }
            for (Bateau bat : mesBateaux)  { // prise en compte des bateaux dans le meme ordre (pourrait etre ameliore en rendant le processus aléatoire mais nous ne sommes pas certains de l'impact)
                ArrayList<int[]> Listaux = Jsup.PossibdePlacement(bat.getTaille()); // Cherche si il y a des possibilité de placement d'un bateau : utile quand peu de cases libres et plusieurs bateaux restants : Placer un bateau a un endroit peu complétement bloquer les autres 
                if (Listaux.isEmpty()) {
                    break;
                } else {
                    int p = Listaux.size();
                    int q = (int) (Math.random() * p);
                    int[] m = Listaux.get(q);
                    if (m[2] == 0) { // placement des bateaux pris (de manière aléatoire)  
                        Bateau bataux = new Bateau(bat.getNom()); // Creation de nouveau bateaux nécéssaires pour ne pas modifier ce en place
                        Jsup.placerManuel(bataux, m[0], m[1], m[0], m[1] + bat.getTaille()); // Placement du bateau pour ne pas "croiser" certains bateaux 
                        compt -= 1;
                    } else {
                        Bateau bataux = new Bateau(bat.getNom());
                        Jsup.placerManuel(bataux, m[0], m[1], m[0] + bat.getTaille(), m[1]);
                        compt -= 1;
                    }
                }

            }

        }
        if (tour != 100) { // Si tour = 100 alors forcément il y eu un problème et un bateau a deja été touché mais n'a pas été fini ( Problème évoquer dans la synthèse ) mais ce n'est pas toujours le cas
            for (int k = 0; k < 100; k++) { // on ajoute 1 occurence a la matrice qui compte les occurences 
                if ((Jsup.GetmesCases())[k / 10][k % 10].getSigle() == 'B') {
                    N[k / 10][k % 10] += 1;
                }
            }
        }
    }
    // TirOrdisup réalise le meilleur tir en suposant que les bateaux adverses ont été placés aléatoirement 

    public void TirordiSup(int n) {  // fonction qui boule la fonction précédente n fois , pour avoir un nombre conséquent d'occurence -> Probabilité 
        int[][] N = new int[10][10];
        for (int i = 0; i < n; i++) {
            /*for (int a = 0; a < 10; a++) {
                System.out.println();
                for (int j = 0; j < 10; j++) {
                    if (((Jsup.GetmesCases())[a][j]).getSigle() != 'B') {
                        System.out.print(((Jsup.GetmesCases())[a][j]).getSigle() + " ");
                    } else {
                        System.out.print("- ");
                    }}}}
        
        //Jsup.CreationJeu();
        for (Bateau bat : this.mesBateaux) {
                String Nom = bat.getNom();
                Bateau batsup = new Bateau(Nom);
                Jsup.Placeralea(batsup);
            }  // il faudrait potentiellement incorporer un mode "rétroactif" qui supprime les issues impossibles : On pourrait faire ca en balayant la matrice 
             */

            TOrdisupaux(N);}
            for (int a = 0; a < 10; a++) {
                //System.out.println();
                for (int j = 0; j < 10; j++) {
                    //System.out.print(N[a][j] + " ");

                }
            }

            int k = indmaxi(N); // on tir sur l'endroit ou la probabilité est maximale 
            boolean b = true;
            for(int i=0;i<10;i++){
                for(int j=0;j<10;j++){
                   b= N[i][j]==0 && b;
                   
            }}
            
            if (b){
                tirAleabis(); // Si la matrice est vide : Le probleme vu au dessus -> pas de possiblité de placement donc tirAléatoire pour ne pas planter
            }
            else {tirManuel(k / 10, k % 10);}

        
    }}
