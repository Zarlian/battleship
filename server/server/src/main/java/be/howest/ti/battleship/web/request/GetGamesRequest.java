package be.howest.ti.battleship.web.request;

import be.howest.ti.battleship.logic.Game;
import be.howest.ti.battleship.web.request.response.GameResponseBody;
import be.howest.ti.battleship.web.request.response.StartedGameResponseBody;
import be.howest.ti.battleship.web.request.response.WaitingGameResponseBody;
import io.vertx.ext.web.RoutingContext;

import java.util.*;

public class GetGamesRequest extends Request {

    private final List<GameResponseBody> games = new ArrayList<>();

    private GameResponseBody buildResponse(Game game) {
        if (game.isStarted()) {
            return buildStartedGameResponse(game);
        } else {
            return buildWaitingGameResponse(game);
        }
    }

    private StartedGameResponseBody buildStartedGameResponse(Game game){
        return new StartedGameResponseBody(game);
    }

    private GameResponseBody buildWaitingGameResponse(Game game){
        return new WaitingGameResponseBody(game);
    }

    public void setGames(Set<Game> games) {
        for (Game game : games) {
            this.games.add(buildResponse(game));
        }
    }

    public GetGamesRequest(RoutingContext ctx) {
        super(ctx);
    }

    // Response
    @Override
    public void sendResponse() {
        Map<String, List<GameResponseBody>> response = new HashMap<>();
        response.put("games", games);
        Response.sendJson(ctx, 200, response);
    }

    public String getStatus() {
        return String.valueOf(params.queryParameter("status"));
    }

    public String getType() {
        return String.valueOf(params.queryParameter("type"));
    }

    public String getPrefix() {
        return String.valueOf(params.queryParameter("prefix"));
    }

    public int getRows() {
        if(params.queryParameter("rows") == null){
            return -1;
        }else {
            return params.queryParameter("rows").getInteger();
        }
    }

    public int getCols() {
        if(params.queryParameter("cols") == null){
            return -1;
        }else {
            return params.queryParameter("cols").getInteger();
        }
    }
}
