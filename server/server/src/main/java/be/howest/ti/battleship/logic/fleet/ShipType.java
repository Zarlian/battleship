package be.howest.ti.battleship.logic.fleet;

import java.util.Map;

public enum ShipType {
    CARRIER(5),
    BATTLESHIP(4),
    CRUISER(3),
    SUBMARINE(3),
    DESTROYER(2);

    private final int size;

    ShipType(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public String getName() {
       return this.name().toLowerCase();
    }

    public Map<String, Object> getType(){
        return Map.of("name", getName(),
                        "size", getSize());
    }
}
