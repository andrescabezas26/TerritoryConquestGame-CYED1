package model;

import datastructures.*;
import java.util.*;

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

    public String getName(int player) {
        return players[player].getName();
    }

    public String coquistTerritory(int player, String newTerritoryName) {
        int newPos = graphAdjacencyList.searchVertex(newTerritoryName);
        if (newPos == -1) {
            return "The territory doesn't exists or is misspelled";
        }

        String startName=players[player].getConquistedCountries().get(players[player].locationInt());
        boolean isFound = false;
        for (Edge<String> edge : graphAdjacencyList.getVertexes().get(graphAdjacencyList.searchVertex(startName)).getAdjacents()) {
            if(edge.getVertex2().getValue().equals(newTerritoryName)){
                isFound = true;
                break;
            }
        }

        if(!isFound){
            return "This territory isn't adjacent to " + startName;
        }

        ///FALTA VERIFICAR QUE NO CONQUISTE UNO PREVIAMENTE CONQUISTADO/////////////
        

        double troopsCost = graphAdjacencyList
                .searchEdge(players[player].location(), graphAdjacencyList.getVertexes().get(newPos).getValue())
                .getWeight();

        if (players[player].getTroops() >= troopsCost) {
            players[player].addTerrytory(newTerritoryName);
            players[player].setTroops((int) (players[player].getTroops() - troopsCost));
            return players[player].getName() + " conquist " + players[player].location() + " troops standing: "
                    + players[player].getTroops();
        }

        return "The troops requeried to conquist: " + newTerritoryName + " are: " + troopsCost;

    }

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

    public String actualTroopsPlayer(int numPlayer) {
        String msj = "Troops of the player: " + players[numPlayer].getTroops();

        return msj;

    }

    public String militarStrategyPlayer(int numPlayer) {

        String militarStrategyPlayer = "";

        if (isMatrix == false) {

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

    public String showAllTerritories() {
        if (isMatrix) {
            return graphMatrix.printTerritories();
        } else {
            return graphAdjacencyList.printTerritories();
        }
    }

    public void setNamesPlayers(String name1, String name2) {
        players[0].setName(name1);
        players[1].setName(name2);
    }

    public void setPlayersTerritories() {
        if(isMatrix == false){
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
            
        }else{

             int randomValue = (int) (Math.random() * 50);
        players[0].addTerrytory(
                graphMatrix.getVertices().get(randomValue).getValue());
        players[0].setScore(1);

        int randomValue2 = 0;
        do {
            randomValue2 = (int) (Math.random() * 50);
        } while (randomValue == randomValue2);
        players[1].addTerrytory(
                graphMatrix.getVertices().get(randomValue2).getValue());
        players[1].setScore(1);
            
        }
       
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

        String msjj = "";
        StringBuilder msj = new StringBuilder();
        
        if(!isMatrix){

              msjj = "TERRITORY   TROOPS_REQUIRED";
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
        

        }else{

            msjj = "TERRITORY   TROOPS_REQUIRED";
        ArrayList<Edge<String>> adjacents = graphMatrix.getVertices()
                .get(graphMatrix.searchVertex(players[player].location())).getAdjacents();
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
            
        }

        return msjj + msj.toString();
      
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
