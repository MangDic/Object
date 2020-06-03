package ch1;

// 초대장 유무를 판단하여 그에 맞는 금액을 지불한다. 현금, 초대장, 티켓을 관리한다.
public class Bag {
    private Long cash;
    private Ticket ticket;
    private Invitation invitation;

    // 초대장을 가지고 있지 않을 경우
    public Bag(Long cash) {
        this(null, cash);
    }

    // 초대장을 가지고 있을 경우
    public Bag(Invitation invitation, Long cash) {
        this.invitation = invitation;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    /*초대장을 가지고 있다면 지불할 금액 0, 그렇지 않다면 티켓 가격 지불
    만약 지불 금액 판단을 Audience 에서 한다면 Bag 는 수동적인 존재로 책임이 없다
    아래처럼 Bag 에 접근하는 로직을 캡슐화해서 결합도를 낮추자!
    결과적으로 상태와 행위를 모두 가지는 응집도가 높은 클래스가 되었다.*/
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

    // 티켓 금액만큼 잔액 감소 ㅜㅜ
    public void minusCash(Long getFee) {
        this.cash -= getFee;
    }

    // 초대장을 가지고 있는지 판별
    private boolean hasInvitation() {
        return invitation != null;
    }

    public boolean hasTicket() {
        return ticket != null;
    }
}
