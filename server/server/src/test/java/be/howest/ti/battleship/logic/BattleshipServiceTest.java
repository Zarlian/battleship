package be.howest.ti.battleship.logic;

import be.howest.ti.battleship.logic.fleet.Fleet;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BattleshipServiceTest {

    @Test
    void getVersion() {
        BattleshipService service = new BattleshipServiceImpl();
        assertEquals("0.0.1", service.getVersion());
    }

    @Test
    void computeNewGameNoPrefix() {
        BattleshipService service = new BattleshipServiceImpl();
        DemoData demoData = new DemoData();
        Fleet fleet = demoData.getDemoFleet();

        assertEquals("1", service.computeGame("sammy", "simple", fleet, null));
    }

    @Test
    void computeNewGameWithPrefix() {
        BattleshipService service = new BattleshipServiceImpl();
        DemoData demoData = new DemoData();
        Fleet fleet = demoData.getDemoFleet();

        assertEquals("test_1", service.computeGame("sammy", "simple", fleet, "test"));
    }

    @Test
    void getGameList() {
        BattleshipService service = new BattleshipServiceImpl();
        DemoData demoData = new DemoData();
        Fleet fleet = demoData.getDemoFleet();
        service.computeGame("sammy", "simple", fleet, null);

        assertEquals(1, service.getGames("null", "null", "null", -1, -1).size());

    }

    @Test
    void getFleetDetails() {
        BattleshipService service = new BattleshipServiceImpl();
        DemoData demoData = new DemoData();
        Fleet fleet = demoData.getDemoFleet();
        service.computeGame("sammy", "simple", fleet, null);

        Fleet sammysFleet = service.getFleetDetails("1", "sammy");
        assertEquals(fleet, sammysFleet);
    }

    @Test
    void getHitResponse() {
        GameManager gameManager = new GameManager();
        BattleshipService service = new BattleshipServiceImpl();
        DemoData demoData = new DemoData();
        service.computeGame("sammy", "simple", demoData.getDemoFleet(), null);
        service.computeGame("jimmy", "simple", demoData.getDemoFleet(), null);
        List<String> shotList = new ArrayList<>();
        shotList.add("A-5");
        Map<String, String> expected = new HashMap<>();
        expected.put("A-5", "CARRIER");
        assertEquals(expected, service.getHitResponse("1", "jimmy", shotList));
    }

    @Test
    void DeleteGames() {
        GameManager gameManager = new GameManager();
        BattleshipService service = new BattleshipServiceImpl();
        DemoData demoData = new DemoData();
        service.computeGame("sammy", "simple", demoData.getDemoFleet(), null);
        service.computeGame("jimmy", "simple", demoData.getDemoFleet(), null);

        service.deleteGames();
        assertEquals(0, service.getGames("null", "null", "null", -1, -1).size());

    }

}
