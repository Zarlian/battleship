package be.howest.ti.battleship.web.request.response;

import be.howest.ti.battleship.logic.Game;

import java.util.Map;

public class StartedGameResponseBody  implements GameResponseBody {
    protected final Game game;

    public StartedGameResponseBody(Game game) {
        this.game = game;
    }

    public String getId(){
        return game.getId();
    }
    public String getType(){
        return game.getType();
    }
    public String getAttackingCommander(){
        return game.getAttackingCommander().getName();
    }
    public String getDefendingCommander(){
        return game.getDefendingCommander().getName();
    }
    public String getWinner(){
        return game.getWinner();
    }
    public boolean getStarted(){
        return true;
    }
    public Map<String, Integer> getSize(){
        return Map.of(
                "rows", game.getAttackingCommander().getFleet().getRows(),
                "cols", game.getAttackingCommander().getFleet().getCols());
    }
}
