package com.example.outerbank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(9876543210,'Pramod',10000,'ajay@gmail.com','012345678911','MYBK1234567')");
        db.execSQL("insert into user_table values(8765432109,'Nikhil',10000,'raghav@gmail.com','123456789012','MYBK1234567')");
        db.execSQL("insert into user_table values(7654321089,'Pallavi',10000,'ankit@gmail.com','123434345667','MYBK1234567')");
        db.execSQL("insert into user_table values(6543210987,'Dharmesh',10000,'gaurav@gmail.com','123456767899','MYBK1234567')");
        db.execSQL("insert into user_table values(7410852963,'Nirav',10000,'kabir@gmail.com','98765645553','MYBK1234567')");
        db.execSQL("insert into user_table values(8529637410,'Tarun',10000,'anmol@gmail.com','456784536779','MYBK1234567')");
        db.execSQL("insert into user_table values(9637418520,'Vicky',10000,'uday@gmail.com','2345645366787','MYBK1234567')");
        db.execSQL("insert into user_table values(7538694120,'Kinjal',10000,'snehil@gmail.com','4365476432423','MYBK1234567')");
        db.execSQL("insert into user_table values(9516238470,'Harshil',10000,'prakhar@gmail.com','23465765422435','MYBK1234567')");
        db.execSQL("insert into user_table values(8426759130,'Raj',10000,'ajinkya@gmail.com','3875475890594','MYBK1234567')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
