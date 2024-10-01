package be.howest.ti.battleship.web.request.response.history.converter;

import be.howest.ti.battleship.logic.history.Turn;

public class TurnResponse {
    private final String commander;

    public TurnResponse(Turn turn){
        commander = turn.getCommander();
    }

    public String getAttackingCommander(){
        return commander;
    }
}
