package edu.illinois.adsc.sentosa.rest;

import edu.illinois.adsc.sentosa.config.Config;
import edu.illinois.adsc.sentosa.error.ErrorCode;
import edu.illinois.adsc.sentosa.query.Interface.Attraction;
import edu.illinois.adsc.sentosa.query.naive.NaiveQueryImpl;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by robert on 28/12/16.
 */
@Path("history_trend")
public class HistoryTrend {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getTrend(
            @DefaultValue("0") @QueryParam("id") int id,
            @DefaultValue(Config.NumberOfDaysInHistoricalQuery + "") @QueryParam("numberOfDays") int numberOfDays) {
        JSONObject jsonObject = new JSONObject();
        for (int i = 1; i <= numberOfDays; i++) {
            JSONObject jsonObjectForADay = new JSONObject();

            JSONArray jsonArrayForFlow = new JSONArray();
            List<Integer> historicalFlows = NaiveQueryImpl.instance().retrieveFlowHistory(id, i);
            for(Integer f: historicalFlows) {
                jsonArrayForFlow.put(f);
            }
            jsonObjectForADay.put("flow", jsonArrayForFlow);

            JSONArray jsonArrayForQueuingTime = new JSONArray();
            List<Integer> historicalQueueingTimes = NaiveQueryImpl.instance().retrieveQueuingTimeHistory(id, i);
            for(Integer time: historicalQueueingTimes) {
                jsonArrayForQueuingTime.put(time);
            }
            jsonObjectForADay.put("queuing time", jsonArrayForQueuingTime);

            Calendar time = Calendar.getInstance();
            time.setTime(NaiveQueryImpl.instance().getCurrentTime().getTime());
            time.add(Calendar.DATE, - i);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            jsonObject.put(simpleDateFormat.format(time.getTime()), jsonObjectForADay);
        }
        return jsonObject.toString();
    }
}
