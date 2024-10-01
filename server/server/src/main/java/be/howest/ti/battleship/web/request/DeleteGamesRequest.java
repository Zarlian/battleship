package be.howest.ti.battleship.web.request;
import be.howest.ti.battleship.web.BattleshipUser;
import io.vertx.ext.web.RoutingContext;

public class DeleteGamesRequest extends Request {
    private final BattleshipUser admin = new BattleshipUser("8008", "with great power comes great responsibility");
    public DeleteGamesRequest(RoutingContext ctx) {
        super(ctx);
    }

    public BattleshipUser getAdmin() {
        return admin;
    }

    // Response
    @Override
    public void sendResponse() {
        Response.sendJson(ctx, 200, "Welcome masters");
    }
}
