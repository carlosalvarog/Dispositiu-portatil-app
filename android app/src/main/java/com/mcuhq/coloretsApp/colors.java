package com.mcuhq.coloretsApp;

import android.util.Log;

/**
 * Created by trank on 10/11/2018.
 */


/*
Classe-objecte que transforma l’string rebut de la comunicació Bluetooth i que obté les components RGBC, guardant-les en variables. (Kotlin)
*/
public class colors {
public int b,r,g,c;
    public float c1,r1,g1,b1;
    public void getcols(String str) {
String sub;
int indxofp;


try {
    //tractament strings
    indxofp = str.indexOf(')');
    String[] strl;
    sub = str.substring(8, indxofp);
    strl = sub.split(",");

    //si té 4 components (r,g,b,c)
    if(strl.length==4) {



        c1 = Float.parseFloat(strl[0]);
        if (c1 < 0) {
            c = Math.round(c1);
            c1 = c & 0x0000ffffL;

        }

//obtenció valors en 8 bits
        r1 = Float.parseFloat(strl[1]) / c1 * 255;
        g1 = Float.parseFloat(strl[2]) / c1 * 255;
        b1 = Float.parseFloat(strl[3]) / c1 * 255;
        b = Math.round(b1);
        r = Math.round(r1);
        g = Math.round(g1);


        Log.d("Log", Integer.toString(r) + "," + Integer.toString(g) + "," + Integer.toString(b));
    }

    else {

        try {

            if(r!=0 & g!=0 & b!=0)

            {
             r=r; g=g; b=b;
            }

        }

        catch (Exception e) {
           r=0; g=0; b=0;

        }
        Log.d("log", "Bad measure");
    }



}

catch (Exception e) {
    r=0; g=0; b=0;

}


    }

}
