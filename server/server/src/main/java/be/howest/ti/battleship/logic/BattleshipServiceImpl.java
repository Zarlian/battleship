package be.howest.ti.battleship.logic;

import be.howest.ti.battleship.logic.fleet.Fleet;
import be.howest.ti.battleship.logic.fleet.Ship;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class BattleshipServiceImpl implements BattleshipService {

    public final GameManager gameManager = new GameManager();

    @Override
    public String getVersion() {
        return "0.0.1";
    }

    @Override
    public Set<Game> getGames(String status, String type, String prefix, int rows, int cols) {
        return gameManager.getGameList(status, type, prefix, rows, cols);
    }

    @Override
    public String computeGame(String commander, String type, Fleet fleet, String prefix) {
        return gameManager.connectGame(commander, type, fleet, prefix);
    }

    @Override
    public Fleet getFleetDetails(String id, String requestingCommander) {
        return gameManager.getGameById(id, requestingCommander).getFleetByCommanderName(requestingCommander);
    }

    @Override
    public Map<String, String> getHitResponse(String id, String commander, List<String> hitList) {
        Game game = gameManager.getGameById(id);
        return game.processShot(commander, hitList);
    }

    @Override
    public Game getGameById(String id) {
        return gameManager.getGameById(id);
    }

    @Override
    public void deleteGames() {
        gameManager.deleteGames();
    }

    @Override
    public Ship moveShip(String gameId, String movingCommander, String ship) {
        Game game = gameManager.getGameById(gameId);
        return game.processMove(movingCommander, ship);
    }
}
