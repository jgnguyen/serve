package edu.cs.fsu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class signIn extends Activity{
	
	EditText username;
	EditText password;
	    /** Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.signin);
	    }
	    public void signInClick(View target)
	    {
	    	// getting a handle on the username and password
	    	username = (EditText) findViewById(R.id.et_username);
	    	password = (EditText) findViewById(R.id.et_password);
	    	
	    	// Do some DB checking and call another intent if they check out

	    
	    	Intent i = new Intent(this,edu.cs.fsu.sessionPicker.class);
	    	startActivity(i);
	    }
	
}
