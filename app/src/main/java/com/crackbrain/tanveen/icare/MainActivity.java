package com.crackbrain.tanveen.icare;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.crackbrain.tanveen.icare.alarm.AlarmActivity;
import com.crackbrain.tanveen.icare.alarm.BaseActivity;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    ImageButton btnUser,btnDoc,btnDiet,btnVaccin,btnMedicalList,btnEmargency,btnBmi;

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
        btnEmargency.setOnClickListener(this);
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
                startActivity(i);
                break;
            }
            case R.id.imgbtnBmi:{
                Intent i=new Intent(MainActivity.this,BMI.class);
                startActivity(i);
                break;
            }

        }
    }
}
