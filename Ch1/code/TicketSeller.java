package ch1;

// 티켓 판매원 : 관객에게 티켓을 판매한다.
public class TicketSeller {

    private TicketOffice ticketOffice;

    public TicketSeller(TicketOffice ticketOffice) {
        this.ticketOffice = ticketOffice;
    }

    // 극장은 티켓 판매소가 금액을 받아서 티켓을 빼준다는 것을 모른다.
    public void sellTo(Audience audience) {
        ticketOffice.sellTicketTo(audience);
    }

}
