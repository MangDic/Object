package ch1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TicketOffice {

    private Long amount;
    private List<Ticket> tickets;

    public TicketOffice(Long amount, Ticket ... tickets) {
        this.amount = amount;
        this.tickets.addAll(Arrays.asList(tickets));
    }

    public void sellTicketTo(Audience audience) {
        plusAmount(audience.buyTicket(getTicket()));
    }

    public void plusAmount(Long amount) {
        this.amount += amount;
    }

    public Ticket getTicket() {
        return tickets.remove(0);
    }
}
