package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import dto.PersonContactDTO;
import dto.PersonDTO;
import entity.Person;
import exception.KrakException;
import facade.Facade;
import java.util.List;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("person")
public class PersonRest {

    private Facade facade = new Facade(Persistence.createEntityManagerFactory("pu"));
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
    @Path("phone/{phoneNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonByPhone(@PathParam("phoneNumber") int phoneNumber) throws KrakException {
        PersonDTO person = facade.getInformation(phoneNumber);
        String json = gson.toJson(person);
        return Response.ok().entity(json).type(MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("contactInfo/{phoneNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContactInformation(@PathParam("phoneNumber") int phoneNumber) throws KrakException {
        PersonContactDTO person = facade.getContactInformation(phoneNumber);
        String json = gson.toJson(person);
        return Response.ok().entity(json).type(MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("hobby/{hobbyName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonsByHobby(@PathParam("hobbyName") String hobby) throws KrakException {
        List<PersonDTO> persons = facade.getPersonsByHobby(hobby);
        String json = gson.toJson(persons);
        return Response.ok().entity(json).type(MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("hobby/count/{hobby}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response countOfPeopleByHobby(@PathParam("hobby") String hobby) throws KrakException {
        JsonObject jo = new JsonObject();
        jo.addProperty("count", facade.countOfPeopleByHobby(hobby));
        String json = gson.toJson(jo);
        return Response.ok().entity(json).type(MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("zip/{zipcode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonsInCity(@PathParam("zipcode") int zip) throws KrakException {
        List<PersonDTO> persons = facade.getPersonsInCity(zip);
        String json = gson.toJson(persons);
        return Response.ok().entity(json).type(MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findPersonById(@PathParam("id") int id) throws KrakException {
        PersonDTO person = facade.findPersonDTOById(id);
        String json = gson.toJson(person);
        return Response.ok().entity(json).type(MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Path("firstname/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findPersonDTOByFirstName(@PathParam("name") String name) throws KrakException{
        List<PersonDTO> person = facade.getPersonDTOByFirstName(name);
        String json = gson.toJson(person);
        return Response.ok().entity(json).type(MediaType.APPLICATION_JSON).build();
    }

     @GET
    @Path("lastname/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findPersonDTOByLastName(@PathParam("name") String name) throws KrakException{
        List<PersonDTO> person = facade.getPersonDTOByLastName(name);
        String json = gson.toJson(person);
        return Response.ok().entity(json).type(MediaType.APPLICATION_JSON).build();
    }
    
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPerson(String json) throws KrakException {
        PersonDTO personDTO = gson.fromJson(json, PersonDTO.class);
        Person person = new Person(personDTO);
        facade.addPerson(person);
        String responseJson = gson.toJson(person);
        return Response.ok().entity(responseJson).type(MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPerson(String json) throws KrakException {
        PersonDTO personDTO = gson.fromJson(json, PersonDTO.class);
        Person person = new Person(personDTO);
        facade.editPerson(person);
        String responseJson = gson.toJson(person);
        return Response.ok().entity(responseJson).type(MediaType.APPLICATION_JSON).build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePerson(@PathParam("id") int id) throws KrakException {
        PersonDTO personDTO = facade.deletePerson(id);
        String json = gson.toJson(personDTO);
        return Response.ok().entity(json).type(MediaType.APPLICATION_JSON).build();
    }

    
    
}
