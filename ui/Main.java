package ui;

import java.util.Scanner;
import model.Game;

public class Main {

    private Scanner reader;
    private Game controller;
    private boolean finish;

    public Main() {
        reader = new Scanner(System.in);
        controller = new Game();
    }

    public static void main(String[] args) {
        Main main = new Main();

        System.out.println("\n<<<<< Welcome to Territory Conquest Game >>>>>");

        int option = 1;

        do {
            option = main.getOptionShowMenu();
            main.executeOption(option);
        } while (option != 2);

        main.getReader().close();
    }

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
     * getOptionMenu: Get the option of the menu
     *
     * @return int
     */
    public int getOptionShowMenu() {
        int option = -1;
        while (option == -1) {

            System.out.println(map() +
                    "1. Play \t" +
                    "2. Exit\n");

            option = validateIntegerOption();
        }

        return option;
    }

    public int getPlayOptionShowMenu() {
        int option = -1;
        while (option == -1) {

            System.out.println(
                    "1. See Conquested Territories \n" +
                            "2. See Actual Territory\n" +
                            "3. See Minimum Path to a Territory\n" +
                            "4. Conquist Territory\n" +
                            "5. Give up \n");

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
                int player = 1;
                String name = name1;
                while (playOption != 0) {
                    System.out.println("PLAYER " + name.toUpperCase() + " TURN");
                    playOption = getPlayOptionShowMenu();
                    executePlayOption(playOption, player);
                    if (player == 1) {
                        player = 2;
                        name = name2;
                    } else {
                        player = 1;
                        name = name1;
                    }
                    if (finish) {
                        break;
                    }
                }

                break;
            case 2:
                System.out.println("███▓▒░FINISH GAME░▒▓███");
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
    }

    public void executePlayOption(int option, int player) {
        switch (option) {
            case 1:
                // See Conquested Territories
                System.out.println(controller.printPlayerTerritories(player - 1));
                executePlayOption(option, player);
                break;
            case 2:
                // See Actual Territory
                System.out.println(controller.actualTerritory(player - 1));
                executePlayOption(option, player);
                break;
            case 3:
                // See Minimum Path to a Territory
                reader.nextLine();
                System.out.println("\n Enter the name of territory of start");
                String territoryStart = reader.nextLine();
                System.out.println("\n Enter the name of territory of finish");
                String territoryTwo = reader.nextLine();
                System.out.println(controller.printTerritoryMinimumPath(territoryStart, territoryTwo));
                executePlayOption(option, player);
                break;
            case 4:
                // Conquist Territory

                break;
            case 5:
                // Give up
                finishGame(true, player);
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
    }

    public void finishGame(boolean giveUp, int player) {
        if (giveUp) {
            finish = true;
            System.out.println("\n\n███▓▒░FINISH GAME░▒▓███\n" + controller.finishForGiveUp(player - 1));
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
}