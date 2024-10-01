package be.howest.ti.battleship.logic;

import be.howest.ti.battleship.logic.fleet.Fleet;
import be.howest.ti.battleship.web.ForbiddenAccessException;

import java.util.*;
import java.util.stream.Collectors;

public class GameManager {

    private int gameId = 1;
    private final List<Game> games = new LinkedList<>();

    public GameManager() {
        //This remains empty because it just needs to be started up when server goes live. No games are in yet
    }

    public Set<Game> getGameList(String status, String type, String prefix, int rows, int cols) {
        Set<Game> filteredGames = new HashSet<>(games);
        if(status != "null"){
            filteredGames = filterByStatus(status, filteredGames);
        }
        if(type != "null"){
            filteredGames = filterByType(type, filteredGames);
        }
        if(prefix != "null"){
            filteredGames = filterByPrefix(type, filteredGames);
        }
        if(rows != -1){
            filteredGames = filterByRows(rows, filteredGames);
        }
        if(cols != -1){
            filteredGames = filterByColumns(cols, filteredGames);
        }
        return filteredGames;
    }

    public void incrementId(){
        gameId++;
    }

    public void addGame(Game game){
        games.add(game);
        game.setId(String.valueOf(gameId));
        incrementId();
    }

    public Set<Game> filterByStatus(String status, Set<Game> filteredGames){
        boolean temp = status.equals("started");
        return filteredGames.stream()
                .filter(game -> game.isStarted() == temp)
                .collect(Collectors.toSet());
    }

    public Set<Game> filterByType(String type,Set<Game> filteredGames){
        return filteredGames.stream()
                .filter(game -> game.getType().equals(type))
                .collect(Collectors.toSet());
    }

    public Set<Game> filterByPrefix(String prefix, Set<Game> filteredGames){
        return  filteredGames.stream()
                .filter(game -> game.getPrefix().equals(prefix) && !game.isStarted())
                .collect(Collectors.toSet());
    }

    public Set<Game> filterByRows(int rows, Set<Game> filteredGames){
        return filteredGames.stream()
                .filter(game -> game.getAttackingCommander().getFleet().getRows() == rows)
                .collect(Collectors.toSet());
    }

    public Set<Game> filterByColumns(int columns, Set<Game> filteredGames){
        return filteredGames.stream()
                .filter(game -> game.getAttackingCommander().getFleet().getCols() == columns)
                .collect(Collectors.toSet());
    }

    public Game getGameById(String gameId){
        return games.stream()
                    .filter(game -> game.getId().equals(gameId))
                    .findFirst()
                    .orElse(null);
    }

    public Game getGameById(String id, String requestingCommander) {
        Game game = getGameById(id);
        if(game == null){
            throw new ForbiddenAccessException("You are not allowed to view this game.");
        }
        if(game.getFleetByCommanderName(requestingCommander) == null){
            throw new BattleshipResourceNotFoundException(id);
        }
        return game;
    }

    public Game findMatchingGameWithPrefix(Commander commander, String type, String prefix) {
        Set<Game> filteredGames = new HashSet<>(filterGames(commander, type));

        return filterByPrefix(prefix, filteredGames).stream()
                                                       .findFirst()
                                                       .orElse(null);
    }

    public Game findMatchingGame(Commander commander, String type) {
        return filterGames(commander, type).stream()
                                        .filter(game -> game.getPrefix() == null)
                                        .findFirst()
                                        .orElse(null);
    }

    public List<Game> filterGames(Commander commander, String type) {
        List<Game> filtered = new ArrayList<>();

        for( Game game : games){
            if(isSameCommander(game.getAttackingCommander(), commander) && isSameType(game.getType(), type) && !game.isStarted()){
                throw new BattleshipException("This commanders name is already taken");
            }
            if(!game.isStarted() && isSameType(game.getType(), type) && isSameFleet(game.getAttackingCommander().getFleet(), commander.getFleet())){
                filtered.add(game);
            }
        }
        return filtered;
    }

    public String createOrJoinGame(Commander commander, String type, String prefix, Game filteredGame) {
        if(filteredGame == null){
            return createNewGame(commander, type, prefix);
        }else{
            return joinExistingGame(commander, filteredGame);
        }
    }

    public String joinExistingGame(Commander commander, Game filteredGame) {
        filteredGame.setStarted(true);
        filteredGame.addCommander(commander);
        filteredGame.setCommanderSalvoSize();
        return filteredGame.getId();
    }

    private String createNewGame(Commander commander, String type, String prefix) {
        Game game = new Game(commander, type, prefix);
        addGame(game);
        return game.getId();
    }

    private boolean isSameCommander(Commander waitingCommander, Commander commander) {
        return Objects.equals(waitingCommander, commander);
    }

    private boolean isSameType(String waitingGameType, String type) {
        return waitingGameType.equals(type);
    }

    private boolean isSameFleet(Fleet waitingCommanderfleet, Fleet fleet) {
        return waitingCommanderfleet.equals(fleet);
    }

    public void deleteGames() {
        games.clear();
    }

    public String connectGame(String commander, String type, Fleet fleet, String prefix) {
        Commander com = new Commander(commander, fleet);
        if(prefix != null){
            Game gameWithPrefix = findMatchingGameWithPrefix(com, type, prefix);
            return createOrJoinGame(com, type, prefix, gameWithPrefix);
        }

        Game matchingGame = findMatchingGame(com, type);
        return createOrJoinGame(com, type,null, matchingGame);
    }


}
