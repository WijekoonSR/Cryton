package com.application.ultimatee_bookreader;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

public class databaseHelper extends SQLiteOpenHelper {



    private static final int database_version = 1;
    private static  final String databse_Name = "contact.db";
    private static  final String table_Name = "contact";
    private static  final String databse_fName = "fName";
    private static  final String databse_lName = "lName";
    private static  final String databse_Email = "email";
    private static  final String databse_Password = "password";
    private static  final String databse_cPassword = "cPassword";
    SQLiteDatabase db;


    private static final String table_Create = "create table contact (email String primary key noy null auto_increment,"+" fName text not null, lName text not null, password text not null, cPassword text not null)";


    public databaseHelper (Context context){

        super(context ,databse_Name, null , database_version);
    }


    public void onCreate(SQLiteDatabase sqLiteiDatabase) {

        sqLiteiDatabase.execSQL(table_Create);
        this.db = sqLiteiDatabase;



    }


    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String query = "DROP Table if EXISTS"+table_Create;
        sqLiteDatabase.execSQL(query);
        this.onCreate(sqLiteDatabase);

    }


    public void insertContact(contact c ){

        db = this.getWritableDatabase();
        ContentValues values = new  ContentValues();


        values.put(databse_fName,c.getfName());
        values.put(databse_lName,c.getlName());
        values.put(databse_Email,c.getEmail());
        values.put(databse_Password,c.getcPassword());
        values.put(databse_cPassword,c.getcPassword());

        db.insert(table_Name,null, values);





    }
}
