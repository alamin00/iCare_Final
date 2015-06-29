package com.crackbrain.tanveen.icare.profileimagedemo.Fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crackbrain.tanveen.icare.R;
import com.crackbrain.tanveen.icare.profileimagedemo.DoctorActivity;
import com.crackbrain.tanveen.icare.profileimagedemo.Helpers.Constants;
import com.crackbrain.tanveen.icare.profileimagedemo.*;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class OrderListFragment extends Fragment {


    public OrderListFragment() {
        // Required empty public constructor
    }

    public static OrderListFragment newInstance(int sectionNumber) {
        OrderListFragment fragment = new OrderListFragment();
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
        return inflater.inflate(R.layout.fragment_order_list, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((DoctorActivity) activity).onSectionAttached(
                getArguments().getInt(Constants.ARG_SECTION_NUMBER));
    }


}
