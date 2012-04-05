package edu.cs.fsu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class sessionNew extends Activity{
	
	
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sessionnew);
	}
	
    public void uploadDocClick(View v)
    {
    	Intent i = new Intent(this,edu.cs.fsu.sessionResults.class);
    	//add information to the intent
    	startActivity(i);
    }
    public void createFormClick(View v)
    {
    	Intent i = new Intent(this,edu.cs.fsu.sessionResults.class);
    	//add information to the intent
    	startActivity(i);
    	
    }
    public void attendenceCheckClick(View v)
    {
    	Intent i = new Intent(this,edu.cs.fsu.sessionResults.class);
    	//add information to the intent
    	startActivity(i);
    	
    }

}
