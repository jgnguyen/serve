package edu.cs.fsu;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class sessionResults extends Activity {
	ListView results;
	String nameList;
	String[] values;
	String url;
	String jsonresults;
	EditText sessionIDet;
	private JSONArray jObject;
	public String sessionID;
	public String type;
	String sendResult;
	EditText et_question1, et_question2, et_question3, et_question4, et_question5, et_question6, et_question7, et_question8, et_question9, et_question10;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(this);
		sessionID = app_preferences.getString("sessionID", "");
		type = app_preferences.getString("type", "");
		
		updateResults();
	}

	public void updateResults() {
		if (type.equals("attendance")) {
			setContentView(R.layout.sessionresults);
			url = String.format("http://www.fsurugby.org/serve/request.php?attendees=1&sessionID=%s", sessionID);
			getSessionResults();
		}
		else if (type.equals("submitSurvey")) {
			setContentView(R.layout.sessionresults);
			
//			url = String.format("http://www.fsurugby.org/serve/request.php?get_answers=1&sessionID=%s&fname=%s&lname=%s", sessionID, fname, lname);
			
		}
		else {
			setContentView(R.layout.sessionresultsurvey);

			final Button createSurvey = (Button) findViewById(R.id.createSurveyButton);

			et_question1 = (EditText) findViewById(R.id.editText_question1);
			et_question2 = (EditText) findViewById(R.id.editText_question2);
			et_question3 = (EditText) findViewById(R.id.editText_question3);
			et_question4 = (EditText) findViewById(R.id.editText_question4);
			et_question5 = (EditText) findViewById(R.id.editText_question5);
			et_question6 = (EditText) findViewById(R.id.editText_question6);
			et_question7 = (EditText) findViewById(R.id.editText_question7);
			et_question8 = (EditText) findViewById(R.id.editText_question8);
			et_question9 = (EditText) findViewById(R.id.editText_question9);
			et_question10 = (EditText) findViewById(R.id.editText_question10);
			
			final SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(this);
			final SharedPreferences.Editor editor = app_preferences.edit();

			createSurvey.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					// TODO Auto-generated method stub

					String question1 = et_question1.getText().toString();
					String question2 = et_question2.getText().toString();
					String question3 = et_question3.getText().toString();
					String question4 = et_question4.getText().toString();
					String question5 = et_question5.getText().toString();
					String question6 = et_question6.getText().toString();
					String question7 = et_question7.getText().toString();
					String question8 = et_question8.getText().toString();
					String question9 = et_question9.getText().toString();
					String question10 = et_question10.getText().toString();

					sendResult = "";

					if (!question1.equals(""))
						sendResult += question1;
					if (!question2.equals("")) {
						sendResult += ",";
						sendResult += question2;
					}
					if (!question3.equals("")) {
						sendResult += ",";
						sendResult += question3;
					}
					if (!question4.equals("")) {
						sendResult += ",";
						sendResult += question4;
					}
					if (!question5.equals("")) {
						sendResult += ",";
						sendResult += question5;
					}
					if (!question6.equals("")) {
						sendResult += ",";
						sendResult += question6;
					}
					if (!question7.equals("")) {
						sendResult += ",";
						sendResult += question7;
					}
					if (!question8.equals("")) {
						sendResult += ",";
						sendResult += question8;
					}
					if (!question9.equals("")) {
						sendResult += ",";
						sendResult += question9;
					}
					if (!question10.equals("")){ 
						sendResult += ",";
						sendResult += question10;
					}


					Log.d("SessionResult", sendResult);

					url = String.format("http://www.fsurugby.org/serve/request.php?new_survey=1&sessionID=%s&questions=%s", sessionID, sendResult);

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
						Log.d("CreatedSurvey", "Created it correctly!");
						editor.putString("type", "submitSurvey");
						editor.commit();
						updateResults();
					}  
				}
			});
		}
	}
/*
	public void getSurveryResults() {
		
	}
	*/
	
	public void getSessionResults()
	{
		try{
			Log.d("url: ", url);
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
			Log.d("nameList: ", nameList);
			Log.e("tagger", nameList);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		values = nameList.split(",");
		results = (ListView) findViewById(R.id.sessionResultsListView); 
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, values);

		results.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.sessionresultsmenu, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		Log.e("sessionResult","in option bool");
		switch (item.getItemId()) {
		case R.id.menu_item_endsession:    		
			final SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(this);
			final SharedPreferences.Editor editor = app_preferences.edit();

			editor.putString("type", "");
			editor.putString("sessionID", "");
			editor.commit();

			Intent i = new Intent(this,edu.cs.fsu.sessionPicker.class);
			startActivity(i);
		break;
		case R.id.menu_item_email:     
			break;  
		case R.id.menu_item_save: 
			break;
		case R.id.menu_item_refresh: 
			updateResults();	
			Log.e("sessionResult","refresh");
			break;
		}
		Log.e("sessionResult","out option bool");
		return true;
	}


}