package be.howest.ti.battleship.logic;

import be.howest.ti.battleship.logic.fleet.Direction;
import be.howest.ti.battleship.logic.fleet.Location;
import be.howest.ti.battleship.logic.fleet.Ship;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {

    @Test
    void isHit() {
        Location l1 = new Location(10, 10);
        Ship ship = new Ship("carrier", List.of(l1));

        Location l2 = new Location(10, 10);

        assertTrue(ship.fireShot(l2));
    }


    @Test
    void isMiss() {
        Location l1 = new Location(10, 10);
        Ship ship = new Ship("carrier", List.of(l1));

        Location l2 = new Location(7, 10);

        assertFalse(ship.fireShot(l2));
    }

    @Test
    void getLength() {
        Ship ship = new Ship("carrier", List.of(
                new Location(10, 10),
                new Location(9, 10),
                new Location(8, 10)
        ));

        assertEquals(3, ship.getLength());
    }

    @Test
    void isHitString() {
        Location l1 = new Location("A-1");
        Ship ship = new Ship("carrier", List.of(l1));

        Location l2 = new Location("A-1");

        assertTrue(ship.fireShot(l2));
    }

    @Test
    void isMissString() {
        Location l1 = new Location("A-1");
        Ship ship = new Ship("carrier", List.of(l1));

        Location l2 = new Location("A-2");

        assertFalse(ship.fireShot(l2));
    }

    @Test
    void isSunk() {
        Location l1 = new Location(10, 10);
        Location l2 = new Location(9, 10);
        Location l3 = new Location(8, 10);
        Ship ship = new Ship("carrier", List.of(l1, l2, l3));

        Location l4 = new Location(10, 10);
        Location l5 = new Location(9, 10);
        Location l6 = new Location(8, 10);

        ship.fireShot(l4);
        ship.fireShot(l5);
        ship.fireShot(l6);

        assertTrue(ship.isSunk());
    }


    @Test
    void isNotSunk() {
        Location l1 = new Location(10, 10);
        Location l2 = new Location(9, 10);
        Location l3 = new Location(8, 10);
        Ship ship = new Ship("carrier", List.of(l1, l2, l3));

        Location l4 = new Location(10, 10);
        Location l5 = new Location(9, 10);

        ship.fireShot(l4);
        ship.fireShot(l5);

        assertFalse(ship.isSunk());
    }

    @Test
    void isNotSunkBecauseHitTwiceOnSamePlace() {
        Location l1 = new Location(10, 10);
        Location l2 = new Location(9, 10);
        Location l3 = new Location(8, 10);
        Ship ship = new Ship("carrier", List.of(l1, l2, l3));

        Location l4 = new Location(10, 10);
        Location l5 = new Location(10, 10);
        Location l6 = new Location(9, 10);


        ship.fireShot(l4);
        ship.fireShot(l5);
        ship.fireShot(l6);

        assertFalse(ship.isSunk());
    }

    @Test
    void isSunkString() {
        Location l1 = new Location("A-1");
        Location l2 = new Location("A-2");
        Location l3 = new Location("A-3");
        Ship ship = new Ship("carrier", List.of(l1, l2, l3));

        Location l4 = new Location("A-1");
        Location l5 = new Location("A-2");
        Location l6 = new Location("A-3");

        ship.fireShot(l4);
        ship.fireShot(l5);
        ship.fireShot(l6);

        assertTrue(ship.isSunk());
    }


    @Test
    void isNotSunkString() {
        Location l1 = new Location("A-1");
        Location l2 = new Location("A-2");
        Location l3 = new Location("A-3");
        Ship ship = new Ship("carrier", List.of(l1, l2, l3));

        Location l4 = new Location("A-1");
        Location l5 = new Location("A-2");

        ship.fireShot(l4);
        ship.fireShot(l5);

        assertFalse(ship.isSunk());
    }

    @Test
    void isNotSunkBecauseHitTwiceOnSamePlaceString() {
        Location l1 = new Location("A-1");
        Location l2 = new Location("A-2");
        Location l3 = new Location("A-3");
        Ship ship = new Ship("carrier", List.of(l1, l2, l3));

        Location l4 = new Location("A-1");
        Location l5 = new Location("A-2");
        Location l6 = new Location("A-2");


        ship.fireShot(l4);
        ship.fireShot(l5);
        ship.fireShot(l6);

        assertFalse(ship.isSunk());
    }

    @Test
    void isHorizontal() {
        Ship ship = new Ship("carrier", List.of(
                new Location(10, 10),
                new Location(10, 9),
                new Location(10, 8)
        ));

        assertTrue(ship.isHorizontal());
    }

    @Test
    void isHorizontalFalse() {
        Ship ship = new Ship("carrier", List.of(
                new Location(10, 10),
                new Location(9, 10),
                new Location(8, 10)
        ));

        assertFalse(ship.isHorizontal());
    }

    @Test
    void isHorizontalFalseString() {
        Location l1 = new Location("A-1");
        Location l2 = new Location("A-2");
        Location l3 = new Location("A-3");
        Ship ship = new Ship("carrier", List.of(l1, l2, l3));

        assertTrue(ship.isHorizontal());
    }

    @Test
    void isHorizontalString() {
        Location l1 = new Location("A-1");
        Location l2 = new Location("B-1");
        Location l3 = new Location("C-1");
        Ship ship = new Ship("carrier", List.of(l1, l2, l3));

        assertFalse(ship.isHorizontal());
    }


    @Test
    void getDirectionWhenUP() {
        Ship ship = new Ship("carrier", List.of(
                new Location(10, 10),
                new Location(9, 10),
                new Location(8, 10)
        ));

        assertEquals(Direction.UP, ship.getDirection());
    }

    @Test
    void getDirectionWhenDOWN() {
        Ship ship = new Ship("carrier", List.of(
                new Location(10, 10),
                new Location(11, 10),
                new Location(12, 10)
        ));

        assertEquals(Direction.DOWN, ship.getDirection());
    }

    @Test
    void getDirectionWhenLEFT() {
        Ship ship = new Ship("carrier", List.of(
                new Location(10, 10),
                new Location(10, 9),
                new Location(10, 8)
        ));

        assertEquals(Direction.LEFT, ship.getDirection());
    }

    @Test
    void getDirectionWhenRIGHT() {
        Ship ship = new Ship("carrier", List.of(
                new Location(10, 5),
                new Location(10, 6),
                new Location(10, 7)
        ));

        assertEquals(Direction.RIGHT, ship.getDirection());
    }


    @Test
    void getDirectionWhenUPString() {
        Ship ship = new Ship("carrier", List.of(
                new Location("C-2"),
                new Location("B-2"),
                new Location("A-2")
        ));

        assertEquals(Direction.UP, ship.getDirection());
    }

    @Test
    void getDirectionWhenDOWNString() {
        Ship ship = new Ship("carrier", List.of(
                new Location("A-1"),
                new Location("B-1"),
                new Location("C-1")
        ));

        assertEquals(Direction.DOWN, ship.getDirection());
    }

    @Test
    void getDirectionWhenLEFTString() {
        Ship ship = new Ship("carrier", List.of(
                new Location("A-5"),
                new Location("A-4"),
                new Location("A-3")
        ));

        assertEquals(Direction.LEFT, ship.getDirection());
    }

    @Test
    void getDirectionWhenRIGHTString() {
        Ship ship = new Ship("carrier", List.of(
                new Location("A-3"),
                new Location("A-4"),
                new Location("A-5")
        ));

        assertEquals(Direction.RIGHT, ship.getDirection());
    }

    @Test
    void shipLocationEnumValuesTest() {
        Ship ship = new Ship("carrier", List.of(
                new Location("A-3"),
                new Location("A-4"),
                new Location("A-5")
        ));
        Ship otherShip = new Ship("submarine", List.of(
                new Location("A-3"),
                new Location("A-4"),
                new Location("A-5")
        ));
        assertNotEquals(otherShip.getShipType().getSize(), ship.getShipType().getSize());
    }

    @Test
    void moveShipToRight() {
        Ship ship = new Ship("submarine", List.of(
                new Location("A-3"),
                new Location("A-4"),
                new Location("A-5")
        ));

        ship.moveShip();
        List<Location> expected = List.of(
                new Location("A-4"),
                new Location("A-5"),
                new Location("A-6")
        );

        assertEquals(expected, ship.getLocation());
    }

    @Test
    void moveShipToLeft() {
        Ship ship = new Ship("submarine", List.of(
                new Location("A-5"),
                new Location("A-4"),
                new Location("A-3")
        ));

        ship.moveShip();
        List<Location> expected = List.of(
                new Location("A-4"),
                new Location("A-3"),
                new Location("A-2")
        );

        assertEquals(expected, ship.getLocation());
    }

    @Test
    void moveShipUp() {
        Ship ship = new Ship("submarine", List.of(
                new Location("D-2"),
                new Location("C-2"),
                new Location("B-2")
        ));

        ship.moveShip();
        List<Location> expected = List.of(
                new Location("C-2"),
                new Location("B-2"),
                new Location("A-2")
        );

        assertEquals(expected, ship.getLocation());
    }
    @Test
    void moveShipDown() {
        Ship ship = new Ship("submarine", List.of(
                new Location("B-2"),
                new Location("C-2"),
                new Location("D-2")
        ));

        ship.moveShip();
        List<Location> expected = List.of(
                new Location("C-2"),
                new Location("D-2"),
                new Location("E-2")
        );

        assertEquals(expected, ship.getLocation());
    }
    @Test
    void calulateNewHeadLocationDown() {
        Ship ship = new Ship("submarine", List.of(
                new Location("B-2"),
                new Location("C-2"),
                new Location("D-2")
        ));

        Location expected = new Location("E-2");

        assertEquals(expected, ship.calculateNewHeadLocation());
    }

    @Test
    void calculateNewHeadLocationLeft() {
        Ship ship = new Ship("submarine", List.of(
                new Location("A-5"),
                new Location("A-4"),
                new Location("A-3")
        ));

        Location expected = new Location("A-2");

        assertEquals(expected, ship.calculateNewHeadLocation());
    }

    @Test
    void calculateNewHeadLocationUp() {
        Ship ship = new Ship("submarine", List.of(
                new Location("D-2"),
                new Location("C-2"),
                new Location("B-2")
        ));

        Location expected = new Location("A-2");

        assertEquals(expected, ship.calculateNewHeadLocation());
    }

    @Test
    void shipPositionIsNotConsecutive(){
       Ship cruiserHorizontal = new Ship("cruiser", List.of(new Location("C-1"), new Location("C-2"), new Location("C-4")));
       Ship cruiserVertical = new Ship("cruiser", List.of(new Location("A-1"), new Location("B-1"), new Location("D-1")));

       assertFalse(cruiserHorizontal.isPositionShipConsecutive());
       assertFalse(cruiserVertical.isPositionShipConsecutive());
    }
    @Test
    void shipPositionIsConsecutive(){
        Ship cruiserHorizontal = new Ship("cruiser", List.of(new Location("C-1"), new Location("C-2"), new Location("C-3")));
        Ship cruiserVertical = new Ship("cruiser", List.of(new Location("A-1"), new Location("B-1"), new Location("C-1")));

        assertTrue(cruiserHorizontal.isPositionShipConsecutive());
        assertTrue(cruiserVertical.isPositionShipConsecutive());

    }

    @Test
    void isRightShipSize(){
        Ship cruiser= new Ship("cruiser", List.of(new Location("C-1"), new Location("C-2")));

        assertFalse(cruiser.isRightShipSize());
    }


}
