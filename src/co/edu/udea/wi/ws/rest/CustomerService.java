package co.edu.udea.wi.ws.rest;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.udea.wi.bl.CustomerBl;
import co.edu.udea.wi.ws.dto.Customer;
import co.edu.udea.wi.ws.dto.RequestState;

@Path("Cliente")
@Component
public class CustomerService {

	@Autowired
	private CustomerBl customerBl;
	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	@Path("/create")
	public Customer createCustomer(Customer customer) throws RemoteException {
		
		co.edu.udea.wi.dto.Customer customerModel;
		
		try {
			
			customerModel = new co.edu.udea.wi.dto.Customer();
			customerModel.setID(customer.getID());
			customerModel.setFirstName(customer.getFirstName());
			customerModel.setLastName(customer.getLastName());
			customerModel.setEmail(customer.getEmail());
			customerModel.setPhoneNumber(customer.getPhoneNumber());
			customerModel.setAddress(customer.getAddress());
			customerBl.create(customerModel);
			
		} catch (Exception e) {
			throw new RemoteException(e.getMessage());
		}
		
		return customer;
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public List<Customer> getCustomers() throws RemoteException {
		
		Customer customer;
		List<Customer> customers = new ArrayList<Customer>();
		List<co.edu.udea.wi.dto.Customer> customersModel = null;
		
		try {
			
			customersModel = customerBl.getCustomers();
			
			for (co.edu.udea.wi.dto.Customer item : customersModel) {
				
				customer = new Customer();
				customer.setID(item.getID());
				customer.setFirstName(item.getFirstName());
				customer.setLastName(item.getLastName());
				customer.setEmail(item.getEmail());
				customer.setPhoneNumber(item.getPhoneNumber());
				customer.setAddress(item.getAddress());
				customers.add(customer);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return customers;
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@Path("/{id}")
	public Customer getCustomerById(@PathParam("id") String id) throws RemoteException {
		
		Customer customer = null;
		co.edu.udea.wi.dto.Customer customerModel = null;
		
		try {
			
			customerModel = customerBl.getCustomerById(id);
			customer = new Customer();
			customer.setID(customerModel.getID());
			customer.setFirstName(customerModel.getFirstName());
			customer.setLastName(customerModel.getLastName());
			customer.setEmail(customerModel.getEmail());
			customer.setPhoneNumber(customerModel.getPhoneNumber());
			customer.setAddress(customerModel.getAddress());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return customer;
	}
}
