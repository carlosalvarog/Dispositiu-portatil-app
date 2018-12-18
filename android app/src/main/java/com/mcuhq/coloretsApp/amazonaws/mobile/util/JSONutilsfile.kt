package com.mcuhq.coloretsApp.amazonaws.mobile.util

/**
 * Created by trank on 28/11/2018.
 */

import android.util.Log
import com.mcuhq.coloretsApp.colors
import org.json.JSONObject
import org.json.JSONException


class JSONUtilsFile {

    fun fillCommand(color: colors) : String {
        val contactsObj = JSONObject()
        var stringProducts = ""

        try {

            Log.e("String products", stringProducts)

            contactsObj.put("vermell",color.r.toString())
            contactsObj.put("verd", color.g.toString())
            contactsObj.put("blau", color.b.toString())
            contactsObj.put("clear", color.c1.toString())



        } catch (e: JSONException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }

        val jsonStr = contactsObj.toString()
        Log.e("CONTACTS", jsonStr) // adds only last array to json object

        return jsonStr
    }
}