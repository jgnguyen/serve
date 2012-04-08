package edu.cs.fsu;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class signIn extends Activity{

	EditText username;
	EditText password;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signin);
	}
	public void signInClick(View target) throws ClientProtocolException, JSONException, IOException, NoSuchAlgorithmException
	{
		EditText et_username = (EditText) findViewById(R.id.et_username);
		EditText et_password = (EditText) findViewById(R.id.et_password);
		String username = et_username.getText().toString();
		String password = et_password.getText().toString();
		String url = String.format("http://www.fsurugby.org/serve/request.php?login=1&username=%s&password=%s", username, serveUtilities.SHA1(password));
		
		JSONArray json = new JSONArray(serveUtilities.getStringFromUrl(url));
		String id = "";
		if (json.length() == 1)
			id = json.getJSONObject(0).getString("id");
		
		if (id.isEmpty()) {
			Toast.makeText(this, "Incorrect Login", Toast.LENGTH_LONG).show();
		} else {
			Intent i = new Intent(this,edu.cs.fsu.sessionPicker.class);
			startActivity(i);
		}
	}
}
