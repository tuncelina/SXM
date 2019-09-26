package parser;

import java.util.ArrayList;
import java.util.List;

public class Traveller {

	private String name;
	private String surname;
	private String email;
	private String phoneNumber;
	private String id;
	private List<Trip> trips = new ArrayList<>();
	private String note;

	public Traveller() {
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Trip> getTrips() {
		return trips;
	}

	public void setTrips(List<Trip> trips) {
		this.trips = trips;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Traveller [name: " + name + ", surname: " + surname + ", email: " + email + ", phoneNumber: "
				+ phoneNumber + ", id: " + id);
		if (trips.size() > 0) {
			sb.append(",\n\tTrips:\n");
			for (Trip trip : trips) {
				sb.append("\t\t" + trip.toString() + "\n");
			}
		} else {
			sb.append(",\n\tTrips: No trips yet\n");
		}
		return sb.toString();
	}

}
