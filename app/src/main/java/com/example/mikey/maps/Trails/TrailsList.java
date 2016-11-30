package com.example.mikey.maps.Trails;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Workstation2.0 on 11/9/2016.
 */

public class TrailsList {
    String fileName = "trails.txt";
    String line = null;
    String[] fields;
    ArrayList<Trail> trails = new ArrayList<Trail>();
    Context ctx;
    DatabaseOperations dop;
    int lineCount;

    public TrailsList(Context ctx) {

        System.out.println("in trailsList");
        try {
            this.ctx = ctx;
            dop = new DatabaseOperations(ctx);
            // FileReader reads text files in the default encoding.
            //FileReader fileReader = new FileReader(fileName);
            InputStream inputStream = ctx.getAssets().open("trails.txt");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            // Always wrap FileReader in BufferedReader.
            //BufferedReader bufferedReader = new BufferedReader(fileReader);
            lineCount = 0;
            line = bufferedReader.readLine();
            if(Integer.parseInt(line)!= dop.getDatabase_version()) {
                System.out.println("New database version");
                System.out.println("trails size before " + dop.getTrailsCount());
                SQLiteDatabase db = dop.getWritableDatabase();
                dop.onUpgrade(db, dop.getDatabase_version(), Integer.parseInt((line)));
                System.out.println("trails size after " + dop.getTrailsCount());
                while ((line = bufferedReader.readLine()) != null) {
                    fields = line.split(":");
                    Trail tempTrail = new Trail(fields[0], fields[1], fields[2], fields[3], " ");
                    System.out.println("adding trail " + line);
                    dop.addTrail(tempTrail);

                    //trails.add(new Trail(fields[0],fields[1],fields[2],fields[3]));


                }
            }



            //System.out.println("trails first location " + trails.get(0).getLatitude() + ", " + trails.get(0).getLongtitude());
            // Always close files.
            bufferedReader.close();
        } catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        } catch(IOException ex) {
            System.out.println(
                   "Error reading file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }

    public List<Trail> getTrailList(){
        System.out.println("trails size " + dop.getTrailsCount());
        //System.out.println("trails first location " + trails.get(0).getLatitude() + ", " + trails.get(0).getLongtitude());
        return dop.getAllTrails();
    }
}
