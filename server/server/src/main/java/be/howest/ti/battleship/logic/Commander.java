package be.howest.ti.battleship.logic;

import be.howest.ti.battleship.logic.fleet.Fleet;

import java.util.*;

public class Commander {
    private final String name;

    private final Fleet fleet;

    private int salvoSize;

    public Commander(String name, Fleet fleet) {
        this.name = name;
        this.fleet = fleet;
    }


    public String getName() {
        return name;
    }

    public Fleet getFleet() {
        return fleet;
    }


    public Map<String,String> shoot(List<String>hitList) {
        return fleet.salvoCheck(hitList);
    }



    public Set<Map<String, Object>> getSunkShips() {
        return fleet.getSunkShips();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Commander commander = (Commander) o;

        if (!Objects.equals(name, commander.name)) return false;
        return Objects.equals(fleet, commander.fleet);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (fleet != null ? fleet.hashCode() : 0);
        return result;
    }

    public int getSalvoSize() {
        return salvoSize;
    }

    public void setSalvoSize(int salvoSize){
        this.salvoSize = salvoSize;
    }


}
