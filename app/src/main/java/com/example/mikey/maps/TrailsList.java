package com.example.mikey.maps;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Workstation2.0 on 11/9/2016.
 */

public class TrailsList {
    String fileName = "words.txt";
    String line = null;
    String[] fields;
    ArrayList<Trail> trails = new ArrayList<Trail>();

    private TrailsList() {
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
            fields = line.split(":");
            trails.add(new Trail(fields[0],fields[1],fields[2],fields[3]));
            }
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
