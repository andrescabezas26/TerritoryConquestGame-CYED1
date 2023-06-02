package tests;

import org.junit.Assert;
import org.junit.Test;

import datastructures.*;
import model.*;
import data.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.*;

public class GameTest {
    private Game game;
    private ManagerPersistence managerPersistence;

    public void setUp() {

        game = new Game();
        managerPersistence = new ManagerPersistence();
        
    }

    @Test
    public void testConquistTerritory_ValidTerritory_AdjacentAndEnoughTroops() {
        setUp();
        int player = 0;
        game.getPlayers()[0].setConquistedCountries(new ArrayList<String>());
        game.getPlayers()[0].addTerrytory("COLOMBIA");
        game.getPlayers()[0].setTroops(4096);

        // Act
        String result = game.coquistTerritory(player, "VENEZUELA");

        // Assert
        assertEquals("Player0 conquist Territory1 troops standing: 5", result);
    }

    @Test
    public void ConquistGame(){
        setUp();
        game.coquistTerritory(0, null);
    }
}
