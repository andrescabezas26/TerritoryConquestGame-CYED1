package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import datastructures.GraphMatrix;

public class ManagerPersistence {

    private final File PROJECT_DIR = new File(System.getProperty("user.dir"));
    private Gson gson;

    public ManagerPersistence() {
        gson = new Gson();
    }

    public String getCountries() {
        File projectDir = new File(System.getProperty("user.dir"));

        FileReader countriesFile = null;
        BufferedReader lectorC = null;

        try {
            countriesFile = new FileReader(projectDir + "/data/Countries.txt");
            lectorC = new BufferedReader(countriesFile);

            String linea = lectorC.readLine();
            String countries = "";

            while (linea != null) {
                countries += linea + "\n";
                linea = lectorC.readLine();
            }

            return countries;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (lectorC != null)
                    lectorC.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return null;
    }

}