package model;

import java.util.ArrayList;

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
