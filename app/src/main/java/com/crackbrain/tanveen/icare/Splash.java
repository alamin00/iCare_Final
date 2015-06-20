package com.crackbrain.tanveen.icare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splash extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{


		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		Thread t = new Thread()
		{
			public void run ()
			{
			  try
			  {
				  sleep(1000);
			  }
			  
			  catch(InterruptedException e)
			  {
				  
			  }
			  finally
			  {
				  Intent i = new Intent("com.crackbrain.tanveen.icare.MainActivity");
				  startActivity(i);
			  }
			}
		};
		t.start();
	}
   
}
