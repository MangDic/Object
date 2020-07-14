package ch1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TicketOffice {

    private Long amount;
    private List<Ticket> tickets;

    // 티켓 판매소는 티켓들과 금액 보유
    public TicketOffice(Long amount, Ticket ... tickets) {
        this.amount = amount;
        this.tickets.addAll(Arrays.asList(tickets));
    }

    /*지불 금액 판단 후 그 만큼 판매소의 잔액 증가
    티켓 판매원은 관객의 가방에 대해 접근할 수 없다. 은닉!*/
    public void sellTicketTo(Audience audience) {
        plusAmount(audience.buyTicket(getTicket()));
    }

    public void plusAmount(Long amount) {
        this.amount += amount;
    }

    // 판매 후 티켓을 넘겨줌
    public Ticket getTicket() {
        return tickets.remove(0);
    }
}
