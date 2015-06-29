package com.crackbrain.tanveen.icare.profileimagedemo.Fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.crackbrain.tanveen.icare.R;
import com.crackbrain.tanveen.icare.profileimagedemo.Adapter.DoctorAdapter;
import com.crackbrain.tanveen.icare.profileimagedemo.Data.DatabaseHelperFour;
import com.crackbrain.tanveen.icare.profileimagedemo.DoctorActivity;
import com.crackbrain.tanveen.icare.profileimagedemo.Helpers.Constants;
import com.crackbrain.tanveen.icare.profileimagedemo.Helpers.Enums;
import com.crackbrain.tanveen.icare.profileimagedemo.Model.Doctor;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Use the {@link DoctorListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DoctorListFragment extends Fragment {

    private List<Doctor> mDoctors;
    private ListView mCustomerListView;
    private DoctorAdapter mAdapter;
    private View mRootView;
    private DatabaseHelperFour db;

    public static DoctorListFragment newInstance(int sectionNumber) {
        DoctorListFragment fragment = new DoctorListFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }


    public DoctorListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        db = new DatabaseHelperFour(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_doctor_list, container, false);

        InitializeViews();

        return mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        LoadListData();
    }

    private void LoadListData()
    {
        //First clear the adapter of any Customers it has
        mAdapter.Update();

        //Get the list of customers from the database
        mDoctors = db.getAllDoctor();

        if (mDoctors != null){
            for (Doctor doctor : mDoctors){
                mAdapter.add(doctor);
            }
        }

    }

    private void InitializeViews() {

        mCustomerListView = (ListView) mRootView.findViewById(R.id.customer_list);
        mDoctors = new ArrayList<Doctor>();
        mAdapter = new DoctorAdapter(getActivity(), mDoctors);
        mCustomerListView.setAdapter(mAdapter);
        TextView emptyText = (TextView) mRootView.findViewById(R.id.client_list_empty);
        mCustomerListView.setEmptyView(emptyText);
        mCustomerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Get the selected client
                Doctor tempDoctor = mDoctors.get(position);
                DoctorActivity myActivity = (DoctorActivity)getActivity();
                    //go to edit the selected client
                    myActivity.ReplaceFragment(Enums.FragmentEnums.CustomerDetailsFragment, 3, tempDoctor.getId());

            }
        });

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.customer_list_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_add_customer:
                DoctorActivity myActivity = (DoctorActivity)getActivity();
                myActivity.ReplaceFragment(Enums.FragmentEnums.CustomerDetailsFragment, 3, 0);
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((DoctorActivity) activity).onSectionAttached(
                getArguments().getInt(Constants.ARG_SECTION_NUMBER));
    }



}
