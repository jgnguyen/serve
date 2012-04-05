package edu.cs.fsu;

import edu.cs.fsu.R.id;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class sessionExisting extends Activity{
	EditText sessionkey;
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sessionexisting);
	}
	public void submitCodeClick(View v)
	{
		sessionkey = (EditText) findViewById(id.et_sessionkey);
		
		
		Intent i = new Intent(this,edu.cs.fsu.sessionForm.class);
		startActivity(i);
		
	}
}
