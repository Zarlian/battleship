package be.howest.ti.battleship.web.request.response.ship;

import be.howest.ti.battleship.logic.fleet.Location;
import be.howest.ti.battleship.logic.fleet.Ship;

import java.util.ArrayList;
import java.util.List;

public class ShipLocationResponseBody {

    protected Ship ship;

    public ShipLocationResponseBody(Ship ship) {
        this.ship = ship;
    }

    public List<String> getLocation(){
        List<Location> shipLocations = ship.getLocation();
        List<String> result = new ArrayList<>();
        for (Location shipLocation : shipLocations) {
            result.add(shipLocation.getLocation());
        }
        return result;
    }
}
