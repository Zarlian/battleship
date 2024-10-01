package be.howest.ti.battleship.logic;

import be.howest.ti.battleship.logic.fleet.Fleet;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GameManagerTest {

    @Test
    void increaseIdWhenCreatingNewGame(){
        GameManager manager = new GameManager();
        DemoData demoData = new DemoData();
        Commander commander = new Commander("Alice", demoData.getDemoFleet());
        Commander commander2 = new Commander("Alice", demoData.getDemoFleet());
        Game game = new Game(commander, "simple", null);
        Game game2 = new Game(commander2, "simple", null);
        manager.addGame(game);
        manager.addGame(game2);

        assertEquals("2", game2.getId());
    }

    @Test
    void findGameByID() {
        DemoData demoData = new DemoData();
        GameManager manager = new GameManager();
        Commander commander = new Commander("Alice", demoData.getDemoFleet());
        Game game = new Game(commander, "simple", null);

        manager.addGame(game);

        assertEquals(game, manager.getGameById("1"));
    }

    @Test
    void makeGameWithPrefix(){
        DemoData demoData = new DemoData();
        GameManager manager = new GameManager();
        Fleet fleet = demoData.getDemoFleet();
        Commander commander = new Commander("Alice", fleet);
        Game game = new Game(commander, "simple", "test");

        manager.addGame(game);

        assertEquals("test_1", game.getId());

    }

    @Test
    void findGameByPrefix(){
        DemoData demoData = new DemoData();
        GameManager manager = new GameManager();
        Fleet fleet = demoData.getDemoFleet();
        Commander commander1 = new Commander("Alice", fleet);
        Game game = new Game(commander1, "simple", "test");
        manager.addGame(game);

        Commander commander2 = new Commander("Bob", fleet);

       assertEquals(game, manager.findMatchingGameWithPrefix(commander2,"simple","test"));
    }

    @Test
    void getFullGameList(){
        DemoData demoData = new DemoData();
        GameManager gameManager = new GameManager();
        Commander commander1 = new Commander("sammy", demoData.getDemoFleet());
        Commander commander2 = new Commander("sammy", demoData.getDemoFleet());
        Commander commander3 = new Commander("sammy", demoData.getDemoFleet());

        gameManager.addGame(new Game(commander1, "simple", "test"));
        gameManager.addGame(new Game(commander2, "simple", "test"));
        gameManager.addGame(new Game(commander3, "simple", "test"));

        assertEquals(3, gameManager.getGameList("null", "null", "null", -1, -1).size());

    }

    @Test
    void newGameIsCreated(){
        DemoData demoData = new DemoData();
        Commander sammy = new Commander("sammy", demoData.getDemoFleet());
        GameManager gameManager = new GameManager();

        Game matchingGame = gameManager.findMatchingGame(sammy, "salvo");
        gameManager.createOrJoinGame(sammy, "salvo", null, matchingGame);

        assertEquals("salvo", gameManager.getGameById("1").getType());
    }

    @Test
    void settingsTheSamePlayersAreMatchedTogether(){
        DemoData demoData = new DemoData();
        Commander sammy = new Commander("sammy", demoData.getDemoFleet());
        Commander sammy2 = new Commander("jimmy", demoData.getDemoFleet());
        GameManager gameManager = new GameManager();

        Game matchingGame = gameManager.findMatchingGame(sammy, "salvo");
        gameManager.createOrJoinGame(sammy, "salvo", null, matchingGame);
        matchingGame = gameManager.findMatchingGame(sammy2, "salvo");
        gameManager.createOrJoinGame(sammy2, "salvo", null, matchingGame);

        assertEquals(matchingGame, gameManager.getGameById("1"));
    }

    @Test
    void settingsNotTheSameNewGameIsCreated(){
        DemoData demoData = new DemoData();
        Commander sammy = new Commander("sammy", demoData.getDemoFleet());
        Commander sammy2 = new Commander("jimmy", demoData.getDemoFleet());
        GameManager gameManager = new GameManager();

        Game matchingGame = gameManager.findMatchingGame(sammy, "salvo");
        gameManager.createOrJoinGame(sammy, "salvo", null, matchingGame);
        Game matchingGame2 = gameManager.findMatchingGame(sammy2, "salvo");
        gameManager.createOrJoinGame(sammy2, "salvo", null, matchingGame);

        assertEquals("1", gameManager.getGameById("1").getId());
        assertEquals("2", gameManager.getGameById("2").getId());
    }

    @Test
    void joinGameWithSamePrefix(){
        DemoData demoData = new DemoData();
        Commander sammy = new Commander("sammy", demoData.getDemoFleet());
        Commander sammy2 = new Commander("jimmy", demoData.getDemoFleet());
        GameManager gameManager = new GameManager();

        Game matchingGame = gameManager.findMatchingGame(sammy, "salvo");
        gameManager.createOrJoinGame(sammy, "salvo", "hello", matchingGame);
        matchingGame = gameManager.findMatchingGameWithPrefix(sammy2, "salvo", "hello");
        gameManager.createOrJoinGame(sammy2, "salvo", "hello", matchingGame);

        assertEquals(2, gameManager.getGameById("hello_1").getCommanders().size());
    }

    @Test
    void settingsNotTheSameNewGameIsCreatedWithSamePrefix(){
        DemoData demoData = new DemoData();
        Commander sammy = new Commander("sammy", demoData.getDemoFleet());
        Commander jimmy = new Commander("jimmy", demoData.getDemoFleet());
        GameManager gameManager = new GameManager();

        Game matchingGame = gameManager.findMatchingGame(sammy, "salvo");
        gameManager.createOrJoinGame(sammy, "salvo", "hello", matchingGame);
        matchingGame = gameManager.findMatchingGameWithPrefix(jimmy, "simple", "hello");
        gameManager.createOrJoinGame(jimmy, "simple", "hello", matchingGame);

        assertEquals(1, gameManager.getGameById("hello_1").getCommanders().size());
    }

    @Test
    void sameCommanderNameNotAllowedInWaitingGames(){
        DemoData demoData = new DemoData();
        Commander sammy = new Commander("sammy", demoData.getDemoFleet());
        Commander sammy2 = new Commander("sammy", demoData.getDemoFleet());
        GameManager gameManager = new GameManager();
        Game matchingGame = gameManager.findMatchingGame(sammy, "salvo");
        gameManager.createOrJoinGame(sammy, "salvo", null, matchingGame);
        assertThrows(BattleshipException.class, () ->gameManager.filterGames(sammy2,"salvo"));
        assertEquals(1, gameManager.getGameById("1").getCommanders().size());
    }

    @Test
    void filterByRows(){
        DemoData demoData = new DemoData();
        GameManager manager = new GameManager();

        Game game1 = demoData.getDemoGame(demoData.getDemoCommander("jimmy"), "salvo", "test");
        Game game2 = demoData.getDemoGame(demoData.getDemoCommander("jimmy"), "salvo", "tester");
        Game game3 = demoData.getDemoGame(demoData.getDemoCommander("jimmy"), "simple", "tester");
        Set<Game> games = new HashSet<>();
        games.add(game1);
        games.add(game2);
        games.add(game3);
        manager.addGame(game1);
        manager.addGame(game2);
        manager.addGame(game3);

        assertEquals(Set.of(game1,game2,game3), manager.filterByRows(10, games));
        assertEquals(Set.of(), manager.filterByRows(5,games));
    }

    @Test
    void filterByCols(){
        DemoData demoData = new DemoData();
        GameManager manager = new GameManager();

        Game game1 = demoData.getDemoGame(demoData.getDemoCommander("jimmy"), "salvo", "test");
        Game game2 = demoData.getDemoGame(demoData.getDemoCommander("jimmy"), "salvo", "tester");
        Game game3 = demoData.getDemoGame(demoData.getDemoCommander("jimmy"), "simple", "tester");
        Set<Game> games = new HashSet<>();
        games.add(game1);
        games.add(game2);
        games.add(game3);
        manager.addGame(game1);
        manager.addGame(game2);
        manager.addGame(game3);

        assertEquals(Set.of(game1,game2,game3), manager.filterByColumns(10, games));
        assertEquals(Set.of(), manager.filterByColumns(5,games));
    }

    @Test
    void filterByPrefix(){
        DemoData demoData = new DemoData();
        GameManager manager = new GameManager();

        Game game1 = demoData.getDemoGame(demoData.getDemoCommander("jimmy"), "salvo", "test");
        Game game2 = demoData.getDemoGame(demoData.getDemoCommander("jimmy"), "salvo", "tester");
        Set<Game> games = new HashSet<>();
        games.add(game1);
        games.add(game2);
        manager.addGame(game1);
        manager.addGame(game2);

        assertEquals(Set.of(game1), manager.filterByPrefix("test", games));

    }

    @Test
    void filterByStatus(){
        DemoData demoData = new DemoData();
        GameManager manager = new GameManager();

        Game game1 = demoData.getDemoGame(demoData.getDemoCommander("jimmy"), "salvo", "test");
        Game game2 = demoData.getDemoGame(demoData.getDemoCommander("jimmy"), "salvo", "tester");
        Game game3 = demoData.getDemoGame(demoData.getDemoCommander("jimmy"), "simple", "tester");
        Set<Game> games = new HashSet<>();
        games.add(game1);
        games.add(game2);
        games.add(game3);
        manager.addGame(game1);
        manager.addGame(game2);
        manager.addGame(game3);
        manager.joinExistingGame(demoData.getDemoCommander("freddy"),game1);

        assertEquals(Set.of(game1), manager.filterByStatus("started", games));
        assertEquals(Set.of(game2,game3), manager.filterByStatus("waiting", games));
    }

    @Test
    void filterByType(){
        DemoData demoData = new DemoData();
        GameManager manager = new GameManager();

        Game game1 = demoData.getDemoGame(demoData.getDemoCommander("jimmy"), "salvo", "test");
        Game game2 = demoData.getDemoGame(demoData.getDemoCommander("jimmy"), "salvo", "tester");
        Game game3 = demoData.getDemoGame(demoData.getDemoCommander("jimmy"), "simple", "tester");
        Set<Game> games = new HashSet<>();
        games.add(game1);
        games.add(game2);
        games.add(game3);
        manager.addGame(game1);
        manager.addGame(game2);
        manager.addGame(game3);

        assertEquals(Set.of(game1,game2), manager.filterByType("salvo", games));
    }
}