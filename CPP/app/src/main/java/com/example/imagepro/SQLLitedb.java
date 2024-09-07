package com.example.imagepro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SQLLitedb extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="User_DB";
    private static final String TABLE_NAME="USERS";
    private static final String user_name="user_name";
    private static final String password="password";
    private static final String email_id="email_id";
    public SQLiteDatabase database;

    public SQLLitedb(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "+TABLE_NAME+" ("+user_name+" TEXT," +password+ " TEXT,"+email_id+" TEXT "+")");
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public boolean getUser(String user,String pass,String email1){

        SQLiteDatabase db=this.getReadableDatabase();
        String userC,passC,emailC;
        Cursor cursor=db.rawQuery(" SELECT * FROM "+TABLE_NAME,null);
        if(email1.equals(""))
        {
            while (cursor.moveToNext())
            {
                userC=cursor.getString(0);
                passC=cursor.getString(1);
                if (user.equals(userC) && pass.equals(passC))
                    return true;

            }
        }
        else
        {
            while (cursor.moveToNext())
            {
                userC=cursor.getString(0);
                emailC=cursor.getString(2);
                if(user.equals(userC) || email1.equals(emailC))
                    return false;
            }
            return true;
        }
        return false;
    }

    public void addUser(String un,String pass,String email){
        database=this.getWritableDatabase();
        ContentValues val=new ContentValues();

        val.put(user_name,un);
        val.put(password,pass);
        val.put(email_id,email);

        database.insert(TABLE_NAME,null,val);
    }
    public void openDb(){
        SQLiteDatabase db=getReadableDatabase();
    }
}
