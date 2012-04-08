package edu.cs.fsu;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.client.ClientProtocolException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class createAccount extends Activity{

	
	EditText username;
	EditText password;
	EditText firstname;
	EditText lastname;
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createaccount);
	}
	public void submitNewAccountClick(View v) throws NoSuchAlgorithmException, ClientProtocolException, IOException
	{
    	username = (EditText) findViewById(R.id.et_username);
    	password = (EditText) findViewById(R.id.et_password);
    	firstname = (EditText) findViewById(R.id.et_password);
    	lastname = (EditText) findViewById(R.id.et_password);
    	
		String url = String.format("http://www.fsurugby.org/serve/request.php?new_user=1&username=%s&password=%s&fname=&s&lname=%s", username.getText().toString(), serveUtilities.SHA1(password.getText().toString()), firstname.getText().toString(), lastname.getText().toString());
		String result = serveUtilities.getJsonFromUrl(url);
		
		if (result.equals("good")) {
	    	// issue intent to the session picker.
	    	Intent i = new Intent(this,edu.cs.fsu.sessionPicker.class);
	    	startActivity(i);
		} else {
			Toast.makeText(this, "Could not create account", Toast.LENGTH_LONG).show();
		}
	}
}
