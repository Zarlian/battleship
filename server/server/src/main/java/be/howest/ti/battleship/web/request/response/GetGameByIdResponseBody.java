package be.howest.ti.battleship.web.request.response;

import be.howest.ti.battleship.logic.Commander;
import be.howest.ti.battleship.logic.Game;
import be.howest.ti.battleship.web.request.response.history.converter.HistoryMapper;
import be.howest.ti.battleship.web.request.response.history.converter.TurnResponse;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class GetGameByIdResponseBody extends StartedGameResponseBody {

    private final Commander attackingCommander;
    private final Commander defendingCommander;

    private final String requestingCommander;

    public GetGameByIdResponseBody(Game game, String requestingCommander) {
        super(game);
        attackingCommander = game.getAttackingCommander();
        defendingCommander = game.getDefendingCommander();
        this.requestingCommander = requestingCommander;
    }

    public Map<String, Integer> getSalvoSize() {
        return Map.of(attackingCommander.getName(), attackingCommander.getSalvoSize(),
                defendingCommander.getName(), defendingCommander.getSalvoSize());
    }

    public Map<String, Set<Map<String,Object>>> getSunkShips() {
        return Map.of(attackingCommander.getName(), attackingCommander.getSunkShips(),
                defendingCommander.getName(), defendingCommander.getSunkShips());
    }


    public List<TurnResponse> getHistory(){
        HistoryMapper history = new HistoryMapper(super.game.getHistory().getHistoryList(), requestingCommander);
        return history.getHistory();
    }
}
