package be.howest.ti.battleship.logic;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void swapCommanders(){
        DemoData demoData = new DemoData();
        Commander Freddy = new Commander("Freddy", demoData.getDemoFleet());
        Commander Jimmy = new Commander("Jimmy", demoData.getDemoFleet());
        Game game = new Game(Freddy, "salvo", null);
        game.addCommander(Jimmy);
        game.swapCommanders();
        assertEquals("Jimmy", game.getAttackingCommander().getName());
        assertEquals("Freddy", game.getDefendingCommander().getName());
    }


    @Test
    void shootYourself(){
        DemoData demoData = new DemoData();
        Commander Freddy = new Commander("Freddy", demoData.getDemoFleet());
        Commander Jimmy = new Commander("Jimmy", demoData.getDemoFleet());
        Game game = new Game(Freddy, "salvo", null);
        game.addCommander(Jimmy);
        List<String> shotList = new ArrayList<>();
        shotList.add("A-5");
        assertThrows(BattleshipException.class, () -> game.processShot(Freddy.getName(), shotList));
    }
    @Test
    void shootDefender() {
        GameManager gameManager = new GameManager();

        DemoData demoData = new DemoData();
        Commander Freddy = new Commander("Freddy", demoData.getDemoFleet());
        Commander Jimmy = new Commander("Jimmy", demoData.getDemoFleet());

        Game matchingGame = gameManager.findMatchingGame(Freddy, "simple");
        gameManager.createOrJoinGame(Freddy, "simple",null,matchingGame);

        matchingGame = gameManager.findMatchingGame(Jimmy, "simple");
        gameManager.createOrJoinGame(Jimmy, " simple", null, matchingGame);

        Game game = gameManager.getGameById("1");

        List<String> shotList = new ArrayList<>();
        shotList.add("A-5");
        Map<String, String> expected = new HashMap<>();
        expected.put("A-5", "CARRIER");
        assertEquals(expected, game.processShot(Jimmy.getName(), shotList));
    }
    @Test
    void getCommandersFleet(){
        DemoData demoData = new DemoData();
        Commander Freddy = new Commander("Freddy", demoData.getDemoFleet());
        Commander Jimmy = new Commander("Jimmy", demoData.getDemoFleet());
        Game game = new Game(Freddy, "salvo", null);
        game.addCommander(Jimmy);
        assertEquals(10, game.getFleetByCommanderName("Freddy").getRows());
    }

    @Test
    void getCommanderFleetFromNoneExistingCommander(){
        DemoData demoData = new DemoData();
        Commander Freddy = new Commander("Freddy", demoData.getDemoFleet());
        Commander Jimmy = new Commander("Jimmy",demoData.getDemoFleet());
        Game game = new Game(Freddy, "salvo", null);
        game.addCommander(Jimmy);
        assertNull(game.getFleetByCommanderName("James"));
    }
}

