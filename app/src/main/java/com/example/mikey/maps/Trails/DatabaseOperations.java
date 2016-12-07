package com.example.mikey.maps.Trails;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mikey.maps.Trails.Trail;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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
            + TRAIL_NAME + " TEXT,"
            + TRAIL_LATITUDE + " REAL,"
            + TRAIL_LONGITUDE + " REAL,"
            + TRAIL_ACTIVITY + " TEXT,"
            + TRAIL_DESCRIPTION + " TEXT" + " );";

    //sqlite query deletes all queries
    public static final String DELETE_QUERIESS = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DatabaseOperations(Context context) {
        super(context, DATABASE_NAME, null, database_version);
        Log.d("Database operations", "Database created");
    }

    public int getDatabase_version() {
        return database_version;
    }

    public void onCreate(SQLiteDatabase sdb) {
        sdb.execSQL(CREATE_QUERY);

        Log.d("Database operations", "Table created");
    }

    public void onUpgrade(SQLiteDatabase sdb, int oldVersion, int newVersion) {
        sdb.execSQL(DELETE_QUERIESS);
        onCreate(sdb);
    }

    public void onDowngrade(SQLiteDatabase sdb, int oldVersion, int newVersion) {
        onUpgrade(sdb, oldVersion, newVersion);
    }

    public void addTrail(Trail trail) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //System.out.println("storing " + trail.getLatitude());
        values.put(TRAIL_NAME, trail.getName());
        values.put(TRAIL_LATITUDE, trail.getLatitude());
        values.put(TRAIL_LONGITUDE, trail.getLongtitude());
        //System.out.println("adding type " + Arrays.toString(trail.getType()));
        values.put(TRAIL_ACTIVITY, Arrays.toString(trail.getType()));
        values.put(TRAIL_DESCRIPTION, trail.getDescription());

        //inserting row
        db.insert(TABLE_NAME, null, values);
        db.close();     //closing database connection
    }

    public Trail getTrail(String name) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{TRAIL_NAME,
                        TRAIL_LATITUDE, TRAIL_LONGITUDE, TRAIL_ACTIVITY, TRAIL_DESCRIPTION},
                TRAIL_NAME + "=?",
                new String[]{String.valueOf(name)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        //System.out.println("cursor activity: " + cursor.getString(3));
        String activity = cursor.getString(3);
        activity = activity.replace("[", "");
        activity = activity.replace("]", "");
        activity = activity.replaceAll("\\s", "");
        Trail trail = new Trail(cursor.getString(0), Double.parseDouble(cursor.getString(1)),
                Double.parseDouble(cursor.getString(2)), activity.split(","), cursor.getString(4));
        return trail;
    }

    public List<Trail> getAllTrails() {
        List<Trail> trailList = new ArrayList<Trail>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                //System.out.println("cursor activity: " + cursor.getString(3));
                //System.out.println("getting " + cursor.getString(1));
                String activity = cursor.getString(3);
                activity = activity.replace("[", "");
                activity = activity.replace("]", "");
                activity = activity.replaceAll("\\s", "");
                Trail trail = new Trail(cursor.getString(0), Double.parseDouble(cursor.getString(1)),
                        Double.parseDouble(cursor.getString(2)), activity.split(","),
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

    public int updateTrail(Trail trail) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TRAIL_NAME, trail.getName());
        values.put(TRAIL_LATITUDE, trail.getLatitude());
        values.put(TRAIL_LONGITUDE, trail.getLongtitude());
        values.put(TRAIL_ACTIVITY, Arrays.toString(trail.getType()));
        values.put(TRAIL_DESCRIPTION, trail.getDescription());

        // updating row
        return db.update(TABLE_NAME, values, TRAIL_NAME + " = ?",
                new String[]{String.valueOf(trail.getName())});
    }

    public boolean containsTrail(Trail trail) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{TRAIL_NAME,
                        TRAIL_LATITUDE, TRAIL_LONGITUDE, TRAIL_ACTIVITY, TRAIL_DESCRIPTION},
                TRAIL_NAME + "=?",
                new String[]{String.valueOf(trail.getName())}, null, null, null, null);
        if (cursor.moveToFirst()) {
            cursor.close();
            return true;//row was found
        } else {
            cursor.close();
            return false;//row was not found
        }
    }

    public boolean isEmpty(){
        if(getTrailsCount()==0){
            return true;
        }
        return false;
    }


    public void populateDatabase(Context ctx) {
        String fileName = "trails.txt";
        try {

            String line = null;
            String[] fields;


            DatabaseOperations dop = new DatabaseOperations(ctx);
            // FileReader reads text files in the default encoding.
            //FileReader fileReader = new FileReader(fileName);
            InputStream inputStream = ctx.getAssets().open("trails.txt");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            // Always wrap FileReader in BufferedReader.
            //BufferedReader bufferedReader = new BufferedReader(fileReader);


            if (isEmpty()) {
                //System.out.println(dop.getDatabase_version());
                //System.out.println("Database size: " + dop.getTrailsCount());
                SQLiteDatabase db = dop.getWritableDatabase();
                //System.out.println("trails size after " + dop.getTrailsCount());
                while ((line = bufferedReader.readLine()) != null) {
                    //System.out.println("line " + line);
                    fields = line.split(":");
                    //System.out.println("number of fields: " + fields.length);
                    //System.out.println("Activities" + fields[3]);
                    Trail tempTrail = new Trail(fields[0], fields[1], fields[2], fields[3], fields[4]);
                    //System.out.println("adding trail " + line);
                    dop.addTrail(tempTrail);

                    //trails.add(new Trail(fields[0],fields[1],fields[2],fields[3]));


                }
            }
            else{
                SQLiteDatabase db = dop.getWritableDatabase();
                //System.out.println("trails size after " + dop.getTrailsCount());
                while ((line = bufferedReader.readLine()) != null) {
                    //System.out.println("line " + line);
                    fields = line.split(":");
                    //System.out.println("number of fields: " + fields.length);
                    //System.out.println("Activities" + fields[3]);

                    Trail tempTrail = new Trail(fields[0], fields[1], fields[2], fields[3], fields[4]);
                    //System.out.println("adding trail " + line);
                    if(containsTrail(tempTrail)){
                        dop.updateTrail(tempTrail);
                    }
                    else {
                        dop.addTrail(tempTrail);
                    }

                    //trails.add(new Trail(fields[0],fields[1],fields[2],fields[3]));


                }
            }


            //System.out.println("trails first location " + trails.get(0).getLatitude() + ", " + trails.get(0).getLongtitude());
            // Always close files.
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }


    }
}
