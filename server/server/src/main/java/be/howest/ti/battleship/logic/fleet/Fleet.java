package be.howest.ti.battleship.logic.fleet;

import be.howest.ti.battleship.logic.BattleshipException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class Fleet {
    public static final int EXPECTED_FLEET_SIZE = ShipType.values().length;
    int rows;
    int cols;
    private final List<Ship> ships;
    
    @JsonCreator
    public Fleet(
            @JsonProperty("rows") int rows,
            @JsonProperty("cols") int cols,
            @JsonProperty("ships") List<Ship> ships

    ) {
        this.rows = rows;
        this.cols = cols;
        this.ships = ships;
        validateFleet();
    }

    public void validateFleet() {
        if (!isPositionOnBoard()) {
            throw new IllegalArgumentException("Ships cannot be placed outside of board.");
        }
        if (!isPositionFleetConsecutive()) {
            throw new IllegalArgumentException("Ship positions are not consecutive.");
        }
        if (!areAllShipsRightSize()) {
            throw new IllegalArgumentException("Ship is not the right size.");
        }
        if (isShipOverlappingOtherShips()) {
            throw new IllegalArgumentException("A ship is on top of another.");
        }
        if(!isFullFleet()){
            throw new IllegalArgumentException("Your fleet is not big enough.");
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public Map<String, String> salvoCheck(List<String> positions) {
        Map<String, String> results = new HashMap<>();
        for (String coordinate : positions) {
            Location shot = new Location(coordinate);
            results.put(coordinate, whichShipIsHit(shot));
        }
        return results;
    }
    public void canShipMove(String shipName) {
        Ship ship = findShip(shipName);
        Location newHeadLocation = ship.calculateNewHeadLocation();
        if (!isInBoard(newHeadLocation)) {
            throw new BattleshipException("Can not move out of board.");
        }
        if (isFoundInOtherShipPositions(ship, newHeadLocation)) {
            throw new BattleshipException("Can not move into another ship.");
        }
        if (ship.isHit()) {
            throw new BattleshipException("A hit ship can not Move.");
        }
    }

    public Ship findShip(String shipName) {
        for (Ship ship : ships) {
            if (ship.getShipType().getName().equals(shipName)) {
                return ship;
            }
        }
        return null;
    }

    public boolean isInBoard(Location location) {
        if (location.getRow() >= 0 && location.getRow() < rows) {
            return location.getColumn() >= 0 && location.getColumn() < cols;
        }
        return false;
    }

    public Ship moveShip(String shipName) {
        Ship ship = findShip(shipName);
        canShipMove(shipName);
        return ship.moveShip();
    }

    public String whichShipIsHit(Location location) {
        for (Ship ship : ships) {
            if (ship.fireShot(location)) {
                return ship.getShipType().getName().toUpperCase();
            }
        }
        return null;
    }

    public boolean isSunk(String shipName) {
        for (Ship ship : ships) {
            if (ship.getShipType().getName().equals(shipName) && (ship.isSunk())) {
                return true;
            }
        }
        return false;
    }

    public Set<Map<String, Object>> getSunkShips() {
        Set<Map<String, Object>> result = new HashSet<>();
        for (Ship ship : ships) {
            if (ship.isSunk()) {
                result.add(ship.getShipType().getType());
            }
        }
        return result;
    }

    private boolean isPositionOnBoard() {

        for (Ship ship : ships) {
            for (Location location : ship.getLocation()) {
                if (location.getRow() >= rows || location.getColumn() >= cols) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isPositionFleetConsecutive() {
        for (Ship ship : ships) {
            if (!ship.isPositionShipConsecutive()) {
                return false;
            }
        }
        return true;
    }

    private boolean areAllShipsRightSize(){
        for(Ship ship : ships){
            if(!ship.isRightShipSize()){
                return false;
            }
        }
        return true;
    }


    private boolean isShipOverlappingOtherShips() {
        for (Ship ship : ships) {
            for (Location location : ship.getLocation()) {
                if (isFoundInOtherShipPositions(ship, location)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isFoundInOtherShipPositions(Ship ship, Location location) {
        for (Ship otherShip : ships) {
            for (Location otherLocation : otherShip.getLocation()) {
                boolean isLocationOnOtherShip = location.equals(otherLocation) && !ship.getShipType().equals(otherShip.getShipType());
                if (isLocationOnOtherShip) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isFullFleet(){
        Set<String> allShipTypes = new HashSet<>();
        for(Ship ship : ships){
            allShipTypes.add(ship.getShipType().getName());
        }
        return allShipTypes.size() == EXPECTED_FLEET_SIZE && ships.size() == EXPECTED_FLEET_SIZE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fleet fleet = (Fleet) o;

        if (rows != fleet.rows) return false;
        return cols == fleet.cols;
    }

    @Override
    public int hashCode() {
        int result = rows;
        result = 31 * result + cols;
        return result;
    }
}
