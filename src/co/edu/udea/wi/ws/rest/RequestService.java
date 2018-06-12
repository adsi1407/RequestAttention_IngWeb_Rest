package co.edu.udea.wi.ws.rest;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
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

import co.edu.udea.wi.bl.RequestBl;
import co.edu.udea.wi.ws.dto.Customer;
import co.edu.udea.wi.ws.dto.Request;

@Path("Request")
@Component
public class RequestService {

	private CustomerService customerService;
	
	@Autowired
	private RequestBl requestBl;
	
	public RequestService() {
		
		customerService = new CustomerService();
	}
	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	@Path("/create")
	public Request createRequest(Request request) throws RemoteException {
		
		co.edu.udea.wi.dto.Request requestModel;
		
		try {
			
			requestModel = new co.edu.udea.wi.dto.Request();
			requestModel.setApplicationDate(new Date());
			requestModel.setDescription(request.getDescription());
			requestModel.setCustomer(request.getCustomer());
			requestModel.setType(request.getType());
			
			co.edu.udea.wi.dto.RequestState requestState = new co.edu.udea.wi.dto.RequestState();
			requestState.setID(1);
			requestState.setName("Open");
			
			requestModel.setState(requestState);
			requestModel.setDescription(request.getDescription());
			requestBl.create(requestModel);
			
		} catch (Exception e) {
			throw new RemoteException(e.getMessage());
		}
		
		return request;
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public List<Request> getRequests() throws RemoteException {
		
		Request request;
		List<Request> requests = new ArrayList<Request>();
		List<co.edu.udea.wi.dto.Request> requestsModel = null;
		
		try {
			
			requestsModel = requestBl.getRequests();
			
			for (co.edu.udea.wi.dto.Request item : requestsModel) {
				
				request = new Request();
				request.setID(item.getID());
				request.setApplicationDate(item.getApplicationDate());
				request.setDescription(item.getDescription());
				request.setCustomer(item.getCustomer());
				request.setType(item.getType());
				request.setState(item.getState());
				requests.add(request);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return requests;
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@Path("/{id}")
	public Request getRequestById(@PathParam("id") int id) throws RemoteException {
		
		Request request = null;
		co.edu.udea.wi.dto.Request requestModel = null;
		
		try {
			
			requestModel = requestBl.getRequestById(id);
			request = new Request();
			request.setID(requestModel.getID());
			request.setApplicationDate(requestModel.getApplicationDate());
			request.setDescription(requestModel.getDescription());
			request.setCustomer(requestModel.getCustomer());
			request.setType(requestModel.getType());
			request.setState(requestModel.getState());
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return request;
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@Path("/customer/{id}")
	public List<Request> getRequestByCustomer(@PathParam("id") String id) throws RemoteException {
		
		List<Request> requests = new ArrayList<Request>();
		List<co.edu.udea.wi.dto.Request> requestsModel = null;
		Request request;
		
		try {
			
			Customer customer = customerService.getCustomerById(id);
			
			if (customer != null) {
				
				co.edu.udea.wi.dto.Customer customerModel = new co.edu.udea.wi.dto.Customer();
				customerModel = new co.edu.udea.wi.dto.Customer();
				customerModel.setID(customer.getID());
				customerModel.setFirstName(customer.getFirstName());
				customerModel.setLastName(customer.getLastName());
				customerModel.setEmail(customer.getEmail());
				customerModel.setPhoneNumber(customer.getPhoneNumber());
				customerModel.setAddress(customer.getAddress());
				
				requestsModel = requestBl.getRequestByCustomer(customerModel);
				
				for (co.edu.udea.wi.dto.Request item : requestsModel) {
					
					request = new Request();
					request.setID(item.getID());
					request.setApplicationDate(item.getApplicationDate());
					request.setDescription(item.getDescription());
					request.setCustomer(item.getCustomer());
					request.setType(item.getType());
					request.setState(item.getState());
					requests.add(request);
				}
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return requests;
	}
}
