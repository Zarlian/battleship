package be.howest.ti.battleship.logic;

import be.howest.ti.battleship.logic.fleet.Fleet;
import be.howest.ti.battleship.logic.fleet.Location;
import be.howest.ti.battleship.logic.fleet.Ship;

import java.util.List;

public class DemoData {

    public Fleet getDemoFleet(){
        List<Ship> ships = List.of(
                new Ship("carrier", List.of(new Location("A-1"), new Location("A-2"), new Location("A-3"), new Location("A-4"), new Location("A-5"))),
                new Ship("battleship", List.of(new Location("B-1"), new Location("B-2"), new Location("B-3"), new Location("B-4"))),
                new Ship("cruiser", List.of(new Location("C-1"), new Location("C-2"), new Location("C-3"))),
                new Ship("submarine", List.of(new Location("D-1"), new Location("D-2"), new Location("D-3"))),
                new Ship("destroyer", List.of(new Location("E-1"), new Location("E-2")))
        );
        return new Fleet(10, 10, ships);
    }

    public Game getDemoGame(Commander name, String type, String prefix) {
        return new Game(name, type, prefix);

    }

    public Commander getDemoCommander(String name) {
        return new Commander(name,getDemoFleet());
    }

}
