package ch1;

// 관객을 입장시킨다.
public class Theater {

    private TicketSeller ticketSeller;

    public Theater(TicketSeller ticketSeller) {
        this.ticketSeller = ticketSeller;
    }

    public void enter(Audience audience) {

        // 극장은 판매소에 접근할 수 없다. TicketSeller 는 극장이 TicketOffice 에 접근하지 못하게 은닉
        ticketSeller.sellTo(audience);
        System.out.println("입장");
    }
}
