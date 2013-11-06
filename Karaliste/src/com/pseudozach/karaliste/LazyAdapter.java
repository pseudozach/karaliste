package com.pseudozach.karaliste;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;

public class LazyAdapter extends ArrayAdapter<Mesaj>{
	
    // Create a reference to a Firebase location
    Firebase ref = new Firebase("https://pseudozach1.firebaseIO.com/");

    private Activity activity;
    private ArrayList<Mesaj> lMesaj;
    private static LayoutInflater inflater = null;

    public LazyAdapter (Activity activity, int textViewResourceId,ArrayList<Mesaj> _lMesaj) {
        super(activity, textViewResourceId, _lMesaj);
        try {
            this.activity = activity;
            this.lMesaj = _lMesaj;

            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        } catch (Exception e) {

        }
    }

    public int getCount() {
        return lMesaj.size();
    }

    public Mesaj getItem(Mesaj position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public TextView display_address;
        public TextView display_body;     
        public ImageButton mark;
        public ImageButton unmark;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        ViewHolder holder = null;
        int posit = position;
        try {
            if (convertView == null) {
                vi = inflater.inflate(R.layout.list_row, null);
                holder = new ViewHolder();

                holder.display_address = (TextView) vi.findViewById(R.id.title);
                holder.display_body = (TextView) vi.findViewById(R.id.artist);


                vi.setTag(holder);
            } else {
                holder = (ViewHolder) vi.getTag();
            }



            holder.display_address.setText(lMesaj.get(posit).address);
            holder.display_body.setText(lMesaj.get(posit).body);
            posit++;


        } catch (Exception e) {


        }
        
        
        holder.mark = (ImageButton)vi.findViewById(R.id.mark);
        holder.mark.setOnClickListener(new OnClickListener()
        {
        	  @Override
        	  public void onClick(View v)
        	   {
        	        if (position != ListView.INVALID_POSITION) {
                        // Write data to Firebase
        	        	Map<String, Object> toSet = new HashMap<String, Object>();
        	        	toSet.put("address", Mesaj.address);
        	        	toSet.put("body", Mesaj.body);
        	        	ref.setValue(toSet);
        	        }        		  
        	   }
        	});
        
        holder.unmark = (ImageButton)vi.findViewById(R.id.unmark);
        		
        		
        		
        		return vi;
    }
}