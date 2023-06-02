package ui;

import java.util.Scanner;
import model.Game;

/**
 * The Main class is the main driver of the Territory Conquest Game, which allows players to conquer
 * territories and use military strategy to win the game.
 */
public class Main {

    private Scanner reader;
    private Game controller;
    private boolean finish;
    private int player;

    public Main() {
        reader = new Scanner(System.in);
        controller = new Game();
        player = 0;
    }

    public static void main(String[] args) {
        Main main = new Main();

        System.out.println("\n<<<<< Welcome to Territory Conquest Game >>>>>");

        int option = 1;

        do {
            if (main.isFinish()) {
                break;
            }
            option = main.getOptionShowMenu();
            main.executeOption(option);
        } while (option != 2);

        main.getReader().close();
    }

    /**
     * The function returns a string that represents a map of the United States and Colombia.
     * 
     * @return A string that represents a map of the United States and Colombia.
     */
    public String map() {
        return ""
                + "\n**********************"
                + "\n*******EEUU***********"
                + "\n  ******************* "
                + "\n  ******************  "
                + "\n    ************      "
                + "\n      ********        "
                + "\n          ***     **  "
                + "\n            **  ***  *   **"
                + "\n              ********* ****     ***"
                + "\n             ****COL**********    "
                + "\n              *********************"
                + "\n                ****************   "
                + "\n               ******************  "
                + "\n              *******************"
                + "\n               ***************"
                + "\n                ************"
                + "\n                  ******"
                + "\n                   ****"
                + "\n                    ***"
                + "\n                     ***"
                + "\n                     **\n";
    }

    /**
     * getOptionMatrixOrList: Get the option
     *
     * @return int
     */
    public void getOptionMatrixOrList() {

        int option = 0;

        System.out.println(
                "1. Adjacency list \n" +
                        "2. Adjacency matrix\n");

        option = validateIntegerOption();

        while (option > 2 || option < 1 || option == -1) {
            reader.nextLine();
            System.out.println("Choose a valid option");
            System.out.println(
                    "1. Adjacency list \n" +
                            "2. Adjacency matrix\n");
            option = validateIntegerOption();

        }

        if (option == 1) {
            controller.setIsMatrix(false);
        } else if (option == 2) {
            controller.setIsMatrix(true);
        }

    }

    /**
     * getOptionMenu: Get the option of the menu
     *
     * @return int
     */
    public int getOptionShowMenu() {
        int option = 0;

        System.out.println(map() +
                "1. Play \n" +
                "2. Exit\n");

        option = validateIntegerOption();

        while (option == -1 || option > 2 || option < 1) {
            reader.nextLine();
            System.out.println("Choose a valid option");
            System.out.println(map() +
                    "1. Play \n" +
                    "2. Exit\n");

            option = validateIntegerOption();
        }

        return option;
    }

    /**
     * This function displays a menu of options for the player to choose from and validates their
     * input.
     * 
     * @return The method is returning an integer value, which is the option selected by the user from
     * the menu displayed in the console.
     */
    public int getPlayOptionShowMenu() {
        int option = 0;

System.out.println("\n" + controller.getName(player).toUpperCase() + " TURN");
                    System.out.println(controller.actualTroopsPlayer(player));
        System.out.println("<<Difficulty>>: The player in option 4 or 5 must correctly write the name of the country otherwise he will lose the turn\n" +
        "The player has to choose a territory that he can conquer with his troops or he will lose the turn\n\n" +
                "1. See Conquested Territories \n" +
                        "2. See Actual Territory\n" +
                        "3. See Minimum Path to a Territory\n" +
                        "4. Conquist Territory\n" +
                        "5. Change Actual Territory\n" +
                        "6. See militar strategy (COST: 10000 TROOPS)\n" +
                        "7. See all Territories\n" +
                        "8. Select matrix or list\n" +
                        "9. Give up \n");

        option = validateIntegerOption();

        while (option == -1 || option > 9 || option < 1) {

            reader.nextLine();

            System.out.println("Choose a valid option");

System.out.println("\n" + controller.getName(player).toUpperCase() + " TURN");
                    System.out.println(controller.actualTroopsPlayer(player));
            System.out.println("<<Difficulty>>: The player in option 4 or 5 must correctly write the name of the country otherwise he will lose the turn\n" +
             "The player has to choose a territory that he can conquer with his troops or he will lose the turn\n\n" +
                    "1. See Conquested Territories \n" +
                            "2. See Actual Territory\n" +
                            "3. See Minimum Path to a Territory\n" +
                            "4. Conquist Territory\n" +
                            "5. Change Actual Territory\n" +
                            "6. See militar strategy (COST: 10000 TROOPS)\n" +
                            "7. See all Territories\n" +
                            "8. Select matrix or list\n" +
                            "9. Give up \n");

            option = validateIntegerOption();
        }

        return option;
    }

    /**
     * executeOption: Execute the option
     *
     * @param option int - The choosen option
     */
    public void executeOption(int option) {
        switch (option) {
            case 1:
                reader.nextLine();
                System.out.println("\n Enter the name of player 1: ");
                String name1 = reader.nextLine();

                System.out.println("\n Enter the name of player 2: ");
                String name2 = reader.nextLine();

                controller.setNamesPlayers(name1, name2);

                System.out.println("\n<<GAME STARTED>>");
                int playOption = 1;
                while (playOption != 8) {
                    finish = controller.verifyFinish();
                    if (finish) {
                        finishGame(false, player);
                        return;
                    }
                    playOption = getPlayOptionShowMenu();
                    executePlayOption(playOption);
                    if (finish) {
                        break;
                    }
                }

                break;
            case 2:
                System.out.println("███▓▒░THANKS FOR PLAYING░▒▓███");
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
    }

    // The `executePlayOption` method is responsible for executing the selected option during the game.
    // It takes in an integer `option` which represents the selected option and then uses a switch
    // statement to determine which action to take based on the selected option. The method can perform
    // actions such as showing the player's conquered territories, showing the minimum path to a
    // territory, conquering a territory, changing the player's current territory, showing the military
    // strategy, showing all territories, selecting the matrix or list, or giving up the game.
    public void executePlayOption(int option) {
        switch (option) {
            case 1:
                // See Conquested Territories (Not necesary Matrix)
                System.out.println(controller.printPlayerTerritories(player));
                int op = getPlayOptionShowMenu();
                executePlayOption(op);
                break;
            case 2:
                // See Actual Territory (Not necesary Matrix)
                System.out.println(controller.actualTerritory(player));
                op = getPlayOptionShowMenu();
                executePlayOption(op);
                break;
            case 3:
                // See Minimum Path to a Territory (Necesary Matrix) 
                reader.nextLine();
                System.out.println("\n Enter the name of territory of start");
                String territoryStart = reader.nextLine();
                System.out.println("\n Enter the name of territory of finish");
                String territoryTwo = reader.nextLine();
                System.out.println(controller.printTerritoryMinimumPath(territoryStart, territoryTwo));
                op = getPlayOptionShowMenu();
                executePlayOption(op);
                break;
            case 4:
                // Conquist Territory (Necesary Matrix)
                System.out.println(controller.printAdjacentsAndWeight(player));
                reader.nextLine();
                System.out.println("\n Enter the name of Territory that you want to conquist: ");
                String territory = reader.nextLine();
                System.out.println(controller.coquistTerritory(player, territory.toUpperCase()));
                if (this.player == 0) {
                    this.player = 1;
                } else {
                    this.player = 0;
                }
                op = getPlayOptionShowMenu();
                executePlayOption(op);
                break;

            case 5:
                // Change Actual Territory (Not necesary Matrix)
                System.out.println(controller.printPlayerTerritories(player));
                reader.nextLine();
                System.out.println("\n Enter the name of Territory that you want to move you (This is free): ");
                String territoryChange = reader.nextLine();
                System.out.println(controller.moveTerritory(territoryChange.toUpperCase(), player));
                if (this.player == 0) {
                    this.player = 1;
                } else {
                    this.player = 0;
                }
                op = getPlayOptionShowMenu();
                executePlayOption(op);
                break;
            case 6:
                // See militar strategy (Necesary Matrix) 
                System.out.println(
                        "This option allows you to see how to conquer all the territories using the least number of troops: \n ");
                System.out.println(controller.militarStrategyPlayer(player));
                op = getPlayOptionShowMenu();
                executePlayOption(op);
                break;
            case 7:
                // Show all territories (Necesary Matrix) 
                System.out.println(controller.showAllTerritories());
                op = getPlayOptionShowMenu();
                executePlayOption(op);
                break;
            case 8:
                // Select matrix or list
                getOptionMatrixOrList();
                op = getPlayOptionShowMenu();
                executePlayOption(op);
                break;
            case 9:
                // Give up
                finishGame(true, player);
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
    }

    // The `finishGame` method is used to end the game if a player decides to give up. It takes in two
    // parameters: `giveUp` which is a boolean value indicating whether the player has given up or not,
    // and `player` which is an integer representing the player who has given up. If `giveUp` is true,
    // the method sets the `finish` variable to true, indicating that the game is over, and prints a
    // message indicating that the game has ended due to the player giving up.
    public void finishGame(boolean giveUp, int player) {
        if (giveUp) {
            finish = true;
            System.out.println("\n\n███▓▒░FINISH GAME░▒▓███\n" + controller.finishForGiveUp(player));
        } else{
            System.out.println("\n\n███▓▒░FINISH GAME░▒▓███\n" + controller.casueFinish() + controller.calculateScore());
        }
    }

    /**
     * validateIntegerOption: This method checks if a number is an integer
     *
     * @return option - int: Returns the entered number if it is an integer or
     *         returns -1 if it is not an integer
     */
    public int validateIntegerOption() {
        int option = 0;

        if (reader.hasNextInt()) {
            option = reader.nextInt();

        } else {
            reader.nextLine();
            option = -1;
        }

        return option;
    }

    /**
     * @param reader the reader to set
     */
    public void setReader(Scanner reader) {
        this.reader = reader;
    }

    /**
     * @return Scanner return the reader
     */
    public Scanner getReader() {
        return reader;
    }

    /**
     * @return Game return the controller
     */
    public Game getController() {
        return controller;
    }

    /**
     * @param controller the controller to set
     */
    public void setController(Game controller) {
        this.controller = controller;
    }

    /**
     * @return boolean return the finish
     */
    public boolean isFinish() {
        return finish;
    }

    /**
     * @param finish the finish to set
     */
    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    /**
     * @return int return the player
     */
    public int getPlayer() {
        return player;
    }

    /**
     * @param player the player to set
     */
    public void setPlayer(int player) {
        this.player = player;
    }

    

}