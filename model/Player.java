package model;

import java.util.ArrayList;

public class Player {

    private String name;
    private ArrayList<String> conquistedCountries;
    private double score;

    public Player(String name) {
        this.name = name;
        this.conquistedCountries = new ArrayList<>();
        this.score = 0;
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

}