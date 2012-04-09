package edu.cs.fsu;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class sessionNew extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sessionnew);
    
        String fname, lname, sessName, sID;	
        final SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(this);
        
        EditText editText_sessionName, editText_sessionID;
        
        editText_sessionName = (EditText)findViewById(R.id.editText_sessionName);
        editText_sessionID = (EditText)findViewById(R.id.editText_sessionID);
        
        fname = app_preferences.getString("fname", "");
        lname = app_preferences.getString("lname", "");
        sessName = editText_sessionName.getText().toString();
        sID = editText_sessionID.getText().toString();
        
        String url = String.format("http://www.fsurugby.org/serve/request.php?new_session=1&sessionID=%s&sessionName=%s&fname=%s&lname=%s", sID, sessName, fname, lname);
        String result = "";
		try {
			result = serveUtilities.getStringFromUrl(url);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		if (!result.equals("good")) {
			Log.e("StartingSession","Failed to create session");
		}
	}
	
    public void uploadDocClick(View v)
    {
    	Intent i = new Intent(this,edu.cs.fsu.sessionResults.class);
    	//add information to the intent
    	startActivity(i);
    }
    
    public void createFormClick(View v)
    {
    	Intent i = new Intent(this,edu.cs.fsu.sessionResults.class);
    	//add information to the intent
    	startActivity(i);
    }
    
    public void attendenceCheckClick(View v)
    {
    	Intent i = new Intent(this,edu.cs.fsu.sessionResults.class);
    	//add information to the intent
    	startActivity(i);
    }
}
