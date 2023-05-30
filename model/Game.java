package model;

import datastructures.GraphAdjacencyList;

public class Game {

    private GraphAdjacencyList<String> graphAdjacencyList;
    private ManagerPersistence managerPersistence;
    private Player[] players;

    public Game() {

        this.graphAdjacencyList = new GraphAdjacencyList<>(true);
        this.managerPersistence = new ManagerPersistence();
        this.players = new Player[2];

        addCountries();
        addEdges();
    }

    public void savedata() {

    }

    public void addCountries() {
        String[] countries = managerPersistence.getCountries().split("\n");
        for (String country : countries) {
            graphAdjacencyList.addVertex(country);
        }
    }

    public void addEdges() {
        String[] countriesEdges = managerPersistence.getEdges().split("\n");
        for (String countryEdges : countriesEdges) {
            String[] edges = countryEdges.split(",");
            graphAdjacencyList.addEdge(edges[0], edges[1], Double.parseDouble(edges[2]));
        }
    }

}
