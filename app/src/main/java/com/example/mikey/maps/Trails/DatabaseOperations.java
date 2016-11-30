package com.example.mikey.maps.Trails;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mikey.maps.Trails.Trail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trooper on 11/19/2016.
 */

public class DatabaseOperations extends SQLiteOpenHelper {
    //All static variables
    //database version
    public static final int database_version = 1;
    //database name
    public static final String DATABASE_NAME = "Trail_Manager";

    //table name
    public static final String TABLE_NAME = "Trails";

    //Trails table Columns names
    public static final String TRAIL_NAME = "trail_name";
    public static final String TRAIL_LATITUDE = "Trail_Latitude";
    public static final String TRAIL_LONGITUDE = "Trail_Longitude";
    public static final String TRAIL_ACTIVITY = "Trail_Activity";
    public static final String TRAIL_DESCRIPTION = "Trail_Description";

    //sqlite query crates columns
    public String CREATE_QUERY = "CREATE TABLE " + TABLE_NAME + "("
            +TRAIL_NAME+ " TEXT,"
            +TRAIL_LATITUDE+ " REAL,"
            + TRAIL_LONGITUDE+ " REAL,"
            + TRAIL_ACTIVITY+ " TEXT,"
            +TRAIL_DESCRIPTION+ " TEXT" + " );";

    //sqlite query deletes all queries
    public static final String DELETE_QUERIESS = "DROP TABLE IF EXISTS " +TABLE_NAME;

    public DatabaseOperations(Context context){
        super(context, DATABASE_NAME, null, database_version);
        Log.d("Database operations", "Database created");
    }

    public int getDatabase_version(){
        return database_version;
    }

    public void onCreate(SQLiteDatabase sdb){
        sdb.execSQL(CREATE_QUERY);
        Log.d("Database operations", "Table created");
    }

    public void onUpgrade(SQLiteDatabase sdb, int oldVersion, int newVersion){
        sdb.execSQL(DELETE_QUERIESS);
        onCreate(sdb);
    }

    public void onDowngrade(SQLiteDatabase sdb, int oldVersion, int newVersion){
        onUpgrade(sdb,oldVersion, newVersion);
    }

    public void addTrail(Trail trail) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //System.out.println("storing " + trail.getLatitude());
        values.put(TRAIL_NAME, trail.getName());
        values.put(TRAIL_LATITUDE, trail.getLatitude());
        values.put(TRAIL_LONGITUDE, trail.getLongtitude());
        values.put(TRAIL_ACTIVITY, trail.getType());
        values.put(TRAIL_DESCRIPTION, trail.getDescription());

        //inserting row
        db.insert(TABLE_NAME, null, values);
        db.close();     //closing database connection
    }
    public Trail getTrail(String name){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] { TRAIL_NAME,
                        TRAIL_LATITUDE, TRAIL_LONGITUDE,TRAIL_ACTIVITY,TRAIL_DESCRIPTION },
                        TRAIL_NAME + "=?",
                new String[] { String.valueOf(name) }, null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        Trail trail = new Trail(cursor.getString(0),Double.parseDouble(cursor.getString(1)),
                Double.parseDouble(cursor.getString(2)),cursor.getString(3), cursor.getString(4));
        return trail;
    }
    public List<Trail> getAllTrails(){
        List<Trail> trailList = new ArrayList<Trail>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                //System.out.println("getting " + cursor.getString(1));
                Trail trail = new Trail(cursor.getString(0),Double.parseDouble(cursor.getString(1)),
                        Double.parseDouble(cursor.getString(2)),cursor.getString(3),
                        cursor.getString(4));
                // Adding contact to list
                trailList.add(trail);
            } while (cursor.moveToNext());
        }

        // return contact list
        return trailList;

    }
    public int getTrailsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    public int updateTrail(Trail trail){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TRAIL_NAME, trail.getName());
        values.put(TRAIL_LATITUDE, trail.getLatitude());
        values.put(TRAIL_LONGITUDE, trail.getLongtitude());
        values.put(TRAIL_ACTIVITY, trail.getType());
        values.put(TRAIL_DESCRIPTION, trail.getDescription());

        // updating row
        return db.update(TABLE_NAME, values, TRAIL_NAME + " = ?",
                new String[] { String.valueOf(trail.getName()) });
    }

    public boolean containsTrail(Trail trail){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] { TRAIL_NAME,
                        TRAIL_LATITUDE, TRAIL_LONGITUDE,TRAIL_ACTIVITY,TRAIL_DESCRIPTION },
                TRAIL_NAME + "=?",
                new String[] { String.valueOf(trail.getName()) }, null, null, null, null);
        if(cursor.moveToFirst()){
            cursor.close();
            return true;//row was found
        }
        else{
            cursor.close();
            return false;//row was not found
        }
    }



}
