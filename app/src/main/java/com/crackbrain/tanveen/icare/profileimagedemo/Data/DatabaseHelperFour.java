package com.crackbrain.tanveen.icare.profileimagedemo.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.crackbrain.tanveen.icare.profileimagedemo.Helpers.Constants;
import com.crackbrain.tanveen.icare.profileimagedemo.Model.Doctor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Valentine on 4/15/2015.
 */
public class DatabaseHelperFour extends SQLiteOpenHelper{

    // Context in which this database exists.
    private Context mContext;

    // Database version.
    public static final int DATABASE_VERSION = 1;

    // Database name.
    public static final String DATABASE_NAME = "ImageExample";

    // Table names.
    public static final String TABLE_DOCTORS = "customers";

    private final static String TAG = DatabaseHelperFour.class.getSimpleName();


    public DatabaseHelperFour(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DOCTOR_TABLE);
      }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOCTORS);

    }

    // Command to create a table of clients.
    private static final String CREATE_DOCTOR_TABLE = "CREATE TABLE " + TABLE_DOCTORS + " ("
            + Constants.COLUMN_DOCTOR_ID + " INTEGER PRIMARY KEY, "
            + Constants.COLUMN_IMAGE_PATH + " TEXT, "
            + Constants.COLUMN_NAME + " TEXT, "
            + Constants.COLUMN_PHONE + " TEXT, "
            + Constants.COLUMN_EMAIL + " TEXT, "
            + Constants.COLUMN_STREET + " TEXT, "
            + Constants.COLUMN_CITY + " TEXT, "
            + Constants.COLUMN_HOSPITAL + " TEXT, "
            + Constants.COLUMN_HOSPITAL_NUMBER + " TEXT)";

    // Database lock to prevent conflicts.
    public static final Object[] databaseLock = new Object[0];


    public List<Doctor> getAllDoctor() {
        //Initialize an empty list of Customers
        List<Doctor> doctorList = new ArrayList<Doctor>();

        //Command to select all Customers
        String selectQuery = "SELECT * FROM " + TABLE_DOCTORS;

        //lock database for reading
        synchronized (databaseLock) {
            //Get a readable database
            SQLiteDatabase database = getReadableDatabase();

            //Make sure database is not empty
            if (database != null) {

                //Get a cursor for all Customers in the database
                Cursor cursor = database.rawQuery(selectQuery, null);
                if (cursor.moveToFirst()) {
                    while (!cursor.isAfterLast()) {
                        Doctor doctor = getDoctor(cursor);
                        doctorList.add(doctor);
                        cursor.moveToNext();
                    }
                }
                //Close the database connection
                database.close();
            }
            //Return the list of customers
            return doctorList;
        }

    }


    private static Doctor getDoctor(Cursor cursor) {
        Doctor doctor = new Doctor();
        doctor.setId(cursor.getInt(cursor.getColumnIndex(Constants.COLUMN_DOCTOR_ID)));
        doctor.setName(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_NAME)));
        doctor.setEmailAddress(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_EMAIL)));
        doctor.setPhoneNumber(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_PHONE)));
        doctor.setStreetAddress(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_STREET)));
        doctor.setCity(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_CITY)));
        doctor.setState(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_HOSPITAL)));
        doctor.setPostalCode(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_HOSPITAL_NUMBER)));
        doctor.setImagePath(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_IMAGE_PATH)));
        return doctor;
    }

    public Long addCustomer(Doctor doctor) {
        Long ret = null;

        //Lock database for writing
        synchronized (databaseLock) {
            //Get a writable database
            SQLiteDatabase database = getWritableDatabase();

            //Ensure the database exists
            if (database != null) {
                //Prepare the doctor information that will be saved to the database
                ContentValues values = new ContentValues();
                values.put(Constants.COLUMN_NAME, doctor.getName());
                values.put(Constants.COLUMN_EMAIL, doctor.getEmailAddress());
                values.put(Constants.COLUMN_PHONE, doctor.getPhoneNumber());
                values.put(Constants.COLUMN_CITY, doctor.getCity());
                values.put(Constants.COLUMN_STREET, doctor.getStreetAddress());
                values.put(Constants.COLUMN_HOSPITAL, doctor.getState());
                values.put(Constants.COLUMN_HOSPITAL_NUMBER, doctor.getPostalCode());
                values.put(Constants.COLUMN_IMAGE_PATH, doctor.getImagePath());

                //Attempt to insert the client information into the transaction table
                try {
                    ret = database.insert(TABLE_DOCTORS, null, values);
                } catch (Exception e) {
                    Log.e(TAG, "Unable to add Doctor to database " + e.getMessage());
                }
                //Close database connection
                database.close();
            }
        }
        return ret;
    }

    public Doctor getDoctorById(long id){
        List<Doctor> tempDoctorList = getAllDoctor();
        for (Doctor doctor : tempDoctorList){
            if (doctor.getId() == id){
                return doctor;
            }
        }
        return null;
    }

    public boolean customerExists(long id){
        //Check if there is an existing customer
        List<Doctor> tempDoctorList = getAllDoctor();
        for (Doctor doctor : tempDoctorList){
            if (doctor.getId() == id){
                return true;
            }
        }
        return false;
    }


}
