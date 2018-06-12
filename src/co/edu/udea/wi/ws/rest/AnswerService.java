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

import co.edu.udea.wi.bl.AnswerBl;
import co.edu.udea.wi.ws.dto.Answer;
import co.edu.udea.wi.ws.dto.Attendant;
import co.edu.udea.wi.ws.dto.Request;

@Path("Answer")
@Component
public class AnswerService {

	private AttendantService attendantService;
	private RequestService requestService;
	
	@Autowired
	private AnswerBl answerBl;
	
	public AnswerService() {
		
		attendantService = new AttendantService();
		requestService = new RequestService();
	}
	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	@Path("/create")
	public Answer createAnswer(Answer answer) throws RemoteException {
		
		co.edu.udea.wi.dto.Answer answerModel;
		
		try {
			
			answerModel = new co.edu.udea.wi.dto.Answer();
			answerModel.setAnswerDate(new Date());
			answerModel.setDescription(answer.getDescription());
			answerModel.setAttendant(answer.getAttendant());
			answerModel.setRequest(answer.getRequest());
			
			answerBl.create(answerModel);
			
		} catch (Exception e) {
			throw new RemoteException(e.getMessage());
		}
		
		return answer;
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public List<Answer> getAnswers() throws RemoteException {
		
		Answer answer;
		List<Answer> answers = new ArrayList<Answer>();
		List<co.edu.udea.wi.dto.Answer> answersModel = null;
		
		try {
			
			answersModel = answerBl.getAnswers();
			
			for (co.edu.udea.wi.dto.Answer item : answersModel) {
				
				answer = new Answer();
				answer.setID(item.getID());
				answer.setAnswerDate(item.getAnswerDate());
				answer.setDescription(item.getDescription());
				answer.setAttendant(item.getAttendant());
				answer.setRequest(item.getRequest());
				answers.add(answer);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return answers;
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@Path("/{id}")
	public Answer getAnswerById(@PathParam("id") int id) throws RemoteException {
		
		Answer answer = null;
		co.edu.udea.wi.dto.Answer answerModel = null;
		
		try {
			
			answerModel = answerBl.getAnswerById(id);
			answer = new Answer();
			answer.setID(answerModel.getID());
			answer.setAnswerDate(answerModel.getAnswerDate());
			answer.setDescription(answerModel.getDescription());
			answer.setAttendant(answerModel.getAttendant());
			answer.setRequest(answerModel.getRequest());
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return answer;
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@Path("/attendant/{id}")
	public List<Answer> getAnswersByAttendant(@PathParam("id") String id) throws RemoteException {
		
		List<Answer> answers = new ArrayList<Answer>();
		List<co.edu.udea.wi.dto.Answer> answersModel = null;
		Answer answer;
		
		try {
			
			Attendant attendant = attendantService.getAttendantById(id);
			
			if (attendant != null) {
				
				co.edu.udea.wi.dto.Attendant attendantModel = new co.edu.udea.wi.dto.Attendant();
				attendantModel = new co.edu.udea.wi.dto.Attendant();
				attendantModel.setID(attendant.getID());
				attendantModel.setFirstName(attendant.getFirstName());
				attendantModel.setLastName(attendant.getLastName());
				attendantModel.setEmail(attendant.getEmail());
				attendantModel.setPhoneNumber(attendant.getPhoneNumber());
				attendantModel.setPosition(attendant.getPosition());
				
				answersModel = answerBl.getAnswersByAttendant(attendantModel);
				
				for (co.edu.udea.wi.dto.Answer item : answersModel) {
					
					answer = new Answer();
					answer.setID(item.getID());
					answer.setAnswerDate(item.getAnswerDate());
					answer.setDescription(item.getDescription());
					answer.setAttendant(item.getAttendant());
					answer.setRequest(item.getRequest());
					answers.add(answer);
				}
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return answers;
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@Path("/request/{id}")
	public List<Answer> getAnswersByRequest(@PathParam("id") int id) throws RemoteException {
		
		List<Answer> answers = new ArrayList<Answer>();
		List<co.edu.udea.wi.dto.Answer> answersModel = null;
		Answer answer;
		
		try {
			
			Request request = requestService.getRequestById(id);
			
			if (request != null) {
				
				co.edu.udea.wi.dto.Request requestModel = new co.edu.udea.wi.dto.Request();
				requestModel = new co.edu.udea.wi.dto.Request();
				requestModel.setID(request.getID());
				requestModel.setApplicationDate(request.getApplicationDate());
				requestModel.setCustomer(request.getCustomer());
				requestModel.setDescription(request.getDescription());
				requestModel.setState(request.getState());
				requestModel.setType(request.getType());
				
				answersModel = answerBl.getAnswersByRequest(requestModel);
				
				for (co.edu.udea.wi.dto.Answer item : answersModel) {
					
					answer = new Answer();
					answer.setID(item.getID());
					answer.setAnswerDate(item.getAnswerDate());
					answer.setDescription(item.getDescription());
					answer.setRequest(item.getRequest());
					answers.add(answer);
				}
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return answers;
	}
}
