package com.crackbrain.tanveen.icare;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


public class UserRegistrationActivity extends ActionBarActivity {
    private EditText etName;
    private EditText etTitle;
    private EditText etEmail;
    private EditText etAddress;
    private EditText etCity;
    private EditText etCountry;

    private Button btnSave;
    private DBAdapter dbAdapter;
    ArrayList<User> userArrayList;
    static int empId;
    static String from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        dbAdapter=new DBAdapter(this);
        initialization();

        Bundle extras=getIntent().getExtras();
        from=extras.getString("from");
        if(from.contains("detailsActivity")){
            empId=extras.getInt("id");
            userArrayList =new ArrayList<User>();
            userArrayList =dbAdapter.getEmployeeInfoById(empId);
            setEditTextField();
            btnSave.setText("Update");
        }

    }

    private void setEditTextField() {
        for(User user : userArrayList){
            etName.setText(user.getName());
            etEmail.setText(user.getEmail());
            etTitle.setText(user.getTitle());
            etAddress.setText(user.getAddress());
            etCity.setText(user.getCity());
            etCountry.setText(user.getCountry());
        }
    }

    private void initialization() {
        etAddress= (EditText) findViewById(R.id.etAddress);
        etName= (EditText) findViewById(R.id.etName);
        etTitle= (EditText) findViewById(R.id.etAge);
        etCity= (EditText) findViewById(R.id.etBlood);
        etCountry= (EditText) findViewById(R.id.etMobile);
        etEmail= (EditText) findViewById(R.id.etEmail);

        btnSave= (Button) findViewById(R.id.btnSave);
        btnSave.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        btnSave.getBackground().setAlpha(100);
                        break;
                    case MotionEvent.ACTION_UP:
                        btnSave.getBackground().setAlpha(255);
                        // insert data to database ..........
                        String name=etName.getText().toString().trim();
                        String title=etTitle.getText().toString().trim();
                        String email=etEmail.getText().toString().trim();
                        String address=etAddress.getText().toString().trim();
                        String city=etCity.getText().toString().trim();
                        String country=etCountry.getText().toString().trim();
                        if(name.isEmpty() || title.isEmpty() || email.isEmpty()
                                || address.isEmpty() || city.isEmpty()|| country.isEmpty()){
                            AlertDialog.Builder alert=new AlertDialog.Builder(UserRegistrationActivity.this);
                            alert.setTitle("Oops");
                            alert.setMessage("Please Enter All Information Carefully!!");
                            alert.show();
                        }else{
                            dbAdapter.openDB();
                            if(from.contains("mainActivity")){
                                User user =new User(name,title,email,address,city,country);
                                long inserted=dbAdapter.addEmployee(user);
                                if(inserted>=0){
                                    Toast.makeText(getApplicationContext(), "Data Saved On Database Successfully!", Toast.LENGTH_SHORT).show();
                                }
                                Intent gotoMainActivity=new Intent(UserRegistrationActivity.this,MainActivity.class);
                                startActivity(gotoMainActivity);
                                finish();

                            }
                            else if(from.contains("detailsActivity")){
                                User user =new User(name,title,email,address,city,country);
                                long updated=dbAdapter.updateEmployee(user,empId);
                                if(updated>=0){
                                    Toast.makeText(getApplicationContext(), "Data Updated Successfully!!", Toast.LENGTH_SHORT).show();
                                }
                                Intent gotoEmployeeDetailsActivity=new Intent(UserRegistrationActivity.this,UserDetailsActivity.class);
                                gotoEmployeeDetailsActivity.putExtra("id",empId);
                                startActivity(gotoEmployeeDetailsActivity);
                                finish();

                            }


                        }

                        break;
                }
                return false;
            }
        });
    }



    @Override
    public void onBackPressed() {
        Intent takeUserToMainActivity=new Intent(UserRegistrationActivity.this,MainActivity.class);
        startActivity(takeUserToMainActivity);
        finish();
    }
}
