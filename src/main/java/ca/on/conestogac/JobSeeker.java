package ca.on.conestogac;

import java.io.Serializable;

public class JobSeeker implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5579221022602933181L;
	
	private String sEmailAddress;
	private String sFirstName;
	private String sLastName;

	public String getsLastName() {
		return sLastName;
	}

	public void setsLastName(String sLastName) {
		this.sLastName = sLastName;
	}

	public String getsFirstName() {
		return sFirstName;
	}

	public void setsFirstName(String sFirstName) {
		this.sFirstName = sFirstName;
	}

	public String getsEmailAddress() {
		return sEmailAddress;
	}

	public void setsEmailAddress(String sEmailAddress) {
		this.sEmailAddress = sEmailAddress;
	}

}
