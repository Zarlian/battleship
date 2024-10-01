package be.howest.ti.battleship.web.request;

import be.howest.ti.battleship.logic.fleet.Fleet;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.vertx.ext.web.RoutingContext;

import java.util.Map;

public class JoinGameRequest extends Request {

    public static class Body {
        @JsonProperty
        private String commander;
        @JsonProperty
        private String type;

        @JsonProperty
        private Fleet fleet;

        @JsonProperty
        private String prefix;
    }

    private Map<String, String> tokenResponse;

    public String getCommander() {
        return ctx.body().asPojo(Body.class).commander;
    }

    public String getType() {
        return ctx.body().asPojo(Body.class).type;
    }

    public Fleet getFleet() {
        return ctx.body().asJsonObject().getJsonObject("fleet").mapTo(Fleet.class);
    }

    public String getPrefix() {
        return ctx.body().asPojo(Body.class).prefix;
    }

    public JoinGameRequest(RoutingContext ctx) {
        super(ctx);
    }

    public void setResponse(String id, String token) {
        this.tokenResponse = Map.of("gameId", id,
                                    "playerToken", token);
    }

    // Response
    @Override
    public void sendResponse() {
        Response.sendJson(ctx, 200, tokenResponse);
    }


}
