package be.howest.ti.battleship.web.request.response;

import be.howest.ti.battleship.logic.Game;

import java.util.List;
import java.util.Map;

public class WaitingGameResponseBody implements GameResponseBody {

    private final Game game;

    public WaitingGameResponseBody(Game game) {
        this.game = game;
    }
    @Override
    public String getId() {
        return game.getId();
    }

    @Override
    public String getType() {
        return game.getType();
    }
    public List<String > getCommanders(){
        return List.of(game.getAttackingCommander().getName());
    }
    public boolean getStarted(){
        return false;
    }

    @Override
    public Map<String, Integer> getSize(){
        return Map.of(
                "rows", game.getAttackingCommander().getFleet().getRows(),
                "cols", game.getAttackingCommander().getFleet().getCols());
    }
}
