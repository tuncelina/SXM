package parser;

import java.util.ArrayList;
import java.util.List;

public class TravelAgency {

	private String name;
	private int year;
	private List<Person> people = new ArrayList<>();
	private List<Consultant> consultants = new ArrayList<>();
	private List<Traveller> travellers = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public List<Person> getPeople() {
		return people;
	}

	public void setPeople(List<Person> people) {
		this.people = people;
	}

	public List<Consultant> getConsultants() {
		return consultants;
	}

	public void setConsultants(List<Consultant> consultants) {
		this.consultants = consultants;
	}

	public List<Traveller> getTravellers() {
		return travellers;
	}

	public void setTravellers(List<Traveller> travellers) {
		this.travellers = travellers;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("--- TravelAgency ---\n");
		sb.append("Name: " + name + "\n" + "Year: " + year + "\n\n" + "People:\n");
		for (Person person : people) {
			sb.append("\t" + person.toString() + "\n");
		}
		sb.append("\n");
		sb.append("Consultants:\n");
		for (Consultant consultant : consultants) {
			sb.append("\t" + consultant.toString() + "\n");
		}
		sb.append("\n");
		sb.append("Travellers:\n");
		for (Traveller traveller : travellers) {
			sb.append("-\t" + traveller.toString() + "\n\n");
		}
		return sb.toString();
	}

}
