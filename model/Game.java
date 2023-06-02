package model;

import datastructures.*;
import java.util.*;

public class Game {

    private GraphAdjacencyList<String> graphAdjacencyList;
    private GraphMatrix<String> graphMatrix;
    private ManagerPersistence managerPersistence;
    private Player[] players;

    public Game() {
        this.graphAdjacencyList = new GraphAdjacencyList<>(true);
        this.graphMatrix = new GraphMatrix<>(true);
        this.managerPersistence = new ManagerPersistence();
        this.players = new Player[2];
        players[0] = new Player("Player 1");
        players[1] = new Player("Player 2");
        addCountries();
        addEdges();
        setPlayersTerritories();
    }

    public void setNamesPlayers(String name1, String name2) {
        players[0].setName(name1);
        players[1].setName(name2);
    }

    public void setPlayersTerritories() {
        int randomValue = (int) (Math.random() * 50);
        players[0].addTerrytory(
                graphAdjacencyList.getVertexes().get(randomValue).getValue());
        players[0].setScore(1);

        int randomValue2 = 0;
        do {
            randomValue2 = (int) (Math.random() * 50);
        } while (randomValue == randomValue2);
        players[1].addTerrytory(
                graphAdjacencyList.getVertexes().get(randomValue2).getValue());
        players[1].setScore(1);
    }

    // Imprime territorios conquistados de un player
    public String printPlayerTerritories(int player) {
        StringBuilder msj = new StringBuilder();
        msj.append("TERRITORIES: \n");
        for (int i = 0; i < players[player].getConquistedCountries().size(); i++) {
            String territory = players[player].getConquistedCountries().get(i);
            msj.append((i + 1) + ") " + territory + "\n");
        }
        return msj.toString();
    }

    public String actualTerritory(int player) {
        return players[player].location();
    }

    /**
     * The function prints the adjacent territories and the number of troops
     * required for each.
     * 
     * @return The method is returning a string that contains the names of the
     *         adjacent territories and
     *         the number of troops required to attack them.
     */
    public String printAdjacentsAndWeight(int player) {
        StringBuilder msj = new StringBuilder();
        msj.append("TERRITORY   TROOPS_REQUIRED");
        for (int i = 0; i < players[player].getConquistedCountries().size(); i++) {
            ArrayList<Vertex<String>> adjacents = graphAdjacencyList.getAdjacencyList().get(graphAdjacencyList.searchVertex(players[player].getConquistedCountries().get(i))).getAdjacents();
            for (Vertex<String> adjacent : adjacents) {
                if(!players[0].isTerritoryConquisted(adjacent.getValue()) && !players[1].isTerritoryConquisted(adjacent.getValue())){
                    msj.append(adjacent.getValue()).append(" " + adjacent.getWeight());
                }
            }
            msj.append("\n" + (i + 1) + ") ").append(getAdjacents.get(i).getVertex2().getValue())
                    .append(" " + getAdjacents.get(i).getWeight());
        }
        return msj.toString();
    }

    public String printTerritoryAdjacentsByIndex(int posTerritory, int player) {
        String territoryName = players[player].getConquistedCountries().get(posTerritory);
        int graphPos = graphAdjacencyList.searchVertex(territoryName);
        return graphAdjacencyList
                .getVertexes()
                .get(graphPos)
                .printAdjacentsAndWeight();
    }

    public String printTerritoryAdjacentsByName(String territoryName) {
        if (graphAdjacencyList.searchVertex(territoryName.toUpperCase()) == -1) {
            return "This territory doesn't exists or is misspelled";
        }
        String territory = graphAdjacencyList
                .getVertexes()
                .get(graphAdjacencyList.searchVertex(territoryName.toUpperCase()))
                .getValue();
        int graphPos = graphAdjacencyList.searchVertex(territoryName);
        return graphAdjacencyList
                .getVertexes()
                .get(graphPos)
                .printAdjacentsAndWeight();
    }

    public String printTerritoryMinimumPath(
            String territoryName,
            String territoryDestination) {
        if (graphAdjacencyList.searchVertex(territoryName.toUpperCase()) == -1) {
            return "This territory doesn't exists or is misspelled";
        }
        StringBuilder msj = new StringBuilder();
        List<String> lista = reconstructPath(
                graphAdjacencyList.dijkstra(territoryName.toUpperCase()),
                territoryName,
                territoryDestination.toUpperCase());
        if (lista == null) {
            return ("There isn't a mimium path from:" +
                    territoryName.toUpperCase() +
                    " to " +
                    territoryDestination.toUpperCase());
        }
        String[] x = new String[2];
        msj.append(
                "The List of territories to go from " +
                        territoryName.toUpperCase() +
                        " to " +
                        territoryDestination.toUpperCase() +
                        " are:\n");
        for (int i = 0; i < lista.size(); i++) {
            if (i == 0) {
                x = lista.get(i).split("/");
                msj.append(x[0] + "\n");
            } else {
                msj.append(lista.get(i) + "\n");
            }
        }
        msj.append("The cost of troops will be: " + x[1]);
        return msj.toString();
    }

    public List<String> reconstructPath(
            Map<String, DistanceInfo<String>> previousVertices,
            String startVertexValue,
            String destinationVertexValue) {
        List<String> path = new ArrayList<>();
        String currentVertex = destinationVertexValue;
        double totalWeight = previousVertices.get(currentVertex).getDistance();
        while (currentVertex != null && !currentVertex.equals(startVertexValue)) {
            path.add(currentVertex);
            currentVertex = previousVertices.get(currentVertex).getPreviousVertex().getValue();
        }

        if (currentVertex != null && currentVertex.equals(startVertexValue)) {
            path.add(startVertexValue + "/" + totalWeight);
            Collections.reverse(path);
            return path;
        } else {
            return Collections.emptyList();
        }
    }

    // Agrega los paises a los grafos
    public void addCountries() {
        String[] countries = managerPersistence.getCountries().split("\n");
        for (String country : countries) {
            graphAdjacencyList.addVertex(country);
            graphMatrix.addVertex(country);
        }
    }

    // Agrega las aristas a los grafos
    public void addEdges() {
        String[] countriesEdges = managerPersistence.getEdges().split("\n");
        for (String countryEdges : countriesEdges) {
            String[] edges = countryEdges.split(",");
            graphAdjacencyList.addEdge(
                    edges[0],
                    edges[1],
                    Double.parseDouble(edges[2]));
            graphMatrix.addEdge(edges[0], edges[1], Double.parseDouble(edges[2]));
        }
    }

    public String calculateScore() {
        double score1 = players[0].getScore();
        double score2 = players[1].getScore();

        String msj = "\nThe Score of " +
                players[0].getName().toUpperCase() +
                " was: " +
                score1 +
                "\nThe Score of " +
                players[0].getName().toUpperCase() +
                " was: " +
                score2;
        if (score1 > score2) {
            return "The winner is: " + players[0].getName().toUpperCase() + msj;
        } else if (score1 == score2) {
            return "There's a draw " + msj;
        } else {
            return "The winner is: " + players[1].getName().toUpperCase() + msj;
        }
    }

    public String finishForGiveUp(int player) {
        int win = 0;
        if (player == 0) {
            win = 1; // Si entra 0, gana el 1 por rendirse el 0
        }

        return "The winner is: " + players[win].getName().toUpperCase() + "\n The score was: "
                + players[win].getScore();

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
