package edu.cs.fsu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class main extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}
	
	public void createNewAccountClick(View target)
	{	
		Intent i = new Intent(this, edu.cs.fsu.createAccount.class);
		startActivity(i);
	}
	
	public void signInClick(View target)
	{	
		Intent i = new Intent(this, edu.cs.fsu.signIn.class);
		startActivity(i);
	}
}