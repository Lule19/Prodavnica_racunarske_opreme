package luka.rest;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import luka.data.proizvod;
import luka.exception.prodavnica_exception;
import luka.service.proizvod_service;


@Path("proizvod")
public class proizvod_rest {

    private final proizvod_service customerService = proizvod_service.getInstance();
    
    @GET
    @Path("/{naziv}")
    @Produces(MediaType.APPLICATION_JSON)
    public proizvod getCustomerByName(@PathParam("naziv") String naziv) throws prodavnica_exception {
        return customerService.findProizvod(naziv);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<proizvod> getAllProizvodi() throws prodavnica_exception {
        return customerService.getAllProizvodi();  
    }
    
    
}