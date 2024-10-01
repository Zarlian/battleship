package be.howest.ti.battleship.web.request;

import be.howest.ti.battleship.logic.fleet.Fleet;
import be.howest.ti.battleship.web.request.response.FleetOpponentResponseBody;
import be.howest.ti.battleship.web.request.response.FleetResponseBody;
import io.vertx.ext.web.RoutingContext;

public class GetFleetDetailsRequest extends Request {

    private FleetResponseBody fleetDetails;

    private FleetOpponentResponseBody opponentDetails;

    public GetFleetDetailsRequest(RoutingContext ctx) {
        super(ctx);
    }

    public void setResponse(Fleet fleet){
        fleetDetails = new FleetResponseBody(fleet);
    }

    public void setOpponentResponse(Fleet fleet){
        opponentDetails = new FleetOpponentResponseBody(fleet);
    }

    // Response
    @Override
    public void sendResponse() {
        Response.sendJson(ctx, 200, fleetDetails);
    }

    public void sendOpponentResponse(){Response.sendJson(ctx, 200, opponentDetails);}

}
