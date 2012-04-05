package edu.cs.fsu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class createAccount extends Activity{

	
	EditText username;
	EditText password;
	EditText firstname;
	EditText lastname;
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createaccount);
	}
	public void submitNewAccountClick(View v)
	{
    	username = (EditText) findViewById(R.id.et_username);
    	password = (EditText) findViewById(R.id.et_password);
    	firstname = (EditText) findViewById(R.id.et_password);
    	lastname = (EditText) findViewById(R.id.et_password);
    	
    	// check the DB to see if the user already exist
    	// insert him into the database with all his info
    	
    	
    	
    	// issue intent to the session picker.
    	Intent i = new Intent(this,edu.cs.fsu.sessionPicker.class);
    	startActivity(i);
	}
}
