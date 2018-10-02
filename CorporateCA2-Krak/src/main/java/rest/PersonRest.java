package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.PersonContactDTO;
import dto.PersonDTO;
import entity.Person;
import facade.FacadeInterface;
import facade.KrakException;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("person")
public class PersonRest {

    private FacadeInterface facade = null;
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    public PersonRest() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersons() {
        List<PersonDTO> personDtoList = facade.getAllPersons();
        String json = gson.toJson(personDtoList);
        return Response.ok().entity(json).type(MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("{phoneNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson(@PathParam("phoneNumber") int phoneNumber) throws KrakException {
        PersonDTO person = facade.getInformation(phoneNumber);
        if (person == null) {
            throw new KrakException("Could not find person", 404);
        }
        String json = gson.toJson(person);
        return Response.ok().entity(json).type(MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("contactInfo/{phoneNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContactInformation(@PathParam("phoneNumber") int phoneNumber) throws KrakException {
        PersonContactDTO person = facade.getContactInformation(phoneNumber);
        if (person == null) {
            throw new KrakException("Could not find person", 404);
        }
        String json = gson.toJson(person);
        return Response.ok().entity(json).type(MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("{hobbyName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonsByHobby(@PathParam("hobbyName") String hobby) {
        List<PersonDTO> persons = facade.getPersonsByHobby(hobby);
        String json = gson.toJson(persons);
        return Response.ok().entity(json).type(MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("{zipcode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonsInCity(@PathParam("zipcode") int zip) {
        List<PersonDTO> persons = facade.getPersonsInCity(zip);
        String json = gson.toJson(persons);
        return Response.ok().entity(json).type(MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findPersonById(@PathParam("id") int id) throws KrakException {
        PersonDTO person = facade.findPersonById(id);
        if (person == null) {
            throw new KrakException("Could not find person", 404);
        }
        String json = gson.toJson(person);
        return Response.ok().entity(json).type(MediaType.APPLICATION_JSON).build();
    }

}
