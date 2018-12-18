package com.mcuhq.coloretsApp.biblio;

/**
 * Created by trank on 27/11/2018.
 */


//simple classe que allotja variables del color
    /*
    Classe que es dedica a generar elements visuals dinàmicament a partir d'un Array d’objectes
    colormODEL, poblant així els ListView de la biblioteca. Cada element visual generat té associat l’arxiu item_color.xml (programat amb java)
     */

public class colormODEL  {
    private int r, g, b, c;
    public colormODEL(int r1, int g1, int b1, int c1) {
      r=r1; g=g1; b=b1; c=c1; }
    public int getr() {        return r;   }
    public int getb() {        return b;}
    public int getg() {        return g;    }
    public int getc() {        return c;    }
}