package parser;

public class Consultant {

	private String specialization;
	private String name;

	public Consultant() {
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Consultants [specialization: " + specialization + ", name: " + name + "]";
	}

}
