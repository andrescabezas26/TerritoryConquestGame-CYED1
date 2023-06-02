package model;

import datastructures.*;
import java.util.*;

/**
 * The Game class represents a game of conquering territories, with methods for
 * managing players,
 * territories, and game mechanics.
 */
public class GameForTest {

    private GraphAdjacencyList<String> graphAdjacencyList;
    private GraphMatrix<String> graphMatrix;
    private Player[] players;
    private ManagerPersistence managerPersistence;
    private boolean isMatrix;
    public static final int COST_MILITAR_STRATEGY = 10000;

    public GameForTest() {
        this.managerPersistence = new ManagerPersistence();
        this.graphAdjacencyList = new GraphAdjacencyList<>(true);
        this.graphMatrix = new GraphMatrix<>(true);
        this.players = new Player[2];
        players[0] = new Player("Player 1");
        players[1] = new Player("Player 2");
        addCountries();
        addEdges();
        setPlayersTerritories();
        isMatrix = false;
    }

    /**
     * The function adds countries as vertices to two different graph data
     * structures.
     */
    public void addCountries() {
        String countries = "ESTADOS_UNIDOS\n" +
                "CANADA\n" +
                "MEXICO\n" +
                "GUATEMALA\n" +
                "BELICE\n" +
                "HONDURAS\n" +
                "EL_SALVADOR\n" +
                "NICARAGUA\n" +
                "COSTA_RICA\n" +
                "PANAMA\n" +
                "BRASIL\n" +
                "ARGENTINA\n" +
                "COLOMBIA\n" +
                "PERU\n" +
                "VENEZUELA\n" +
                "CHILE\n" +
                "ECUADOR\n" +
                "BOLIVIA\n" +
                "URUGUAY\n" +
                "NIGERIA\n" +
                "EGIPTO\n" +
                "SUDAFRICA\n" +
                "GHANA\n" +
                "ARGELIA\n" +
                "MARRUECOS\n" +
                "ETIOPIA\n" +
                "MOZAMBIQUE\n" +
                "ANGOLA\n" +
                "RUSIA\n" +
                "ALEMANIA\n" +
                "REINO_UNIDO\n" +
                "FRANCIA\n" +
                "ITALIA\n" +
                "ESPANA\n" +
                "POLONIA\n" +
                "UCRANIA\n" +
                "RUMANIA\n" +
                "PAISES_BAJOS\n" +
                "CHINA\n" +
                "INDIA\n" +
                "JAPON\n" +
                "COREA_DEL_SUR\n" +
                "INDONESIA\n" +
                "PAKISTAN\n" +
                "TURQUIA\n" +
                "IRAN\n" +
                "ARABIA_SAUDITA\n" +
                "NUEVA_ZELANDA\n" +
                "AUSTRALIA\n" +
                "MALASIA\n";
        String[] countries2 = countries.split("\n");
        for (String country : countries2) {
            graphAdjacencyList.addVertex(country);
            graphMatrix.addVertex(country);
        }
    }

    /**
     * The function adds edges to a graph data structure from a string
     * representation of edges obtained
     * from a persistence manager.
     */
    public void addEdges() {
        String data = "ESTADOS_UNIDOS,CANADA,6218\n" +
                "ESTADOS_UNIDOS,MEXICO,3287\n" +
                "ESTADOS_UNIDOS,RUSIA,7284\n" +
                "CANADA,ESTADOS_UNIDOS,8887\n" +
                "CANADA,REINO_UNIDO,5903\n" +
                "MEXICO,ESTADOS_UNIDOS,1569\n" +
                "MEXICO,GUATEMALA,4131\n" +
                "MEXICO,BELICE,1224\n" +
                "GUATEMALA,MEXICO,7528\n" +
                "GUATEMALA,BELICE,2514\n" +
                "GUATEMALA,HONDURAS,6362\n" +
                "GUATEMALA,EL_SALVADOR,4297\n" +
                "BELICE,GUATEMALA,6146\n" +
                "BELICE,MEXICO,3939\n" +
                "BELICE,HONDURAS,1822\n" +
                "HONDURAS,GUATEMALA,4511\n" +
                "HONDURAS,EL_SALVADOR,8114\n" +
                "HONDURAS,NICARAGUA,2765\n" +
                "EL_SALVADOR,GUATEMALA,9124\n" +
                "EL_SALVADOR,HONDURAS,3679\n" +
                "EL_SALVADOR,NICARAGUA,9482\n" +
                "NICARAGUA,HONDURAS,7035\n" +
                "NICARAGUA,COSTA_RICA,1356\n" +
                "NICARAGUA,EL_SALVADOR,4821\n" +
                "COSTA_RICA,NICARAGUA,9513\n" +
                "COSTA_RICA,PANAMA,8701\n" +
                "PANAMA,COSTA_RICA,4216\n" +
                "PANAMA,COLOMBIA,3599\n" +
                "BRASIL,ARGENTINA,7591\n" +
                "BRASIL,COLOMBIA,8499\n" +
                "BRASIL,PERU,4441\n" +
                "BRASIL,VENEZUELA,5388\n" +
                "BRASIL,URUGUAY,6206\n" +
                "ARGENTINA,BRASIL,2857\n" +
                "ARGENTINA,BOLIVIA,7411\n" +
                "ARGENTINA,URUGUAY,6417\n" +
                "ARGENTINA,CHILE,2804\n" +
                "COLOMBIA,BRASIL,9636\n" +
                "COLOMBIA,PERU,5225\n" +
                "COLOMBIA,VENEZUELA,4091\n" +
                "COLOMBIA,ECUADOR,3186\n" +
                "COLOMBIA,PANAMA,7920\n" +
                "PERU,BRASIL,3799\n" +
                "PERU,COLOMBIA,5346\n" +
                "PERU,BOLIVIA,8682\n" +
                "PERU,ECUADOR,6163\n" +
                "PERU,CHILE,7843\n" +
                "VENEZUELA,BRASIL,1397\n" +
                "VENEZUELA,COLOMBIA,5796\n" +
                "CHILE,BOLIVIA,9102\n" +
                "CHILE,ARGENTINA,6701\n" +
                "CHILE,PERU,6939\n" +
                "CHILE,NUEVA_ZELANDA,3149\n" +
                "ECUADOR,COLOMBIA,7301\n" +
                "ECUADOR,PERU,6234\n" +
                "BOLIVIA,ARGENTINA,8165\n" +
                "BOLIVIA,CHILE,6789\n" +
                "BOLIVIA,PERU,3947\n" +
                "URUGUAY,ARGENTINA,1747\n" +
                "URUGUAY,BRASIL,9345\n" +
                "RUSIA,POLONIA,3362\n" +
                "RUSIA,UCRANIA,8589\n" +
                "RUSIA,RUMANIA,7453\n" +
                "RUSIA,CHINA,9315\n" +
                "RUSIA,JAPON,7122\n" +
                "ALEMANIA,FRANCIA,2477\n" +
                "ALEMANIA,POLONIA,4058\n" +
                "ALEMANIA,PAISES_BAJOS,2174\n" +
                "REINO_UNIDO,FRANCIA,1552\n" +
                "REINO_UNIDO,PAISES_BAJOS,6925\n" +
                "REINO_UNIDO,CANADA,4869\n" +
                "FRANCIA,ALEMANIA,3084\n" +
                "FRANCIA,REINO_UNIDO,5003" +
                "FRANCIA,ITALIA,6687\n" +
                "FRANCIA,ESPANA,9754\n" +
                "ITALIA,FRANCIA,7516\n" +
                "ITALIA,ESPANA,2005\n" +
                "ITALIA,ARGELIA,8997\n" +
                "ESPANA,FRANCIA,4943\n" +
                "ESPANA,ITALIA,3674\n" +
                "ESPANA,MARRUECOS,5203\n" +
                "POLONIA,RUSIA,1547\n" +
                "POLONIA,ALEMANIA,7293\n" +
                "POLONIA,UCRANIA,8246\n" +
                "UCRANIA,RUSIA,4367\n" +
                "UCRANIA,POLONIA,1875\n" +
                "UCRANIA,RUMANIA,6199\n" +
                "RUMANIA,RUSIA,3894\n" +
                "RUMANIA,UCRANIA,9978\n" +
                "RUMANIA,TURQUIA,1853\n" +
                "PAISES_BAJOS,RUSIA,6829\n" +
                "PAISES_BAJOS,REINO_UNIDO,4486\n" +
                "PAISES_BAJOS,ALEMANIA,7268\n" +
                "NIGERIA,EGIPTO,8712\n" +
                "NIGERIA,SUDAFRICA,1331\n" +
                "NIGERIA,GHANA,3667\n" +
                "NIGERIA,ARGELIA,2300\n" +
                "NIGERIA,ETIOPIA,2100\n" +
                "EGIPTO,NIGERIA,5263\n" +
                "EGIPTO,SUDAFRICA,6901\n" +
                "EGIPTO,TURQUIA,4103\n" +
                "EGIPTO,ARABIA_SAUDITA,2982\n" +
                "EGIPTO,ETIOPIA,3000\n" +
                "EGIPTO,ARGELIA,4000\n" +
                "SUDAFRICA,NIGERIA,5775\n" +
                "SUDAFRICA,EGIPTO,8036\n" +
                "SUDAFRICA,MOZAMBIQUE,9228\n" +
                "SUDAFRICA,ANGOLA,2937\n" +
                "SUDAFRICA,GHANA,5584\n" +
                "ETIOPIA,EGIPTO,1183\n" +
                "ETIOPIA,ANGOLA,1940\n" +
                "ETIOPIA,ARABIA_SAUDITA,2300\n" +
                "ETIOPIA,NIGERIA,1500\n" +
                "ARGELIA,MARRUECOS,7891\n" +
                "ARGELIA,ITALIA,4783\n" +
                "ARGELIA,NIGERIA,1300\n" +
                "ARGELIA,EGIPTO,2300\n" +
                "MARRUECOS,ARGELIA,2895\n" +
                "MARRUECOS,ESPANA,7222\n" +
                "GHANA,NIGERIA,4744\n" +
                "GHANA,SUDAFRICA,8115\n" +
                "MOZAMBIQUE,SUDAFRICA,6241\n" +
                "ANGOLA,SUDAFRICA,3496\n" +
                "ANGOLA,ETIOPIA,2333\n" +
                "CHINA,RUSIA,8162\n" +
                "CHINA,INDONESIA,4488\n" +
                "CHINA,INDIA,7066\n" +
                "INDIA,PAKISTAN,9451\n" +
                "INDIA,CHINA,2752\n" +
                "JAPON,COREA_DEL_SUR,5613\n" +
                "COREA_DEL_SUR,JAPON,8789\n" +
                "INDONESIA,PAKISTAN,1745\n" +
                "INDONESIA,CHINA,5158\n" +
                "PAKISTAN,INDIA,2972\n" +
                "PAKISTAN,INDONESIA,7736\n" +
                "PAKISTAN,IRAN,2489\n" +
                "MALASIA,INDIA,6883\n" +
                "MALASIA,AUSTRALIA,8356\n" +
                "TURQUIA,IRAN,4507\n" +
                "TURQUIA,ARABIA_SAUDITA,6726\n" +
                "TURQUIA,EGIPTO,8029\n" +
                "TURQUIA,RUMANIA,4213\n" +
                "IRAN,TURQUIA,3907\n" +
                "IRAN,PAKISTAN,7475\n" +
                "IRAN,ARABIA_SAUDITA,9327\n" +
                "ARABIA_SAUDITA,TURQUIA,3131\n" +
                "ARABIA_SAUDITA,EGIPTO,6089\n" +
                "ARABIA_SAUDITA,IRAN,8535\n" +
                "ARABIA_SAUDITA,ETIOPIA,5935\n" +
                "NUEVA_ZELANDA,AUSTRALIA,5200\n" +
                "NUEVA_ZELANDA,CHILE,4300\n" +
                "AUSTRALIA,MALASIA,3900\n" +
                "AUSTRALIA,NUEVA_ZELANDA,4800\n" +
                "AUSTRALIA,SUDAFRICA,3200\n" +
                "SUDAFRICA,AUSTRALIA,5000";
        String[] countriesEdges = data.split("\n");
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
     * This Java function returns the name of a player based on their index in an
     * array of players.
     * 
     * @param player The parameter "player" is an integer that represents the index
     *               of a player in an
     *               array of player objects. The method "getName" returns the name
     *               of the player at the specified
     *               index.
     * @return The method `getName` is returning the name of the player at the index
     *         specified by the
     *         `player` parameter. The name is obtained from the `players` array
     *         using the `getName` method of
     *         the `Player` class.
     */
    public String getName(int player) {
        return players[player].getName();
    }

    /**
     * This function verifies if the game has finished based on the number of troops
     * and conquered
     * countries of the players.
     * 
     * @return A boolean value is being returned.
     */
    public boolean verifyFinish() {
        if (players[0].getTroops() <= edgeWithMinorTroops() || players[1].getTroops() <= edgeWithMinorTroops()) {
            return true;
        } else if (players[0].getConquistedCountries().size() >= 20
                || players[1].getConquistedCountries().size() >= 20) {
            return true;
        } else {
            return false;
        }
    }

    public String casueFinish() {
        if (players[0].getTroops() <= edgeWithMinorTroops()) {
            return " The player " + players[0].getName() + " hasn't troops. ";
        } else if (players[1].getTroops() <= edgeWithMinorTroops()) {
            return " The player " + players[1].getName() + " hasn't troops. ";
        } else if (players[0].getConquistedCountries().size() >= 20) {
            return " The player " + players[0].getName() + " won by conquering more territories ";
        } else if (players[1].getConquistedCountries().size() >= 20) {
            return " The player " + players[1].getName() + " won by conquering more territories ";
        } else {
            return " Skadoosh ";
        }
    }

    /**
     * This function returns the weight of the edge with the smallest weight in a
     * graph represented by
     * an adjacency list.
     * 
     * @return The method `edgeWithMinorTroops` returns the weight of the edge with
     *         the smallest weight
     *         in the graph.
     */
    public double edgeWithMinorTroops() {
        double minimunWeight = Double.POSITIVE_INFINITY;
        for (Vertex<String> vertex : graphAdjacencyList.getVertexes()) {
            for (Edge<String> edge : vertex.getAdjacents()) {
                if (edge.getWeight() < minimunWeight) {
                    minimunWeight = edge.getWeight();
                }
            }
        }
        return minimunWeight;
    }

    /**
     * This function allows a player to conquer a new territory in a game, given
     * certain conditions.
     * 
     * @param player           An integer representing the player who is trying to
     *                         conquer a territory.
     * @param newTerritoryName The name of the territory that the player wants to
     *                         conquer.
     * @return The method returns a String message that depends on the input
     *         parameters and the state
     *         of the game. The possible messages are:
     *         - "The territory doesn't exists or is misspelled"
     *         - "This territory is already conquisted, select another one"
     *         - "This territory isn't adjacent to [startName]"
     *         - "[player's name] conquist [player's location] troops standing:
     *         [player
     */
    public String coquistTerritory(int player, String newTerritoryName) {
        int newPos = -1;
        double troopsCost = 0.0;
        boolean isFound = false;
        String startName = players[player].location();
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
            players[player].setScore(players[player].getScore() + 1);
            return players[player].getName() + " conquist " + players[player].location() + " troops standing: "
                    + players[player].getTroops();
        }

        return "The troops requeried to conquist: " + newTerritoryName + " are: " + troopsCost;
    }

    /**
     * The function prints a militar strategy based on a minimum spanning tree of
     * edges.
     * 
     * @param minimunSpanningTree A list of edges representing the minimum spanning
     *                            tree of a graph.
     *                            Each edge contains two vertices and a weight
     *                            representing the cost of traversing that edge. In
     *                            this context, the graph likely represents
     *                            territories that need to be defended and the
     *                            weight
     *                            represents the number of troops needed to defend
     *                            that territory. The method
     * @return The method `printMilitarStrategy` returns a string representation of
     *         the minimum
     *         spanning tree of a graph, where each edge represents a necessary
     *         troop deployment between two
     *         territories. If the input `minimunSpanningTree` is null, the method
     *         returns the string "Mistake
     *         with military strategy".
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
     * @param numPlayer The parameter "numPlayer" is an integer that represents the
     *                  index of the player
     *                  in the "players" array whose troops we want to retrieve.
     * @return The method `actualTroopsPlayer` returns a string that includes the
     *         number of troops of a
     *         specific player in the game. The string is formatted as "Troops of
     *         the player: [number of
     *         troops]".
     */
    public String actualTroopsPlayer(int numPlayer) {
        return "Troops of the player: " + players[numPlayer].getTroops();
    }

    /**
     * The function returns a string representing the military strategy of a player
     * in a game, based on
     * the minimum spanning tree of the game's graph, and deducts the cost of the
     * strategy from the
     * player's troops if they have enough.
     * 
     * @param numPlayer The parameter numPlayer is an integer representing the index
     *                  of the player in
     *                  the players array for whom the military strategy is being
     *                  calculated.
     * @return The method returns a String representing the military strategy for a
     *         player in a game.
     *         If the player has enough troops to execute the strategy, the method
     *         also subtracts the cost of
     *         the strategy from the player's troop count. If the player does not
     *         have enough troops, the
     *         method returns a message indicating that.
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
     * This function returns a string representation of all territories in a graph,
     * either using a
     * matrix or adjacency list.
     * 
     * @return The method `showAllTerritories` is returning a string that represents
     *         all the
     *         territories in the graph. The specific implementation depends on
     *         whether the graph is
     *         represented as a matrix or an adjacency list. If the graph is
     *         represented as a matrix, the
     *         method calls the `printTerritories` method of the `graphMatrix`
     *         object. If the graph is
     *         represented as an adjacency list, the method calls the
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
     * @param name2 The parameter "name2" is a String variable that represents the
     *              name of the second
     *              player. It is used in the method "setNamesPlayers" to set the
     *              name of the second player in an
     *              array of Player objects.
     */
    public void setNamesPlayers(String name1, String name2) {
        players[0].setName(name1);
        players[1].setName(name2);
    }

    /**
     * This function sets the territories and scores for two players in a graph,
     * either represented as
     * an adjacency list or a matrix.
     */
    public void setPlayersTerritories() {
        if (!isMatrix) {
            int randomValue = (int) (Math.random() * graphAdjacencyList.getVertexes().size() - 1);
            players[0].addTerrytory(
                    graphAdjacencyList.getVertexes().get(randomValue).getValue());
            players[0].setScore(1);

            int randomValue2 = 0;
            do {
                randomValue2 = (int) (Math.random() * graphAdjacencyList.getVertexes().size() - 1);
            } while (randomValue == randomValue2);
            players[1].addTerrytory(
                    graphAdjacencyList.getVertexes().get(randomValue2).getValue());
            players[1].setScore(1);

        } else {

            int randomValue = (int) (Math.random() * graphMatrix.getVertices().size() - 1);
            players[0].addTerrytory(
                    graphMatrix.getVertices().get(randomValue).getValue());
            players[0].setScore(1);

            int randomValue2 = 0;
            do {
                randomValue2 = (int) (Math.random() * graphMatrix.getVertices().size() - 1);
            } while (randomValue == randomValue2);
            players[1].addTerrytory(
                    graphMatrix.getVertices().get(randomValue2).getValue());
            players[1].setScore(1);

        }

    }

    /**
     * This Java function prints a list of territories conquered by a specific
     * player.
     * 
     * @param player The parameter "player" is an integer representing the index of
     *               the player whose
     *               territories are being printed.
     * @return The method is returning a string that contains a list of territories
     *         that have been
     *         conquered by a specific player. The string starts with the header
     *         "TERRITORIES:" and then lists
     *         each territory with a number and its name.
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
                            .append("  "
                                    + graphMatrix.searchEdge(players[player].location(), adjacents.get(j)).getWeight());
                }
            }
            if (msj.toString().isEmpty()) {
                return "No territories available to conquest in " + players[player].location();
            }

        }

        return msj1 + msj.toString();

    }

    /**
     * This function returns the minimum path and cost of troops needed to travel
     * from one territory to
     * another in a graph, either represented as an adjacency list or a matrix.
     * 
     * @param territoryName        The name of the starting territory for finding
     *                             the minimum path.
     * @param territoryDestination The destination territory to find the minimum
     *                             path to.
     * @return The method is returning a String that represents the minimum path and
     *         cost of troops to
     *         go from a given territory to a destination territory in a graph. If
     *         the given territories do not
     *         exist or there is no minimum path between them, the method returns an
     *         error message.
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
     * This function reconstructs the shortest path between two vertices in a graph
     * using a map of
     * previous vertices and their distances.
     * 
     * @param previousVertices       A map that contains information about the
     *                               previous vertices for each
     *                               vertex in a graph. The key is the vertex value
     *                               and the value is an object of type DistanceInfo
     *                               that contains the distance and previous vertex
     *                               information.
     * @param startVertexValue       The value of the starting vertex in the graph.
     * @param destinationVertexValue The value of the vertex we want to reach in the
     *                               graph.
     * @return The method returns a list of strings representing the path from the
     *         start vertex to the
     *         destination vertex, along with the total weight of the path. If there
     *         is no path from the start
     *         vertex to the destination vertex, an empty list is returned.
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
     * This function calculates the scores of two players and determines the winner
     * or if there is a
     * draw.
     * 
     * @return The method is returning a String that indicates the winner of a game
     *         and the scores of
     *         both players. If player 1 has a higher score than player 2, the
     *         method returns "The winner is:
     *         [player 1's name]". If player 2 has a higher score than player 1, the
     *         method returns "The winner
     *         is: [player 2's name]". If both
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
     * The function returns the winner and score of a game where one player has
     * given up.
     * 
     * @param player The parameter "player" is an integer that represents the player
     *               who gave up the
     *               game. If the value of "player" is 0, it means that player 0
     *               gave up and player 1 wins the game.
     * @return The method is returning a String that includes the name of the winner
     *         (the player who
     *         did not give up) and their score.
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
     * This Java function allows a player to move their territory to a new location
     * if they have
     * already conquered it.
     * 
     * @param territory A String representing the name of the territory that the
     *                  player wants to move
     *                  to.
     * @param player    The integer representing the player who is making the move.
     * @return If the player has conquered the territory, the method will return a
     *         string that says
     *         "Now your Territory Actual is [territory name]". If the player has
     *         not conquered the territory,
     *         the method will return a string that says "This Territory isn't
     *         conquisted".
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
