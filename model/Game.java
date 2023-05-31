package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

import datastructures.GraphAdjacencyList;
import datastructures.GraphMatrix;
import datastructures.Vertex;

public class Game {

    private GraphAdjacencyList<String> graphAdjacencyList;
    private GraphMatrix<String> graphMatrix;
    private ManagerPersistence managerPersistence;
    private Player[] players;

    public Game() {

        this.graphAdjacencyList = new GraphAdjacencyList<>(true);
        this.graphMatrix= new GraphMatrix<>(true);
        this.managerPersistence = new ManagerPersistence();
        this.players = new Player[2];
        players[0]= new Player("Player 1");
        players[1]= new Player("Player 2");
        addCountries();
        addEdges();
        setPlayersTerritories();
    }

    public void setPlayersTerritories(){
        ArrayList<String> territories = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            territories.add(graphAdjacencyList.getVertices().get(i).getValue());
        }
        players[0].setConquistedCountries(territories);
        ArrayList<String> territories2 = new ArrayList<>();
        for (int i = 25; i < 50; i++) {
            territories2.add(graphAdjacencyList.getVertices().get(i).getValue());
        }
        players[1].setConquistedCountries(territories2);
    }

    public String printPlayerTerritories(int player){
        StringBuilder msj = new StringBuilder();
        msj.append("TERRITORIES: \n");
        for (int i=0; i< players[player].getConquistedCountries().size();i++) {
            String territory= players[player].getConquistedCountries().get(i);
            msj.append((i+1)+") " + territory+"\n");
        }
        return msj.toString();
    }

    public String printTerritoryAdjacents(int posTerritory, int player){
        String territoryName= players[player].getConquistedCountries().get(posTerritory);
        int graphPos= graphAdjacencyList.searchVertex(territoryName);
        return graphAdjacencyList.getVertices().get(graphPos).printAdjacentsAndWeight();
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

    /**
     * @return ManagerPersistence return the managerPersistence
     */
    public ManagerPersistence getManagerPersistence() {
        return managerPersistence;
    }

    /**
     * @param managerPersistence the managerPersistence to set
     */
    public void setManagerPersistence(ManagerPersistence managerPersistence) {
        this.managerPersistence = managerPersistence;
    }

    /**
     * @return Player[] return the players
     */
    public Player[] getPlayers() {
        return players;
    }

    /**
     * @param players the players to set
     */
    public void setPlayers(Player[] players) {
        this.players = players;
    }

}
