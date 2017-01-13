package edu.illinois.adsc.sentosa.rest;

import edu.illinois.adsc.sentosa.query.naive.NaiveQueryImpl;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by robert on 28/12/16.
 */
@Path("warning_message")
public class WarningMessage {
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String setWainingMessage(
            @FormParam("message") String message) {
        NaiveQueryImpl.instance().setWarningMessage(message);
        return "Success!";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getWarningMessage(@DefaultValue("null") @QueryParam("message") String message) {
        if (message.equals("null")) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", NaiveQueryImpl.instance().getWarningMessage());
            return jsonObject.toString();
        } else {
            NaiveQueryImpl.instance().setWarningMessage(message);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("response", "updated");
            return jsonObject.toString();
        }
    }
}
