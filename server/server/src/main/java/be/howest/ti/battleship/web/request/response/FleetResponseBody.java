package be.howest.ti.battleship.web.request.response;

import be.howest.ti.battleship.logic.fleet.Fleet;
import be.howest.ti.battleship.logic.fleet.Ship;
import be.howest.ti.battleship.web.request.response.ship.ShipResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FleetResponseBody {

    private final Fleet fleet;

    public FleetResponseBody(Fleet fleet) {
        this.fleet = fleet;
    }

    public int getRows(){
        return fleet.getRows();
    }
    public int getCols(){
        return fleet.getCols();
    }
    public List<ShipResponseBody> getShips(){
        List<ShipResponseBody> result = new ArrayList<>();
        List<Ship> ships = fleet.getShips();
        for(Ship ship : ships){
            result.add(new ShipResponseBody(ship));
        }
        return result;
    }

    public Map<String,Integer> getSize(){
        return Map.of(  "rows", fleet.getRows(),
                        "cols", fleet.getCols());
    }
}
