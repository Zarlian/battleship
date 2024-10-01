package be.howest.ti.battleship.web.request.response;

import be.howest.ti.battleship.logic.fleet.Fleet;

public class FleetOpponentResponseBody {
    private final Fleet fleet;

    public FleetOpponentResponseBody(Fleet fleet) {
        this.fleet = fleet;
    }

    public int getRows(){
        return fleet.getRows();
    }
    public int getCols(){
        return fleet.getCols();
    }
}
