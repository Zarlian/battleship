package be.howest.ti.battleship.logic.fleet;

public enum Direction {
    UP (-1, 0),
    DOWN (1, 0),
    LEFT (0, -1),
    RIGHT(0, 1);

    public final int row;
    public final int column;
    Direction(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
