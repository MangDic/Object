package ch1;

// 관객 : 티켓을 구매한다.
public class Audience {
    private Bag bag;

    public Audience(Bag bag) {
        this.bag = bag;
    }

    // bag.hold 를 통해 지불 금액 판단 (초대장 o : 0L , 초대장 x : fee)
    public Long buyTicket(Ticket ticket) {

        return bag.hold(ticket);
    }
}
