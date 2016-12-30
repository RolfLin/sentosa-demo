package edu.illinois.adsc.sentosa.rest;

import edu.illinois.adsc.sentosa.query.Interface.Attraction;
import edu.illinois.adsc.sentosa.query.Interface.Point;
import edu.illinois.adsc.sentosa.query.Interface.Route;
import edu.illinois.adsc.sentosa.query.naive.NaiveQueryImpl;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.List;

@Path("recommend_routes")
public class RecommendRoutes {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getShops(@DefaultValue("1.258609") @QueryParam("x") double x,
                           @DefaultValue("103.819424") @QueryParam("y") double y) {
        JSONArray jsonArray = new JSONArray();
        List<Route> routes = NaiveQueryImpl.instance().getRecommendRoutes(x, y);
        for (Route route: routes) {
            JSONObject jsonObjectForRoute = new JSONObject();
            jsonObjectForRoute.put("walking distance", route.walkingDistanceInMeters);
            jsonObjectForRoute.put("time", route.estimateTimeInMins);


            JSONArray jsonArrayForRoute = new JSONArray();
            for(Attraction attraction: route.attractions) {
                JSONObject jsonObjectForAttraction = new JSONObject();
                jsonObjectForAttraction.put("name", attraction.name);
                jsonObjectForAttraction.put("image", attraction.image);
                jsonObjectForAttraction.put("rating", attraction.rating);
                jsonObjectForAttraction.put("lng", attraction.x);
                jsonObjectForAttraction.put("lat", attraction.y);

                JSONArray jsonArrayForPoints = new JSONArray();
                Collection<Point> points = NaiveQueryImpl.instance().getPointInAnAttraction(attraction.id);
                for (Point point: points) {
                    JSONObject jsonObjectForPoint = new JSONObject();
                    jsonObjectForPoint.put("name", point.name);
                    jsonObjectForPoint.put("lng", point.x);
                    jsonObjectForPoint.put("lat", point.y);
                    jsonArrayForPoints.put(jsonObjectForPoint);
                }

                jsonObjectForAttraction.put("Places", jsonArrayForPoints);

                jsonArrayForRoute.put(jsonObjectForAttraction);
            }

            jsonObjectForRoute.put("route", jsonArrayForRoute);

            jsonArray.put(jsonObjectForRoute);
        }
        return jsonArray.toString();
    }
}
