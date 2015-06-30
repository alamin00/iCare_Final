package com.crackbrain.tanveen.icare.profileimagedemo.Model;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View.OnClickListener;

import com.crackbrain.tanveen.icare.R;

public class DoctorCal extends Activity {

    private Button callBtn;
    private Button dialBtn;
    private EditText number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_cal);

        number = (EditText) findViewById(R.id.phoneNumber);
        callBtn = (Button) findViewById(R.id.call);
        dialBtn = (Button) findViewById(R.id.dial);

        // add PhoneStateListener for monitoring
        MyPhoneListener phoneListener = new MyPhoneListener();
        TelephonyManager telephonyManager =
                (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        // receive notifications of telephony state changes
        telephonyManager.listen(phoneListener,PhoneStateListener.LISTEN_CALL_STATE);

        callBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    // set the data
                    String uri = "tel:"+number.getText().toString();
                    Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(uri));

                    startActivity(callIntent);
                }catch(Exception e) {
                    Toast.makeText(getApplicationContext(),"Your call has failed...",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });

        dialBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    String uri = "tel:"+number.getText().toString();
                    Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse(uri));

                    startActivity(dialIntent);
                }catch(Exception e) {
                    Toast.makeText(getApplicationContext(),"Your call has failed...",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
    }

    private class MyPhoneListener extends PhoneStateListener {

        private boolean onCall = false;

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {

            switch (state) {
                case TelephonyManager.CALL_STATE_RINGING:
                    // phone ringing...
                    Toast.makeText(DoctorCal.this, incomingNumber + " calls you",
                            Toast.LENGTH_LONG).show();
                    break;

                case TelephonyManager.CALL_STATE_OFFHOOK:
                    // one call exists that is dialing, active, or on hold
                    Toast.makeText(DoctorCal.this, "on call...",
                            Toast.LENGTH_LONG).show();
                    //because user answers the incoming call
                    onCall = true;
                    break;

                case TelephonyManager.CALL_STATE_IDLE:
                    // in initialization of the class and at the end of phone call

                    // detect flag from CALL_STATE_OFFHOOK
                    if (onCall == true) {
                        Toast.makeText(DoctorCal.this, "restart app after call",
                                Toast.LENGTH_LONG).show();

                        // restart our application
                        Intent restart = getBaseContext().getPackageManager().
                                getLaunchIntentForPackage(getBaseContext().getPackageName());
                        restart.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(restart);

                        onCall = false;
                    }
                    break;
                default:
                    break;
            }

        }
    }

}
