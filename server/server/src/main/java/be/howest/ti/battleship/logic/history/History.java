package be.howest.ti.battleship.logic.history;

import be.howest.ti.battleship.logic.Commander;
import be.howest.ti.battleship.logic.fleet.Ship;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class History {

    private final List<Turn> turns = new ArrayList<>();

    public History(){
        //the game is created with an empty history list
    }

    public List<Turn> getHistoryList() {
        return turns;
    }

    public void addTurn(Commander commander,  Map<String, String> hitResult) {
        turns.add(new SalvoTurn(commander, hitResult));
    }

    public void addTurn(Commander commander, Ship moveResult) {
        turns.add((new MoveTurn(commander, moveResult)));
    }


}
