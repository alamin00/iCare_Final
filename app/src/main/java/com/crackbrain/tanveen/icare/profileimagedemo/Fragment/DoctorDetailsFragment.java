package com.crackbrain.tanveen.icare.profileimagedemo.Fragment;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.crackbrain.tanveen.icare.R;
import com.crackbrain.tanveen.icare.profileimagedemo.Data.DatabaseHelperFour;
import com.crackbrain.tanveen.icare.profileimagedemo.DoctorActivity;
import com.crackbrain.tanveen.icare.profileimagedemo.Helpers.Constants;
import com.crackbrain.tanveen.icare.profileimagedemo.Helpers.FileUtils;
import com.crackbrain.tanveen.icare.profileimagedemo.Model.Doctor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Use the {@link DoctorDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DoctorDetailsFragment extends Fragment {

    private boolean InEditMode;
    private Doctor mDoctor;
    private View mRootView;
    private DatabaseHelperFour db;

    //Image properties
    private String mCurrentImagePath = null;
    private Uri mCapturedImageURI = null;
    private ImageButton mProfileImageButton;

    private EditText mNameEditText,
            mEmailEditText,
            mPhoneEditText,
            mStreetEditText,
            mCityEditText,
            mZipCodeEditText,
            mStateEditText;



    public static DoctorDetailsFragment newInstance(int sectionNumber, long custId) {
        DoctorDetailsFragment fragment = new DoctorDetailsFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.ARG_SECTION_NUMBER, sectionNumber);
        if (custId > 0){
            args.putLong(Constants.ARG_CUST_ID, custId );
        }
        fragment.setArguments(args);
        return fragment;
    }

    private void GetPassedInCustomer(){
        Bundle args = getArguments();
        if (args != null && args.containsKey(Constants.ARG_CUST_ID)){
            long customerId = args.getLong(Constants.ARG_CUST_ID, 0);
            if (customerId > 0){
                mDoctor = db.getDoctorById(customerId);
                InEditMode = true;
            }
        }else {
            mDoctor = new Doctor();
            InEditMode = false;
        }

    }


    public DoctorDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DatabaseHelperFour(getActivity());

        setHasOptionsMenu(true);
        ActionBar actionBar = ((ActionBarActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Ensure there is a saved instance state.
        if (savedInstanceState != null) {

            // Get the saved Image uri string.
            String ImageUriString = savedInstanceState.getString(Constants.KEY_IMAGE_URI);

            // Restore the Image uri from the Image uri string.
            if (ImageUriString != null) {
                mCapturedImageURI = Uri.parse(ImageUriString);
            }
            mCurrentImagePath = savedInstanceState.getString(Constants.KEY_IMAGE_URI);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mCapturedImageURI != null) {
            outState.putString(Constants.KEY_IMAGE_URI, mCapturedImageURI.toString());
        }
        outState.putString(Constants.KEY_IMAGE_PATH, mCurrentImagePath);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_doctor_details, container, false);
        InitializeViews();
        return mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        GetPassedInCustomer();
        if (InEditMode)
        {
            PopulateFields();
        }
    }

    private void PopulateFields() {
        DoctorActivity myActivity = (DoctorActivity) getActivity();
        mNameEditText.setText(mDoctor.getName());
        mPhoneEditText.setText(mDoctor.getPhoneNumber());
        mEmailEditText.setText(mDoctor.getEmailAddress());
        mStreetEditText.setText(mDoctor.getStreetAddress());
        mCityEditText.setText(mDoctor.getCity());
        mStateEditText.setText(mDoctor.getState());
        mZipCodeEditText.setText(mDoctor.getPostalCode());


        // Update profile's Image
        if (mCurrentImagePath != null && !mCurrentImagePath.isEmpty()) {
            mProfileImageButton.setImageDrawable(new BitmapDrawable(getResources(),
                    FileUtils.getResizedBitmap(mCurrentImagePath, 512, 512)));
        } else {
            mProfileImageButton.setImageDrawable(mDoctor.getImage(getActivity()));
        }

    }

    private void InitializeViews() {
        mNameEditText = (EditText) mRootView.findViewById(R.id.edit_text_customer_name);
        mEmailEditText = (EditText) mRootView.findViewById(R.id.edit_text_customer_email);
        mPhoneEditText = (EditText) mRootView.findViewById(R.id.edit_text_customer_phone);
        mPhoneEditText.setInputType(InputType.TYPE_CLASS_PHONE);
        mPhoneEditText.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        mStreetEditText = (EditText) mRootView.findViewById(R.id.edit_text_customer_street_address);
        mCityEditText = (EditText) mRootView.findViewById(R.id.edit_text_customer_city);
        mStateEditText = (EditText) mRootView.findViewById(R.id.edit_text_customer_state);
        mZipCodeEditText = (EditText) mRootView.findViewById(R.id.edit_text_customer_zip_code);
        mProfileImageButton = (ImageButton)mRootView.findViewById(R.id.customer_image_button);
        mProfileImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((DoctorActivity) activity).onSectionAttached(
                getArguments().getInt(Constants.ARG_SECTION_NUMBER));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.customer_details_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                getActivity().onBackPressed();
                break;
            case R.id.action_save_customer:
                SaveCustomer();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void SaveCustomer(){
        mDoctor.setName(mNameEditText.getText().toString());
        mDoctor.setEmailAddress(mEmailEditText.getText().toString());
        mDoctor.setPhoneNumber(mPhoneEditText.getText().toString());
        mDoctor.setStreetAddress(mStreetEditText.getText().toString());
        mDoctor.setCity(mCityEditText.getText().toString());
        mDoctor.setState(mStateEditText.getText().toString());
        mDoctor.setPostalCode(mZipCodeEditText.getText().toString());

        //Check to see if there is valid image path temporarily in memory
        //Then save that image path to the database and that becomes the profile
        //Image for this user.
        if (mCurrentImagePath != null && !mCurrentImagePath.isEmpty())
        {
            mDoctor.setImagePath(mCurrentImagePath);
        }

        long result = db.addCustomer(mDoctor);
        if (result == -1 ){
            Toast.makeText(getActivity(), "Unable to add doctor: " + mDoctor.getName(), Toast.LENGTH_LONG).show();
        }
        getActivity().onBackPressed();
    }

    private void chooseImage(){

        //We need the customer's name to to save the image file
        if (mNameEditText.getText() != null && !mNameEditText.getText().toString().isEmpty()) {
            // Determine Uri of camera image to save.
            final File rootDir = new File(Constants.PICTURE_DIRECTORY);

             //noinspection ResultOfMethodCallIgnored
            rootDir.mkdirs();

            // Create the temporary file and get it's URI.

            //Get the customer name
            String customerName = mNameEditText.getText().toString();

            //Remove all white space in the customer name
            customerName.replaceAll("\\s+", "");

            //Use the customer name to create the file name of the image that will be captured
            File file = new File(rootDir, FileUtils.generateImageName(customerName));
            mCapturedImageURI = Uri.fromFile(file);

            // Initialize a list to hold any camera application intents.
            final List<Intent> cameraIntents = new ArrayList<Intent>();

            // Get the default camera capture intent.
            final Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            // Get the package manager.
            final PackageManager packageManager = getActivity().getPackageManager();

            // Ensure the package manager exists.
            if (packageManager != null) {

                // Get all available image capture app activities.
                final List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);

                // Create camera intents for all image capture app activities.
                for(ResolveInfo res : listCam) {

                    // Ensure the activity info exists.
                    if (res.activityInfo != null) {

                        // Get the activity's package name.
                        final String packageName = res.activityInfo.packageName;

                        // Create a new camera intent based on android's default capture intent.
                        final Intent intent = new Intent(captureIntent);

                        // Set the intent data for the current image capture app.
                        intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
                        intent.setPackage(packageName);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, mCapturedImageURI);

                        // Add the intent to available camera intents.
                        cameraIntents.add(intent);
                    }
                }
            }

            // Create an intent to get pictures from the filesystem.
            final Intent galleryIntent = new Intent();
            galleryIntent.setType("image/*");
            galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

            // Chooser of filesystem options.
            final Intent chooserIntent = Intent.createChooser(galleryIntent, "Select Source");

            // Add the camera options.
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS,
                    cameraIntents.toArray(new Parcelable[cameraIntents.size()]));

            // Start activity to choose or take a picture.
            startActivityForResult(chooserIntent, Constants.ACTION_REQUEST_IMAGE);
        } else {
            mNameEditText.setError("Please enter doctor name");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK){
               // Get the resultant image's URI.
                final Uri selectedImageUri = (data == null) ? mCapturedImageURI : data.getData();

                // Ensure the image exists.
                if (selectedImageUri != null) {

                    // Add image to gallery if this is an image captured with the camera
                    //Otherwise no need to re-add to the gallery if the image already exists
                    if (requestCode == Constants.ACTION_REQUEST_IMAGE) {
                        final Intent mediaScanIntent =
                                new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                        mediaScanIntent.setData(selectedImageUri);
                        getActivity().sendBroadcast(mediaScanIntent);
                    }

                   mCurrentImagePath = FileUtils.getPath(getActivity(), selectedImageUri);

                    // Update client's picture
                    if (mCurrentImagePath != null && !mCurrentImagePath.isEmpty()) {
                        mProfileImageButton.setImageDrawable(new BitmapDrawable(getResources(),
                                FileUtils.getResizedBitmap(mCurrentImagePath, 512, 512)));
                    }
                }
        }

    }
}
