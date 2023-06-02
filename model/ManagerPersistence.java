package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * The ManagerPersistence class reads and returns the contents of two text
 * files, Countries.txt and
 * Edges.txt.
 */
public class ManagerPersistence {

    public ManagerPersistence() {
    }

    /**
     * This function reads a file containing a list of countries and returns a
     * string with the contents
     * of the file.
     * 
     * @return The method is returning a String containing the contents of the
     *         "Countries.txt" file
     *         located in the "data" folder of the project directory. If there is an
     *         IOException, the method
     *         will return null.
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

    public String getCountriesTest() {
        File countriesFile = new File(getClass().getResource("/data/Edges.txt").getFile());
        BufferedReader lectorC = null;

        try {
            FileReader fileReader = new FileReader(countriesFile);
            lectorC = new BufferedReader(fileReader);

            String linea = lectorC.readLine();
            StringBuilder countries = new StringBuilder();

            while (linea != null) {
                countries.append(linea).append("\n");
                linea = lectorC.readLine();
            }

            return countries.toString();

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
     * This function reads the contents of a file named Edges.txt and returns it as
     * a string.
     * 
     * @return The method is returning a String containing the contents of the
     *         "Edges.txt" file located
     *         in the "data" directory of the project. If there is an IOException,
     *         the method will return null.
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

    public String getEdgesTest() {
        File edgesFile = new File(getClass().getResource("/data/Edges.txt").getFile());
        BufferedReader reader = null;

        try {
            FileReader fileReader = new FileReader(edgesFile);
            reader = new BufferedReader(fileReader);

            String line = reader.readLine();
            StringBuilder edges = new StringBuilder();

            while (line != null) {
                edges.append(line).append("\n");
                line = reader.readLine();
            }

            return edges.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return null;
    }

}