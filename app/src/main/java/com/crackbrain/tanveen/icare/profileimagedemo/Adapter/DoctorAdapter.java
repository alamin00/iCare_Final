package com.crackbrain.tanveen.icare.profileimagedemo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.crackbrain.tanveen.icare.R;
import com.crackbrain.tanveen.icare.profileimagedemo.Model.Doctor;
import com.crackbrain.tanveen.icare.profileimagedemo.*;

import java.util.List;

/**
 * Created by Valentine on 4/16/2015.
 */
public class DoctorAdapter extends ArrayAdapter<Doctor>{
    private Context mContext;
    private List<Doctor> mDoctors;


    public DoctorAdapter(Context context, List<Doctor> doctors)
    {
        super(context, R.layout.row_customer_list, doctors);
        context = mContext;
        mDoctors = doctors;
    }



    @Override
    public int getCount() {
        return mDoctors.size();
    }

    @Override
    public Doctor getItem(int position) {
        if (position < mDoctors.size()) {
            return mDoctors.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder{
        private TextView Name;
        private TextView Email;
        private ImageView Thumbnail;
    }



    public void Add(Doctor doctor)
    {
        mDoctors.add(doctor);
        this.notifyDataSetChanged();
    }

    public void Update()
    {
        mDoctors.clear();
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Holder mHolder;

        Doctor doctor = getItem(position);

        if (view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.row_customer_list, null);

            mHolder = new Holder();
            mHolder.Name = (TextView) view.findViewById(R.id.textCustomerName);
            mHolder.Email = (TextView) view.findViewById(R.id.textCustomerEmail);
            mHolder.Thumbnail = (ImageView) view.findViewById(R.id.customer_image_thumbnail);

            view.setTag(mHolder);
        }else {
            mHolder = (Holder)view.getTag();
        }

        //Set the doctor name
        if (mHolder.Name != null) {
            mHolder.Name.setText(doctor.getName());
        }
        //Set the doctor email
        if (mHolder.Email != null) {
            mHolder.Email.setText(doctor.getEmailAddress());
        }

        //set the doctor thumbnail
        if (mHolder.Thumbnail != null){
            mHolder.Thumbnail.setImageDrawable(doctor.getThumbnail(getContext()));
        }
        return view;
    }




}
