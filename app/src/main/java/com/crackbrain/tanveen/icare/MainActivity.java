package com.crackbrain.tanveen.icare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.crackbrain.tanveen.icare.alarm.AlarmActivity;
import com.crackbrain.tanveen.icare.profileimagedemo.AllUserList;
import com.crackbrain.tanveen.icare.profileimagedemo.DoctorActivity;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    ImageButton btnUser,btnDoc,btnDiet,btnVaccin,btnMedicalList,btnEmargency,btnBmi;
    private static final String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialization();

    }

    private void initialization() {
    btnUser=(ImageButton)findViewById(R.id.imgbtnFamily);
        btnUser.setOnClickListener(this);
        btnDoc=(ImageButton)findViewById(R.id.imgbtnDoc);
        btnDoc.setOnClickListener(this);
        btnDiet=(ImageButton)findViewById(R.id.imgbtnDiet);
        btnDiet.setOnClickListener(this);
        btnVaccin=(ImageButton)findViewById(R.id.imgbtnVaccin);
        btnVaccin.setOnClickListener(this);
        btnMedicalList=(ImageButton)findViewById(R.id.imgbtnHealth);
        btnMedicalList.setOnClickListener(this);
        btnEmargency=(ImageButton)findViewById(R.id.imgbtnEmargency);
        btnEmargency.setOnClickListener(this);
        btnBmi=(ImageButton)findViewById(R.id.imgbtnBmi);
        btnBmi.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgbtnFamily:{
                Intent i=new Intent(MainActivity.this,AllUserList.class);
                startActivity(i);
                break;
            }
            case R.id.imgbtnDiet:{
                Intent i=new Intent(MainActivity.this,AlarmActivity.class);
                Log.d(TAG,"Diet Button Clicked");
                startActivity(i);
                break;
            }
            case R.id.imgbtnBmi:{
                Intent i=new Intent(MainActivity.this,BMI.class);
                Log.d(TAG,"Bmi Button Clicked");
                startActivity(i);
                break;
            }
            case R.id.imgbtnDoc:{
                Intent i = new Intent(MainActivity.this,DoctorActivity.class);
                startActivity(i);
                break;
            }

        }
    }
}
