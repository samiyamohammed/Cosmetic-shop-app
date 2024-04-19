package com.rachana.Cosmei;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.rachana.Cosmei.model.Products;

public class DBhelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Login.db";
    private static final String TABLE_NAME = "cart";
    private static final String COL1 = "productName";
    private static final String COL2 = "quantity"; // Add a quantity column
    private static final String COL3 = "price"; // Add a quantity column


    public DBhelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(username TEXT primary key,password TEXT)");
        String createTable = "CREATE TABLE " + TABLE_NAME + " ("
                + COL1 + " TEXT PRIMARY KEY,"
                + COL2 + " INTEGER,"
                + COL3 + " REAL)"; // Use REAL for price if it's a floating-point number
        MyDB.execSQL(createTable);
    }


    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("DROP TABLE IF EXISTS users");
        MyDB.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(MyDB);
    }



    public Boolean insertData(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long results = MyDB.insert("users", null, contentValues);
        return results != -1;
    }

    public Boolean addData(Products product, int quantity) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, product.getProductName());
        contentValues.put(COL2, quantity);
        contentValues.put(COL3, product.getProductPrice());

        Log.d("DBhelper", "addData: Adding " + product.getProductName() + " to " + TABLE_NAME);
        long results = MyDB.insertWithOnConflict(TABLE_NAME, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
        return results != -1;
    }


    public Cursor getData() {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        return MyDB.rawQuery(query, null);
    }

    public boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM users WHERE username=?", new String[]{username});
        return cursor.getCount() > 0;
    }

    public boolean checkusernamepassword(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM users WHERE username=? AND password=?", new String[]{username, password});
        return cursor.getCount() > 0;
    }

    public void clearCart() {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        MyDB.delete(TABLE_NAME, null, null);
        MyDB.close();
    }

}
