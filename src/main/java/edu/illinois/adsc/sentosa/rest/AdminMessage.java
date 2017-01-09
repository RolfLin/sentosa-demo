package edu.illinois.adsc.sentosa.rest;

import edu.illinois.adsc.sentosa.query.naive.NaiveQueryImpl;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by robert on 28/12/16.
 */
@Path("admin_message")
public class AdminMessage {
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String setWainingMessage(
            @FormParam("message") String message) {
        NaiveQueryImpl.instance().setAdminMessage(message);
        return "Success!";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getWarningMessage() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", NaiveQueryImpl.instance().getAdminMessage());
        return jsonObject.toString();
    }
}
