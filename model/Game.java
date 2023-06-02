package model;

import datastructures.*;
import java.util.*;

/**
 * The Game class represents a game of conquering territories, with methods for managing players,
 * territories, and game mechanics.
 */
public class Game {

    private GraphAdjacencyList<String> graphAdjacencyList;
    private GraphMatrix<String> graphMatrix;
    private ManagerPersistence managerPersistence;
    private Player[] players;
    private boolean isMatrix;
    public static final int COST_MILITAR_STRATEGY = 10000;

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
        isMatrix = false;
    }

    /**
     * This Java function returns the name of a player based on their index in an array of players.
     * 
     * @param player The parameter "player" is an integer that represents the index of a player in an
     * array of player objects. The method "getName" returns the name of the player at the specified
     * index.
     * @return The method `getName` is returning the name of the player at the index specified by the
     * `player` parameter. The name is obtained from the `players` array using the `getName` method of
     * the `Player` class.
     */
    public String getName(int player) {
        return players[player].getName();
    }
    
  

    /**
     * This function verifies if the game has finished based on the number of troops and conquered
     * countries of the players.
     * 
     * @return A boolean value is being returned.
     */
    public boolean verifyFinish(){
        if (players[0].getTroops() <= edgeWithMinorTroops() || players[1].getTroops() <= edgeWithMinorTroops()) {
            return true;
        } else if(players[0].getConquistedCountries().size() >= 20 || players[1].getConquistedCountries().size() >= 20){
            return true;
        } else{
            return false;
        }
    }

    public String casueFinish(){
        if (players[0].getTroops() <= edgeWithMinorTroops()){
            return " The player " + players[0].getName() + " hasn't troops. ";
        } else if(players[1].getTroops() <= edgeWithMinorTroops()) {
            return " The player " + players[1].getName() + " hasn't troops. ";
        } else if(players[0].getConquistedCountries().size() >= 20){
            return " The player " + players[0].getName() + " won by conquering more territories ";
        } else if(players[1].getConquistedCountries().size() >= 20){
            return " The player " + players[1].getName() + " won by conquering more territories ";
        } else{
            return " Skadoosh ";
        }
    }

    
    /**
     * This function returns the weight of the edge with the smallest weight in a graph represented by
     * an adjacency list.
     * 
     * @return The method `edgeWithMinorTroops` returns the weight of the edge with the smallest weight
     * in the graph.
     */
    public double edgeWithMinorTroops(){
        double minimunWeight = Double.POSITIVE_INFINITY;
        for (Vertex<String> vertex : graphAdjacencyList.getVertexes()) {
            for (Edge<String> edge : vertex.getAdjacents()) {
                if(edge.getWeight() < minimunWeight){
                    minimunWeight= edge.getWeight();
                } 
            }
        }
        return minimunWeight;
    }

    /**
     * This function allows a player to conquer a new territory in a game, given certain conditions.
     * 
     * @param player An integer representing the player who is trying to conquer a territory.
     * @param newTerritoryName The name of the territory that the player wants to conquer.
     * @return The method returns a String message that depends on the input parameters and the state
     * of the game. The possible messages are:
     * - "The territory doesn't exists or is misspelled"
     * - "This territory is already conquisted, select another one"
     * - "This territory isn't adjacent to [startName]"
     * - "[player's name] conquist [player's location] troops standing: [player
     */
    public String coquistTerritory(int player, String newTerritoryName) {
        int newPos= -1;
        double troopsCost=0.0;
        boolean isFound = false;
        String startName= players[player].location();
        if (!isMatrix) {
            newPos = graphAdjacencyList.searchVertex(newTerritoryName);
            if (newPos == -1) {
                return "The territory doesn't exists or is misspelled";
            }
            if (players[0].isTerritoryConquisted(newTerritoryName)
                    || players[1].isTerritoryConquisted(newTerritoryName)) {
                return "This territory is already conquisted, select another one";
            }
            for (Edge<String> edge : graphAdjacencyList.getVertexes().get(graphAdjacencyList.searchVertex(startName))
                    .getAdjacents()) {
                if (edge.getVertex2().getValue().equals(newTerritoryName)) {
                    isFound = true;
                    break;
                }
            }
            if (!isFound) {
                return "This territory isn't adjacent to " + startName;
            }
            troopsCost = graphAdjacencyList.searchEdge(startName, newTerritoryName).getWeight();

        } else {
            newPos = graphMatrix.searchVertex(newTerritoryName);
            if (newPos == -1) {
                return "The territory doesn't exists or is misspelled";
            }
            if (players[0].isTerritoryConquisted(newTerritoryName)
                    || players[1].isTerritoryConquisted(newTerritoryName)) {
                return "This territory is already conquisted, select another one";
            }
            for (String territory : graphMatrix.getAdjacents(graphMatrix.searchVertex(startName))) {
                if (territory.equals(newTerritoryName)) {
                    isFound = true;
                    break;
                }
            }
    
            if (!isFound) {
                return "This territory isn't adjacent to " + startName;
            }

            troopsCost = graphMatrix.searchEdge(startName, newTerritoryName).getWeight();
        }


        if (players[player].getTroops() >= troopsCost) {
            players[player].addTerrytory(newTerritoryName);
            players[player].setTroops((int) (players[player].getTroops() - troopsCost));
            players[player].setScore(players[player].getScore()+1);
            return players[player].getName() + " conquist " + players[player].location() + " troops standing: "
                    + players[player].getTroops();
        }

        return "The troops requeried to conquist: " + newTerritoryName + " are: " + troopsCost;
    }

    /**
     * The function prints a militar strategy based on a minimum spanning tree of edges.
     * 
     * @param minimunSpanningTree A list of edges representing the minimum spanning tree of a graph.
     * Each edge contains two vertices and a weight representing the cost of traversing that edge. In
     * this context, the graph likely represents territories that need to be defended and the weight
     * represents the number of troops needed to defend that territory. The method
     * @return The method `printMilitarStrategy` returns a string representation of the minimum
     * spanning tree of a graph, where each edge represents a necessary troop deployment between two
     * territories. If the input `minimunSpanningTree` is null, the method returns the string "Mistake
     * with military strategy".
     */
    public String printMilitarStrategy(List<Edge<String>> minimunSpanningTree) {

        if (minimunSpanningTree == null) {
            return "Mistake with military strategy";
        }

        StringBuilder sb = new StringBuilder("----MILITAR STRATEGY----");

        for (Edge<String> edge : minimunSpanningTree) {

            sb.append("\n Territory: ").append(edge.getVertex1().getValue()).append(" -- ")
                    .append(edge.getVertex2().getValue())
                    .append(" Necessary troops:  ").append(edge.getWeight());

        }

        return sb.toString();

    }

    /**
     * The function returns the number of troops of a specific player in a game.
     * 
     * @param numPlayer The parameter "numPlayer" is an integer that represents the index of the player
     * in the "players" array whose troops we want to retrieve.
     * @return The method `actualTroopsPlayer` returns a string that includes the number of troops of a
     * specific player in the game. The string is formatted as "Troops of the player: [number of
     * troops]".
     */
    public String actualTroopsPlayer(int numPlayer) {
        return "Troops of the player: " + players[numPlayer].getTroops();
    }

    /**
     * The function returns a string representing the military strategy of a player in a game, based on
     * the minimum spanning tree of the game's graph, and deducts the cost of the strategy from the
     * player's troops if they have enough.
     * 
     * @param numPlayer The parameter numPlayer is an integer representing the index of the player in
     * the players array for whom the military strategy is being calculated.
     * @return The method returns a String representing the military strategy for a player in a game.
     * If the player has enough troops to execute the strategy, the method also subtracts the cost of
     * the strategy from the player's troop count. If the player does not have enough troops, the
     * method returns a message indicating that.
     */
    public String militarStrategyPlayer(int numPlayer) {

        String militarStrategyPlayer = "";

        if (!isMatrix) {

            List<Edge<String>> minimumSpanningTree = graphAdjacencyList.prim(graphAdjacencyList.getVertexes()
                    .get(graphAdjacencyList.searchVertex(players[numPlayer].location())).getValue());
            militarStrategyPlayer = printMilitarStrategy(minimumSpanningTree);
            if (COST_MILITAR_STRATEGY <= players[numPlayer].getTroops()) {

                players[numPlayer].setTroops(players[numPlayer].getTroops() - COST_MILITAR_STRATEGY);

            } else {
                return "You don't have enough troops";
            }

        } else {

            List<Edge<String>> minimumSpanningTree = graphMatrix.prim(graphMatrix.getVertices()
                    .get(graphMatrix.searchVertex(players[numPlayer].location())).getValue());
            militarStrategyPlayer = printMilitarStrategy(minimumSpanningTree);
            if (COST_MILITAR_STRATEGY <= players[numPlayer].getTroops()) {

                players[numPlayer].setTroops(players[numPlayer].getTroops() - COST_MILITAR_STRATEGY);

            } else {
                return "You don't have enough troops";
            }

        }

        return militarStrategyPlayer;

    }

    /**
     * This function returns a string representation of all territories in a graph, either using a
     * matrix or adjacency list.
     * 
     * @return The method `showAllTerritories` is returning a string that represents all the
     * territories in the graph. The specific implementation depends on whether the graph is
     * represented as a matrix or an adjacency list. If the graph is represented as a matrix, the
     * method calls the `printTerritories` method of the `graphMatrix` object. If the graph is
     * represented as an adjacency list, the method calls the
     */
    public String showAllTerritories() {
        if (isMatrix) {
            return graphMatrix.printTerritories();
        } else {
            return graphAdjacencyList.printTerritories();
        }
    }

    /**
     * This function sets the names of two players in a game.
     * 
     * @param name1 The name of the first player.
     * @param name2 The parameter "name2" is a String variable that represents the name of the second
     * player. It is used in the method "setNamesPlayers" to set the name of the second player in an
     * array of Player objects.
     */
    public void setNamesPlayers(String name1, String name2) {
        players[0].setName(name1);
        players[1].setName(name2);
    }

    /**
     * This function sets the territories and scores for two players in a graph, either represented as
     * an adjacency list or a matrix.
     */
    public void setPlayersTerritories() {
        if (!isMatrix) {
            int randomValue = (int) (Math.random() * graphAdjacencyList.getVertexes().size()-1);
            players[0].addTerrytory(
                    graphAdjacencyList.getVertexes().get(randomValue).getValue());
            players[0].setScore(1);

            int randomValue2 = 0;
            do {
                randomValue2 = (int) (Math.random() * graphAdjacencyList.getVertexes().size()-1);
            } while (randomValue == randomValue2);
            players[1].addTerrytory(
                    graphAdjacencyList.getVertexes().get(randomValue2).getValue());
            players[1].setScore(1);

        } else {

            int randomValue = (int) (Math.random() * graphMatrix.getVertices().size()-1);
            players[0].addTerrytory(
                    graphMatrix.getVertices().get(randomValue).getValue());
            players[0].setScore(1);

            int randomValue2 = 0;
            do {
                randomValue2 = (int) (Math.random() * graphMatrix.getVertices().size()-1);
            } while (randomValue == randomValue2);
            players[1].addTerrytory(
                    graphMatrix.getVertices().get(randomValue2).getValue());
            players[1].setScore(1);

        }

    }

   
    /**
     * This Java function prints a list of territories conquered by a specific player.
     * 
     * @param player The parameter "player" is an integer representing the index of the player whose
     * territories are being printed.
     * @return The method is returning a string that contains a list of territories that have been
     * conquered by a specific player. The string starts with the header "TERRITORIES:" and then lists
     * each territory with a number and its name.
     */
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

        String msj1 = "";
        StringBuilder msj = new StringBuilder();

        if (!isMatrix) {

            msj1 = "TERRITORY   TROOPS_REQUIRED";
            ArrayList<Edge<String>> adjacents = graphAdjacencyList.getVertexes()
                    .get(graphAdjacencyList.searchVertex(players[player].location())).getAdjacents();
            msj = new StringBuilder();
            for (int j = 0; j < adjacents.size(); j++) {
                if (!players[0].isTerritoryConquisted(adjacents.get(j).getVertex2().getValue())
                        && !players[1].isTerritoryConquisted(adjacents.get(j).getVertex2().getValue())) {
                    msj.append("\n" + (j + 1) + ") ").append(adjacents.get(j).getVertex2().getValue())
                            .append("  " + adjacents.get(j).getWeight());
                }
            }
            if (msj.toString().isEmpty()) {
                return "No territories available to conquest in " + players[player].location();
            }

        } else {

            msj1 = "TERRITORY   TROOPS_REQUIRED";
            List<String> adjacents = graphMatrix.getAdjacents(graphMatrix.searchVertex(players[player].location()));
            msj = new StringBuilder();
            for (int j = 0; j < adjacents.size(); j++) {
                if (!players[0].isTerritoryConquisted(adjacents.get(j))
                        && !players[1].isTerritoryConquisted(adjacents.get(j))) {
                    msj.append("\n" + (j + 1) + ") ").append(adjacents.get(j))
                            .append("  " + graphMatrix.searchEdge(players[player].location(), adjacents.get(j)).getWeight());
                }
            }
            if (msj.toString().isEmpty()) {
                return "No territories available to conquest in " + players[player].location();
            }

        }

        return msj1 + msj.toString();

    }

    /**
     * This function returns the minimum path and cost of troops needed to travel from one territory to
     * another in a graph, either represented as an adjacency list or a matrix.
     * 
     * @param territoryName The name of the starting territory for finding the minimum path.
     * @param territoryDestination The destination territory to find the minimum path to.
     * @return The method is returning a String that represents the minimum path and cost of troops to
     * go from a given territory to a destination territory in a graph. If the given territories do not
     * exist or there is no minimum path between them, the method returns an error message.
     */
    public String printTerritoryMinimumPath(String territoryName, String territoryDestination) {

        if (!isMatrix) {
            if (graphAdjacencyList.searchVertex(territoryName.toUpperCase()) == -1) {
                return "This territory doesn't exists or is misspelled";
            }
            if (graphAdjacencyList.searchVertex(territoryDestination.toUpperCase()) == -1) {
                return "This territory doesn't exists or is misspelled";
            }
            StringBuilder msj = new StringBuilder();
            List<String> lista = reconstructPath(graphAdjacencyList.dijkstra(territoryName.toUpperCase()),
                    territoryName, territoryDestination.toUpperCase());
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
        } else {
            if (graphMatrix.searchVertex(territoryName.toUpperCase()) == -1) {
                return "This territory doesn't exists or is misspelled";
            }
            if (graphMatrix.searchVertex(territoryDestination.toUpperCase()) == -1) {
                return "This territory doesn't exists or is misspelled";
            }
            StringBuilder msj = new StringBuilder();
            List<String> lista = reconstructPath(graphMatrix.dijkstra(territoryName.toUpperCase()),
                    territoryName, territoryDestination.toUpperCase());
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
    }

    /**
     * This function reconstructs the shortest path between two vertices in a graph using a map of
     * previous vertices and their distances.
     * 
     * @param previousVertices A map that contains information about the previous vertices for each
     * vertex in a graph. The key is the vertex value and the value is an object of type DistanceInfo
     * that contains the distance and previous vertex information.
     * @param startVertexValue The value of the starting vertex in the graph.
     * @param destinationVertexValue The value of the vertex we want to reach in the graph.
     * @return The method returns a list of strings representing the path from the start vertex to the
     * destination vertex, along with the total weight of the path. If there is no path from the start
     * vertex to the destination vertex, an empty list is returned.
     */
    public List<String> reconstructPath(Map<String, DistanceInfo<String>> previousVertices, String startVertexValue,
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

 
    /**
     * The function adds countries as vertices to two different graph data structures.
     */
    public void addCountries() {
        String[] countries = managerPersistence.getCountries().split("\n");
        for (String country : countries) {
            graphAdjacencyList.addVertex(country);
            graphMatrix.addVertex(country);
        }
    }

    
    /**
     * The function adds edges to a graph data structure from a string representation of edges obtained
     * from a persistence manager.
     */
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

    /**
     * This function calculates the scores of two players and determines the winner or if there is a
     * draw.
     * 
     * @return The method is returning a String that indicates the winner of a game and the scores of
     * both players. If player 1 has a higher score than player 2, the method returns "The winner is:
     * [player 1's name]". If player 2 has a higher score than player 1, the method returns "The winner
     * is: [player 2's name]". If both
     */
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

    /**
     * The function returns the winner and score of a game where one player has given up.
     * 
     * @param player The parameter "player" is an integer that represents the player who gave up the
     * game. If the value of "player" is 0, it means that player 0 gave up and player 1 wins the game.
     * @return The method is returning a String that includes the name of the winner (the player who
     * did not give up) and their score.
     */
    public String finishForGiveUp(int player) {
        int win = 0;
        if (player == 0) {
            win = 1; // Si entra 0, gana el 1 por rendirse el 0
        }

        return "The winner is: " + players[win].getName().toUpperCase() + "\n The score was: "
                + players[win].getScore();

    }

    /**
     * This Java function allows a player to move their territory to a new location if they have
     * already conquered it.
     * 
     * @param territory A String representing the name of the territory that the player wants to move
     * to.
     * @param player The integer representing the player who is making the move.
     * @return If the player has conquered the territory, the method will return a string that says
     * "Now your Territory Actual is [territory name]". If the player has not conquered the territory,
     * the method will return a string that says "This Territory isn't conquisted".
     */
    public String moveTerritory(String territory, int player) {
        if (players[player].isTerritoryConquisted(territory)) {
            players[player].swap(territory, players[player].location());
            return "Now you Territory Actual is " + players[player].location();
        } else {
            return "This Territory isn't conquisted";
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

    /**
     * @return GraphAdjacencyList<String> return the graphAdjacencyList
     */
    public GraphAdjacencyList<String> getGraphAdjacencyList() {
        return graphAdjacencyList;
    }

    /**
     * @param graphAdjacencyList the graphAdjacencyList to set
     */
    public void setGraphAdjacencyList(GraphAdjacencyList<String> graphAdjacencyList) {
        this.graphAdjacencyList = graphAdjacencyList;
    }

    /**
     * @return GraphMatrix<String> return the graphMatrix
     */
    public GraphMatrix<String> getGraphMatrix() {
        return graphMatrix;
    }

    /**
     * @param graphMatrix the graphMatrix to set
     */
    public void setGraphMatrix(GraphMatrix<String> graphMatrix) {
        this.graphMatrix = graphMatrix;
    }

    /**
     * @return boolean return the isMatrix
     */
    public boolean isIsMatrix() {
        return isMatrix;
    }

    /**
     * @param isMatrix the isMatrix to set
     */
    public void setIsMatrix(boolean isMatrix) {
        this.isMatrix = isMatrix;
    }

}
