package be.howest.ti.battleship.web.request.response;

import be.howest.ti.battleship.logic.fleet.Ship;
import be.howest.ti.battleship.web.request.response.ship.ShipLocationResponseBody;

import java.util.Map;

public class MoveResponseBody extends ShipLocationResponseBody {

    public MoveResponseBody(Ship ship) {
        super(ship);
    }
    public Map<String, String> getShip() {

        return Map.of("name",ship.getShipType().getName(),
                      "size", String.valueOf(ship.getLength()));
    }
}
