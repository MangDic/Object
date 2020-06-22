package ch2;

import java.time.LocalDateTime;

public class Screening {
    private Movie movie;
    private int sequent;
    private LocalDateTime whenScreened;

    public Screening(Movie movie, int sequent, LocalDateTime whenScreened) {
        this.movie = movie;
        this.sequent = sequent;
        this.whenScreened = whenScreened;
    }

    public LocalDateTime getStartTime() {
        return whenScreened;
    }

    public boolean isSequence(int sequent) {
        return this.sequent == sequent;
    }

    public Money getMovieFee() {
        return movie.getFee();
    }

    private Reservation reserve(Customer customer, int audienceCount) {
        return new Reservation(customer, this, calculateFee(audienceCount), audienceCount);
    }

    private Money calculateFee(final int audienceCount) {
        return movie.calculateMovieFee(this).times(audienceCount);
    }
}
