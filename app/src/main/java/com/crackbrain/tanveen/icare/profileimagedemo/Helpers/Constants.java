package com.crackbrain.tanveen.icare.profileimagedemo.Helpers;

import android.os.Environment;

import com.crackbrain.tanveen.icare.R;
import com.crackbrain.tanveen.icare.profileimagedemo.*;

import java.io.File;

/**
 * Created by Valentine on 4/10/2015.
 */
public class Constants {

    public static final String ARG_SECTION_NUMBER = "section_number";
    public static final String ARG_CUST_ID = "customer_id";


    public static final String COLUMN_DOCTOR_ID = "_id";
    public static final String COLUMN_IMAGE_PATH = "imagePath";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_STREET = "street";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_HOSPITAL = "state";
    public static final String COLUMN_HOSPITAL_NUMBER = "zipCode";

    public static final String KEY_IMAGE_URI = "image_uri";

    public static final String KEY_IMAGE_PATH = "key_image_path";
    public static int ACTION_REQUEST_IMAGE = 1000;
    public static int SELECT_IMAGE = 1001;
    public static final String TAKE_PHOTO = "Take Photo";
    public static final String CHOOSE_FROM_GALLERY = "Choose from gallery";
    public static final String CANCEL= "Cancel";

    public static final int DEFAULT_IMAGE_RESOURCE = R.drawable.default_customer_picture;
    public static final String PICTURE_DIRECTORY = Environment.getExternalStorageDirectory()
            + File.separator + "DCIM" + File.separator + "ProfilePicture" + File.separator;

}
