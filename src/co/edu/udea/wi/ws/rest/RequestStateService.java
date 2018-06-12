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

import co.edu.udea.wi.bl.RequestStateBl;
import co.edu.udea.wi.ws.dto.RequestState;

@Path("RequestState")
@Component
public class RequestStateService {

	@Autowired
	private RequestStateBl requestStateBl;
	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	@Path("/create/{name}")
	public RequestState createRequestState(@PathParam("name") String name) throws RemoteException {
		
		RequestState requestState = null;
		co.edu.udea.wi.dto.RequestState requestStateModel;
		
		try {
			
			requestStateModel = new co.edu.udea.wi.dto.RequestState();
			requestStateModel.setName(name);
			requestStateBl.create(requestStateModel);
			
			requestState = new RequestState();
			requestState.setName(name);
			
		} catch (Exception e) {
			throw new RemoteException(e.getMessage());
		}
		
		return requestState;
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public List<RequestState> getRequestStates() throws RemoteException {
		
		RequestState requestState;
		List<RequestState> requestStates = new ArrayList<RequestState>();
		List<co.edu.udea.wi.dto.RequestState> requestStatesModel = null;
		
		try {
			
			requestStatesModel = requestStateBl.getRequestStates();
			
			for (co.edu.udea.wi.dto.RequestState item : requestStatesModel) {
				
				requestState = new RequestState();
				requestState.setID(item.getID());
				requestState.setName(item.getName());
				requestStates.add(requestState);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return requestStates;
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@Path("/{id}")
	public RequestState getRequestStateById(@PathParam("id") int id) throws RemoteException {
		
		RequestState requestState = null;
		co.edu.udea.wi.dto.RequestState requestStateModel = null;
		
		try {
			
			requestStateModel = requestStateBl.getRequestStateById(id);
			requestState = new RequestState();
			requestState.setID(requestStateModel.getID());
			requestState.setName(requestStateModel.getName());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return requestState;
	}
}
