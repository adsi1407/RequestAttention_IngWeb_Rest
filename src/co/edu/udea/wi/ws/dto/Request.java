package co.edu.udea.wi.ws.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import co.edu.udea.wi.dto.Customer;
import co.edu.udea.wi.dto.RequestState;
import co.edu.udea.wi.dto.RequestType;

@XmlRootElement
public class Request {

	private int ID;
	private Date applicationDate;
	private RequestType type;
	private RequestState state;
	private Customer customer;
	private String description;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public Date getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}
	public RequestType getType() {
		return type;
	}
	public void setType(RequestType type) {
		this.type = type;
	}
	public RequestState getState() {
		return state;
	}
	public void setState(RequestState state) {
		this.state = state;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
