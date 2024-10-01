package be.howest.ti.battleship.web.request;

import be.howest.ti.battleship.logic.fleet.Ship;
import be.howest.ti.battleship.web.request.response.MoveResponseBody;
import io.vertx.ext.web.RoutingContext;

public class MoveShipRequest extends Request {
    private MoveResponseBody ship;
    public MoveShipRequest(RoutingContext ctx) {
        super(ctx);
    }

    // Response
    @Override
    public void sendResponse() {
        Response.sendJson(ctx, 200, ship);
    }

    public String getShip() {
        return ctx.pathParam("ship");
    }

    public void setResponse(Ship ship) {
        this.ship = new MoveResponseBody(ship);
    }
}
