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

        // Verificación de resultados
        assertEquals("The winner is: PLAYER 1\nThe Score of PLAYER 1 was: 10\nThe Score of PLAYER 2 was: 5", "");
    }

}
