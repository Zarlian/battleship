package be.howest.ti.battleship.logic.history;

import be.howest.ti.battleship.logic.Commander;
import be.howest.ti.battleship.logic.fleet.Ship;
import be.howest.ti.battleship.web.request.response.history.converter.TurnResponse;
import be.howest.ti.battleship.web.request.response.history.converter.move.OpponentCommanderMove;
import be.howest.ti.battleship.web.request.response.history.converter.move.RequestingCommanderMove;

public class MoveTurn extends Turn {
    private final Ship moveResult;
    public MoveTurn(Commander commander, Ship moveResult) {
        super(commander);
        this.moveResult = moveResult;
    }

    public Ship getResult(){
        return moveResult;
    }

    @Override
    public TurnResponse convert(String requestingCommander) {
        if(requestingCommander.equals(super.getCommander())){
            return new RequestingCommanderMove(this);
        }else{
            return  new OpponentCommanderMove(this);
        }
    }
}
