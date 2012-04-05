package edu.cs.fsu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class sessionResults extends Activity {
	
	
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sessionresults);
	}
	public void endSessionClick(View v)
	{
		Intent i = new Intent(this,edu.cs.fsu.sessionPicker.class);
		startActivity(i);
	}
	public void emailSessionClick(View v)
	{
		
		
	}
	public void saveSessionClick(View v)
	{
		
		
	}

}
