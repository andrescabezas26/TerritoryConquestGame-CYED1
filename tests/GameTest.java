package tests;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import datastructures.*;
import model.*;
import data.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.*;

public class GameTest {
    private GameForTest game;

    public void setUp() {
        game = new GameForTest();
    }

    @Test
    public void testCalculateScore1() {
        setUp();

        game.getPlayers()[0].setScore(10);
        game.getPlayers()[1].setScore(5);

        assertEquals("The winner is: PLAYER 1\nThe Score of PLAYER 1 was: 10.0\nThe Score of PLAYER 2 was: 5.0",
                game.calculateScore());
    }

    @Test
    public void testCalculateScore2() {
        setUp();

        game.getPlayers()[0].setScore(10);
        game.getPlayers()[1].setScore(10);

        assertEquals("There's a draw \nThe Score of PLAYER 1 was: 10.0\nThe Score of PLAYER 2 was: 10.0",
                game.calculateScore());
    }

    @Test
    public void testCalculateScore3() {
        setUp();

        game.getPlayers()[0].setScore(5);
        game.getPlayers()[1].setScore(10);

        assertEquals("The winner is: PLAYER 2\nThe Score of PLAYER 1 was: 5.0\nThe Score of PLAYER 2 was: 10.0",
                game.calculateScore());
    }

    @Test
    public void testMoveTerritoryConquered() {
        setUp();
        game.getPlayers()[0].addTerrytory("COLOMBIA");

        String result = game.moveTerritory("COLOMBIA", 0);

        assertEquals("Now your Territory Actual is COLOMBIA", result);
        assertEquals("COLOMBIA", game.getPlayers()[0].location());
    }

    @Test
    public void testMoveTerritoryNotConquered() {
        setUp();

        String result = game.moveTerritory("COLOMBIA", 0);
        String reuslt2 = game.getPlayers()[0].location();

        assertEquals("This Territory isn't conquisted", result);
    }

    @Test
    public void testMoveTerritoryConquered2() {
        setUp();
        game.getPlayers()[0].addTerrytory("RUSIA");
        game.getPlayers()[0].addTerrytory("ESPANA");
        game.getPlayers()[0].addTerrytory("VENEZUELA");

        String result = game.moveTerritory("VENEZUELA", 0);

        assertEquals("Now your Territory Actual is VENEZUELA", result);
        assertEquals("VENEZUELA", game.getPlayers()[0].location());
    }

    @Test
    public void conquist1() {
        setUp();
        game.getPlayers()[0].getConquistedCountries().set(0, "COLOMBIA");
        ;
        game.coquistTerritory(0, "VENEZUELA");
        assertEquals("VENEZUELA", game.getPlayers()[0].location());
    }

    @Test
    public void conquist2() {
        setUp();
        game.getPlayers()[0].getConquistedCountries().set(0, "COLOMBIA");
        assertEquals("This territory isn't adjacent to COLOMBIA", game.coquistTerritory(0, "RUSIA"));
    }

    @Test
    public void conquist3() {
        setUp();
        game.getPlayers()[0].getConquistedCountries().set(0, "RUMANIA");
        game.getPlayers()[1].getConquistedCountries().set(0, "RUSIA");

    }

    @Test
    public void printMilitarStrategy() {
        setUp();
        game.getPlayers()[0].getConquistedCountries().set(0, "SUDAFRICA");
        String estrategiaMilitar = "----MILITAR STRATEGY----\n" +
                " Territory: SUDAFRICA -- NIGERIA Necessary troops:  5775.0\n" +
                " Territory: SUDAFRICA -- EGIPTO Necessary troops:  8036.0\n" +
                " Territory: SUDAFRICA -- MOZAMBIQUE Necessary troops:  9228.0\n" +
                " Territory: SUDAFRICA -- ANGOLA Necessary troops:  2937.0\n" +
                " Territory: SUDAFRICA -- GHANA Necessary troops:  5584.0\n" +
                " Territory: SUDAFRICA -- AUSTRALIA Necessary troops:  5000.0\n" +
                " Territory: ANGOLA -- ETIOPIA Necessary troops:  2333.0\n" +
                " Territory: ETIOPIA -- EGIPTO Necessary troops:  1183.0\n" +
                " Territory: ETIOPIA -- ARABIA_SAUDITA Necessary troops:  2300.0\n" +
                " Territory: ETIOPIA -- NIGERIA Necessary troops:  1500.0\n" +
                " Territory: EGIPTO -- TURQUIA Necessary troops:  4103.0\n" +
                " Territory: EGIPTO -- ARGELIA Necessary troops:  4000.0\n" +
                " Territory: NIGERIA -- GHANA Necessary troops:  3667.0\n" +
                " Territory: NIGERIA -- ARGELIA Necessary troops:  2300.0\n" +
                " Territory: ARABIA_SAUDITA -- TURQUIA Necessary troops:  3131.0\n" +
                " Territory: ARABIA_SAUDITA -- IRAN Necessary troops:  8535.0\n" +
                " Territory: ARGELIA -- MARRUECOS Necessary troops:  7891.0\n" +
                " Territory: ARGELIA -- ITALIA Necessary troops:  4783.0\n" +
                " Territory: TURQUIA -- IRAN Necessary troops:  4507.0\n" +
                " Territory: TURQUIA -- RUMANIA Necessary troops:  4213.0\n" +
                " Territory: RUMANIA -- RUSIA Necessary troops:  3894.0\n" +
                " Territory: RUMANIA -- UCRANIA Necessary troops:  9978.0\n" +
                " Territory: RUSIA -- POLONIA Necessary troops:  3362.0\n" +
                " Territory: RUSIA -- UCRANIA Necessary troops:  8589.0\n" +
                " Territory: RUSIA -- CHINA Necessary troops:  9315.0\n" +
                " Territory: RUSIA -- JAPON Necessary troops:  7122.0\n" +
                " Territory: POLONIA -- ALEMANIA Necessary troops:  7293.0\n" +
                " Territory: POLONIA -- UCRANIA Necessary troops:  8246.0\n" +
                " Territory: IRAN -- PAKISTAN Necessary troops:  7475.0\n" +
                " Territory: ITALIA -- FRANCIA Necessary troops:  7516.0\n" +
                " Territory: ITALIA -- ESPANA Necessary troops:  2005.0\n" +
                " Territory: ESPANA -- FRANCIA Necessary troops:  4943.0\n" +
                " Territory: ESPANA -- MARRUECOS Necessary troops:  5203.0\n" +
                " Territory: FRANCIA -- ALEMANIA Necessary troops:  3084.0\n" +
                " Territory: FRANCIA -- REINO_UNIDO Necessary troops:  5003.0\n" +
                " Territory: ALEMANIA -- PAISES_BAJOS Necessary troops:  2174.0\n" +
                " Territory: PAISES_BAJOS -- REINO_UNIDO Necessary troops:  4486.0\n" +
                " Territory: REINO_UNIDO -- CANADA Necessary troops:  4869.0\n" +
                " Territory: CANADA -- ESTADOS_UNIDOS Necessary troops:  8887.0\n" +
                " Territory: AUSTRALIA -- MALASIA Necessary troops:  3900.0\n" +
                " Territory: AUSTRALIA -- NUEVA_ZELANDA Necessary troops:  4800.0\n" +
                " Territory: MALASIA -- INDIA Necessary troops:  6883.0\n" +
                " Territory: NUEVA_ZELANDA -- CHILE Necessary troops:  4300.0\n" +
                " Territory: CHILE -- BOLIVIA Necessary troops:  9102.0\n" +
                " Territory: CHILE -- ARGENTINA Necessary troops:  6701.0\n" +
                " Territory: CHILE -- PERU Necessary troops:  6939.0\n" +
                " Territory: ARGENTINA -- BRASIL Necessary troops:  2857.0\n" +
                " Territory: ARGENTINA -- BOLIVIA Necessary troops:  7411.0\n" +
                " Territory: ARGENTINA -- URUGUAY Necessary troops:  6417.0\n" +
                " Territory: BRASIL -- COLOMBIA Necessary troops:  8499.0\n" +
                " Territory: BRASIL -- PERU Necessary troops:  4441.0\n" +
                " Territory: BRASIL -- VENEZUELA Necessary troops:  5388.0\n" +
                " Territory: BRASIL -- URUGUAY Necessary troops:  6206.0\n" +
                " Territory: PERU -- COLOMBIA Necessary troops:  5346.0\n" +
                " Territory: PERU -- ECUADOR Necessary troops:  6163.0\n" +
                " Territory: COLOMBIA -- VENEZUELA Necessary troops:  4091.0\n" +
                " Territory: COLOMBIA -- ECUADOR Necessary troops:  3186.0\n" +
                " Territory: COLOMBIA -- PANAMA Necessary troops:  7920.0\n" +
                " Territory: INDIA -- CHINA Necessary troops:  2752.0\n" +
                " Territory: CHINA -- INDONESIA Necessary troops:  4488.0\n" +
                " Territory: INDONESIA -- PAKISTAN Necessary troops:  1745.0\n" +
                " Territory: JAPON -- COREA_DEL_SUR Necessary troops:  5613.0\n" +
                " Territory: PANAMA -- COSTA_RICA Necessary troops:  4216.0\n" +
                " Territory: COSTA_RICA -- NICARAGUA Necessary troops:  9513.0\n" +
                " Territory: ESTADOS_UNIDOS -- MEXICO Necessary troops:  3287.0\n" +
                " Territory: MEXICO -- GUATEMALA Necessary troops:  4131.0\n" +
                " Territory: MEXICO -- BELICE Necessary troops:  1224.0\n" +
                " Territory: BELICE -- HONDURAS Necessary troops:  1822.0\n" +
                " Territory: HONDURAS -- EL_SALVADOR Necessary troops:  8114.0\n" +
                " Territory: HONDURAS -- NICARAGUA Necessary troops:  2765.0\n" +
                " Territory: NICARAGUA -- EL_SALVADOR Necessary troops:  4821.0\n" +
                " Territory: GUATEMALA -- EL_SALVADOR Necessary troops:  4297.0";
        String result = game.militarStrategyPlayer(0);
        assertEquals(estrategiaMilitar, result);

    }

    @Test
    public void printMilitarStrategy2() {
        setUp();
        game.getPlayers()[1].getConquistedCountries().set(0, "ALEMANIA");
        String texto = "----MILITAR STRATEGY----\n" +
                " Territory: ALEMANIA -- FRANCIA Necessary troops:  2477.0\n" +
                " Territory: ALEMANIA -- POLONIA Necessary troops:  4058.0\n" +
                " Territory: ALEMANIA -- PAISES_BAJOS Necessary troops:  2174.0\n" +
                " Territory: PAISES_BAJOS -- RUSIA Necessary troops:  6829.0\n" +
                " Territory: PAISES_BAJOS -- REINO_UNIDO Necessary troops:  4486.0\n" +
                " Territory: FRANCIA -- ITALIA Necessary troops:  6687.0\n" +
                " Territory: FRANCIA -- ESPANA Necessary troops:  9754.0\n" +
                " Territory: POLONIA -- RUSIA Necessary troops:  1547.0\n" +
                " Territory: POLONIA -- UCRANIA Necessary troops:  8246.0\n" +
                " Territory: RUSIA -- RUMANIA Necessary troops:  7453.0\n" +
                " Territory: RUSIA -- CHINA Necessary troops:  9315.0\n" +
                " Territory: RUSIA -- JAPON Necessary troops:  7122.0\n" +
                " Territory: REINO_UNIDO -- CANADA Necessary troops:  4869.0\n" +
                " Territory: CANADA -- ESTADOS_UNIDOS Necessary troops:  8887.0\n" +
                " Territory: ITALIA -- ESPANA Necessary troops:  2005.0\n" +
                " Territory: ITALIA -- ARGELIA Necessary troops:  8997.0\n" +
                " Territory: ESPANA -- MARRUECOS Necessary troops:  5203.0\n" +
                " Territory: MARRUECOS -- ARGELIA Necessary troops:  2895.0\n" +
                " Territory: ARGELIA -- NIGERIA Necessary troops:  1300.0\n" +
                " Territory: ARGELIA -- EGIPTO Necessary troops:  2300.0\n" +
                " Territory: NIGERIA -- SUDAFRICA Necessary troops:  1331.0\n" +
                " Territory: NIGERIA -- GHANA Necessary troops:  3667.0\n" +
                " Territory: NIGERIA -- ETIOPIA Necessary troops:  2100.0\n" +
                " Territory: SUDAFRICA -- MOZAMBIQUE Necessary troops:  9228.0\n" +
                " Territory: SUDAFRICA -- ANGOLA Necessary troops:  2937.0\n" +
                " Territory: SUDAFRICA -- AUSTRALIA Necessary troops:  5000.0\n" +
                " Territory: ETIOPIA -- EGIPTO Necessary troops:  1183.0\n" +
                " Territory: ETIOPIA -- ANGOLA Necessary troops:  1940.0\n" +
                " Territory: ETIOPIA -- ARABIA_SAUDITA Necessary troops:  2300.0\n" +
                " Territory: EGIPTO -- TURQUIA Necessary troops:  4103.0\n" +
                " Territory: ARABIA_SAUDITA -- TURQUIA Necessary troops:  3131.0\n" +
                " Territory: ARABIA_SAUDITA -- IRAN Necessary troops:  8535.0\n" +
                " Territory: TURQUIA -- IRAN Necessary troops:  4507.0\n" +
                " Territory: TURQUIA -- RUMANIA Necessary troops:  4213.0\n" +
                " Territory: IRAN -- PAKISTAN Necessary troops:  7475.0\n" +
                " Territory: AUSTRALIA -- MALASIA Necessary troops:  3900.0\n" +
                " Territory: AUSTRALIA -- NUEVA_ZELANDA Necessary troops:  4800.0\n" +
                " Territory: MALASIA -- INDIA Necessary troops:  6883.0\n" +
                " Territory: NUEVA_ZELANDA -- CHILE Necessary troops:  4300.0\n" +
                " Territory: CHILE -- BOLIVIA Necessary troops:  9102.0\n" +
                " Territory: CHILE -- ARGENTINA Necessary troops:  6701.0\n" +
                " Territory: CHILE -- PERU Necessary troops:  6939.0\n" +
                " Territory: ARGENTINA -- BRASIL Necessary troops:  2857.0\n" +
                " Territory: ARGENTINA -- BOLIVIA Necessary troops:  7411.0\n" +
                " Territory: ARGENTINA -- URUGUAY Necessary troops:  6417.0\n" +
                " Territory: BRASIL -- COLOMBIA Necessary troops:  8499.0\n" +
                " Territory: BRASIL -- PERU Necessary troops:  4441.0\n" +
                " Territory: BRASIL -- VENEZUELA Necessary troops:  5388.0\n" +
                " Territory: BRASIL -- URUGUAY Necessary troops:  6206.0\n" +
                " Territory: PERU -- COLOMBIA Necessary troops:  5346.0\n" +
                " Territory: PERU -- ECUADOR Necessary troops:  6163.0\n" +
                " Territory: COLOMBIA -- VENEZUELA Necessary troops:  4091.0\n" +
                " Territory: COLOMBIA -- ECUADOR Necessary troops:  3186.0\n" +
                " Territory: COLOMBIA -- PANAMA Necessary troops:  7920.0\n" +
                " Territory: INDIA -- CHINA Necessary troops:  2752.0\n" +
                " Territory: CHINA -- INDONESIA Necessary troops:  4488.0\n" +
                " Territory: INDONESIA -- PAKISTAN Necessary troops:  1745.0\n" +
                " Territory: JAPON -- COREA_DEL_SUR Necessary troops:  5613.0\n" +
                " Territory: PANAMA -- COSTA_RICA Necessary troops:  4216.0\n" +
                " Territory: COSTA_RICA -- NICARAGUA Necessary troops:  9513.0\n" +
                " Territory: ESTADOS_UNIDOS -- MEXICO Necessary troops:  3287.0\n" +
                " Territory: MEXICO -- GUATEMALA Necessary troops:  4131.0\n" +
                " Territory: MEXICO -- BELICE Necessary troops:  1224.0\n" +
                " Territory: BELICE -- HONDURAS Necessary troops:  1822.0\n" +
                " Territory: HONDURAS -- EL_SALVADOR Necessary troops:  8114.0\n" +
                " Territory: HONDURAS -- NICARAGUA Necessary troops:  2765.0\n" +
                " Territory: NICARAGUA -- EL_SALVADOR Necessary troops:  4821.0\n" +
                " Territory: GUATEMALA -- EL_SALVADOR Necessary troops:  4297.0";

        String result = game.militarStrategyPlayer(1);
        assertEquals(texto, result);

    }

    @Test
    public void printMilitarStrategy3() {
        setUp();
        game.getPlayers()[0].getConquistedCountries().set(0, "CHINA");
        String estrategiaMilitar = "----MILITAR STRATEGY----\n" +
                " Territory: CHINA -- RUSIA Necessary troops:  8162.0\n" +
                " Territory: CHINA -- INDONESIA Necessary troops:  4488.0\n" +
                " Territory: CHINA -- INDIA Necessary troops:  7066.0\n" +
                " Territory: INDONESIA -- PAKISTAN Necessary troops:  1745.0\n" +
                " Territory: PAKISTAN -- INDIA Necessary troops:  2972.0\n" +
                " Territory: PAKISTAN -- IRAN Necessary troops:  2489.0\n" +
                " Territory: IRAN -- TURQUIA Necessary troops:  3907.0\n" +
                " Territory: IRAN -- ARABIA_SAUDITA Necessary troops:  9327.0\n" +
                " Territory: TURQUIA -- ARABIA_SAUDITA Necessary troops:  6726.0\n" +
                " Territory: TURQUIA -- EGIPTO Necessary troops:  8029.0\n" +
                " Territory: TURQUIA -- RUMANIA Necessary troops:  4213.0\n" +
                " Territory: RUMANIA -- RUSIA Necessary troops:  3894.0\n" +
                " Territory: RUMANIA -- UCRANIA Necessary troops:  9978.0\n" +
                " Territory: RUSIA -- POLONIA Necessary troops:  3362.0\n" +
                " Territory: RUSIA -- UCRANIA Necessary troops:  8589.0\n" +
                " Territory: RUSIA -- JAPON Necessary troops:  7122.0\n" +
                " Territory: POLONIA -- ALEMANIA Necessary troops:  7293.0\n" +
                " Territory: POLONIA -- UCRANIA Necessary troops:  8246.0\n" +
                " Territory: ARABIA_SAUDITA -- EGIPTO Necessary troops:  6089.0\n" +
                " Territory: ARABIA_SAUDITA -- ETIOPIA Necessary troops:  5935.0\n" +
                " Territory: ETIOPIA -- EGIPTO Necessary troops:  1183.0\n" +
                " Territory: ETIOPIA -- ANGOLA Necessary troops:  1940.0\n" +
                " Territory: ETIOPIA -- NIGERIA Necessary troops:  1500.0\n" +
                " Territory: EGIPTO -- SUDAFRICA Necessary troops:  6901.0\n" +
                " Territory: EGIPTO -- ARGELIA Necessary troops:  4000.0\n" +
                " Territory: NIGERIA -- SUDAFRICA Necessary troops:  1331.0\n" +
                " Territory: NIGERIA -- GHANA Necessary troops:  3667.0\n" +
                " Territory: NIGERIA -- ARGELIA Necessary troops:  2300.0\n" +
                " Territory: SUDAFRICA -- MOZAMBIQUE Necessary troops:  9228.0\n" +
                " Territory: SUDAFRICA -- AUSTRALIA Necessary troops:  5000.0\n" +
                " Territory: ARGELIA -- MARRUECOS Necessary troops:  7891.0\n" +
                " Territory: ARGELIA -- ITALIA Necessary troops:  4783.0\n" +
                " Territory: ITALIA -- FRANCIA Necessary troops:  7516.0\n" +
                " Territory: ITALIA -- ESPANA Necessary troops:  2005.0\n" +
                " Territory: ESPANA -- FRANCIA Necessary troops:  4943.0\n" +
                " Territory: ESPANA -- MARRUECOS Necessary troops:  5203.0\n" +
                " Territory: FRANCIA -- ALEMANIA Necessary troops:  3084.0\n" +
                " Territory: FRANCIA -- REINO_UNIDO Necessary troops:  5003.0\n" +
                " Territory: ALEMANIA -- PAISES_BAJOS Necessary troops:  2174.0\n" +
                " Territory: PAISES_BAJOS -- REINO_UNIDO Necessary troops:  4486.0\n" +
                " Territory: REINO_UNIDO -- CANADA Necessary troops:  4869.0\n" +
                " Territory: CANADA -- ESTADOS_UNIDOS Necessary troops:  8887.0\n" +
                " Territory: AUSTRALIA -- MALASIA Necessary troops:  3900.0\n" +
                " Territory: AUSTRALIA -- NUEVA_ZELANDA Necessary troops:  4800.0\n" +
                " Territory: NUEVA_ZELANDA -- CHILE Necessary troops:  4300.0\n" +
                " Territory: CHILE -- BOLIVIA Necessary troops:  9102.0\n" +
                " Territory: CHILE -- ARGENTINA Necessary troops:  6701.0\n" +
                " Territory: CHILE -- PERU Necessary troops:  6939.0\n" +
                " Territory: ARGENTINA -- BRASIL Necessary troops:  2857.0\n" +
                " Territory: ARGENTINA -- BOLIVIA Necessary troops:  7411.0\n" +
                " Territory: ARGENTINA -- URUGUAY Necessary troops:  6417.0\n" +
                " Territory: BRASIL -- COLOMBIA Necessary troops:  8499.0\n" +
                " Territory: BRASIL -- PERU Necessary troops:  4441.0\n" +
                " Territory: BRASIL -- VENEZUELA Necessary troops:  5388.0\n" +
                " Territory: BRASIL -- URUGUAY Necessary troops:  6206.0\n" +
                " Territory: PERU -- COLOMBIA Necessary troops:  5346.0\n" +
                " Territory: PERU -- ECUADOR Necessary troops:  6163.0\n" +
                " Territory: COLOMBIA -- VENEZUELA Necessary troops:  4091.0\n" +
                " Territory: COLOMBIA -- ECUADOR Necessary troops:  3186.0\n" +
                " Territory: COLOMBIA -- PANAMA Necessary troops:  7920.0\n" +
                " Territory: JAPON -- COREA_DEL_SUR Necessary troops:  5613.0\n" +
                " Territory: PANAMA -- COSTA_RICA Necessary troops:  4216.0\n" +
                " Territory: COSTA_RICA -- NICARAGUA Necessary troops:  9513.0\n" +
                " Territory: ESTADOS_UNIDOS -- MEXICO Necessary troops:  3287.0\n" +
                " Territory: MEXICO -- GUATEMALA Necessary troops:  4131.0\n" +
                " Territory: MEXICO -- BELICE Necessary troops:  1224.0\n" +
                " Territory: BELICE -- HONDURAS Necessary troops:  1822.0\n" +
                " Territory: HONDURAS -- EL_SALVADOR Necessary troops:  8114.0\n" +
                " Territory: HONDURAS -- NICARAGUA Necessary troops:  2765.0\n" +
                " Territory: NICARAGUA -- EL_SALVADOR Necessary troops:  4821.0\n" +
                " Territory: GUATEMALA -- EL_SALVADOR Necessary troops:  4297.0";

        String result = game.militarStrategyPlayer(0);
        assertEquals(estrategiaMilitar, result);

    }

    @Test
    public void actualTroopsPlayer1() {
        setUp();
        game.getPlayers()[0].setTroops(1000);
        assertEquals("Troops of the player: 1000", game.actualTroopsPlayer(0));
    }

    @Test
    public void actualTroopsPlayer2() {
        setUp();
        game.getPlayers()[0].setTroops(2000);
        assertEquals("Troops of the player: 2000", game.actualTroopsPlayer(0));
    }

    @Test
    public void actualTroopsPlayer3() {
        setUp();
        game.getPlayers()[0].setTroops(1500);
        assertEquals("Troops of the player: 1500", game.actualTroopsPlayer(0));
    }

    @Test
    public void finishGame1() {
        setUp();
        game.getPlayers()[0].setScore(10);
        game.getPlayers()[1].setScore(5);

        String expected = "The winner is: " + game.getPlayers()[0].getName().toUpperCase() +
                "\nThe Score of " + game.getPlayers()[0].getName().toUpperCase() +
                " was: 10.0" +
                "\nThe Score of " + game.getPlayers()[1].getName().toUpperCase() +
                " was: 5.0";

        assertEquals(expected, game.calculateScore());
    }

    @Test
    public void finishGame2() {
        setUp();
        game.getPlayers()[0].setScore(8);
        game.getPlayers()[1].setScore(8);

        String expected = "There's a draw " +
                "\nThe Score of " + game.getPlayers()[0].getName().toUpperCase() +
                " was: 8.0" +
                "\nThe Score of " + game.getPlayers()[1].getName().toUpperCase() +
                " was: 8.0";

        assertEquals(expected, game.calculateScore());
    }

    @Test
    public void finishGame3() {
        setUp();
        game.getPlayers()[0].setScore(3);
        game.getPlayers()[1].setScore(7);

        String expected = "The winner is: " + game.getPlayers()[1].getName().toUpperCase() +
                "\nThe Score of " + game.getPlayers()[0].getName().toUpperCase() +
                " was: 3.0" +
                "\nThe Score of " + game.getPlayers()[1].getName().toUpperCase() +
                " was: 7.0";

        assertEquals(expected, game.calculateScore());
    }

    @Test
    public void finishForGiveUp1() {
        setUp();
        game.getPlayers()[0].setName("Player 1");
        game.getPlayers()[1].setName("Player 2");
        game.getPlayers()[0].setScore(10.0);
        game.getPlayers()[1].setScore(5.0);

        String expected = "The winner is: " + game.getPlayers()[1].getName().toUpperCase() +
                "\n The score was: " + game.getPlayers()[1].getScore();

        assertEquals(expected, game.finishForGiveUp(0));
    }

    @Test
    public void finishForGiveUp2() {
        setUp();
        game.getPlayers()[0].setName("Player 1");
        game.getPlayers()[1].setName("Player 2");
        game.getPlayers()[0].setScore(7.0);
        game.getPlayers()[1].setScore(12.0);

        String expected = "The winner is: " + game.getPlayers()[0].getName().toUpperCase() +
                "\n The score was: " + game.getPlayers()[0].getScore();

        assertEquals(expected, game.finishForGiveUp(1));
    }

    @Test
    public void finishForGiveUp3() {
        setUp();
        game.getPlayers()[0].setName("Player 1");
        game.getPlayers()[1].setName("Player 2");
        game.getPlayers()[0].setScore(8.0);
        game.getPlayers()[1].setScore(12.0);

        String expected = "The winner is: " + game.getPlayers()[0].getName().toUpperCase() +
                "\n The score was: " + game.getPlayers()[0].getScore();

        assertEquals(expected, game.finishForGiveUp(1));
    }

    @Test
    public void seeMinimumPathTest1() {
        setUp();
        String expected = "The List of territories to go from ITALIA to COLOMBIA are:\n" +
                "ITALIA\n" +
                "ARGELIA\n" +
                "NIGERIA\n" +
                "SUDAFRICA\n" +
                "AUSTRALIA\n" +
                "NUEVA_ZELANDA\n" +
                "CHILE\n" +
                "PERU\n" +
                "COLOMBIA\n" +
                "The cost of troops will be: 38013.0";

        assertEquals(expected, game.printTerritoryMinimumPath("ITALIA", "COLOMBIA"));

    }

    @Test
    public void seeMinimumPathTest2() {
        setUp();
        String expected = "The List of territories to go from ESTADOS_UNIDOS to JAPON are:\n" +
                "ESTADOS_UNIDOS\n" +
                "RUSIA\n" +
                "JAPON\n" +
                "The cost of troops will be: 14406.0";

        assertEquals(expected, game.printTerritoryMinimumPath("ESTADOS_UNIDOS", "JAPON"));

    }

    @Test
    public void seeMinimumPathTest3() {
        setUp();
        String expected = "The List of territories to go from FRANCIA to ETIOPIA are:\n" +
                "FRANCIA\n" +
                "ITALIA\n" +
                "ARGELIA\n" +
                "NIGERIA\n" +
                "ETIOPIA\n" +
                "The cost of troops will be: 19084.0";

        assertEquals(expected, game.printTerritoryMinimumPath("FRANCIA", "ETIOPIA"));

    }

}
