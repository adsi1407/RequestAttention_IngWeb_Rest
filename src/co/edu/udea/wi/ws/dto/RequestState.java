package co.edu.udea.wi.ws.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RequestState {

	private int ID;
	private String Name;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
}
