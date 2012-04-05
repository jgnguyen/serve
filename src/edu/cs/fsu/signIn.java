package edu.cs.fsu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
			EditText et_username = (EditText) findViewById(R.id.et_username);
			EditText et_password = (EditText) findViewById(R.id.et_password);
			String username = et_username.getText().toString();
			String password = et_password.getText().toString();
			
			try {
				String url = String.format("http://www.fsurugby.org/serve/request.php?user=1&username=%s&password=%s", username, SHA1Hash.SHA1(password));
				android.util.Log.d("url: ", url);
				DefaultHttpClient client = new DefaultHttpClient();
				HttpGet httpget = new HttpGet(url);
				HttpResponse response = client.execute(httpget);
				HttpEntity entity = response.getEntity();
				BufferedHttpEntity buffer = new BufferedHttpEntity(entity);
				InputStream is = buffer.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				StringBuilder sb = new StringBuilder();
				String line;
				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}
				String id = sb.toString();
				android.util.Log.d("id: ", id);
				if (id.equals("-1")) {
					Toast.makeText(this, "incorrect login", Toast.LENGTH_LONG).show();
				} else {
			    	Intent i = new Intent(this,edu.cs.fsu.sessionPicker.class);
			    	startActivity(i);
				}
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	
}
