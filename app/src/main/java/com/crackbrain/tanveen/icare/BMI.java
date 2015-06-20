package com.crackbrain.tanveen.icare;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class BMI extends ActionBarActivity {

    TextView tvResult;
    EditText etheight,etweight;
    Button cal;

    private float fHeight;
    private float fWeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        tvResult=(TextView)findViewById(R.id.tvShow);
        etheight=(EditText)findViewById(R.id.etHeight);
        etweight=(EditText)findViewById(R.id.etWeight);
        cal=(Button)findViewById(R.id.btnCal);

        String sHeight=etheight.getText().toString().trim();
        String sWeight=etweight.getText().toString().trim();

        fHeight = Float.parseFloat(sHeight);
        fWeight = Float.parseFloat(sWeight);

        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float result = (fWeight / (fHeight * fHeight) * 703);
                if (result < 18.5) {
                    tvResult.setText("You'r too sick. Need more Nutrition Food");
                } else if (18.5 <= result && result <= 24.9) {
                    tvResult.setText("You have Normal weight");
                } else if (25 <= result && result <= 29.99) {
                    tvResult.setText("You'r Over weight need to control Diet");
                } else {
                    tvResult.setText("You'r too fat. Get hurry to control your weight");
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bmi, menu);
        return true;
    }

    public void etCLR(){
        etweight.setText("");
        etheight.setText("");
    }
}
