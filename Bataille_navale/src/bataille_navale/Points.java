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
public class Points {

    //Attributs
    private int x;
    private int y;

    //Constructeur
    public Points(int a, int b) {
        x = a;
        y = b;
    }

    public int GetX() {
        return x;
    }

    public int GetY() {
        return y;
    }

    public void SetX(int i) {
        x = i;
    }

    public void SetY(int i) {
        y = i;
    }

}
