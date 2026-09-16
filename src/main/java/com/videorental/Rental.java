package com.videorental;

class Rental {
	private static Movie movie;
	private static int daysRented;

	public Rental(Movie movie, int daysRented) {
		this.movie = movie;
		this.daysRented = daysRented;
	}

	public static int getDaysRented() {
		return daysRented;
	}

	public static Movie getMovie() {
		return movie;
	}

	double getCharge() {
		return getChargeFor(daysRented);
	}
}