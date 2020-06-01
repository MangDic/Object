package ch1;

public class Bag {
    private Long cash;
    private Ticket ticket;
    private Invitation invitation;

    public Bag(Long cash) {
        this(null, cash);
    }

    public Bag(Invitation invitation, Long cash) {
        this.invitation = invitation;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Long hold(Ticket ticket) {
        setTicket(ticket);
        if (hasInvitation()) {
            return 0L;
        }
        else {
            minusCash(ticket.getFee());
            return ticket.getFee();
        }
    }

    public void plusCash(Long getFee) {
        this.cash += getFee;
    }

    public void minusCash(Long getFee) {
        this.cash -= getFee;
    }

    private boolean hasInvitation() {
        return invitation != null;
    }

    public boolean hasTicket() {
        return ticket != null;
    }
}
