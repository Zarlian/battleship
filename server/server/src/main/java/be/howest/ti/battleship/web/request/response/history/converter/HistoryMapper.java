package be.howest.ti.battleship.web.request.response.history.converter;
import be.howest.ti.battleship.logic.history.Turn;

import java.util.ArrayList;
import java.util.List;

public class HistoryMapper {
    private final List<TurnResponse> turns;

    public HistoryMapper(List<Turn> turns, String requestingCommander) {
        this.turns = convert(turns, requestingCommander);
    }
    public List<TurnResponse> getHistory(){
        return turns;
    }

    public List<TurnResponse> convert(List<Turn> turns, String requestingCommander){
        List<TurnResponse> result = new ArrayList<>();
        for(Turn turn : turns){
            result.add(turn.convert(requestingCommander));
        }
        return result;
    }

}
