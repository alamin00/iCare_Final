package com.crackbrain.tanveen.icare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Jakir on 5/12/2015.
 */
public class DBAdapter {

    private DBHelper dbHelper;
    private SQLiteDatabase database;

    public DBAdapter(Context context){
        dbHelper=new DBHelper(context);
    }

    public void openDB(){
        database=dbHelper.getWritableDatabase();
    }
    public void closeDB(){
        dbHelper.close();
    }

    public long addEmployee(User user){
        ContentValues values=new ContentValues();
        values.put(DBHelper.NAME_FIELD, user.getName());
        values.put(DBHelper.AGE_FIELD, user.getAge());
        values.put(DBHelper.EMAIL_FIELD, user.getEmail());
        values.put(DBHelper.ADDRESS_FIELD, user.getAddress());
        values.put(DBHelper.BLOOD_FIELD, user.getBlood());
        values.put(DBHelper.MOBILE_FIELD, user.getMobile());

        long inserted=database.insert(dbHelper.TABLE_User,null,values);

        return inserted;
    }
    public long updateEmployee(User user,int id){
        ContentValues values=new ContentValues();
        values.put(DBHelper.NAME_FIELD, user.getName());
        values.put(DBHelper.AGE_FIELD, user.getAge());
        values.put(DBHelper.EMAIL_FIELD, user.getEmail());
        values.put(DBHelper.ADDRESS_FIELD, user.getAddress());
        values.put(DBHelper.BLOOD_FIELD, user.getBlood());
        values.put(DBHelper.MOBILE_FIELD, user.getMobile());

        long inserted=database.update(dbHelper.TABLE_User,values,DBHelper.ID_FIELD+" = "+id,null);

        return inserted;
    }


    public ArrayList<User> getEmployeeInfo(){
        ArrayList<User> users =new ArrayList<User>();
        database=dbHelper.getReadableDatabase();

        Cursor cursor=database.query(DBHelper.TABLE_User, null, null, null, null, null, null);
        if(cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();
            for(int i=0;i<cursor.getCount();i++){
                String name=cursor.getString(cursor.getColumnIndex(DBHelper.NAME_FIELD));
                int id= Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBHelper.ID_FIELD)));
                String email=cursor.getString(cursor.getColumnIndex(DBHelper.EMAIL_FIELD));
                String age=cursor.getString(cursor.getColumnIndex(DBHelper.AGE_FIELD));
                String address=cursor.getString(cursor.getColumnIndex(DBHelper.ADDRESS_FIELD));
                String city=cursor.getString(cursor.getColumnIndex(DBHelper.BLOOD_FIELD));
                String country=cursor.getString(cursor.getColumnIndex(DBHelper.MOBILE_FIELD));

                User user =new User(id,name,age,email,address,city,country);
                users.add(user);
                cursor.moveToNext();
            }
        }
        cursor.close();
        database.close();
        return users;
    }
    public ArrayList<User> getEmployeeInfoById(int empId){
        ArrayList<User> users =new ArrayList<User>();
        database=dbHelper.getReadableDatabase();

        String queryy="select * from "+DBHelper.TABLE_User +" where "+DBHelper.TABLE_User +"."+DBHelper.ID_FIELD+" = "
                +empId;

        Cursor cursor=database.rawQuery(queryy,null);
        if(cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();
            for(int i=0;i<cursor.getCount();i++){
                String name=cursor.getString(cursor.getColumnIndex(DBHelper.NAME_FIELD));
                int id= Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBHelper.ID_FIELD)));
                String email=cursor.getString(cursor.getColumnIndex(DBHelper.EMAIL_FIELD));
                String title=cursor.getString(cursor.getColumnIndex(DBHelper.AGE_FIELD));
                String address=cursor.getString(cursor.getColumnIndex(DBHelper.ADDRESS_FIELD));
                String city=cursor.getString(cursor.getColumnIndex(DBHelper.BLOOD_FIELD));
                String country=cursor.getString(cursor.getColumnIndex(DBHelper.MOBILE_FIELD));

                User user =new User(id,name,title,email,address,city,country);
                users.add(user);
                cursor.moveToNext();
            }
        }
        cursor.close();
        database.close();
        return users;
    }

    public ArrayList<User> getEmployeeInfoByName(String eName){
        ArrayList<User> users =new ArrayList<User>();
        database=dbHelper.getReadableDatabase();

        String queryy="select * from "+DBHelper.TABLE_User +" WHERE "+DBHelper.TABLE_User +"."+DBHelper.NAME_FIELD+" LIKE '%"+eName+"%'";


        Cursor cursor=database.rawQuery(queryy,null);
        if(cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();
            for(int i=0;i<cursor.getCount();i++){
                String name=cursor.getString(cursor.getColumnIndex(DBHelper.NAME_FIELD));
                int id= Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBHelper.ID_FIELD)));
                String email=cursor.getString(cursor.getColumnIndex(DBHelper.EMAIL_FIELD));
                String title=cursor.getString(cursor.getColumnIndex(DBHelper.AGE_FIELD));
                String address=cursor.getString(cursor.getColumnIndex(DBHelper.ADDRESS_FIELD));
                String city=cursor.getString(cursor.getColumnIndex(DBHelper.BLOOD_FIELD));
                String country=cursor.getString(cursor.getColumnIndex(DBHelper.MOBILE_FIELD));

                User user =new User(id,name,title,email,address,city,country);
                users.add(user);
                cursor.moveToNext();
            }
        }
        cursor.close();
        database.close();
        return users;
    }


    public void delete(long id) {
        database=dbHelper.getReadableDatabase();
        database.delete(DBHelper.TABLE_User, DBHelper.ID_FIELD + "=?",
                new String[] { String.valueOf(id) });

        database.close();
    }

    public void allDelete() {
        database=dbHelper.getReadableDatabase();
        database.delete(dbHelper.TABLE_User, null, null);
        database.close();
    }



}
