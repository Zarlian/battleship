package be.howest.ti.battleship.logic;

public enum GameType {
    SIMPLE("simple", 1),
    SALVO("salvo", 5),
    MOVE("move", 1),
    MOVESALVO("move+salvo", 5);

    private final String type;
    private final int shots;

    GameType(String type, int shots){
        this.type = type;
        this.shots = shots;
    }

    public String getName() {
        return type;
    }

    public static GameType getType(String type){
        for(GameType gameType : GameType.values()){
            if(gameType.getName().equals(type)){
                return gameType;
            }
        }
        throw new IllegalArgumentException("this type doesn't exist");
    }

    public int getShots(){
        return shots;
    }
}
