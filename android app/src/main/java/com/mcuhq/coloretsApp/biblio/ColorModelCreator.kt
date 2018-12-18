package com.mcuhq.coloretsApp.biblio

/**
 * Created by trank on 27/11/2018.
 */
import android.util.Log
import org.json.JSONArray

class ColorModelCreator {



    //classe intermedia entre rebre el objecte del núvol fins a salvar, en un un format array, els colors
/*
Classe que transforma l’objecte JSON rebut del núvol (que conté tots els elements de la base de dades) en un Array de colormODELS. (kotlin)
 */
    public fun createColors(data: String): ArrayList<colormODEL> {

        val list1= ArrayList<colormODEL>();
        val JSONobjects = JSONArray(data)
        val JSONobjectsLenght = JSONobjects.length()
        Log.e("APIJSON: ", data)
        Log.e("APIJSON Lenght", JSONobjectsLenght.toString())


        for (jsonObj in 0 until JSONobjectsLenght) {
val colorm: colormODEL;
val r: Int;
            val g: Int;
            val c: Int;
            val b: Int;



            r=JSONobjects.getJSONObject(jsonObj)["vermell"].toString().toInt()

            g=JSONobjects.getJSONObject(jsonObj)["verd"].toString().toInt();
            b=JSONobjects.getJSONObject(jsonObj)["blau"].toString().toInt();
            c=JSONobjects.getJSONObject(jsonObj)["clear"].toString().toInt();

            colorm= colormODEL(r, g, b, c);


             list1.add(colorm);

        }

    return list1
    }
}
