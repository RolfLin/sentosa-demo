package edu.illinois.adsc.sentosa.rest;

import edu.illinois.adsc.sentosa.query.naive.NaiveQueryImpl;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by robert on 28/12/16.
 */
@Path("login")
public class Login {
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String getFlow(
            @FormParam("name") String name,
            @FormParam("password") String password) {
        System.out.println(String.format("Name: %s, Password: %s", name, password));
        return "Success!";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getWarningMessage(@QueryParam("name") String message,
                                    @QueryParam("password") String password) {
        JSONObject object = new JSONObject();
        object.put("response", "success");
        return object.toString();
    }
}
