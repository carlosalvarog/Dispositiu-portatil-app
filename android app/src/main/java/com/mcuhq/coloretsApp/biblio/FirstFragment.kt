package com.mcuhq.coloretsApp.biblio

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.mcuhq.coloretsApp.R


import com.mcuhq.coloretsApp.amazonaws.mobile.api.id8a6y7xdey6.ColorsApiClient;

import com.mcuhq.coloretsApp.GlobalFile


import kotlinx.android.synthetic.main.fragment_first.view.*



/**
 * A simple [Fragment] subclass.
 */

//fragment que allotja una listView on aniran els elements visuals, adaptats pel colorAdapter. Sols afegeix els items que corresponguin
class FirstFragment : Fragment() {
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

        //agafem el view (element visual superior)
        val viewOfLayout = inflater!!.inflate(R.layout.fragment_first, container, false)






        // Construct the data source


        val arrayOfColors = ArrayList<colormODEL>()


        //el list1 ha d'estar creat previament
        arrayOfColors!= GlobalFile.list1;

// Create the adapter to convert the array to views
        val adapterC = ColorAdapter(getActivity().getBaseContext(), arrayOfColors)
// Attach the adapter to a ListView

        viewOfLayout.colorsList.adapter = adapterC




        for(i in GlobalFile.list1.orEmpty()) {
            if(i.getr()>= i.getb() && i.getr()>=i.getg()) {
                // l'adaptr afegeix items

            adapterC.add(i);}

        }


        return viewOfLayout;

    }








}