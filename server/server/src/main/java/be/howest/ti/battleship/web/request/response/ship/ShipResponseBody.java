package be.howest.ti.battleship.web.request.response.ship;

import be.howest.ti.battleship.logic.fleet.Ship;

public class ShipResponseBody extends ShipLocationResponseBody {

    public ShipResponseBody(Ship ship) {
        super(ship);
    }

    public String getName(){
        return ship.getShipType().getName();
    }
}
