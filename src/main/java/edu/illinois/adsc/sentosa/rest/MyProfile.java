package edu.illinois.adsc.sentosa.rest;

import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by robert on 28/12/16.
 */
@Path("myprofile")
public class MyProfile {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getProfile() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Image", "sentosa-demo-image/my_profile.png");
        jsonObject.put("Nickname", "Jack");
        jsonObject.put("Sex", "male");
        jsonObject.put("Phone", "+65-110119114");

        return jsonObject.toString();
    }
}
