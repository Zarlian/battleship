package be.howest.ti.battleship.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTypeTest {

    @Test
    void getCorrectType(){
        assertEquals("move+salvo", GameType.MOVESALVO.getName());
    }

}