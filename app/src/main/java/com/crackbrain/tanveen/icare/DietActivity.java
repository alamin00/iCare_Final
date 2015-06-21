package com.crackbrain.tanveen.icare;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class DietActivity extends ActionBarActivity implements View.OnClickListener {

    Button milk,clr,rice,bread,pizza, chicken,curt,meat;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);
        milk=(Button)findViewById(R.id.btnMilk);
        milk.setOnClickListener(this);
        clr=(Button)findViewById(R.id.btnClr);
        clr.setOnClickListener(this);
        rice=(Button)findViewById(R.id.btnRice);
        rice.setOnClickListener(this);
        bread=(Button)findViewById(R.id.btnBread);
        bread.setOnClickListener(this);
        pizza=(Button)findViewById(R.id.btnPizza);
        pizza.setOnClickListener(this);
        chicken=(Button)findViewById(R.id.btnChiken);
        chicken.setOnClickListener(this);
        curt=(Button)findViewById(R.id.btnCurt);
        curt.setOnClickListener(this);
        meat=(Button)findViewById(R.id.btnMeat);
        meat.setOnClickListener(this);

        tvResult=(TextView)findViewById(R.id.tvResult);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_diet, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int result=0;

        switch (v.getId()){
            case R.id.btnRice:{
                int rice=206;
                result=result+rice;
                tvResult.setText("You'r received "+result+" Calories from your food!!!!");
                break;
            }

            case R.id.btnBread:{
                int bread=70;
                result=result+bread;
                tvResult.setText("You'r received "+result+" Calories from your food!!!!");
                break;
            }
            case R.id.btnPizza:{
                int pizza=285;
                result=result+pizza;
                tvResult.setText("You'r received "+result+" Calories from your food!!!!");
                break;
            }
            case R.id.btnChiken:{
                int chicken=306;
                result=result+chicken;
                tvResult.setText("You'r received "+result+" Calories from your food!!!!");
                break;
            }
            case R.id.btnCurt:{
                int curd=180;
                result=result+curd;
                tvResult.setText("You'r received "+result+" Calories from your food!!!!");
                break;
            }
            case R.id.btnMilk:{
                int milk=103;
                result=result+milk;
                tvResult.setText("You'r received "+result+" Calories from your food!!!!");
                break;
            }
            case R.id.btnMeat:{
                int meat=213;
                result=result+meat;
                tvResult.setText("You'r received "+result+" Calories from your food!!!!");
                break;
            }
            case R.id.btnClr:{
                result=0;
                tvResult.setText("");
                break;
            }

        }


    }
}
