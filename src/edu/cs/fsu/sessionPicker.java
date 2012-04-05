package edu.cs.fsu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class sessionPicker extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sessionpicker);
        
    }
    public void newSessionClick(View v)
    {
    	Intent i = new Intent(this,edu.cs.fsu.sessionNew.class);
    	startActivity(i);
    
    }
    public void joinSessionClick(View v)
    {
    	
    	Intent i = new Intent(this,edu.cs.fsu.sessionExisting.class);
    	startActivity(i);
    
    }
     
}