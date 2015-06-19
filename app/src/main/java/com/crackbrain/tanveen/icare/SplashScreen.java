package com.crackbrain.tanveen.icare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ProgressBar;


public class SplashScreen extends ActionBarActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        progressBar= (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setProgress(0);
        progressBar.setMax(30);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    while (progressBar.getProgress()<=progressBar.getMax()){
                        Thread.sleep(100);
                        if(progressBar.getProgress()==progressBar.getMax()){
                            Intent gotoMainActivity=new Intent(SplashScreen.this,MainActivity.class);
                            startActivity(gotoMainActivity);
                            finish();
                            break;
                        }
                        progressBar.incrementProgressBy(1);
                    }

                }catch (Exception e){

                }
            }
        }).start();

    }

}
