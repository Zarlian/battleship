package be.howest.ti.battleship.web.request.response.history.converter.move;

import be.howest.ti.battleship.logic.fleet.Ship;
import be.howest.ti.battleship.logic.history.MoveTurn;
import be.howest.ti.battleship.web.request.response.history.converter.TurnResponse;
import be.howest.ti.battleship.web.request.response.ship.HistoryShipResponseBody;

public class RequestingCommanderMove extends TurnResponse {
    private final Ship ship;
    public RequestingCommanderMove(MoveTurn moveTurn) {
        super(moveTurn);
        ship = moveTurn.getResult();
    }

    public HistoryShipResponseBody getResult(){
        return new HistoryShipResponseBody(ship);
    }
}
