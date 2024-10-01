package be.howest.ti.battleship.web.request.response;

import java.util.Map;

public interface GameResponseBody {

    String getId();

    String getType();

    Map<String, Integer> getSize();
}
