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
@Path("attraction_detail")
public class AttractionDetail {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getDetail(
            @DefaultValue("0") @QueryParam("id") int id) {
        Attraction attraction = NaiveQueryImpl.instance().getAttractionInfo(id);
        JSONObject jsonObject = new JSONObject();
        if (attraction == null) {
            jsonObject.put("error", ErrorCode.ATTRACTION_NOT_FOUND);
        } else {
            jsonObject.put("introduce", attraction.introduce);
            jsonObject.put("lng", attraction.x);
            jsonObject.put("lat time", attraction.y);
        }

        return jsonObject.toString();
    }
}
