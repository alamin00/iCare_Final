package com.crackbrain.tanveen.icare.profileimagedemo.Fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.crackbrain.tanveen.icare.R;
import com.crackbrain.tanveen.icare.profileimagedemo.DoctorActivity;
import com.crackbrain.tanveen.icare.profileimagedemo.Helpers.Constants;
import com.crackbrain.tanveen.icare.profileimagedemo.Model.DoctorCal;
import com.crackbrain.tanveen.icare.profileimagedemo.Model.EmailActivity;
import com.crackbrain.tanveen.icare.profileimagedemo.Model.MsgActivity;
import com.crackbrain.tanveen.icare.profileimagedemo.prescriction_work.prescription_List2;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class Contact_with_doctor extends Fragment {





    public Contact_with_doctor() {
        // Required empty public constructor
    }

    public static Contact_with_doctor newInstance(int sectionNumber) {
        Contact_with_doctor fragment = new Contact_with_doctor();
        Bundle args = new Bundle();
        args.putInt(Constants.ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.contact_with_doctor, container, false);

        final   Button btnCall = (Button)view.findViewById(R.id.btnDoctorCall);

        final  Button  btnMsg = (Button)view.findViewById(R.id.btnMsgSendDoctor);

        final Button btnEmail = (Button)view.findViewById(R.id.btnEmailSendDoctor);

        final  Button btnprescription = (Button)view.findViewById(R.id.btnprescription);


        btnprescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(),prescription_List2.class);
                startActivity(in);
            }
        });

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(),EmailActivity.class);
                startActivity(in);
            }
        });


        btnMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(),MsgActivity.class);
                startActivity(in);
            }
        });



        btnCall.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent in = new Intent(getActivity(),DoctorCal.class);
               startActivity(in);
           }
       });


        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((DoctorActivity) activity).onSectionAttached(
                getArguments().getInt(Constants.ARG_SECTION_NUMBER));
    }


}
