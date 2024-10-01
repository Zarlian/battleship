package be.howest.ti.battleship.web.request.response.history.converter.move;

import be.howest.ti.battleship.logic.history.MoveTurn;
import be.howest.ti.battleship.web.request.response.history.converter.TurnResponse;

public class OpponentCommanderMove extends TurnResponse {

    private static final String MOVEMENT_RESPONSE = "ship movement detected";
    public OpponentCommanderMove(MoveTurn moveTurn) {
        super(moveTurn);
    }

    public String getResult(){
        return MOVEMENT_RESPONSE;
    }
}
