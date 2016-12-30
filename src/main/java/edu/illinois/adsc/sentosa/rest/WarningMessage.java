package edu.illinois.adsc.sentosa.rest;

import edu.illinois.adsc.sentosa.query.naive.NaiveQueryImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by robert on 28/12/16.
 */
@Path("warning_message")
public class WarningMessage {
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String setWainingMessage(
            @FormParam("message") String message) {
        NaiveQueryImpl.instance().setWarningMessage(message);
        return "Success!";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getWarningMessage() {
        return NaiveQueryImpl.instance().getWarningMessage();
    }
}
