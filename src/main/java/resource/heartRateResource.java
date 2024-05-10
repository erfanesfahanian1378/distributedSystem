package resource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/heart-rate")
public class heartRateResource {
    private static ConcurrentHashMap<String, List<Integer>> heartRates = new ConcurrentHashMap<>();

    @POST
    @Path("/{playerId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response receiveHeartRate(@PathParam("playerId") String playerId, List<Integer> rates) {
        heartRates.putIfAbsent(playerId, new ArrayList<>());
        heartRates.get(playerId).addAll(rates);
        return Response.ok().build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response calculateStatistics() {
        // This map will hold the player IDs along with their heart rates and average
        ConcurrentHashMap<String, HeartRateStatistics> statistics = new ConcurrentHashMap<>();

        heartRates.forEach((playerId, rates) -> {
            double average = rates.stream().mapToInt(Integer::intValue).average().orElse(0.0);
            statistics.put(playerId, new HeartRateStatistics(rates, average));
        });

        return Response.status(Response.Status.OK).entity(statistics).build();
    }




    static class HeartRateStatistics {
        private List<Integer> heartRates;
        private double average;

        public HeartRateStatistics(List<Integer> heartRates, double average) {
            this.heartRates = heartRates;
            this.average = average;
        }

        public List<Integer> getHeartRates() {
            return heartRates;
        }

        public void setHeartRates(List<Integer> heartRates) {
            this.heartRates = heartRates;
        }

        public double getAverage() {
            return average;
        }

        public void setAverage(double average) {
            this.average = average;
        }
    }

}
