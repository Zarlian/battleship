package be.howest.ti.battleship.logic;

import be.howest.ti.battleship.logic.fleet.ShipType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShipTypeTest {

    @Test
    void getSizeShip(){
        ShipType shipType1 = ShipType.CARRIER;
        ShipType shipType2 = ShipType.DESTROYER;
        ShipType shipType3 = ShipType.CRUISER;

        assertEquals(5, shipType1.getSize());
        assertEquals(2, shipType2.getSize());
        assertEquals(3, shipType3.getSize());
    }

    @Test
    void getShipAccordingToSize(){
        int size = 3;
        List<ShipType> shipTypes = new ArrayList<>();
        ArrayList<ShipType> expectedShipTypes = new ArrayList<>();
        expectedShipTypes.add(ShipType.CRUISER);
        expectedShipTypes.add(ShipType.SUBMARINE);
        for (ShipType shipType : ShipType.values()) {
            if(shipType.getSize() == 3){
                shipTypes.add(shipType);
            }
        }
        assertEquals(expectedShipTypes, shipTypes);
    }
@Test
    void getCorrectShipName(){
    ShipType carrier = ShipType.CARRIER;
    assertEquals("carrier", carrier.getName());
}
}