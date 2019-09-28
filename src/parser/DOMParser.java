package parser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.*;

import org.w3c.dom.*;

public class DOMParser {
	// Nacitanie xml suboru
	public TravelAgency readXML(File xmlSubor) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder parser;
		try {
			parser = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new RuntimeException("Can't create parser", e);
		}
		Document doc;
		try {
			doc = parser.parse(xmlSubor);
		} catch (Exception e) {
			throw new RuntimeException("Unable to create or parse XML document", e);
		}

		return createTravelAgency(doc.getDocumentElement());
	}

	private TravelAgency createTravelAgency(Element xmlTravelAgency) {
		// Overime, ci elementom je naozaj TravelAgency
		if (!"TravelAgency".equals(xmlTravelAgency.getNodeName())) {
			throw new RuntimeException("Not correct root element");
		}
		// Vytvorime TravelAgency a nastavime mu atributy
		TravelAgency travelAgency = new TravelAgency();

		travelAgency.setName(xmlTravelAgency.getAttribute("name"));
		travelAgency.setYear(Integer.parseInt(xmlTravelAgency.getAttribute("year")));

		// Vyberieme child nodes korenoveho elementa (TechnicalSupport, Consultants,
		// Clients)
		NodeList childNodes = xmlTravelAgency.getChildNodes();

		// Prejdeme vsetky forcyklom
		for (int i = 0; i < childNodes.getLength(); i++) {
			// Vyberieme si prvy (najprv to je TechnicalSupport)
			Node node = childNodes.item(i);

			if (node.getNodeName().equals("TechnicalSupport")) {
				List<Person> people = new ArrayList<>();
				// Prejdeme vsetky jeho ChildNodes, co su elementy Person
				// a vytvorime objekty Person ktore dame do listu People
				NodeList nodes = node.getChildNodes();
				for (int j = 0; j < nodes.getLength(); j++) {
					Node person = nodes.item(j);
					if ((person instanceof Element) && ("Person".equals(person.getNodeName()))) {
						Person per = createPerson((Element) person);
						people.add(per);
					}
				}
				travelAgency.setPeople(people);
			}

			if (node.getNodeName().equals("Consultants")) {
				List<Consultant> consultants = new ArrayList<>();
				// Prejdeme vsetky jeho ChildNodes, co su elementy Consultant
				// a vytvorime objekty Consultant ktore dame do listu Consultants
				NodeList nodes = node.getChildNodes();
				for (int j = 0; j < nodes.getLength(); j++) {
					Node consultant = nodes.item(j);
					if ((consultant instanceof Element) && ("Consultant".equals(consultant.getNodeName()))) {
						Consultant con = createConsultant((Element) consultant);
						consultants.add(con);
					}
				}
				travelAgency.setConsultants(consultants);
			}

			if (node.getNodeName().equals("Clients")) {
				List<Traveller> travellers = new ArrayList<>();
				// Prejdeme vsetky jeho ChildNodes, co su elementy Traveller
				// a vytvorime objekty Traveller ktore dame do listu Travellers
				NodeList nodes = node.getChildNodes();
				for (int j = 0; j < nodes.getLength(); j++) {
					Node traveller = nodes.item(j);
					if ((traveller instanceof Element) && ("Traveller".equals(traveller.getNodeName()))) {
						Traveller tra = createTraveller((Element) traveller);
						travellers.add(tra);
					}
				}
				travelAgency.setTravellers(travellers);
			}
		}

		return travelAgency;
	}

	// Metoda na vytvorenie objektu Person z elementu
	private Person createPerson(Element personElement) {
		Person person = new Person();

		person.setRole(personElement.getAttribute("role"));
		person.setId(personElement.getAttribute("id"));
		person.setAccessLevel(Integer.parseInt(personElement.getAttribute("accessLevel")));
		person.setName(personElement.getAttribute("name"));

		return person;
	}

	// Metoda na vytvorenie objektu Consultant z elementu
	private Consultant createConsultant(Element consultantElement) {
		Consultant consultant = new Consultant();

		consultant.setSpecialization(consultantElement.getAttribute("specialization"));
		consultant.setName(consultantElement.getAttribute("name"));

		return consultant;
	}

	// Metoda na vytvorenie objektu Traveller z elementu
	private Traveller createTraveller(Element travellerElement) {
		Traveller traveller = new Traveller();

		Element name = returnChildElement(travellerElement, "Name");
		traveller.setName(name.getTextContent());

		Element surname = returnChildElement(travellerElement, "Surname");
		traveller.setSurname(surname.getTextContent());

		Element email = returnChildElement(travellerElement, "EMail");
		traveller.setEmail(email.getTextContent());

		Element phoneNumber = returnChildElement(travellerElement, "PhoneNumber");
		traveller.setPhoneNumber(phoneNumber.getTextContent());

		Element id = returnChildElement(travellerElement, "ID");
		traveller.setId(id.getTextContent());

		Element trips = returnChildElement(travellerElement, "Trips");

		NodeList xmlDeti = trips.getChildNodes();
		List<Trip> tripList = new ArrayList<>();

		for (int i = 0; i < xmlDeti.getLength(); i++) {
			Node xmlDieta = xmlDeti.item(i);

			// Ak sme nasli medzi detmi element Student, tak ho spracujeme
			if ((xmlDieta instanceof Element) && ("Trip".equals(xmlDieta.getNodeName()))) {
				Trip trip = createTrip((Element) xmlDieta);
				tripList.add(trip);
			}
		}

		traveller.setTrips(tripList);

		Element note = returnChildElement(travellerElement, "Note");
		traveller.setNote(note.getTextContent());

		return traveller;
	}

	// Vratenie child elementu v danom elemente
	private Element returnChildElement(Element xmlElement, String elementName) {
		NodeList xmlDeti = xmlElement.getChildNodes();
		for (int i = 0; i < xmlDeti.getLength(); i++) {
			Node xmlDieta = xmlDeti.item(i);
			if ((xmlDieta instanceof Element) && (elementName.equals(xmlDieta.getNodeName()))) {
				return (Element) xmlDieta;
			}
		}
		return null;
	}

	// Metoda na vytvorenie objektu Trip z elementu
	private Trip createTrip(Element tripElement) {
		Trip trip = new Trip();

		trip.setTitle(tripElement.getAttribute("title"));
		trip.setDate((tripElement.getAttribute("date")));
		trip.setDuration((tripElement.getAttribute("duration")));
		if (tripElement.hasAttribute("discount")) {
			trip.setDiscount(tripElement.getAttribute("discount"));
		} else {
			trip.setDiscount("");
		}

		Element way = returnChildElement(tripElement, "Way");
		trip.setWay(way.getTextContent());

		Element fellowTravellersElement = returnChildElement(tripElement, "FellowTravellers");

		if (fellowTravellersElement != null) {
			NodeList xmlDeti = fellowTravellersElement.getChildNodes();
			List<FellowTraveller> fellowTravellers = new ArrayList<>();

			for (int i = 0; i < xmlDeti.getLength(); i++) {
				Node xmlDieta2 = xmlDeti.item(i);

				if ((xmlDieta2 instanceof Element) && ("FellowTraveller".equals(xmlDieta2.getNodeName()))) {
					FellowTraveller fellowTraveller = createFellowTraveller((Element) xmlDieta2);
					fellowTravellers.add(fellowTraveller);
				}
			}
			trip.setFellowTravellers(fellowTravellers);
		}

		return trip;
	}

	// Metoda na vytvorenie objektu FellowTraveller z elementu
	private FellowTraveller createFellowTraveller(Element fellowTravellerElement) {
		FellowTraveller ft = new FellowTraveller();

		ft.setType(fellowTravellerElement.getAttribute("type"));
		ft.setName(fellowTravellerElement.getAttribute("name"));

		return ft;
	}

	public static void main(String[] args) {
		DOMParser domParser = new DOMParser();
		TravelAgency ta = domParser.readXML(new File("TravelAgency.xml"));
		System.out.println(ta);
	}
}