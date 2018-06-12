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

import co.edu.udea.wi.bl.RequestTypeBl;
import co.edu.udea.wi.ws.dto.RequestType;

@Path("TipoSolicitud")
@Component
public class RequestTypeService {

	@Autowired
	private RequestTypeBl requestTypeBl;
	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	@Path("/create/{name}")
	public RequestType createRequestType(@PathParam("name") String name) throws RemoteException {
		
		RequestType requestType = null;
		co.edu.udea.wi.dto.RequestType requestTypeModel;
		
		try {
			
			requestTypeModel = new co.edu.udea.wi.dto.RequestType();
			requestTypeModel.setName(name);
			requestTypeBl.create(requestTypeModel);
			
			requestType = new RequestType();
			requestType.setName(name);
			
		} catch (Exception e) {
			throw new RemoteException(e.getMessage());
		}
		
		return requestType;
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public List<RequestType> getRequestTypes() throws RemoteException {
		
		RequestType requestType;
		List<RequestType> requestTypes = new ArrayList<RequestType>();
		List<co.edu.udea.wi.dto.RequestType> requestTypesModel = null;
		
		try {
			
			requestTypesModel = requestTypeBl.getRequestTypes();
			
			for (co.edu.udea.wi.dto.RequestType item : requestTypesModel) {
				
				requestType = new RequestType();
				requestType.setID(item.getID());
				requestType.setName(item.getName());
				requestTypes.add(requestType);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return requestTypes;
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@Path("/{id}")
	public RequestType getRequestTypeById(@PathParam("id") int id) throws RemoteException {
		
		RequestType requestType = null;
		co.edu.udea.wi.dto.RequestType requestTypeModel = null;
		
		try {
			
			requestTypeModel = requestTypeBl.getRequestTypeById(id);
			requestType = new RequestType();
			requestType.setID(requestTypeModel.getID());
			requestType.setName(requestTypeModel.getName());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return requestType;
	}
}
