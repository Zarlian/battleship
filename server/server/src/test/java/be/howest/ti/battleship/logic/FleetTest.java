package be.howest.ti.battleship.logic;

import be.howest.ti.battleship.logic.fleet.Fleet;
import be.howest.ti.battleship.logic.fleet.Location;
import be.howest.ti.battleship.logic.fleet.Ship;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FleetTest {
    @Test
    void isHit() {
        List<Ship> ships = List.of(
                new Ship("carrier", List.of(new Location(10, 10), new Location(9, 10), new Location(8, 10), new Location(7, 10), new Location(6, 10))),
                new Ship("battleship", List.of(new Location(10, 9), new Location(9, 9), new Location(8, 9), new Location(7, 9))),
                new Ship("cruiser", List.of(new Location(4, 9), new Location(4, 8), new Location(4, 7))),
                new Ship("submarine", List.of(new Location(2, 9), new Location(2, 8), new Location(2, 7))),
                new Ship("destroyer", List.of(new Location(6, 9), new Location(6, 8)))
        );
        Fleet fleet = new Fleet(11, 11, ships);

        Location l1 = new Location(8, 10);

        String expected = "CARRIER";

        assertEquals(expected, fleet.whichShipIsHit(l1));
    }

    @Test
    void isMiss() {
        List<Ship> ships = List.of(
                new Ship("carrier", List.of(new Location(10, 10), new Location(9, 10), new Location(8, 10), new Location(7, 10), new Location(6, 10))),
                new Ship("battleship", List.of(new Location(10, 9), new Location(9, 9), new Location(8, 9), new Location(7, 9))),
                new Ship("cruiser", List.of(new Location(4, 9), new Location(4, 8), new Location(4, 7))),
                new Ship("submarine", List.of(new Location(2, 9), new Location(2, 8), new Location(2, 7))),
                new Ship("destroyer", List.of(new Location(6, 9), new Location(6, 8)))
        );
        Fleet fleet = new Fleet(11, 11, ships);

        Location l1 = new Location(1, 1);

        String expected = "carrier";

        assertNotEquals(expected, fleet.whichShipIsHit(l1));
    }

    @Test
    void oneSalvoCheck() {
        List<Ship> ships = List.of(
                new Ship("carrier", List.of(new Location("A-1"), new Location("A-2"), new Location("A-3"), new Location("A-4"), new Location("A-5"))),
                new Ship("battleship", List.of(new Location("B-1"), new Location("B-2"), new Location("B-3"), new Location("B-4"))),
                new Ship("cruiser", List.of(new Location("C-1"), new Location("C-2"), new Location("C-3"))),
                new Ship("submarine", List.of(new Location("D-1"), new Location("D-2"), new Location("D-3"))),
                new Ship("destroyer", List.of(new Location("E-1"), new Location("E-2")))
        );
        Fleet fleet = new Fleet(10, 10, ships);

        List<String> l1 = new ArrayList<>();
        l1.add("A-2");

        Map<String, String> expected = new HashMap<>();
        expected.put("A-2", "CARRIER");

        assertEquals(expected, fleet.salvoCheck(l1));
    }

    @Test
    void multiSalvoCheck() {
        List<Ship> ships = List.of(
                new Ship("carrier", List.of(new Location("A-1"), new Location("A-2"), new Location("A-3"), new Location("A-4"), new Location("A-5"))),
                new Ship("battleship", List.of(new Location("B-1"), new Location("B-2"), new Location("B-3"), new Location("B-4"))),
                new Ship("cruiser", List.of(new Location("C-1"), new Location("C-2"), new Location("C-3"))),
                new Ship("submarine", List.of(new Location("D-1"), new Location("D-2"), new Location("D-3"))),
                new Ship("destroyer", List.of(new Location("E-1"), new Location("E-2")))
        );
        Fleet fleet = new Fleet(10, 10, ships);

        List<String> l1 = new ArrayList<>();
        l1.add("A-2");
        l1.add("A-3");
        l1.add("D-1");

        Map<String, String> expected = new HashMap<>();
        expected.put("A-2", "CARRIER");
        expected.put("A-3", "CARRIER");
        expected.put("D-1", "SUBMARINE");

        assertEquals(expected, fleet.salvoCheck(l1));
    }

    @Test
    void isMissString() {
        List<Ship> ships = List.of(
                new Ship("carrier", List.of(new Location("A-1"), new Location("A-2"), new Location("A-3"), new Location("A-4"), new Location("A-5"))),
                new Ship("battleship", List.of(new Location("B-1"), new Location("B-2"), new Location("B-3"), new Location("B-4"))),
                new Ship("cruiser", List.of(new Location("C-1"), new Location("C-2"), new Location("C-3"))),
                new Ship("submarine", List.of(new Location("D-1"), new Location("D-2"), new Location("D-3"))),
                new Ship("destroyer", List.of(new Location("E-1"), new Location("E-2")))
        );
        Fleet fleet = new Fleet(10, 10, ships);

        List<String> l1 = new ArrayList<>();
        l1.add("A-2");

        Map<String, String> expected = new HashMap<>();
        expected.put("l1", "carrier");

        assertNotEquals(expected, fleet.salvoCheck(l1));
    }

    @Test
    void isSunk() {
        List<Ship> ships = List.of(
                new Ship("carrier", List.of(new Location(10, 10), new Location(9, 10), new Location(8, 10), new Location(7, 10), new Location(6, 10))),
                new Ship("battleship", List.of(new Location(10, 9), new Location(9, 9), new Location(8, 9), new Location(7, 9))),
                new Ship("cruiser", List.of(new Location(4, 9), new Location(4, 8), new Location(4, 7))),
                new Ship("submarine", List.of(new Location(2, 9), new Location(2, 8), new Location(2, 7))),
                new Ship("destroyer", List.of(new Location(6, 9), new Location(6, 8)))
        );
        Fleet fleet = new Fleet(11, 11, ships);

        Location l1 = new Location(2, 9);
        Location l2 = new Location(2, 8);
        Location l3 = new Location(2, 7);

        fleet.whichShipIsHit(l1);
        fleet.whichShipIsHit(l2);
        fleet.whichShipIsHit(l3);

        assertTrue(fleet.isSunk("submarine"));
        assertFalse(fleet.isSunk("battleship"));
    }

    @Test
    void isSunkString() {
        List<Ship> ships = List.of(
                new Ship("carrier", List.of(new Location("A-1"), new Location("A-2"), new Location("A-3"), new Location("A-4"), new Location("A-5"))),
                new Ship("battleship", List.of(new Location("B-1"), new Location("B-2"), new Location("B-3"), new Location("B-4"))),
                new Ship("cruiser", List.of(new Location("C-1"), new Location("C-2"), new Location("C-3"))),
                new Ship("submarine", List.of(new Location("D-1"), new Location("D-2"), new Location("D-3"))),
                new Ship("destroyer", List.of(new Location("E-1"), new Location("E-2")))
        );
        Fleet fleet = new Fleet(10, 10, ships);

        Location l1 = new Location("C-1");
        Location l2 = new Location("C-2");
        Location l3 = new Location("C-3");

        fleet.whichShipIsHit(l1);
        fleet.whichShipIsHit(l2);
        fleet.whichShipIsHit(l3);

        assertTrue(fleet.isSunk("cruiser"));
        assertFalse(fleet.isSunk("battleship"));
    }

    @Test
    void getSunkShips() {
        List<Ship> ships = List.of(
                new Ship("carrier", List.of(new Location(10, 10), new Location(9, 10), new Location(8, 10), new Location(7, 10), new Location(6, 10))),
                new Ship("battleship", List.of(new Location(10, 9), new Location(9, 9), new Location(8, 9), new Location(7, 9))),
                new Ship("cruiser", List.of(new Location(4, 9), new Location(4, 8), new Location(4, 7))),
                new Ship("submarine", List.of(new Location(2, 9), new Location(2, 8), new Location(2, 7))),
                new Ship("destroyer", List.of(new Location(6, 9), new Location(6, 8)))
        );
        Fleet fleet = new Fleet(11, 11, ships);

        Location l1 = new Location(4, 9);
        Location l2 = new Location(4, 8);
        Location l3 = new Location(4, 7);

        fleet.whichShipIsHit(l1);
        fleet.whichShipIsHit(l2);
        fleet.whichShipIsHit(l3);

        String expected = "cruiser";

        assertEquals(1, fleet.getSunkShips().size());
        assertTrue(fleet.getSunkShips().contains(Map.of("name", "cruiser",
                                                            "size", 3)));
    }

    @Test
    void passingTheValidator() {
        List<Ship> ships = List.of(
                new Ship("carrier", List.of(new Location("A-1"), new Location("A-2"), new Location("A-3"), new Location("A-4"), new Location("A-5"))),
                new Ship("battleship", List.of(new Location("B-1"), new Location("B-2"), new Location("B-3"), new Location("B-4"))),
                new Ship("cruiser", List.of(new Location("C-1"), new Location("C-2"), new Location("C-3"))),
                new Ship("submarine", List.of(new Location("D-1"), new Location("D-2"), new Location("D-3"))),
                new Ship("destroyer", List.of(new Location("E-1"), new Location("E-2")))
        );
        Fleet fleet1 = new Fleet(10, 10, ships);
        Fleet fleet2 = new Fleet(10, 10, ships);

        assertEquals(fleet1.getShips(), fleet2.getShips());
    }

    @Test
    void positionFleetOutOfBoard() {
        List<Ship> ships = List.of(
                new Ship("carrier", List.of(new Location("A-1"), new Location("A-2"), new Location("A-3"), new Location("A-4"), new Location("A-5"))),
                new Ship("battleship", List.of(new Location("B-1"), new Location("B-2"), new Location("B-3"), new Location("B-4"))),
                new Ship("cruiser", List.of(new Location("C-1"), new Location("C-2"), new Location("C-3"))),
                new Ship("submarine", List.of(new Location("D-1"), new Location("D-2"), new Location("D-3"))),
                new Ship("destroyer", List.of(new Location("E-40"), new Location("E-41")))
        );

        assertThrows(IllegalArgumentException.class, () -> new Fleet(10, 10, ships));
    }

    @Test
    void checkConsecutiveShipLocations() {
        List<Ship> ships = List.of(
                new Ship("carrier", List.of(new Location("A-1"), new Location("A-2"), new Location("A-3"), new Location("A-4"), new Location("A-8"))),
                new Ship("battleship", List.of(new Location("B-1"), new Location("B-2"), new Location("B-3"), new Location("B-4"))),
                new Ship("cruiser", List.of(new Location("C-1"), new Location("C-2"), new Location("C-3"))),
                new Ship("submarine", List.of(new Location("D-1"), new Location("D-2"), new Location("D-3"))),
                new Ship("destroyer", List.of(new Location("E-1"), new Location("E-2")))
        );
        assertThrows(IllegalArgumentException.class, () -> new Fleet(10, 10, ships));
    }

    @Test
    void isNotRightShipSize() {
        List<Ship> ships = List.of(
                new Ship("carrier", List.of(new Location("A-1"), new Location("A-2"), new Location("A-3"))),
                new Ship("battleship", List.of(new Location("B-1"), new Location("B-2"), new Location("B-3"), new Location("B-4"))),
                new Ship("cruiser", List.of(new Location("C-1"), new Location("C-2"), new Location("C-3"))),
                new Ship("submarine", List.of(new Location("D-1"), new Location("D-2"), new Location("D-3"))),
                new Ship("destroyer", List.of(new Location("E-1"), new Location("E-2")))
        );
        assertThrows(IllegalArgumentException.class, () -> new Fleet(10, 10, ships));
    }

    @Test
    void isOverlapping() {
        List<Ship> ships = List.of(
                new Ship("carrier", List.of(new Location("A-1"), new Location("A-2"), new Location("A-3"), new Location("A-4"), new Location("A-5"))),
                new Ship("battleship", List.of(new Location("A-1"), new Location("B-1"), new Location("C-1"), new Location("D-1"))),
                new Ship("cruiser", List.of(new Location("C-1"), new Location("C-2"), new Location("C-3"))),
                new Ship("submarine", List.of(new Location("D-1"), new Location("D-2"), new Location("D-3"))),
                new Ship("destroyer", List.of(new Location("E-1"), new Location("E-2")))
        );
        assertThrows(IllegalArgumentException.class, () -> new Fleet(10, 10, ships));
    }

    @Test
    void isLocationInBoard() {
        List<Ship> ships = List.of(
                new Ship("carrier", List.of(new Location("A-1"), new Location("A-2"), new Location("A-3"), new Location("A-4"), new Location("A-5"))),
                new Ship("battleship", List.of(new Location("B-1"), new Location("B-2"), new Location("B-3"), new Location("B-4"))),
                new Ship("cruiser", List.of(new Location("C-1"), new Location("C-2"), new Location("C-3"))),
                new Ship("submarine", List.of(new Location("D-1"), new Location("D-2"), new Location("D-3"))),
                new Ship("destroyer", List.of(new Location("E-1"), new Location("E-2")))
        );
        Fleet fleet = new Fleet(10, 10, ships);

        Location l1 = new Location("A-1");

        assertTrue(fleet.isInBoard(l1));
    }

    @Test
    void isLocationInBoardFalse() {
        List<Ship> ships = List.of(
                new Ship("carrier", List.of(new Location("A-1"), new Location("A-2"), new Location("A-3"), new Location("A-4"), new Location("A-5"))),
                new Ship("battleship", List.of(new Location("B-1"), new Location("B-2"), new Location("B-3"), new Location("B-4"))),
                new Ship("cruiser", List.of(new Location("C-1"), new Location("C-2"), new Location("C-3"))),
                new Ship("submarine", List.of(new Location("D-1"), new Location("D-2"), new Location("D-3"))),
                new Ship("destroyer", List.of(new Location("E-1"), new Location("E-2")))
        );
        Fleet fleet = new Fleet(10, 10, ships);

        Location l1 = new Location("A-11");

        assertFalse(fleet.isInBoard(l1));
    }

    @Test
    void findShip() {
        List<Ship> ships = List.of(
                new Ship("carrier", List.of(new Location("A-5"), new Location("A-4"), new Location("A-3"), new Location("A-2"), new Location("A-1"))),
                new Ship("battleship", List.of(new Location("B-1"), new Location("B-2"), new Location("B-3"), new Location("B-4"))),
                new Ship("cruiser", List.of(new Location("C-1"), new Location("C-2"), new Location("C-3"))),
                new Ship("submarine", List.of(new Location("D-1"), new Location("D-2"), new Location("D-3"))),
                new Ship("destroyer", List.of(new Location("E-1"), new Location("E-2")))
        );
        Fleet fleet = new Fleet(10, 10, ships);

        Ship result = fleet.findShip("carrier");
        assertEquals(ships.get(0), result);
    }


    @Test
    void shipCanMove() {
        List<Ship> ships = List.of(
                new Ship("carrier", List.of(new Location("A-1"), new Location("A-2"), new Location("A-3"), new Location("A-4"), new Location("A-5"))),
                new Ship("battleship", List.of(new Location("B-1"), new Location("B-2"), new Location("B-3"), new Location("B-4"))),
                new Ship("cruiser", List.of(new Location("C-1"), new Location("C-2"), new Location("C-3"))),
                new Ship("submarine", List.of(new Location("D-1"), new Location("D-2"), new Location("D-3"))),
                new Ship("destroyer", List.of(new Location("E-1"), new Location("E-2")))
        );
        Fleet fleet = new Fleet(10, 10, ships);

        assertDoesNotThrow(() -> fleet.canShipMove("carrier"));
    }

    @Test
    void shipCanNotMoveBecauseOutOfBoard() {
        List<Ship> ships = List.of(
                new Ship("carrier", List.of(new Location("A-5"), new Location("A-4"), new Location("A-3"), new Location("A-2"), new Location("A-1"))),
                new Ship("battleship", List.of(new Location("B-1"), new Location("B-2"), new Location("B-3"), new Location("B-4"))),
                new Ship("cruiser", List.of(new Location("C-1"), new Location("C-2"), new Location("C-3"))),
                new Ship("submarine", List.of(new Location("D-1"), new Location("D-2"), new Location("D-3"))),
                new Ship("destroyer", List.of(new Location("E-1"), new Location("E-2")))
        );
        Fleet fleet = new Fleet(10, 10, ships);

        assertThrows(BattleshipException.class, () -> fleet.canShipMove("carrier"));
    }

    @Test
    void shipCanNotMoveBecauseOtherShipBlocks() {
        List<Ship> ships = List.of(
                new Ship("carrier", List.of(new Location("A-5"), new Location("A-4"), new Location("A-3"), new Location("A-2"), new Location("A-1"))),
                new Ship("battleship", List.of(new Location("E-5"), new Location("D-5"), new Location("C-5"), new Location("B-5"))),
                new Ship("cruiser", List.of(new Location("C-1"), new Location("C-2"), new Location("C-3"))),
                new Ship("submarine", List.of(new Location("D-1"), new Location("D-2"), new Location("D-3"))),
                new Ship("destroyer", List.of(new Location("E-1"), new Location("E-2")))
        );
        Fleet fleet = new Fleet(10, 10, ships);

        assertThrows(BattleshipException.class, () -> fleet.canShipMove("battleship"));
    }

    @Test
    void moveShip() {
        List<Ship> ships = List.of(
                new Ship("carrier", List.of(new Location("A-1"), new Location("A-2"), new Location("A-3"), new Location("A-4"), new Location("A-5"))),
                new Ship("battleship", List.of(new Location("B-1"), new Location("B-2"), new Location("B-3"), new Location("B-4"))),
                new Ship("cruiser", List.of(new Location("C-1"), new Location("C-2"), new Location("C-3"))),
                new Ship("submarine", List.of(new Location("D-1"), new Location("D-2"), new Location("D-3"))),
                new Ship("destroyer", List.of(new Location("E-1"), new Location("E-2")))
        );
        Fleet fleet = new Fleet(10, 10, ships);
        fleet.moveShip("carrier");

        List<Location> expected = List.of(
                new Location("A-2"),
                new Location("A-3"),
                new Location("A-4"),
                new Location("A-5"),
                new Location("A-6")
        );
        assertEquals(expected, ships.get(0).getLocation());
    }

    @Test
    void moveShipFalse() {
        List<Ship> ships = List.of(
                new Ship("carrier", List.of(new Location("A-5"), new Location("A-4"), new Location("A-3"), new Location("A-2"), new Location("A-1"))),
                new Ship("battleship", List.of(new Location("E-5"), new Location("D-5"), new Location("C-5"), new Location("B-5"))),
                new Ship("cruiser", List.of(new Location("C-1"), new Location("C-2"), new Location("C-3"))),
                new Ship("submarine", List.of(new Location("D-1"), new Location("D-2"), new Location("D-3"))),
                new Ship("destroyer", List.of(new Location("E-1"), new Location("E-2")))
        );
        Fleet fleet = new Fleet(10, 10, ships);

        List<Location> expected = List.of(
                new Location("A-5"),
                new Location("A-4"),
                new Location("A-3"),
                new Location("A-2"),
                new Location("A-1")
        );
        assertEquals(expected, fleet.findShip("carrier").getLocation());
    }


    @Test
    void checkIncompleteFleet() {
        List<Ship> incompleteShips = List.of(
                new Ship("carrier", List.of(new Location("A-5"), new Location("A-4"), new Location("A-3"), new Location("A-2"), new Location("A-1"))),
                new Ship("battleship", List.of(new Location("E-5"), new Location("D-5"), new Location("C-5"), new Location("B-5"))),
                new Ship("cruiser", List.of(new Location("C-1"), new Location("C-2"), new Location("C-3"))),
                new Ship("submarine", List.of(new Location("D-1"), new Location("D-2"), new Location("D-3")))
        );

        assertThrows(IllegalArgumentException.class, () -> new Fleet(10, 10, incompleteShips));
    }

    @Test
    void checkDoubleShipsInFleet() {
        List<Ship> doubleShips = List.of(
                new Ship("carrier", List.of(new Location("A-5"), new Location("A-4"), new Location("A-3"), new Location("A-2"), new Location("A-1"))),
                new Ship("carrier", List.of(new Location("A-5"), new Location("A-4"), new Location("A-3"), new Location("A-2"), new Location("A-1"))),
                new Ship("cruiser", List.of(new Location("C-1"), new Location("C-2"), new Location("C-3"))),
                new Ship("submarine", List.of(new Location("D-1"), new Location("D-2"), new Location("D-3"))),
                new Ship("destroyer", List.of(new Location("E-1"), new Location("E-2")))
        );

        assertThrows(IllegalArgumentException.class, () -> new Fleet(10, 10, doubleShips));

    }

    @Test
    void checkTooLargeFleet() {
        List<Ship> tooManyShips = List.of(
                new Ship("carrier", List.of(new Location("A-5"), new Location("A-4"), new Location("A-3"), new Location("A-2"), new Location("A-1"))),
                new Ship("carrier", List.of(new Location("A-5"), new Location("A-4"), new Location("A-3"), new Location("A-2"), new Location("A-1"))),
                new Ship("battleship", List.of(new Location("E-5"), new Location("D-5"), new Location("C-5"), new Location("B-5"))),
                new Ship("cruiser", List.of(new Location("C-1"), new Location("C-2"), new Location("C-3"))),
                new Ship("submarine", List.of(new Location("D-1"), new Location("D-2"), new Location("D-3"))),
                new Ship("destroyer", List.of(new Location("E-1"), new Location("E-2")))
        );

        assertThrows(IllegalArgumentException.class, () -> new Fleet(10, 10, tooManyShips));
    }

    @Test
    void hitShipCanNotMove() {
        List<Ship> ships = List.of(
                new Ship("carrier", List.of(new Location("A-5"), new Location("A-4"), new Location("A-3"), new Location("A-2"), new Location("A-1"))),
                new Ship("battleship", List.of(new Location("E-5"), new Location("D-5"), new Location("C-5"), new Location("B-5"))),
                new Ship("cruiser", List.of(new Location("C-1"), new Location("C-2"), new Location("C-3"))),
                new Ship("submarine", List.of(new Location("D-1"), new Location("D-2"), new Location("D-3"))),
                new Ship("destroyer", List.of(new Location("E-1"), new Location("E-2")))
        );
        Fleet fleet = new Fleet(10, 10, ships);

        fleet.whichShipIsHit(new Location("A-5"));

        assertThrows(BattleshipException.class, () -> fleet.canShipMove("carrier"));



    }


}