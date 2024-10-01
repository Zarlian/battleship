package be.howest.ti.battleship.web.request.response.ship;

import be.howest.ti.battleship.logic.fleet.Location;
import be.howest.ti.battleship.logic.fleet.Ship;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HistoryShipResponseBody {
    private final Ship ship;
    public HistoryShipResponseBody(Ship ship) {
        this.ship = ship;
    }

    public Map<String, Object> getShip(){
        return Map.of("name" , ship.getShipType().getName(),
                "size", ship.getShipType().getSize());
    }

    public List<String> getNewLocation(){
        List<Location> shipLocations = ship.getLocation();
        List<String> result = new ArrayList<>();
        for (Location shipLocation : shipLocations) {
            result.add(shipLocation.getLocation());
        }
        return result;
    }
}
