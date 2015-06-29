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


public class AllUserList extends ActionBarActivity {

    private ListView lvEmployeeList;
    private TextView tvAddNew;
    private ArrayList<User> userArrayList;
    private ListViewAdapter adapter;
    private DBAdapter dbAdapter;
    private EditText etSearch;
    static String name;
    ImageButton plusButton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_user_list);

        initialization();

    }

    private void initialization() {
        lvEmployeeList = (ListView) findViewById(R.id.lvUserList);
        tvAddNew = (TextView) findViewById(R.id.tvAdd);
        userArrayList = new ArrayList<User>();
        dbAdapter = new DBAdapter(this);
        // etSearch = (EditText) findViewById(R.id.etSearch);

        userArrayList = dbAdapter.getEmployeeInfo();
        adapter = new ListViewAdapter(this, userArrayList);
        lvEmployeeList.setAdapter(adapter);

        tvAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoUserRegistrationForm = new Intent(AllUserList.this, UserRegistrationActivity.class);
                gotoUserRegistrationForm.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                gotoUserRegistrationForm.putExtra("from", "mainActivity");
                startActivity(gotoUserRegistrationForm);
                finish();
            }
        });



        plusButton = (ImageButton)findViewById(R.id.plusButton);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoUserRegistrationForm = new Intent(AllUserList.this, UserRegistrationActivity.class);
                gotoUserRegistrationForm.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                gotoUserRegistrationForm.putExtra("from", "mainActivity");
                startActivity(gotoUserRegistrationForm);
                finish();


            }
        });

        /*etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                name = s.toString().trim();
                userArrayList = dbAdapter.getEmployeeInfoByName(name);
                adapter = new ListViewAdapter(MainActivity.this, userArrayList);
                lvEmployeeList.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            Intent gotoEmployeeRegistrationForm = new Intent(AllUserList.this, UserRegistrationActivity.class);
            gotoEmployeeRegistrationForm.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            gotoEmployeeRegistrationForm.putExtra("from", "mainActivity");
            startActivity(gotoEmployeeRegistrationForm);
            finish();
        }
        else if (id == R.id.alarm){
            Intent gotoalarm = new Intent(AllUserList.this , AlarmActivity.class);
            startActivity(gotoalarm);

        }


        return super.onOptionsItemSelected(item);
    }

}
