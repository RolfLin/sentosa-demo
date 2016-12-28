package edu.illinois.adsc.sentosa.rest;

import edu.illinois.adsc.sentosa.error.ErrorCode;
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
@Path("trend_analysis")
public class TrendAnalysis {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getTrend(
            @DefaultValue("0") @QueryParam("id") int id) {
        List<Integer> flows = NaiveQueryImpl.instance().predicateFlow(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("current", flows.get(0));

        JSONArray jsonArray = new JSONArray();
        for(int i = 1; i < flows.size(); i++) {
            jsonArray.put(flows.get(i));
        }
        jsonObject.put("trend", jsonArray);
        return jsonObject.toString();
    }
}
