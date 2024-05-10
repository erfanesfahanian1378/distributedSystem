package resource;

import entities.Player;
import entities.PlayerInfo;
import entities.Position;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/player")
public class PlayerResource {
    private static ConcurrentHashMap<String, Player> players = new ConcurrentHashMap<>();
    private static final List<Position> availablePositions = new ArrayList<>(); // Manage available positions just with an Integer


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlayer(Player player) {

        System.out.println("are we in add player ????");
        System.out.println(player);
        System.out.println("are we in add player ????");
        if (players.containsKey(player.getId())) {
            return Response.status(Response.Status.CONFLICT).entity("Player already exists.").build();
        }

        player.setPosition(assignRandomPosition());

        players.put(player.getId(), player);
        return Response.status(Response.Status.CREATED)
                .entity(new PlayerInfo(player.getId(), player.getPosition(), (ArrayList<PlayerInfo>) getPlayersInfo()))
                .build();
    }

    private synchronized Position assignRandomPosition() {
        int positionX = (int) (Math.random() * 100); // Assuming 100 * 100 positions
        int positionY = (int) (Math.random() * 100);
        Position position = new Position(positionX, positionY);
        while (availablePositions.contains(position)) {
             positionX = (int) (Math.random() * 100); // Assuming 100 * 100 positions
             positionY = (int) (Math.random() * 100);
             position = new Position(positionX, positionY);
        }
        availablePositions.add(new Position(positionX, positionY));
        return position;
    }

    private List<PlayerInfo> getPlayersInfo() {
        List<PlayerInfo> info = new ArrayList<>();
        players.values().forEach(player ->
                info.add(new PlayerInfo(player.getId(), player.getPosition(), null))
        );
        return info;
    }
}
