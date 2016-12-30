package edu.illinois.adsc.sentosa.rest;

import edu.illinois.adsc.sentosa.query.Interface.IQuery;
import edu.illinois.adsc.sentosa.query.naive.NaiveQueryImpl;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by robert on 28/12/16.
 */
@Path("hot_attractions_ranking")
public class HotAttractionsRanking {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getRanking() {
        List<IQuery.AttractionAndScore> attractionAndFlows = NaiveQueryImpl.instance().getHotAttractionsRanking();
        JSONArray jsonArray = new JSONArray();
        for (IQuery.AttractionAndScore attractionAndFlow: attractionAndFlows) {
            JSONObject object = new JSONObject();
            object.put("name", attractionAndFlow.attraction.name);
            object.put("score", attractionAndFlow.score);
            jsonArray.put(object);
        }
        return jsonArray.toString();
    }
}
