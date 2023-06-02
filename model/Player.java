package model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The Player class represents a player in a game with properties such as name, conquered territories,
 * troops, and score.
 */
public class Player {

    private String name;
    private ArrayList<String> conquistedCountries;
    private int troops;
    private double score;

    public Player(String name) {
        this.name = name;
        this.conquistedCountries = new ArrayList<>();
        this.score = 0;
        this.troops = 100000;
    }

    /**
     * @return the territory where is the player
     */
    public String location() {
        return conquistedCountries.get(conquistedCountries.size() - 1);
    }

    /**
     * The function returns the index of the last element in the "conquistedCountries" list.
     * 
     * @return The method `locationInt()` is returning an integer value which is the size of the
     * `conquistedCountries` list minus 1.
     */
    public int locationInt(){
        return conquistedCountries.size()-1;
    }

    /**
     * This Java function returns the index of a given territory in a list of conquered countries.
     * 
     * @param terry a String representing the name of a territory.
     * @return The method returns an integer value, which is the index of the specified territory in
     * the list of conquered countries. If the territory is not found in the list, the method returns
     * 0.
     */
    public int indexTerritory(String terry){
        for (int index = 0; index < conquistedCountries.size(); index++) {
            if (terry.equals(conquistedCountries.get(index))) {
                return index;
            }
        }
        return 0;
    }

    /**
     * The function swaps the positions of two elements in a list of conquered countries based on their
     * indices.
     * 
     * @param index1 The first index or position of the element to be swapped in the
     * "conquistedCountries" list.
     * @param index2 The parameter "index2" is a String variable representing the index or position of
     * a territory in a list called "conquistedCountries". This method uses the "Collections.swap"
     * method to swap the positions of two territories in the list based on their indexes.
     */
    public void swap(String index1, String index2) {
        Collections.swap(conquistedCountries, indexTerritory(index1), indexTerritory(index2));
    }

    /**
     * Add a terrytory
     * 
     * @param terrytory
     * @return
     */
    public boolean addTerrytory(String terrytory) {
        return conquistedCountries.add(terrytory);
    }

    /**
     * Verifica si el territorio ya fue conquistado
     * 
     * @param nameTerritory
     * @return boolean
     */
    public boolean isTerritoryConquisted(String nameTerritory) {
        for (String string : conquistedCountries) {
            if (string.equals(nameTerritory)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return ArrayList<String> return the conquistedCountries
     */
    public ArrayList<String> getConquistedCountries() {
        return conquistedCountries;
    }

    /**
     * @param conquistedCountries the conquistedCountries to set
     */
    public void setConquistedCountries(ArrayList<String> conquistedCountries) {
        this.conquistedCountries = conquistedCountries;
    }

    /**
     * @return double return the score
     */
    public double getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(double score) {
        this.score = score;
    }

    /**
     * @return int return the troops
     */
    public int getTroops() {
        return troops;
    }

    /**
     * @param troops the troops to set
     */
    public void setTroops(int troops) {
        this.troops = troops;
    }

}
