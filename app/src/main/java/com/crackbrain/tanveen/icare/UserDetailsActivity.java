package com.crackbrain.tanveen.icare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;


public class UserDetailsActivity extends ActionBarActivity {

    private TextView tvName;
    private TextView tvEmail;
    private TextView tvTitle;
    private TextView tvAddress;
    private TextView tvCity;
    private TextView tvCountry;

    ArrayList<User> userArrayList;
    DBAdapter dbAdapter;
    static int empId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        initialization();
        retriveDatafromDatabase();
    }

    private void retriveDatafromDatabase() {
        Bundle extras=getIntent().getExtras();
        empId=extras.getInt("id");

        userArrayList =dbAdapter.getEmployeeInfoById(empId);
        for(User user : userArrayList){
            tvName.setText(user.getName());
            tvEmail.setText(user.getEmail());
            tvTitle.setText(user.getTitle());
            tvAddress.setText(user.getAddress());
            tvCity.setText(user.getCity());
            tvCountry.setText(user.getCountry());
        }
    }

    private void initialization() {
        tvName= (TextView) findViewById(R.id.tvNameInDetails);
        tvEmail= (TextView) findViewById(R.id.tvEmailInDetails);
        tvAddress= (TextView) findViewById(R.id.tvAddressInDetails);
        tvCity= (TextView) findViewById(R.id.tvCityInDetails);
        tvTitle= (TextView) findViewById(R.id.tvTitleInDetails);
        tvCountry= (TextView) findViewById(R.id.tvCountryInDetails);

        userArrayList =new ArrayList<User>();
        dbAdapter=new DBAdapter(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user_details,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();

        if(id==R.id.action_edit){
            Intent takeUserToRegistrationForm=new Intent(UserDetailsActivity.this,UserRegistrationActivity.class);
            takeUserToRegistrationForm.putExtra("from","detailsActivity");
            takeUserToRegistrationForm.putExtra("id",empId);
            startActivity(takeUserToRegistrationForm);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
