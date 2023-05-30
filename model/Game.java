package model;

import datastructures.GraphAdjacencyList;

public class Game {

    private GraphAdjacencyList grafolista;
    private ManagerPersistence managerPersistence;

    public void addCountries() {
        String[] countries = managerPersistence.getCountries().split("\n");

    }

}
