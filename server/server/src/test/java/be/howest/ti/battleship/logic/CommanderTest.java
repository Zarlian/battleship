package be.howest.ti.battleship.logic;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CommanderTest {

    @Test
    void sameNameRowsColsIsEqualCommander(){
        DemoData demoData = new DemoData();
        Commander commander1 = new Commander("alice", demoData.getDemoFleet());
        Commander commander2 = new Commander("alice", demoData.getDemoFleet());

        assertEquals(commander2, commander1);
    }

}