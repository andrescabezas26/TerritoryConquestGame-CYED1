package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * The ManagerPersistence class reads and returns the contents of two text files, Countries.txt and
 * Edges.txt.
 */
public class ManagerPersistence {

    public ManagerPersistence() {}

    /**
     * This function reads a file containing a list of countries and returns a string with the contents
     * of the file.
     * 
     * @return The method is returning a String containing the contents of the "Countries.txt" file
     * located in the "data" folder of the project directory. If there is an IOException, the method
     * will return null.
     */
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

    /**
     * This function reads the contents of a file named Edges.txt and returns it as a string.
     * 
     * @return The method is returning a String containing the contents of the "Edges.txt" file located
     * in the "data" directory of the project. If there is an IOException, the method will return null.
     */
    public String getEdges() {
        File projectDir = new File(System.getProperty("user.dir"));

        FileReader countriesFile = null;
        BufferedReader lectorC = null;

        try {
            countriesFile = new FileReader(projectDir + "/data/Edges.txt");
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