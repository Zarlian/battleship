package be.howest.ti.battleship.web.request.response.history.converter.salvo;
import be.howest.ti.battleship.logic.history.SalvoTurn;
import be.howest.ti.battleship.web.request.response.history.converter.TurnResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RequestingCommanderSalvo extends TurnResponse {

    private final Map<String, String> hitList;

    public RequestingCommanderSalvo(SalvoTurn salvoTurn) {
        super(salvoTurn);
        hitList = salvoTurn.getResult();
    }

    public Map<String, Object> getResult(){
        return convert(hitList);
    }

    public Map<String, Object> convert(Map<String, String> salvo) {
        Map<String, Object> result = new HashMap<>();
        for (Map.Entry<String, String> hit : salvo.entrySet()) {
            String key = hit.getKey();
            Object value = hit.getValue();
            result.put(key, Objects.requireNonNullElse(value, false));
        }
        return result;
    }
}
