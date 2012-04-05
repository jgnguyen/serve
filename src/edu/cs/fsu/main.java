package edu.cs.fsu;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.util.Log;
import edu.cs.fsu.SHA1Hash;

public class main extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		try {
			android.util.Log.d("sha1: ", SHA1Hash.SHA1("noroot"));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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