package co.edu.udea.wi.ws.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import co.edu.udea.wi.dto.Attendant;
import co.edu.udea.wi.dto.Request;

@XmlRootElement
public class Answer {

	private int ID;
	private Date answerDate;
	private String description;
	private Request request;
	private Attendant attendant;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public Date getAnswerDate() {
		return answerDate;
	}
	public void setAnswerDate(Date answerDate) {
		this.answerDate = answerDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Request getRequest() {
		return request;
	}
	public void setRequest(Request request) {
		this.request = request;
	}
	public Attendant getAttendant() {
		return attendant;
	}
	public void setAttendant(Attendant attendant) {
		this.attendant = attendant;
	}
}
