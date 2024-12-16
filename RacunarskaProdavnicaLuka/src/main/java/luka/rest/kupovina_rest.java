/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luka.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import luka.data.korisnik;
import luka.data.kupovina;
import luka.data.proizvod;
import luka.exception.prodavnica_exception;
import luka.service.kupovina_service;



/**
 *
 * @author aleksandar.miljkovic
 */
@Path("purchase")
public class kupovina_rest {

    
    private final kupovina_service purchaseService = kupovina_service.getInstance();
   
    
@POST
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response makePurchase(kupovina k) {
    try {
        purchaseService.makeKupovina(k.getCustomer(), k.getProduct());
        return Response.ok().build();
    } catch (prodavnica_exception e) {
        return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
    } catch (Exception e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server Error").build();
    }
}

   
    
}
