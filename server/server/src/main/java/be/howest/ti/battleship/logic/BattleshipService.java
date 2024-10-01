package be.howest.ti.battleship.logic;

import be.howest.ti.battleship.logic.fleet.Fleet;
import be.howest.ti.battleship.logic.fleet.Ship;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface BattleshipService {

    String getVersion();

    Set<Game> getGames(String status, String type, String prefix, int rows, int cols);

    String computeGame(String commander, String type, Fleet fleet, String prefix);

    Fleet getFleetDetails(String id, String commander);

    Map<String, String> getHitResponse(String id, String commander, List<String> hitList);

    Game getGameById(String id);

    void deleteGames();

    Ship moveShip(String gameId, String movingCommander, String ship);
}
