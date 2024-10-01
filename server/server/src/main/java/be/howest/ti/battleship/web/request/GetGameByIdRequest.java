package be.howest.ti.battleship.web.request;

import be.howest.ti.battleship.logic.Game;
import be.howest.ti.battleship.web.request.response.GameResponseBody;
import be.howest.ti.battleship.web.request.response.GetGameByIdResponseBody;
import be.howest.ti.battleship.web.request.response.WaitingGameResponseBody;
import io.vertx.ext.web.RoutingContext;

public class GetGameByIdRequest extends Request {

    private GameResponseBody response;

    public GetGameByIdRequest(RoutingContext ctx) {
        super(ctx);
    }

    // Response
    @Override
    public void sendResponse() {
        Response.sendJson(ctx, 200, response);
    }

    public void setResponse(Game gameById, String requestingCommander) {
        if (gameById.isStarted()) {
            response = new GetGameByIdResponseBody(gameById, requestingCommander);
        } else {
            response = new WaitingGameResponseBody(gameById);
        }
    }
}
