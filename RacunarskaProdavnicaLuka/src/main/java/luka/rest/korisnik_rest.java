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
import luka.data.korisnik;
import luka.exception.prodavnica_exception;
import luka.service.korisnik_service;

@Path("korisnik")
public class korisnik_rest {

    private final korisnik_service customerService = korisnik_service.getInstance();
    
    
    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public korisnik getCustomerByNaziv(@PathParam("username") String username) throws prodavnica_exception {
        return customerService.findCustomer(username);
    }
    
    @GET
    @Path("/id/{korisnik_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public korisnik getCustomerByID(@PathParam("korisnik_id") int korisnik_id) throws prodavnica_exception {
        return customerService.findCustomerID(korisnik_id);
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<korisnik> getAllKorisnici() throws prodavnica_exception {
        return customerService.getAllKorisnici();  
    }
    
  
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCustomer(korisnik k) throws prodavnica_exception{
            customerService.addNewCustomer(k);
            return Response.ok().build();
    }
    
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response prijava(korisnik k) throws prodavnica_exception{
            String rez = customerService.login(k.getUsername(), k.getPassword());
            if (rez != null) {
            return Response.ok(rez).build(); 
        }
        return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid username or password").build();
    }
    
    
    
 

    
}




