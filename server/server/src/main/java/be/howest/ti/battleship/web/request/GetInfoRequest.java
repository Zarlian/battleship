package be.howest.ti.battleship.web.request;
import io.vertx.ext.web.RoutingContext;

import java.util.HashMap;
import java.util.Map;

public class GetInfoRequest extends Request {

    private Map<String, String> response = new HashMap<>();

    private String version;
    private static final String GROUP = "05";

    private static final String CREATORS = "Kevin Demeulenaere ,Kevin Henri ,Remco De Wolf ,Clint Kindermans";

    private static final String COACHES = "With help of the fantastic teaching team of TI.";

    public GetInfoRequest(RoutingContext ctx) {
        super(ctx);
    }

    // Response
    @Override
    public void sendResponse() {
        Response.sendJson(ctx, 200, response);
    }

    public void setResponse(String version) {
        this.version = version;
        buildResponse();
    }

    private void buildResponse() {
        response.put("Version : ", version);
        response.put("Group number : ", GROUP);
        response.put("Created by : ", CREATORS);
        response.put("Howest : ", COACHES);
    }
}
