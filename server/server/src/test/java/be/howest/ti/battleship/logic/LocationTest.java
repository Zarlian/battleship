package be.howest.ti.battleship.logic;

import be.howest.ti.battleship.logic.fleet.Direction;
import be.howest.ti.battleship.logic.fleet.Location;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {

    @Test
    void makeLocation(){
        Location l1 = new Location(10,9);

        assertEquals(10, l1.getRow());
        assertEquals(9, l1.getColumn());
    }

    @Test
    void makeLocationWithString(){
        Location l1 = new Location("AA-20");

        assertEquals(26, l1.getRow());
        assertEquals(19, l1.getColumn());
    }

    @Test
    void locationToString() {
        Location l1 = new Location(0, 0);
        Location l2 = new Location(26, 1);

        assertEquals("A-1", l1.toString());
        assertEquals("AA-2", l2.toString());
    }

    @Test
    void locationIsTheSame(){
        Location l1 = new Location(10, 9);
        Location l2 = new Location(10, 9);

        assertEquals(l1,l2);
    }

    @Test
    void locationIsNotTheSame(){
        Location l1 = new Location(10, 9);
        Location l2 = new Location(10, 10);

        assertNotEquals(l1,l2);
    }


    @Test
    void enterAStringOutputAString() {
        Location l1 = new Location("A-1");

        assertEquals("A-1", l1.getLocation());
    }

    @Test
    void moveUp(){
        Location l1 = new Location(10,10);
        Location l2 = new Location(9,10);

        assertEquals(l2, l1.move(Direction.UP));
    }

    @Test
    void moveDown(){
        Location l1 = new Location(10,10);
        Location l2 = new Location(11,10);

        assertEquals(l2, l1.move(Direction.DOWN));
    }

    @Test
    void moveLeft(){
        Location l1 = new Location(10,10);
        Location l2 = new Location(10,9);

        assertEquals(l2, l1.move(Direction.LEFT));
    }

    @Test
    void moveRight(){
        Location l1 = new Location(10,10);
        Location l2 = new Location(10,11);

        assertEquals(l2, l1.move(Direction.RIGHT));
    }

    @Test
    void moveUpString(){
        Location l1 = new Location("B-1");
        Location l2 = new Location("A-1");

        assertEquals(l2, l1.move(Direction.UP));
    }

    @Test
    void moveDownString(){
        Location l1 = new Location("A-1");
        Location l2 = new Location("B-1");

        assertEquals(l2, l1.move(Direction.DOWN));
    }
    @Test
    void moveLeftString(){
        Location l1 = new Location("A-2");
        Location l2 = new Location("A-1");

        assertEquals(l2, l1.move(Direction.LEFT));
    }
    @Test
    void moveRightString(){
        Location l1 = new Location("A-1");
        Location l2 = new Location("A-2");

        assertEquals(l2, l1.move(Direction.RIGHT));
    }

}