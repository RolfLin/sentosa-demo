package edu.illinois.adsc.sentosa.rest;

import edu.illinois.adsc.sentosa.Utils.Utils;
import edu.illinois.adsc.sentosa.error.ErrorCode;
import edu.illinois.adsc.sentosa.query.Interface.Attraction;
import edu.illinois.adsc.sentosa.query.Interface.Shop;
import edu.illinois.adsc.sentosa.query.naive.NaiveQueryImpl;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("recommend_shops")
public class RecommendShops {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getShops(
            @DefaultValue("0") @QueryParam("id") int id) {
        Attraction attraction = NaiveQueryImpl.instance().getAttractionInfo(id);

        if (attraction == null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("error", ErrorCode.ATTRACTION_NOT_FOUND);
            return jsonObject.toString();
        } else {
            Collection<Shop> shops = NaiveQueryImpl.instance().getRecommendShops(attraction.x, attraction.y, 10);
            JSONArray jsonArray = new JSONArray();
            for(Shop shop: shops) {
                JSONObject jsonObjectForAShop = new JSONObject();
                jsonObjectForAShop.put("photo", shop.image);
                jsonObjectForAShop.put("rating", shop.rating);
                jsonObjectForAShop.put("name", shop.name);
                double distance = Math.sqrt(Math.pow(shop.x - attraction.x, 2) + Math.pow(shop.y - attraction.y, 2))
                        * Utils.coordinatorToMeterFactor;
                jsonObjectForAShop.put("distance", String.format("%d", (int)distance));
                jsonArray.put(jsonObjectForAShop);
            }
            return jsonArray.toString();
        }
    }
}
