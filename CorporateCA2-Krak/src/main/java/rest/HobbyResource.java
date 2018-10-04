/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.HobbyDTO;
import dto.PersonDTO;
import entity.Hobby;
import entity.Person;
import exception.KrakException;
import facade.Facade;
import facade.FacadeInterface;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("hobby")
public class HobbyResource {

    private Facade facade = new Facade(Persistence.createEntityManagerFactory("pu"));
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    @Context
    private UriInfo context;
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findHobbyById(@PathParam("id") int id) throws KrakException {
        HobbyDTO hobby = facade.findHobbyDTOByID(id);
        String json = gson.toJson(hobby);
        return Response.ok().entity(json).type(MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addHobby(String json) throws KrakException {
        HobbyDTO hobbyDTO = gson.fromJson(json, HobbyDTO.class);
        Hobby hobby = new Hobby(hobbyDTO);
        facade.addHobby(hobby);
        String responseJson = gson.toJson(hobby);
        return Response.ok().entity(responseJson).type(MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editHobby(String json) throws KrakException {
        HobbyDTO hobbyDTO = gson.fromJson(json, HobbyDTO.class);
        Hobby hobby = new Hobby(hobbyDTO);
        facade.editHobby(hobby);
        String responseJson = gson.toJson(hobby);
        return Response.ok().entity(responseJson).type(MediaType.APPLICATION_JSON).build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePerson(@PathParam("id") int id) throws KrakException {
        HobbyDTO hobbyDTO = facade.deleteHobby(id);
        String json = gson.toJson(hobbyDTO);
        return Response.ok().entity(json).type(MediaType.APPLICATION_JSON).build();
    }
    
    public static void main(String[] args) {
        Persistence.generateSchema("pu", null);
    }

    
    
    
    
    
    
}
