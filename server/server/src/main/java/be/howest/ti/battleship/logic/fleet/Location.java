package be.howest.ti.battleship.logic.fleet;

public class Location {
    private static final int ALPHABET_OFFSET = 26;
    private final int row;
    private final int column;

    public Location(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public Location(String location) {
        String[] splitLocation = location.split("-");
        this.row = convertAlphabeticToNumber(splitLocation[0]);
        this.column = Integer.parseInt(splitLocation[1]) -1;
    }

    private int convertAlphabeticToNumber(String location) {
        char firstLetter = location.charAt(0);
        int charToNum = firstLetter - 'A';

        if(location.length() == 1){
            return charToNum;
        }else {
            String remainderRow = location.substring(1);
            int charToNumValue = (charToNum + 1) * ALPHABET_OFFSET;
            return convertAlphabeticToNumber(remainderRow) + charToNumValue;
        }
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public String getLocation(){
        return toString();
    }
    public String toString() {
        return convertIntegerToAlphabetic(row) + "-" + (column +1 );
    }

    public String convertIntegerToAlphabetic(int num) {

        int quotient = num / ALPHABET_OFFSET;
        int remainder = num % ALPHABET_OFFSET;
        char letter = (char) ((int) 'A' + remainder);
        if (quotient == 0) {
            return "" + letter;
        } else {
            return convertIntegerToAlphabetic(quotient - 1) + letter;
        }
    }


    public Location move (Direction direction){
        return new Location(this.row + direction.getRow(), this.column + direction.getColumn());
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        Location location = (Location) other;

        if (row != location.row) return false;
        return column == location.column;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + column;
        return result;
    }
}
