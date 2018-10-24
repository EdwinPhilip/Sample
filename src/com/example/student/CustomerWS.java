package com.example.student;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/customers")
@Stateless
@LocalBean
public class CustomerWS {

    @EJB
    private CustomerDAO customersDao;
    
    @GET
    @Path("/host/")
    public String getHost() {
    	InetAddress ip = null ;
    	String Hostname = null;
    	String IPaddress =null;
		try {
			ip = InetAddress.getLocalHost();
			IPaddress ="IP ADDRESS: "+ ip.getHostAddress();
			Hostname ="HOSTNAME: " +ip.getHostName();
	        
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "\n\n <h2>"+IPaddress +"</h2>\n\n <h2>"+Hostname+"</h2>";
    	
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer getCustomer(@PathParam("id") int id) {
        return customersDao.getCustomer(id);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addCustomers(List<Customer> customers) {
        customersDao.addCustomers(customers);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Customer> getAllCustomers() {
    	return customersDao.getAllCustomers();
    }
    
    @PUT
    @Path("{id}/{Fname}/{Lname}")
    public Response updateCustomer(@PathParam("id") String id,@PathParam("Fname") String Fname,@PathParam("Lname") String Lname) {
    	customersDao.updateCustomer(Integer.parseInt(id), Fname, Lname);
    	return Response.ok().build();
    }
    
    @DELETE
    @Path("/{id}")
    public Response deleteCustomer(@PathParam("id") int id) {
        customersDao.deleteCustomer(id);
        return Response.noContent().build();
    }
    
}