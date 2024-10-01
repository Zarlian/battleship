package be.howest.ti.battleship.web.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.vertx.ext.web.RoutingContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FireSalvoRequest extends Request {


    public static class Body{
        @JsonProperty
        public List<String> salvo;
    }

    private Map<String,String> hitResponse = new HashMap<>();

    public FireSalvoRequest(RoutingContext ctx) {
        super(ctx);
    }

    public List<String> getHitList(){
        return ctx.body().asPojo(Body.class).salvo;
    }

    public void setResponse(Map<String,String> hitDetails) {
        hitResponse = hitDetails;
    }

    // Response
    @Override
    public void sendResponse() {
        Response.sendJson(ctx, 200, hitResponse);
    }
}
