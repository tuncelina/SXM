package parser;

import java.util.ArrayList;
import java.util.List;

public class Trip {

	private String title;
	private String date;
	private String duration;
	private String discount;
	private String way;
	private List<FellowTraveller> fellowTravellers = new ArrayList<>();

	public Trip() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getWay() {
		return way;
	}

	public void setWay(String way) {
		this.way = way;
	}

	public List<FellowTraveller> getFellowTravellers() {
		return fellowTravellers;
	}

	public void setFellowTravellers(List<FellowTraveller> fellowTravellers) {
		this.fellowTravellers = fellowTravellers;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Trip [title: " + title + ", date: " + date + ", duration: " + duration + ", discount: " + discount
				+ ", way: " + way);
		if (fellowTravellers.size() > 0) {
			sb.append("\n\t\t\tFellow Travellers:\n");
			for (int i = 0; i < fellowTravellers.size(); i++) {
				sb.append("\t\t\t\t" + fellowTravellers.get(i));
				if (i < fellowTravellers.size() - 1) {
					sb.append("\n");
				}
			}
		} else {
			sb.append("\n\t\t\tFellow Travellers: No fellow travellers\n");
		}
		return sb.toString();
	}

}
