package com.crackbrain.tanveen.icare;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jakir on 5/12/2015.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="user_db";
    public static final int DATABASE_VERSION=2;

    public static final String TABLE_User ="user";

    public static final String ID_FIELD="_id";
    public static final String NAME_FIELD="name";
    public static final String AGE_FIELD ="age";
    public static final String ADDRESS_FIELD="address";
    public static final String BLOOD_FIELD ="blood";
    public static final String MOBILE_FIELD ="mobile";
    public static final String EMAIL_FIELD="email";

    public static final String CREATE_TABLE_SQL="create table "+ TABLE_User +" ( "+ID_FIELD+" INTEGER PRIMARY KEY, "
            +NAME_FIELD+" TEXT, "+ AGE_FIELD +" TEXT, "+EMAIL_FIELD+" TEXT, "+ADDRESS_FIELD+" TEXT, "
            + BLOOD_FIELD +" TEXT, "+ MOBILE_FIELD +" TEXT )";

    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
