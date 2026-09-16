package com.videorental;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Customer {
	private String name;
	private List<Rental> rentals = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Rental rental) {
		rentals.add(rental);
	}

	public String getName() {
		return name;
	}

	public String statement() {
		String result = getStatementHeader();
		result += getRentalLineReport();
		result += getStatementFooter();

		return result;
	}

	private String getStatementFooter() {
		return "Amount owed is " + getTotalAmount() + "\n" + "You earned " + getFrequentRenterPoints() + " frequent renter pointers";
	}

	private String getStatementHeader() {
		return "Rental Record for " + getName() + "\n";
	}

	private String getRentalLineReport() {
		String result = "";
		for ( Rental rental : rentals ) {
			result += "\t" +  rental.getCharge() + "(" + Rental.getMovie().getTitle() + ")" + "\n";
		}
		return result;
	}

	private int getFrequentRenterPoints() {
		int frequentRenterPoints = 0;
		for (Rental rental : rentals) {
			frequentRenterPoints += rental.getFrequentRenterPointsFor();
		}
		return frequentRenterPoints;
	}

	private double getTotalAmount() {
		double totalAmount = 0;
		for (Rental rental : rentals) {
			totalAmount += rental.getCharge();
		}
		return totalAmount;
	}

}