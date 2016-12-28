package edu.illinois.adsc.sentosa.rest;

import edu.illinois.adsc.sentosa.query.Interface.Attraction;
import edu.illinois.adsc.sentosa.query.naive.NaiveQueryImpl;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by robert on 28/12/16.
 */
@Path("flow")
public class Flow {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getFlow(
            @DefaultValue("0") @QueryParam("id") int id) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("current", NaiveQueryImpl.instance().predicateFlow(id).get(0));
        jsonObject.put("Maximum", 2000);
        jsonObject.put("queuing time", NaiveQueryImpl.instance().predicateQueuingTime(id).get(0));

        return jsonObject.toString();
    }
}
