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
        JSONObject jsonObject = new JSONObject();

        JSONObject jsonObjectForHistory = new JSONObject();
        jsonObjectForHistory.put("flow", NaiveQueryImpl.instance().retrieveFlowHistory(id, 0));

        jsonObjectForHistory.put("queuing_time", NaiveQueryImpl.instance().retrieveQueuingTimeHistory(id, 0));

        jsonObject.put("history", jsonObjectForHistory);


        JSONObject jsonObjectForPredication = new JSONObject();
        jsonObjectForPredication.put("flow", NaiveQueryImpl.instance().predicateFlow(id));

        jsonObjectForPredication.put("queuing_time", NaiveQueryImpl.instance().predicateQueuingTime(id));

        jsonObject.put("Predication", jsonObjectForPredication);

        return jsonObject.toString();
    }
}
