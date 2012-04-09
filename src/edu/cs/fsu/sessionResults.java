package edu.cs.fsu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class sessionResults extends Activity {


	ListView results;
	String nameList;
	String[] values;
	String url;
	String sessionID;
	String jsonresults;
	EditText sessionIDet;
	private JSONArray jObject;

	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sessionresults);
        
        results = (ListView) findViewById(R.id.sessionResultsListView); 
        
        
        // this needs to sent from an intent from sessionnew.
      // sessionIDet = (EditText)findViewById(R.id.editText_sessionID);
        //sessionID = sessionIDet.getText().toString();
        sessionID = "1234";
        url = String.format("http://www.fsurugby.org/serve/request.php?attendees=1&sessionID=%s", sessionID);
        
        getSessionResults();
        
	}
	public void endSessionClick(View v)
	{
		Intent i = new Intent(this,edu.cs.fsu.sessionPicker.class);
		startActivity(i);
	}
	public void emailSessionClick(View v)
	{


	}
	public void saveSessionClick(View v)
	{


	}
	public void getSessionResults()
	{

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
        
        try {
        	nameList = jObject.getJSONObject(0).getString("attendees");
	       // String fnameId = menuObject.getString("fname");
	       // String lnameId = menuObject.getString("lname");
	        Log.e("tagger", nameList);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        values = nameList.split(",");
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
    			android.R.layout.simple_list_item_1, android.R.id.text1, values);
        
        results.setAdapter(adapter);
	}

}