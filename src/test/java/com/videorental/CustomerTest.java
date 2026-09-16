package com.videorental;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CustomerTest {
    public static final String NAME = "NAME_NOT_IMPORTANT";
    public static final String TITLE = "TITLE_NOT_IMPORTANT";

    Customer customer = new Customer(NAME);

    private Rental createRentalFor(int priceCode, int daysRented) {
        Movie movie = getMovie(priceCode);
        return new Rental(movie, daysRented);
    }

    private Movie getMovie(int priceCode) {
        switch (priceCode) {
            case Movie.REGULAR:
                return new RegularMovie(TITLE);
            case Movie.NEW_RELEASE:
                return new NewReleaseMovie(TITLE);
            case Movie.CHILDRENS:
                return new ChildrenMovie(TITLE);
            default:
                return null;
        }
    }

    // 1. Customer 생성 테스트
    @Test
    public void returnNewCustomer() {
        assertThat(customer).isNotNull();
    }

    // 2. Movie를 Rental하지 않은 경우 테스트
    @Test
    public void statementForNoRental() {
        // assert
        assertThat(customer.statement()).isEqualTo("Rental Record for NAME_NOT_IMPORTANT\n"
                + "Amount owed is 0.0\n"
                + "You earned 0 frequent renter pointers");
    }

    // 3. Amount 계산 로직 테스트 : Regular movie
    // - if 조건 분기 경계값으로 테스트
    @Test
    public void statementForRegularMovieRentalForLessThan3Days() {

        // arrange
        customer.addRental(createRentalFor(Movie.REGULAR, 2));

        // assert
        assertThat(customer.statement()).isEqualTo("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t2.0(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 2.0\n"
                + "You earned 1 frequent renter pointers");
    }


    // 4. Amount 계산 로직 테스트 : Regular movie
    // - if 조건 분기 경계값으로 테스트
    @Test
    public void statementForRegularMovieRentalForMoreThan2Days() {

        // arrange
        customer.addRental(createRentalFor(Movie.REGULAR, 3));

        // assert
        assertThat(customer.statement()).isEqualTo("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t3.5(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 3.5\n"
                + "You earned 1 frequent renter pointers");
    }

    // 5. Amount 계산 로직 테스트 : New release movie
    @Test
    public void statementForNewReleaseMovie() {

        // arrange
        customer.addRental(createRentalFor(Movie.NEW_RELEASE, 1));

        // assert
        assertThat(customer.statement()).isEqualTo("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t3.0(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 3.0\n"
                + "You earned 1 frequent renter pointers");
    }

    // 6. Amount 계산 로직 테스트 : Childrens movie (1/2)
    // - if 조건 분기 경계값으로 테스트
    @Test
    public void statementForChildrensMovieRentalMoreThan3Days() {

        // arrange
        customer.addRental(createRentalFor(Movie.CHILDRENS, 4));

        // assert
        assertThat(customer.statement()).isEqualTo("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t3.0(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 3.0\n"
                + "You earned 1 frequent renter pointers");
    }

    // 7. Amount 계산 로직 테스트 : Childrens movie
    // - if 조건 분기 경곗값으로 테스트
    @Test
    public void statementForChildrensMovieRentalLessThan4Days() {

        // arrange
        customer.addRental(createRentalFor(Movie.CHILDRENS, 3));

        // assert
        assertThat(customer.statement()).isEqualTo("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t1.5(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 1.5\n"
                + "You earned 1 frequent renter pointers");
    }

    // 8. Frequent renter points 계산 로직
    @Test
    public void statementForNewReleaseMovieRentalMoreThan1Day() {

        // arrange
        customer.addRental(createRentalFor(Movie.NEW_RELEASE, 2));

        // assert
        assertThat(customer.statement()).isEqualTo("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t6.0(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 6.0\n"
                + "You earned 2 frequent renter pointers");
    }
}
