package com.mcuhq.coloretsApp.biblio;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mcuhq.coloretsApp.R;
import java.util.ArrayList;

/**
 * Created by trank on 27/11/2018.
 */

//classe que es dedica a generar un element visual a partir d'una llista de models de color colorModel

public class ColorAdapter extends ArrayAdapter<colormODEL> {
    public ColorAdapter(Context context, ArrayList<colormODEL> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        colormODEL colormodel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_color, parent, false);
        }
        // Lookup view for data population
        TextView rc = (TextView) convertView.findViewById(R.id.rc);
        TextView gc = (TextView) convertView.findViewById(R.id.gc);
        TextView bc = (TextView) convertView.findViewById(R.id.bc);
        LinearLayout ly= (LinearLayout) convertView.findViewById(R.id.line);
        ly.setBackgroundColor(Color.argb(255,colormodel.getr(),colormodel.getg(),colormodel.getb()));
        // Populate the data into the template view using the data object
       rc.setText(Integer.toString(colormodel.getr()) + ",");
        gc.setText(Integer.toString(colormodel.getg())+ ",");
        bc.setText(Integer.toString(colormodel.getb())+ ")");

        // Return the complet
        // ed view to render on screen
        return convertView;
    }
}