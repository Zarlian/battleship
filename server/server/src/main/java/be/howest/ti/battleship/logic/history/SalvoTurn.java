package be.howest.ti.battleship.logic.history;

import be.howest.ti.battleship.logic.Commander;
import be.howest.ti.battleship.web.request.response.history.converter.TurnResponse;
import be.howest.ti.battleship.web.request.response.history.converter.salvo.OpponentCommanderSalvo;
import be.howest.ti.battleship.web.request.response.history.converter.salvo.RequestingCommanderSalvo;

import java.util.Map;

public class SalvoTurn extends Turn {

    private final Map<String, String>  hitResult;
    public SalvoTurn(Commander commander, Map<String, String> hitResult) {
        super(commander);
        this.hitResult = hitResult;
    }

    public Map<String, String> getResult(){
        return hitResult;
    }

    @Override
    public TurnResponse convert(String requestingCommander) {
        if(requestingCommander.equals(super.getCommander())){
            return new RequestingCommanderSalvo(this);
        }else{
            return  new OpponentCommanderSalvo(this);
        }
    }
}
