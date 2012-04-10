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
import android.widget.Toast;

public class sessionExisting extends Activity{
	EditText et_sessionkey;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sessionexisting);
	}
	public void submitCodeClick(View v)
	{
		final SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(this);

		String sessionID;
		String fname = app_preferences.getString("fname", "");
		String lname = app_preferences.getString("lname", "");
		et_sessionkey = (EditText) findViewById(R.id.et_sessionkey);

		sessionID = et_sessionkey.getText().toString();

		String url = String.format("http://www.fsurugby.org/serve/request.php?add_attendee=1&sessionID=%s&fname=%s&lname=%s", sessionID, fname, lname);
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
			Log.e("JoiningSession","Failed to join session");
		}
		else {
			Toast.makeText(getApplicationContext(), "You have successfully joined session "+sessionID, Toast.LENGTH_SHORT);
			
			Intent i = new Intent(this,edu.cs.fsu.sessionResults.class);
			startActivity(i);
		}
	}
}
