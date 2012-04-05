package edu.cs.fsu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class sessionForm extends Activity{
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sessionform);
	}
	public void submitAttendenceClick(View v)
	{
		
		
	}
	public void downloadFileClick(View v)
	{
		
		
	}
	public void exitSession(View v)
	{
		Intent i = new Intent(this,edu.cs.fsu.sessionPicker.class);
		startActivity(i);
		
	}

}
