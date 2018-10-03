/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facade.Facade;
import facade.FacadeInterface;
import java.util.List;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author perlt
 */
@Path("address")
public class AddressResource {

    private Facade facade = new Facade(Persistence.createEntityManagerFactory("pu"));
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    @Context
    private UriInfo context;
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listOfAllZipcodes(){
        List<Integer> list = facade.listOfAllZipcodes();
        String json = gson.toJson(list);
        return Response.ok().entity(json).type(MediaType.APPLICATION_JSON).build();
    }
}
