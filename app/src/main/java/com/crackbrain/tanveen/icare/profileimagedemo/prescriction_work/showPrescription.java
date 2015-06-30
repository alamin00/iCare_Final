package com.crackbrain.tanveen.icare.profileimagedemo.prescriction_work;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.crackbrain.tanveen.icare.R;

public class showPrescription extends Activity {

    Button btnDelete;
    ImageView imageDetail;
    int imageId;
    Bitmap theImage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_prescription);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        imageDetail = (ImageView) findViewById(R.id.imageView1);
        /**
         * getting intent data from search and previous screen
         */
        Intent intnt = getIntent();
        theImage = (Bitmap) intnt.getParcelableExtra("imagename");
        imageId = intnt.getIntExtra("imageid", 20);
        Log.d("Image ID:****", String.valueOf(imageId));
        imageDetail.setImageBitmap(theImage);
        btnDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /**
                 * create DatabaseHandler object
                 */
                DataBaseHandler db = new DataBaseHandler(
                        showPrescription.this);
                /**
                 * Deleting records from database
                 */
                Log.d("Delete Image: ", "Deleting.....");
                db.deleteContact(new Contact(imageId));
                // /after deleting data go to main page
                Intent i = new Intent(showPrescription.this,
                        prescription_List2.class);
                startActivity(i);
                finish();
            }
        });

    }
}