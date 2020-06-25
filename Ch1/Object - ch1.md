## 1장



###### 로버트 글래스

> 이론보다 실무가 먼저
>
> 실무가 어느정도 발전하고 난 뒤에야 이론이 서서히 모습을 갖추고, 해당 분야가 충분히 성숙해지는 시점에서 이론이 실무를 추월한다



###### 로버트 마틴

> 모듈이 가져야하는 세가지 기능(목적)
> (모듈 - 크기와 상관없이 클래스나 패키지, 라이브러리와 같이 프로그램을 구성하는 임의의 요소)
>
> - 실행 중에 제대로 동작하는 것
>   - 모든 모듈의 존재 이유!
> - 변경을 위해 존재 
>   - 대부분 모듈은 생명주기 동안 변경되기 때문에 간단한 작업만으로 변경이 가능해야 함. 변경하기 어려운 모듈은 제대로 동작하더라도 개선해야 함
>
> - 코드를 읽는 사람과 의사소통하는 것
>   - 모듈은 특별한 훈련 없이 개발자가 쉽게 읽고 이해가 가능해야 함. 의사소통할 수 없는 모듈은 개선



###### 응집도(cohesion)

- 밀접하게 연관된 작업만을 수행하고 연관성 없는 작업은 다른 객체에게 위임하는 객체를 가리켜 응집도가 높다고 말함
- 자신의 데이터를 스스로 처리하는 자율적인 객체를 만들면 결합도를 낮출 수 있으며, 응집도를 높일 수 있음



###### 책임의 이동

> 변경 전 극장의 enter()는 프로세스이고, 관객과 판매원, 가방, 판매소는 데이터. 
>
> 프로세스인 극장이 4개의 데이터에 의존하고 있음(Teater에 책임이 집중). 4개의 데이터 중 한 개라도 변경된다면 극장도 함께 변경되어야 함 - 절차지향 프로그래밍이 변경에 취약한 이유 예시 
>
> 해결 : 자신의 데이터를 스스로 처리하도록 프로세스의 적절한 단계를 Audience와 TicketSeller로 이동시키는 것

- 설계를 어렵게 만드는 것은 의존성

- 해결 방법은 불필요한 의존성을 제거하여 객체 사이의 결합도를 낮추는 것

  > 예제에서 결합도를 낮추기 위해 사용된 방법은 캡슐화
  >
  > ~~~ Audience
  > public class Audience {
  >     private Bag bag;
  > 
  >     public Audience(Bag bag) {
  >         this.bag = bag;
  >     }
  >     
  >     public Long buyTicket(Ticket ticket) {
  >         return bag.hold(ticket);
  >     }
  > }
  > ~~~
  >
  > - Bag의 구현을 캡슐화 시키고 Audience를 Bag의 구현이 아닌 인터페이스에만 의존하도록 수정



###### 절차지향(ProCedural Programming)

 - 프로세스와 데이터를 별도의 모듈에 위치시키는 방식
 
 - 절차지향적 설계는 프로세스가 필요한 모든 데이터에 의존하고 있으므로 변경에 취약
 


###### 객체지향(Object Oriented Programming)
- 데이터와 프로세스가 동일한 모듈 내부에 위치하도록 프로그래밍

- 흔히 데이터와 프로세스를 하나의 단위로 통합해 놓은 방식으로 표현

- 객체가 어떤 데이터를 가지느냐보다는 객체에 어떤 책임을 할당할 것인가에 초점

  > 변경에 유연하게 대응할 수 있는 코드 - 이해하기 쉬운 코드
  >
  > 단순히 데이터와 프로세스를 객체라는 덩어리 안으로 밀어 넣었다고 해서 변경하기 쉬운 설계를 얻는 것이 아니다. 
  >
  > 앱은 객체로 구성. 앱의 기능은 객체들 간의 상호작용을 통해 구현. 상호작용은 객체 간 주고 받는 메시지로 표현



###### Java의 일정하지 않은 개수의 파라미터

~~~ Test 예제
public class Test{
 
	public static void main(String[] args) {
            TestClass tc= new TestClass(); 
            tc.test("test1","test2","test3");
    }
}

class TestClass{
    public void test(String... strs) {
    	System.out.println(strs[0]);// test1
    	System.out.println(strs[1]); // test2
        
    }	
}
~~~



###### 다형성

~~~ Bag
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
}
~~~

- Bag은 초대장을 가지고 있는 경우와 초대장을 가지고 있지 않을 경우 각 각 다르게 처리할 수 있다.
- 이처럼 같은 메소드가 파라미터의 타입에 따라 다른 결과를 도출하는 것을 다형성이라 한다.



