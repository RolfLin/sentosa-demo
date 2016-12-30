package edu.illinois.adsc.sentosa.rest;
import edu.illinois.adsc.sentosa.query.Interface.Attraction;
import edu.illinois.adsc.sentosa.query.naive.NaiveQueryImpl;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.List;

/**
 * Created by robert on 28/12/16.
 */
@Path("attractions")
public class Attractions {

    @Path("admin")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllAttractions() {

        Collection<Attraction> attractions = NaiveQueryImpl.instance().queryAllAttractions();

        JSONArray JSONArray = new JSONArray();
        for(Attraction attraction: attractions) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", attraction.id);
            jsonObject.put("name", attraction.name);
            jsonObject.put("rating", attraction.rating);
            jsonObject.put("Opentime", String.format("%02d:%02d-%02d:%02d", attraction.startTimeHour, attraction.startTimeMin,
                    attraction.endTimeHour, attraction.endTimeMin));
            JSONArray.put(jsonObject);
        }
        return JSONArray.toString();
    }

    @Path("tourist")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String smooth(
            @DefaultValue("200") @QueryParam("x") double x,
            @DefaultValue("300") @QueryParam("y") double y) {
        Collection<Attraction> attractions = NaiveQueryImpl.instance().queryNearByAttractions(x, y, 100);

        JSONArray JSONArray = new JSONArray();
        for(Attraction attraction: attractions) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", attraction.id);
            jsonObject.put("name", attraction.name);
            jsonObject.put("rating", attraction.rating);
            jsonObject.put("Opentime", String.format("%02d:%02d-%02d:%02d", attraction.startTimeHour, attraction.startTimeMin,
                    attraction.endTimeHour, attraction.endTimeMin));
            JSONArray.put(jsonObject);
        }
        return JSONArray.toString();
    }



}
