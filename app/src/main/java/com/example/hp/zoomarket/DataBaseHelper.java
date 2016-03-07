package com.example.hp.zoomarket;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 01.03.2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "Orders";

    // Contacts table name
    private static final String TABLE_ORDERS = "orders";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_ORDERED_NAME = "name";
    private static final String KEY_ORDERED_PH_NO = "phone_number";
    private static final String KEY_ITEM_NAME = "item_name";
    private static final String KEY_ITEM_COST = "item_cost";

    private final static String CREATE_ORDERS_TABLE = "CREATE TABLE " + TABLE_ORDERS + "("
            + KEY_ID + " INTEGER PRIMARY KEY," +
            KEY_ORDERED_NAME + " TEXT," +
            KEY_ORDERED_PH_NO + " TEXT" +
            KEY_ITEM_NAME + " TEXT" +
            KEY_ITEM_COST + " TEXT" + ")";

    private static final String TAG = "DataBaseHelper";



    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_ORDERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);

        // Create tables again
        onCreate(db);
    }


    public void addOrder(Order order) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ORDERED_NAME, order.getName()); // Contact Name
        values.put(KEY_ORDERED_PH_NO, order.getNumber()); // Contact Phone Number
        values.put(KEY_ITEM_NAME, order.getItemName()); // Contact Phone Number
        values.put(KEY_ITEM_COST, order.getCost()); // Contact Phone Number

        // Inserting Row
        db.insert(TABLE_ORDERS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    public Order getOrder(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ORDERS, new String[]{KEY_ID,
                        KEY_ORDERED_NAME, KEY_ORDERED_PH_NO, KEY_ITEM_NAME, KEY_ITEM_COST}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Order order = new Order(cursor.getString(1),
                Integer.parseInt(cursor.getString(2)), cursor.getString(3),Integer.parseInt(cursor.getString(4)));
        // return contact
        return order;
    }

    // Getting All Contacts
    public List<Order> getAllContacts(){
        List<Order> orderList = new ArrayList<Order>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ORDERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Order order = new Order();
                order.setName(cursor.getString(1));
                order.setNumber(Integer.parseInt(cursor.getString(2)));
                order.setItemName(cursor.getString(3));
                order.setCost(Integer.parseInt(cursor.getString(4)));
                // Adding contact to list
                orderList.add(order);
            } while (cursor.moveToNext());
        }

        // return contact list
        return orderList;
    }







}