package edu.cs.fsu;

import org.json.JSONArray;
import org.json.JSONException;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class sessionResults extends Activity {
	
	
	ListView results;
	String nameList;
	String[] values;
	String url;
	
	String sessionID;
	String sessionName;
	String sessionType;
	
	
	String jsonresults;
	
	TextView id;
	TextView name;
	TextView type;
	
	private JSONArray jObject;
	
	
	Thread updateData;
	Handler mHandler;
	boolean updateDataThreadBool;

	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("tagger","OnCreate");
        //  set content view
        setContentView(R.layout.sessionresults);
        
        // set up Handler for automatic data refreshes
        mHandler = new Handler();
        updateDataThreadBool = true;
       
        // get handles
        results = (ListView) findViewById(R.id.sessionResultsListView); 
        name = (TextView) findViewById(R.id.tv_results_sessionName);
        id = (TextView) findViewById(R.id.tv_results_sessionID);
        type = (TextView) findViewById(R.id.tv_results_sessionType);
        
        Log.e("tagger","Got Handles");
       
        // get extras from intent
        sessionID = getIntent().getStringExtra("sessionID");
        sessionName = getIntent().getStringExtra("sessionName");
        sessionType = getIntent().getStringExtra("sessionType");
        
        Log.e("tagger","Got Intent Extras");
        
        name.setText(sessionName);
        id.setText(sessionID);
        type.setText(sessionType);
        
        Log.e("tagger","set Text");
        // Get Data via json
        // update values array
        // set array adapter
        
        Log.e("tagger","get first session results");
        getSessionResults();
        Log.e("tagger","going into updatedatathread");
        //updateDataThread();
        Log.e("tagger","leaving updatedatathread");

        
	}
	public void endSessionClick(View v)
	{
		updateDataThreadBool = false;
		Intent i = new Intent(this,edu.cs.fsu.sessionPicker.class);
		startActivity(i);
	}
	public void emailSessionClick(View v)
	{
		updateDataThreadBool = false;
		
	}
	public void saveSessionClick(View v)
	{
		updateDataThreadBool = false;
		
	}
	public void getSessionResults()
	{

		url = String.format("http://www.fsurugby.org/serve/request.php?attendees=1&sessionID=%s", sessionID);
		
        try{
        	jsonresults = serveUtilities.getStringFromUrl(url);
        }
        catch(Exception e)
        {
        	Log.e("tagger","fauked ti getstrubfrom url");
        	
        }
        try {
			jObject = new JSONArray(jsonresults);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
        nameList = "Waiting for results... (empty)";
        try {
        	nameList = jObject.getJSONObject(0).getString("attendees");
	       // String fnameId = menuObject.getString("fname");
	       // String lnameId = menuObject.getString("lname");
	        Log.e("tagger", nameList);
	
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Log.e("tagger",nameList.toString());
		if(! nameList.isEmpty() )
		{
			Log.e("tagger","in if");
			values = nameList.split(",");
		}      
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
    			android.R.layout.simple_list_item_1, android.R.id.text1, values);
        
        results.setAdapter(adapter);
	}
	public void updateDataThread()
	{
		
		Log.e("tagger","Thread Started");
		 new Thread(new Runnable() {
		        public void run() {
		            // TODO Auto-generated method stub
		            while (updateDataThreadBool) {
		                try {
		                    Thread.sleep(10000);
		                    mHandler.post(new Runnable() {

		                        public void run() {
		                        	
		                        	
		                        		getSessionResults();
		                        	}
		                        
		                    });
		                } catch (Exception e) {
		                    // TODO: handle exception
		                }
		                
		            }
		            
		        }
		                
		    }).start();
		 Log.e("tagger","Thread Finished");
	}
}


