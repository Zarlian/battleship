package be.howest.ti.battleship.logic.history;


import be.howest.ti.battleship.logic.Commander;
import be.howest.ti.battleship.web.request.response.history.converter.TurnResponse;

public abstract class  Turn {

    private final Commander commander;

    public Turn(Commander commander) {
        this.commander = commander;
    }

    public String getCommander() {
        return commander.getName();
    }

    public abstract TurnResponse convert(String requestingCommander);
}
