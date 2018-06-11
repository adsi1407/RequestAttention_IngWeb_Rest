package co.edu.udea.wi.ws.rest;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.udea.wi.bl.CustomerBl;
import co.edu.udea.wi.ws.dto.Customer;

@Path("Cliente")
@Component
public class CustomerService {

	@Autowired
	private CustomerBl customerBl;
	
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
			// TODO: handle exception
		}
		
		return customers;
	}
}
