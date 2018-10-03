/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.CompanyDTO;
import dto.PersonDTO;
import entity.Company;
import entity.Person;
import exception.KrakException;
import facade.FacadeInterface;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author perlt
 */
@Path("company")
public class CompanyResource {
    
    private FacadeInterface facade = null;
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    public CompanyResource() {
    }

    
    @GET
    @Path("{phonenumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompanyInformationOnPhone(@PathParam("phonenumber") int phone){
        CompanyDTO companyDTO = facade.companyInformationOnPhone(phone);
        String json = gson.toJson(companyDTO);
        
        return Response.ok().entity(json).type(MediaType.APPLICATION_JSON).build();
    }
   
    @GET
    @Path("{cvr}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response companyInformationOnCVR(@PathParam("cvr") int cvr){
        CompanyDTO companyDTO = facade.companyInformationOnCVR(cvr);
        String json = gson.toJson(companyDTO);
        
        return Response.ok().entity(json).type(MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
     public Response companyWithMoreThanXEmployees(@QueryParam("employeeCount") int count){
        List<CompanyDTO> list = facade.companyWithMoreThanXEmployees(count);
        String json = gson.toJson(list);
        
        return Response.ok().entity(json).type(MediaType.APPLICATION_JSON).build();
    }
     
      @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findCompanyById(@PathParam("id") int id) throws KrakException {
        CompanyDTO company = facade.findCompanyByID(id);
        String json = gson.toJson(company);
        return Response.ok().entity(json).type(MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCompany(String json) {
        CompanyDTO companyDTO = gson.fromJson(json, CompanyDTO.class);
        Company company = new Company(companyDTO);
        facade.addCompany(company);
        String responseJson = gson.toJson(company);
        return Response.ok().entity(responseJson).type(MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editCompany(String json) {
        CompanyDTO companyDTO = gson.fromJson(json, CompanyDTO.class);
        Company company = new Company(companyDTO);
        facade.editCompany(company);
        String responseJson = gson.toJson(company);
        return Response.ok().entity(responseJson).type(MediaType.APPLICATION_JSON).build();
    }
    
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCompany(@PathParam("id") int id){
        CompanyDTO companyDTO = facade.deleteCompany(id);
        String json = gson.toJson(companyDTO);
        return Response.ok().entity(json).type(MediaType.APPLICATION_JSON).build();
    }
    
}
