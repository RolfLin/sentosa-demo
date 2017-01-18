package edu.illinois.adsc.sentosa.rest;

import edu.illinois.adsc.sentosa.query.Interface.Attraction;
import edu.illinois.adsc.sentosa.query.Interface.Point;
import edu.illinois.adsc.sentosa.query.naive.NaiveQueryImpl;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.print.attribute.standard.MediaSize;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by robert on 28/12/16.
 */
@Path("global_preview")
public class GlobalPreview {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getPreview(
            @DefaultValue("-1") @QueryParam("id") int id) {
        if (id != -1)
            return getOneAttraction(id);
        else
            return getAllAttractions();
    }

    private String getOneAttraction(int id) {
        JSONObject jsonObject = new JSONObject();
        List<Point> points = NaiveQueryImpl.instance().getPointInAnAttraction(id);
        List<Integer> pointCounts = NaiveQueryImpl.instance().predicatePointCount(id);

        JSONArray jsonArrayForPoints = new JSONArray();
        for (int i = 0; i < points.size(); i++) {
            JSONObject jsonObjectForPoint = new JSONObject();
            jsonObjectForPoint.put("lat", points.get(i).x);
            jsonObjectForPoint.put("lng", points.get(i).y);
            jsonObjectForPoint.put("count", pointCounts.get(i));
            jsonArrayForPoints.put(jsonObjectForPoint);
        }
        jsonObject.put("Points", jsonArrayForPoints);

        jsonObject.put("in_count", NaiveQueryImpl.instance().getAttractionInCount(id));
        jsonObject.put("out_count", NaiveQueryImpl.instance().getAttractionOutCount(id));
        jsonObject.put("enter_count", NaiveQueryImpl.instance().getAttractionEnterCount(id));
        jsonObject.put("enter_rate", NaiveQueryImpl.instance().getAttractionEnterRate(id));

        return jsonObject.toString();
    }

    private String getAllAttractions() {
        List<Point> allPoints = new ArrayList<>();
        List<Integer> pointCounts = new ArrayList<>();
        Collection<Attraction> allAttractions = NaiveQueryImpl.instance().queryAllAttractions();

        int inCount = 0;
        int outCount = 0;
        int enterCount = 0;
        int enterRate = 0;

        for(Attraction attraction: allAttractions) {
            allPoints.addAll(NaiveQueryImpl.instance().getPointInAnAttraction(attraction.id));
            pointCounts.addAll(NaiveQueryImpl.instance().predicatePointCount(attraction.id));

            inCount += NaiveQueryImpl.instance().getAttractionInCount(attraction.id);
            outCount += NaiveQueryImpl.instance().getAttractionOutCount(attraction.id);
            enterCount += NaiveQueryImpl.instance().getAttractionEnterCount(attraction.id);
            enterRate += NaiveQueryImpl.instance().getAttractionEnterRate(attraction.id);
        }

        JSONObject jsonObject = new JSONObject();

        JSONArray jsonArrayForPoints = new JSONArray();
        for (int i = 0; i < allPoints.size(); i++) {
            JSONObject jsonObjectForPoint = new JSONObject();
            jsonObjectForPoint.put("lat", allPoints.get(i).x);
            jsonObjectForPoint.put("lng", allPoints.get(i).y);
            jsonObjectForPoint.put("count", pointCounts.get(i));
            jsonArrayForPoints.put(jsonObjectForPoint);
        }
        jsonObject.put("Points", jsonArrayForPoints);

        jsonObject.put("in_count", inCount);
        jsonObject.put("out_count", outCount);
        jsonObject.put("enter_count", enterCount);
        jsonObject.put("enter_rate", enterRate);

        return jsonObject.toString();
    }
}
