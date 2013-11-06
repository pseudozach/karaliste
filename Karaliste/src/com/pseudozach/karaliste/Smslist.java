package com.pseudozach.karaliste;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Smslist extends Activity {
	
    private ListView lv;
    
    public void onCreate(Bundle saveInstanceState) {
    	
    	 super.onCreate(saveInstanceState);
         setContentView(R.layout.main);
         lv = (ListView) findViewById(R.id.list);
         
         LazyAdapter lazyadapter;
         lazyadapter = new LazyAdapter(this, 0, fetchInbox());
         lv.setAdapter(lazyadapter);
/*
         // This is the array adapter, it takes the context of the activity as a first // parameter, the type of list view as a second parameter and your array as a third parameter
         ArrayAdapter<String> arrayAdapter =      
         new ArrayAdapter<String>(this,R.layout.list_row, fetchInbox());
         lv.setAdapter(arrayAdapter); 
*/
         
         // Click event for single list row
         lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				
			}
  

         });
    }	
	
	
	
	
    public ArrayList<Mesaj> fetchInbox()
    {
        ArrayList<Mesaj> sms = new ArrayList<Mesaj>();
 
        Uri uriSms = Uri.parse("content://sms/inbox");
        Cursor cursor = getContentResolver().query(uriSms, new String[]{"_id", "address", "date", "body"},null,null,null);
 
        cursor.moveToFirst();
        cursor.moveToPrevious();
        while  (cursor.moveToNext())
        {
               String address = cursor.getString(1);
               String body = cursor.getString(3);
               Mesaj msj = new Mesaj();
               //System.out.println("======&gt; Mobile number =&gt; "+address);
               //System.out.println("=====&gt; SMS Text =&gt; "+body);
               msj.setAddress(address);
               msj.setBody(body);
               
               sms.add(msj);
        }
        return sms;
 
    }
    
}

