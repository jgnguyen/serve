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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class sessionNew extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sessionnew);
		
		Spinner s = (Spinner) findViewById(R.id.spinner_type);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.sessionTypes, android.R.layout.simple_spinner_dropdown_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		s.setAdapter(adapter);
	}
	
	public void submit(View v)
	{
		Spinner s = (Spinner) findViewById(R.id.spinner_type);
		String selected = s.getSelectedItem().toString();
		if (selected.equals("Attendance")) {
			attendenceCheckClick(v);
		}
	}

	public void attendenceCheckClick(View v)
	{

		String fname, lname, sessionName, sessionID;	
		final SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(this);

		EditText editText_sessionName, editText_sessionID;

		editText_sessionName = (EditText)findViewById(R.id.editText_sessionName);
		editText_sessionID = (EditText)findViewById(R.id.editText_sessionID);

		fname = app_preferences.getString("fname", "");
		lname = app_preferences.getString("lname", "");
		sessionName = editText_sessionName.getText().toString();
		sessionID = editText_sessionID.getText().toString();

		String url = String.format("http://www.fsurugby.org/serve/request.php?new_session=1&sessionID=%s&sessionName=%s&fname=%s&lname=%s", sessionID, sessionName, fname, lname);
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
		} else {
			Intent i = new Intent(this,edu.cs.fsu.sessionResults.class);
	    	i.putExtra("sessionID", sessionID);
	    	i.putExtra("sessionName", sessionName);
	    	startActivity(i);
		}
	}
}
