package com.mcuhq.coloretsApp.biblio

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.mcuhq.coloretsApp.GlobalFile
import com.mcuhq.coloretsApp.R
import com.mcuhq.coloretsApp.amazonaws.mobile.api.id8a6y7xdey6.ColorsApiClient
import kotlinx.android.synthetic.main.fragment_first.view.*


/**
 * A simple [Fragment] subclass.
 */
class SecondFragment : Fragment() {
    private var apiClient: ColorsApiClient? = null

    private var listview: ListView? = null
    private lateinit var viewOfLayout: View

    private var arrayl: ArrayList<colormODEL>?=null;

    companion object {
        private val TAG: String = this::class.java.name
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        arrayl= ArrayList<colormODEL>();
        val viewOfLayout = inflater!!.inflate(R.layout.fragment_first, container, false)


// 3

        // val adapter = ArrayAdapter(getActivity().getBaseContext(), R.layout.fragment_first, listItems)







        //  apiGetCallWithPath("colors");


        // Construct the data source


        val arrayOfColors = ArrayList<colormODEL>()

        arrayOfColors!= GlobalFile.list1;
        //  Log.d("wow2",GlobalFile.list1.toString());
// Create the adapter to convert the array to views
        val adapterC = ColorAdapter(getActivity().getBaseContext(), arrayOfColors)
// Attach the adapter to a ListView

        viewOfLayout.colorsList.adapter = adapterC
//Log.d("wow2",arrayOfColors.indices.toString())
        // val newUser = colormODEL("ok")
        Log.d("wow_OK", GlobalFile.list1[0].getb().toString());
        Log.d("wow_OK", GlobalFile.list1[1].getb().toString());

        for(i in GlobalFile.list1.orEmpty()) {
            Log.d("wpw4",i.getb().toString())

            if(i.getg()>= i.getr() && i.getg()>=i.getb()) {
                adapterC.add(i);}

        }


        return viewOfLayout;

    }








}