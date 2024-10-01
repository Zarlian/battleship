package be.howest.ti.battleship.logic;

import be.howest.ti.battleship.logic.fleet.Fleet;
import be.howest.ti.battleship.logic.fleet.Ship;
import be.howest.ti.battleship.logic.fleet.ShipType;
import be.howest.ti.battleship.logic.history.History;

import java.util.*;

public class Game {

    private String id;
    private String prefix;
    private final GameType type;
    private String winner = null;
    private static final int ATTACKING_COMMANDER = 0;
    private static final int DEFENDING_COMMANDER = 1;
    private List<Commander> commanders = new ArrayList<>();
    private boolean started = false;
    private final Map<String, Integer> size;
    private final History history;


    public Game(Commander commander, String type, String prefix) {
        this.type = GameType.getType(type);
        commanders.add(commander);
        size = new HashMap<>();
        size.put("rows", commander.getFleet().getRows());
        size.put("cols", commander.getFleet().getCols());
        this.prefix = prefix;

        history = new History();
    }

    public void setStarted(boolean started) {

        this.started = started;
    }

    public String getPrefix() {

        return prefix;
    }

    public String getId() {
        if (prefix != null) {
            return prefix + "_" + id;
        }
        return id;
    }

    public boolean isStarted() {
        return started;
    }

    public Commander getAttackingCommander() {
        return getCommanders().get(ATTACKING_COMMANDER);
    }

    public Commander getDefendingCommander(){
        return getCommanders().get(DEFENDING_COMMANDER);
    }

    public Commander getCommander(String commander) {
        for (Commander com : commanders) {
            if (com.getName().equals(commander)) {
                return com;
            }
        }
        return null;
    }

    public List<Commander> getCommanders() {
        return commanders;
    }

    public String getType() {
        return type.getName();
    }

    public Map<String, Integer> getSize() {
        return size;
    }

    public void addCommander(Commander commander) {
        commanders.add(commander);
    }

    public void setId(String gameId) {
        id = gameId;
    }

    public String getWinner() {
        return winner;
    }



    public Fleet getFleetByCommanderName(String commanderName) {
        for (Commander commander : commanders) {
            if (commander.getName().equals(commanderName)) {
                return commander.getFleet();
            }
        }
        return null;
    }

    public int getAttackingCommanderSalvoSize() {
        return getAttackingCommander().getSalvoSize();
    }


    public void setCommanderSalvoSize() {
        for (Commander commander : commanders) {
            if (type.equals(GameType.MOVE) || type.equals(GameType.SIMPLE)) {
                commander.setSalvoSize(type.getShots());
            } else {
                int shotSize = type.getShots() - commander.getSunkShips().size();
                commander.setSalvoSize(shotSize);
            }
        }
    }


    public void swapCommanders() {
        List<Commander> swapped = new ArrayList<>();
        swapped.add(getDefendingCommander());
        swapped.add(getAttackingCommander());
        commanders = swapped;
    }

    public void processTurn() {
        checkForWinner();
        setCommanderSalvoSize();
        swapCommanders();
    }

    private void checkForWinner() {
        if (getDefendingCommander().getSunkShips().size() == ShipType.values().length) {
            setWinner(getAttackingCommander().getName());
        }
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public History getHistory() {
        return history;
    }

    public Map<String, String> processShot(String commander, List<String> hitList) {
        Commander defendingCommander = getCommander(commander);
        int salvoSize = getAttackingCommanderSalvoSize();
        validateSalvoRequest(defendingCommander, salvoSize, hitList.size());
        Map<String, String> hitResult = defendingCommander.shoot(hitList);
        history.addTurn(getAttackingCommander(), hitResult);
        processTurn();
        return hitResult;
    }

    public void validateSalvoRequest(Commander com, int salvoSize, int hitList) {
        if (com.getName().equals(getAttackingCommander().getName())) {
            throw new BattleshipException("Only the defending commander can fire a salvo");
        }
        if (salvoSize != hitList) {
            throw new BattleshipException("You have to fire exactly one shot per surviving ship! (expected " + salvoSize + ", found " + hitList);
        }
    }

    public Ship processMove(String movingCommander, String ship) {
        Commander attackingCommander = getAttackingCommander();
        if (!movingCommander.equals(attackingCommander.getName())) {
            throw new BattleshipException("Only the attacking commander can move a ship");
        }
        if (!getType().contains("move")) {
            throw new BattleshipException("This game does not support move");
        }

        history.addTurn(attackingCommander, attackingCommander.getFleet().findShip(ship));
        Ship movingShip = attackingCommander.getFleet().moveShip(ship);
        processTurn();
        return movingShip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        if (started != game.started) return false;
        if (!Objects.equals(id, game.id)) return false;
        if (!Objects.equals(prefix, game.prefix)) return false;
        if (type != game.type) return false;
        if (!Objects.equals(winner, game.winner)) return false;
        if (!Objects.equals(commanders, game.commanders)) return false;
        if (size != null ? !size.equals(game.size) : game.size != null) return false;
        return history != null ? history.equals(game.history) : game.history == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (prefix != null ? prefix.hashCode() : 0);
        return result;
    }
}
