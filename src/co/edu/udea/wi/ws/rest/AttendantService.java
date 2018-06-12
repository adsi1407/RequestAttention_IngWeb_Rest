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

import co.edu.udea.wi.bl.AttendantBl;
import co.edu.udea.wi.ws.dto.Attendant;

@Path("Attendant")
@Component
public class AttendantService {

	@Autowired
	private AttendantBl attendantBl;
	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	@Path("/create")
	public Attendant createAttendant(Attendant attendant) throws RemoteException {
		
		co.edu.udea.wi.dto.Attendant attendantModel;
		
		try {
			
			attendantModel = new co.edu.udea.wi.dto.Attendant();
			attendantModel.setID(attendant.getID());
			attendantModel.setFirstName(attendant.getFirstName());
			attendantModel.setLastName(attendant.getLastName());
			attendantModel.setEmail(attendant.getEmail());
			attendantModel.setPhoneNumber(attendant.getPhoneNumber());
			attendantModel.setPosition(attendant.getPosition());
			attendantBl.create(attendantModel);
			
		} catch (Exception e) {
			throw new RemoteException(e.getMessage());
		}
		
		return attendant;
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public List<Attendant> getAttendants() throws RemoteException {
		
		Attendant attendant;
		List<Attendant> attendants = new ArrayList<Attendant>();
		List<co.edu.udea.wi.dto.Attendant> attendantsModel = null;
		
		try {
			
			attendantsModel = attendantBl.getAttendants();
			
			for (co.edu.udea.wi.dto.Attendant item : attendantsModel) {
				
				attendant = new Attendant();
				attendant.setID(item.getID());
				attendant.setFirstName(item.getFirstName());
				attendant.setLastName(item.getLastName());
				attendant.setEmail(item.getEmail());
				attendant.setPhoneNumber(item.getPhoneNumber());
				attendant.setPosition(item.getPosition());
				attendants.add(attendant);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return attendants;
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@Path("/{id}")
	public Attendant getAttendantById(@PathParam("id") String id) throws RemoteException {
		
		Attendant attendant = null;
		co.edu.udea.wi.dto.Attendant attendantModel = null;
		
		try {
			
			attendantModel = attendantBl.getAttendantById(id);
			attendant = new Attendant();
			attendant.setID(attendantModel.getID());
			attendant.setFirstName(attendantModel.getFirstName());
			attendant.setLastName(attendantModel.getLastName());
			attendant.setEmail(attendantModel.getEmail());
			attendant.setPhoneNumber(attendantModel.getPhoneNumber());
			attendant.setPosition(attendantModel.getPosition());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return attendant;
	}
}
