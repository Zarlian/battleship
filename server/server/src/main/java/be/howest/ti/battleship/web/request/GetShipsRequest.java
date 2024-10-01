package be.howest.ti.battleship.web.request;

import be.howest.ti.battleship.logic.fleet.ShipType;
import io.vertx.ext.web.RoutingContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetShipsRequest extends Request {

    private final List<Map<String, Object>> shipsTypes = new ArrayList<>();
    public GetShipsRequest(RoutingContext ctx) {
        super(ctx);
    }


    // Response
    @Override
    public void sendResponse() {
        Response.sendJson(ctx, 200, Map.of("ships", shipsTypes));
    }

    public void setResponse() {
        ShipType[] shipTypes = ShipType.values();
        for (ShipType type : shipTypes) {
            this.shipsTypes.add(type.getType());
        }
    }
}
