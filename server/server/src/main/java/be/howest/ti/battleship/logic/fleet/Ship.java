package be.howest.ti.battleship.logic.fleet;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class Ship {

    private final ShipType shipType;
    private List<Location> location;
    private final Set<Location> hits;

    @JsonCreator
    public Ship(
            @JsonProperty("name") String name,
            @JsonProperty("location") List<Location> location
    ) {
        this.shipType = ShipType.valueOf(name.toUpperCase());
        this.location = location;
        this.hits = new HashSet<>();
    }


    public boolean isAHit(Location location) {
        return this.location.contains(location);
    }

    public boolean isHit() {
        return !hits.isEmpty();
    }

    public boolean fireShot(Location location) {
        if (isAHit(location)) {
            hits.add(location);
            return true;
        }
        return false;
    }

    public List<Location> getLocation() {
        return location;
    }

    public int getLength() {
        return location.size();
    }

    public ShipType getShipType() {
        return shipType;
    }

    public int getShipTypeLength() {
        return shipType.getSize();
    }

    public boolean isSunk() {
        return hits.size() == location.size();
    }

    public Location getHead() {
        return location.get(location.size() - 1);
    }

    public Location getTail() {
        return location.get(0);
    }

    public Ship moveShip() {
        Direction direction = getDirection();
        List<Location> updatedLocations = new ArrayList<>(location.size());
        for (Location loc : location) {
            updatedLocations.add(loc.move(direction));
        }
        location = updatedLocations;
        return new Ship(this.shipType.getName(), this.location);
    }

    public Location calculateNewHeadLocation() {
        Direction direction = getDirection();
        Location head = getHead();
        return head.move(direction);
    }
    public boolean isHorizontal() {
        return getHead().getRow() == getTail().getRow();
    }

    public Direction getDirection() {
        Location head = getHead();
        Location tail = getTail();

        if (isHorizontal()) {
            return getHorizontalDirection(head, tail);
        } else {
            return getVerticalDirection(head, tail);
        }
    }

    private Direction getVerticalDirection(Location head, Location tail) {
        if (head.getRow() > tail.getRow()) {
            return Direction.DOWN;
        } else {
            return Direction.UP;
        }
    }

    private  Direction getHorizontalDirection(Location head, Location tail) {
        if (head.getColumn() > tail.getColumn()) {
            return Direction.RIGHT;
        } else {
            return Direction.LEFT;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        Ship that = (Ship) other;

        return Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return location != null ? location.hashCode() : 0;
    }

    public boolean isPositionShipConsecutive() {
        for (int shipIndex = 1; shipIndex < location.size(); shipIndex++) {
            if (isHorizontal()) {
                if (!isHorizontalConsecutive(shipIndex)) {
                    return false;
                }
            } else {
                if (!isVerticalConsecutive(shipIndex)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isVerticalConsecutive(int shipIndex) {
        if (!isSameColumn(shipIndex)) {
            return false;
        } else {
            if (isDirectionUP()) {
                return isRowBelowPreviousRow(shipIndex);
            } else {
                return isRowAbovePreviousRow(shipIndex);
            }
        }
    }

    public boolean isDirectionUP() {
        return getDirection().equals(Direction.UP);
    }

    public boolean isRowAbovePreviousRow(int shipIndex) {
        int prevShipIndex = shipIndex - 1;
        return location.get(shipIndex).getRow() == (location.get(prevShipIndex).getRow() + 1);
    }

    public boolean isRowBelowPreviousRow(int shipIndex) {
        int prevShipIndex = shipIndex - 1;
        return location.get(shipIndex).getRow() == (location.get(prevShipIndex).getRow() - 1);
    }

    public boolean isSameColumn(int shipIndex) {
        int prevShipIndex = shipIndex - 1;
        return location.get(shipIndex).getColumn() == location.get(prevShipIndex).getColumn();
    }

    public boolean isHorizontalConsecutive(int shipIndex) {
        if (!isSameRow(shipIndex)) {
            return false;
        } else {
            if (isDirectionLeft()) {
                return isColumnBeforePreviousColumn(shipIndex);
            } else {
                return isColumnAfterPreviousColumn(shipIndex);
            }
        }
    }

    public boolean isDirectionLeft() {
        return getDirection().equals(Direction.LEFT);
    }

    public boolean isColumnAfterPreviousColumn(int shipIndex) {
        int prevShipIndex = shipIndex - 1;
        return location.get(shipIndex).getColumn() == (location.get(prevShipIndex).getColumn() + 1);
    }

    public boolean isColumnBeforePreviousColumn(int shipIndex) {
        int prevShipIndex = shipIndex - 1;
        return location.get(shipIndex).getColumn() == (location.get(prevShipIndex).getColumn() - 1);
    }

    public boolean isSameRow(int shipIndex) {
        int prevShipIndex = shipIndex - 1;
        return location.get(shipIndex).getRow() == location.get(prevShipIndex).getRow();
    }

    public boolean isRightShipSize() {
        return getShipTypeLength() == getLength();
    }

}
