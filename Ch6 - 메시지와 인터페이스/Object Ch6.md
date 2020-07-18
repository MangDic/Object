## 6장

#### 협력과 메시지

- 클라이언트-서버 모델

  - 두 객체 사이의 협력 관계를 설명하기 위해 사용하는 전통적인 메타포

  - 클라이언트 : 협력 안에서 메시지를 전송하는 객체

  - 서버 : 메시지를 수신하는 객체

  - 협력은 클라이언트가 서버의 서비스를 요청하는 단방향 상호작용

  - Screening과 Movie

    - Screening은 Movie에 '가격을 계산하라'라는 메시지 전송 - 클라이언트
    - Movie는 가격을 계산하는 서비스를 제공 - 서버

  - Movie와 DiscountPolicy

    - Movie는 DiscountPolicy에 '할인 요금을 계산하라'라는 메시지 전송 - 클라이언트
    - DiscountPolicy는 할인 요금을 계산하는 서비스 제공 - 서버

  - 이처럼 객체는 협력에 참여하는 동안 클라이언트와 서버의 역할을 동시에 수행하는 것이 일반적

  - 협력의 관점에서 메시지

    - 객체가 수신하는 메시지의 집합
    - 외부의 객체에게 전송하는 메시지의 집합

    

- 메시지와 메시지 전송

  - 메시지 : 객체들이 협력하기 위해 사용할 수 있는 유일한 의사소통 수단

    - 오퍼레이션명
    - 인자
    - 여기에 메시지 수신자를 추가하면 메시지 전송

  - 메시지 전송(메시지 패싱) : 한 객체가 다른 객체에게 도움을 요청하는 행위

    - 오퍼레이션명, 인자, 메시지 수신자의 조합

  - 메시지 전송자 : 클라이언트

  - 메시지 수신자 : 서버

    ~~~java
    condition.isSatisfiedBy(screening);
    // condition : 수신자
    // isSatisfiedBy : 오퍼레이션명
    // screening : 인자
    ~~~

    ~~~objective-c
    [condition isSatisfiedBy: screening]
    ~~~

  

- 메시지와 메소드

  - 메소드 : 메시지를 수신했을 때 실제로 실행되는 함수 또는 프로시저

    - 기술적인 관점에서 객체 사이의 메시지 전송은 전통적인 방식의 함수 호출이나 프로시저 호출과는 다름

    - 전통적인 방식의 개발자는 어떤 코드가 실행될지를 정확하게 알고 있는 상황에서 함수 호출이나 프로시저 호출 구문 작성

      -> 코드의 의미가 컴파일 시점과 실행 시점에 동일

    - 객체는 메시지와 메소드라는 두 가지 서로 다른 개념을 실행 시점에 연결

      -> 컴파일 시점과 실행 시점의 의미가 달라질 수 있음

    - 실행 시점에 메시지와 메소드를 바인딩하는 메커니즘은 두 객체 사이의 결합도를 낮춤으로써 유연하고 확장 가능한 코드를 작성 가능하게 함

- 퍼블릭 인터페이스와 오퍼레이션

  - 퍼블릭 인터페이스 : 객체가 의사소통을 위해 외부에 공개하는 메시지의 집합

  - 오퍼레이션 : 프로그래밍 언어의 관점에서 퍼블릭 인터페이스에 포함된 메시지

    - 수행 가능한 어떤 행동에 대한 추상화
    - 즉, 객체가 다른 객체에게 제공하는 추상적인 서비스
    - 메시지가 전송자와 수신자 사이의 협력관계를 강조하는데 비해 오퍼레이션은 메시지를 수신하는 객체의 인터페이스를 강조
    - 내부의 구현 코드는 제외하고 단순히 메시지와 관련된 시그니처를 가리키는게 대부분
    - DiscountCondition 인터페이스에 정의된 isSatisfiedBy가 오퍼레이션에 해당

  - 메소드 : 메시지에 응답하기 위해 실행되는 코드 블록

    - 오퍼레이션의 구현

    - SequenceCondition과 PeriodCondition에 정의된 각각의 isSatisfiedBy는 실제 구현을 포함하기 때문에 메소드라 부름

      >UML은 공식적으로 오퍼레이션을 다음과 같이 정의한다.
      >
      >오퍼레이션이란 실행하기 위해 객체가 호출될 수 있는 변환이나 정의에 관한 명세. UML 용어로 말하자면, 인터페이스의 각 요소는 오펴레이션. 
      >
      >오퍼레이션은 구현이 아닌 추상화. 반면 UML의 메소드는 오퍼레이션을 구현한 것. 즉, 메소드는 오펴레이션에 대한 구현

    - 프로그래밍 언어의 관점에서 객체가 다른 객체에게 메시지를 전송하면 런타임 시스템은 메시지 전송을 오퍼레이션 호출로 해석

    - 메시지를 수신한 객체의 실제 타입을 기반으로 적절한 메소드를 실행

    - 따라서 퍼블릭 인터페이스와 메시지의 관점에서 보면 '메소드 호출'보다는 '오퍼레이션 호출'이라는 용어를 사용하는 것이 더 적절

    Q. 다형성을 도와주는(?) 과정(파라미터에 따라 적절한 메소드 실행)을 오퍼레이션 호출을 통해 하는 것입니까? 

- 시그니처

  - 오퍼레이션(또는 메소드)의 이름과 파라미터 목록을 합친 것
  - 오펴레이션은 실행 코드 없이 시그니처만을 정의한 것
  - 메소드는 오퍼레이션 + 구현
  - 일반적으로 메시지를 수신하면 오퍼레이션은 시그니처와 동일한 메소드 실행
  - 하나의 오퍼레이션에 대해 오직 하나의 메소드만 존재하면 굳이 오퍼레이션과 메소드를 구분할 필요가 없음 
  - 오퍼레이션 관점에서 다형성이란 동일한 오퍼레이션 호출에 대해 서로 다른 메소드들이 실행되는 것

- 객체가 수신할 수 있는 메시지가 객체의 퍼블릭 인터페이스와 그 안에 포함될 오퍼레이션을 결정



#### 인터페이스와 설계 품질

- 좋은 인터페이스는 최소한의 인터페이스와 추상적인 인터페이스라는 조건을 만족해야 함

  - 최소한 : 꼭 필요한 오퍼레이션만을 인터페이스에 추가
  - 추상적인 : 어떻게 수행하는지가 아닌 무엇을 하는지를 포함

- 책임 주도 설계 

  - 메시지를 먼저 선택하므로 협력과 무관한 오퍼레이션이 인터페이스에 스며드는 것을 방지 -> 최소한의
- 객체가 메시지를 선택하는 것이 아닌 메시지가 객체를 선택하게 함으로써 클라이언트의 의도를 메시지에 표현 가능 -> 추상적인
  

  
- 디미터 법칙

  - 협력하는 객체의 내부 구조에 대한 결합으로 인해 발생하는 설계 문제를 해결하기 위해 제안된 원칙

  - 객체의 내부 구조에 강하게 결합되지 않도록 협력 경로를 제한하라는 것

    > "낯선 자에게 말하지 말라"
    >
    > "오직 인접한 이웃하고만 말하라"
    >
    > 자바나 C# 등 '도트(.)'를 이용해 메시지 전송을 표현하는 언어에서는 "오직 하나의 도트만 사용하라" 라는 말로 요약되기도 함

  - 아래 조건을 만족하는 인스턴스에만 메시지를 전송하도록 프로그래밍 하라

    - this 객체
    - 메소드의 매개변수
    - this의 속성
    - this의 속성인 컬렉션의 요소

    ~~~ java
    public class ReservationAgency {
        public Reservation reserve(Screening screening, ... ) {
            // ReservationAgency는 메소드의 인자로 전달된 Screening 인스턴스에게만 메시지 전송. Screening내부에 대한 어떤 정보도 알지 못함
            Money fee = screening.calculateFee(...);
            return new Reservation(...);
        }
    }
    ~~~

    - ReservationAgency가 Screening의 내부 구조에 결합돼 있지 않기 때문에 Screening의 내부 구현을 변경할 때 ReservationAgency를 함께 변경할 필요 없음

  - 디비터 법칙을 따르면 부끄럼타는 코드(shy code) 작성 가능

    - 불필요한 어떤 것도 다른 객체에게 보여주지 않음
    - 메시지 전송자는 메시지 수신자의 내부 구현에 결합되지 않음
    - 따라서 클라이언트와 서버 사이에 낮은 결합도 유지 가능

  - 디미터 법칙 위반

    - 흔히 기차 충돌이라 부름

    - 클래스의 내부 구현이 외부로 노출될 때 나타나는 전형적인 형태로 메시지 전송자는 메시지 수신자의 내부 정보를 자세히 알게 됨 -> 캡슐화 위반 및 높은 결합도

      ~~~ java
      // 기차 충돌 코드
      screening.getMovie().getDiscountCondisitons();
      // 개선
      screening.calculateFee(audienceCount);
      ~~~

    

- 묻지 말고 시켜라

  - 디미터 법칙은 훌륭한 메시지는 객체의 상태에 관해 묻지 말고 원하는 것을 시킨다는 사실 강조
  - 메시지 전송자는 메시지 수신자의 상태를 기반으로 결정을 내린 후 메시지 수신자의 상태를 바꿔서는 안됨
    - 객체의 외부에서 해당 객체의 상태를 기반으로 결정을 내리는 것은 객체의 캡슐화 위반
  - 이 원칙에 따라 메시지를 결정하다 보면 자연스럽게 정보 전문가에게 책임을 할당하고, 높은 응집도를 가진 클래스를 얻을 확률이 높아짐
  - 상태를 묻는 오퍼레이션을 행동을 요청하는 오퍼레이션으로 대체함으로써 인터페이스를 향상시킬 것
  - 인터페이스는 객체가 어떻게 하는지가 아닌 무엇을 하는지 서술해야 함

  

- 의도를 드러내는 인터페이스

  - 켄트 벡은 메소드 명명하는 두 가지 방법 소개

    - 메소드가 작업을 어떻게 수행하는지 나타내도록 이름 짓는 것

      ~~~ java
      public class PeriodCondition {
          public boolean isSatisfiedByPeriod(...) {...}
      }
      
      public class SequenceCondition {
          public boolean isSatisfiedBySequence(...) {...}
      }
      ~~~

      - 이 방법이 좋지 않은 두 가지 이유
        - 메소드에 대해 제대로 커뮤니케이션하지 못함. 메소드의 이름이 다르기 때문에 동일한 수행을 하고 있음에도 불구하고, 내부 구현을 정확하게 이해하지 못한다면 동일한 수행을 한다는 것을 클라이언트 관점에서 알아채기 어려움
        - 캡슐화를 위반. 할인 여부를 판단하는 방법이 바뀐다면 메소드 이름 역시 변경. 메소드 이름을 변경한다는 것은 메시지를 전송하는 클라이언트의 코드도 함께 변경해야 함을 의미

    - '어떻게'가 아닌 '무엇'을 하는지 드러내도록 이름 짓는 것

      - 코드를 읽고 이해하기 쉽게 만들뿐만 아니라 유연한 코드 설계 가능

      - '어떻게' 수행하는지를 드러내는 이름이란 메소드의 내부 구현을 설명하는 이름

        -> 결과적으로 협력을 설계하기 시작하는 이른 시기부터 클래스 내부 구현에 관해 고민하게 됨

      - '무엇을' 하는지를 드러내도록 메소드의 이름을 짓기 위해서는 객체가 협력 안에서 수행해야 하는 책임에 관해 고민하게 됨

        -> 외부의 객체가 메시지를 전송하는 목적을 먼저 생각하게 되며, 결과적으로 협력하는 클라이언트의 의도에 부합하도록 메소드의 이름을 짓게 됨

      ~~~java
      // 클라이언트 관점에서 두 메소드가 동일한 목적을 가진다는 것을 확인 가능
      // 두 메소드는 동일한 메시지를 서로 다른 방법으로 처리하기 때문에 서로 대체 가능
      public class PeriodCondition {
          public boolean isSatisfiedBy(...) {...}
      }
      
      public class SequenceCondition {
          public boolean isSatisfiedBy(...) {...}
      }
      ~~~

      - 하지만 정적 타이핑 언어에서 단순히 메소드의 이름이 같다고 해서 동일한 메시지를 처리할 수 있는 것은 아님
      - 클라이언트가 두 메소드를 가진 객체를 동일한 타입으로 간주할 수 있도록 동일한 타입 계층으로 묶어야 함 -> 인터페이스 사용

      ~~~java
      public interface DiscountCondition {
          boolean isSatisfiedBy(...);
      }
      ~~~

      ~~~ java
      // DiscountCondition을 구현함으로써 두 메소드가 동일한 메시지를 처리한다는 것을 알 수 있음
      public class PeriodCondition implements DiscountCondition {
          public boolean isSatisfiedBy(...) {...}
      }
      
      public class SequenceCondition implements DiscountCondition {
          public boolean isSatisfiedBy(...) {...}
      }
      ~~~

      - 이처럼 무엇을 하느냐에 따라 메소드의 이름을 짓는 패턴을 '의도를 드러내는 선택자'(Intention Revealing Selector)라고 부름

        >매우 다른 두 번째 구현을 상상하라. 그러고는 해당 메소드에 동일한 이름을 붙인다고 상상하라. 그렇게 하면 아마도 그 순간에 할 수 있는 한 가장 추상적인 이름을 메소드에 붙일 것이다.
        >
        >Q. 매우 다른 두 번째 구현이 뭐죠? 

  - 의도를 드러내는 선택자의 확장한 의도를 드러내는 인터페이스

    - 구현과 관련된 모든 정보를 캡슐화하고 퍼블릭 인터페이스에는 협력과 관련된 의도만을 표현

      > 수행 방법에 관해서는 언급하지 말고, 결과와 목적만을 포함하도록 클래스와 오퍼레이션의 이름을 부여하라. 이렇게 하면 클라이언트 개발자가 내부를 이해해야 할 필요성이 줄어든다.
      >
      > ...
      >
      > 방정식을 푸는 방법을 제시하지 말고 공식으로 표현하라.

      

- 함께 모으기

  - 디미터 법칙을 위반하는 티켓 판매 도메인

    ~~~ java
    public class Theater {
        ...
        public void enter(Audience audience) {
            ...
            // Theater가 audience와 ticketSeller 내부에 포함된 객체에 접근
            // audience 뿐만 아니라 audience 내부의 Bag에도 메시지를 전송
            // 결과적으로 Theater는 Audience의 퍼블릭 인터페이스 뿐만 아니라 내부 구조에도 결합
            Ticket ticket = ticketSeller.getTicketOffice().getTicket();
            audience.getBag().minusAmount(ticket.getFee());
        }
    }
    ~~~

    - 디미터 법칙을 위반한 코드는 사용하기도 어려움

      - 클라이언트 객체의 개발자는 Audience의 퍼블릭 인터페이스뿐만 아니라 그 내부 구조까지도 알고 있어야 하기 때문

      - TicketSeller의 경우에, Theater는 TicketSeller가 getTicketOffice 메시지를 수신할 수 있다는 것 뿐만 아니라 내부에 TicketOffice를 포함하고 있다는 사실까지도 알고 있어야 함.

        게다가 Theater는 반환된 TicketOffice가 getTicket 메시지를 수신할 수 있으며, 이 메소드가 반환하는 Ticket 인스턴스가 getFee 메시지를 이해할 수 있다는 사실도 알고있어야 함.

        -> 매우 심각

      - 이런 코드를 수정하는 일반적인 방법은 Audience와 TicketSeller의 내부 구조를 묻는 대신 직접 자신의 책임을 수행하도록 시키는 것

        

  - 묻지 말고 시켜라 적용

    - Theater가 TicketSeller에게 시키고 싶으 일은 Audience가 Ticket을 가지도록 만드는 것.

    - TicketSeller에 setTicket 메소드를 추가하고 enter 메소드의 로직을 setTicket 메소드로 옮길 것.

      ~~~java
      public class TicketSeller {
          public void setTicket(Audience audience) {
              if(audience.getBag().hasInvitation()) {
                  Ticket ticket = ticketOffice.getTicket();
                  audience.getBag().setTicket(ticket);
              }
              ...
          }
      }
      ~~~

    - 수정된 Theater

      ~~~ java
      public class Theater {
          
          public void enter(Audience audience) {
              ticketSeller.setTicket(audience);
          }
      }
      ~~~

    - TicketSeller가 원하는 것은 Audience가 Ticket을 보유하도록 만드는 것. Audience에게 setTicket을 추가하고 스스로 티켓을 가지도록 수정

      ~~~java
      public class Audience {
          public Long setTicket(Ticket ticket) {
              if (bag.hasInvitation()) {
                  bag.setTicket(ticket);
                  return 0L;
              }
              ...
          }
      }
      // Bag에게 원하는 일을 시키기 전에 hasInvitation 메소드를 이용하기 때문에 디미터 법칙 위반
      ~~~

      ~~~java
      public class TicketSeller {
          public void setTicket(Audience audience) {
              ticketOffice.plusAmount(
              audience.setTicket(ticketOffice.getTicket)));
              ...
          }
      }
      // 속성으로 포함하고 있는 TicketOffice의 인스턴스와 인자로 전달된 Audience에게만 메시지를 전송하므로 디미터 법칙을 준수 !!
      ~~~

    - Audience의 setTicket 메소드 구현을 Bag으로 이동

      ~~~java
      public class Bag {
          public Long setTicket(Ticket ticket) {
              if(hasInvitation) {
                  this.ticket = ticket;
                  return 0L;
              }
              ...
          }
      }
      ~~~

      ~~~java
      public class Audience {
          public Long setTicket(Ticket ticket) {
              return bag.setTicket(ticket);
          }
      }
      // 이제 디미터 법칙 준수 !!
      ~~~

  - 결과적으로 Audience 스스로 자신의 상태를 제어하게 됨. 스스로 관리하고 결정하는 자율적인 객체가 되었다 !

  

  - 인터페이스에 의도를 드러내자

    - 현재의 인터페이스는 클라이언트의 의도를 명확하게 드러내지 못함
    - 메소드 개발자는 TickeetSeller, Audience, Bag에 존재하는 setTicket 메소드는 의도가 서로 다름을 알고 있지만, 클라이언트 개발자는 혼란스럽다.
    - Theater가 TicketSeller에게 setTicket 메시지를 통해 얻고 싶었던 것은 Audience에게 티켓을 판매하는 것 -> setTicket 보다는 sellTo가 의도를 더 명확하게 표현하는 메시지
    - TicketSeller가 Audience에게 setTicket 메시지를 통해 얻고 싶었던 것은 Audience가 티켓을 사도록 만드는 것 -> buy가 더 명확한 메시지
    - 마찬가지로 Audience가 Bag에 의도한 것은 티켓을 저장하도록 만드는 것

    ~~~java
    public class TicketSeller {
        public void sellTo(Audience audience) { ... } 
    }
    
    public class Audience {
        public Long buy(Ticket ticket) { ... } 
    }
    
    public class Bag {
        public void hold(Ticket ticket) { ... } 
    }
    ~~~

    - 이처럼 객체 자신이 아닌 클라이언트의 의도를 표현하는 이름을 가져야 함



#### 원칙의 함정

- 디미터 법칙은 하나의 도트(.)를 강제하는 규칙이 아니다

  ~~~java
  IntStream.of(1, 15, 20, 3, 9).filter(x -> x > 10).distinct().count();
  ~~~

  - 위 코드의 of, filter, distinct 메소드는 모두 IntStream이라는 동일한 클래스의 인스턴스 반환
  - 즉, 이들은 IntStream의 인스턴스를 또 다른 IntStream의 인스턴스로 변환 -> 디미터 법칙 위반 x
  - 디미터 법칙은 객체 내부 구조가 외부로 노출되는 경우로 한정
    - IntStream의 내부 구조 노출 없이 단지 IntStream을 또 다른 IntStream로 변환할 뿐 캡슐화는 유지
  - 따라서 하나 이상의 도트(.)를 사용할 때 이 코드가 객체의 내부 구조를 노출하고 있는지 확인하라

- 결합도와 응집도의 충돌

  ~~~java
  public class Theater {
      
      public void enter(Audience audience) {
          // Theater는 Audience 내부에 포함된 Bag에 대해 질문 후 결과를 이용해 Bag 상태 변경
          // 질문, 판단, 상태를 변경하는 코드를 Audience로 옮겨서 문제를 해결하자
          if(audience.getBag().hasInvitation) {
          	Ticket ticket = ticketSeller.setTicketOffice().getTicket();
          	audience.getBag().setTicket(ticket);    
          }
          else {
          	Ticket ticket = ticketSeller.getTicketOffice().getTicket();
          	audience.getBag().minusAmount(ticket.getFee());
              ticketSeller.getTicketOffice().plusAmount(ticket.getFee());
              audience.getBag().setTicket(ticket); 
          }
      }
  }
  ~~~

  ~~~java
  public class Audience {
      
      // Audience에 위임 메소드 추가
      // 결과적으로 상태와 함께 상태를 조작하는 행동도 포함하기 때문에 응집도가 높아짐
      public Long buy(Ticket ticket) {
          if(bag.hasInvitation) {
              bag.setTicket(ticket);
              return 0L;
          }
          else {
              bag.setTicket(ticket);
              bag.minusAmount(ticket.getFee());
              return ticket.getFee();
          }
      }
  }
  ~~~

  - 이처럼 위임 메소드를 통해 객체의 내부 구조를 감추는 것은 협력에 참여하는 객체들의 결합도를 낮출 수 있는 동시에 객체의 응집도를 높일 수 있는 가장 효과적인 방법!

- 항상 디미터 법칙, '묻지 말고 시켜라'를 적용한 코드가 옳을까?

  - 그 대상이 객체라면 내부 구조를 숨겨야 하므로 디비터 법칙을 따르는 것이 좋음

  - 하지만 대상이 자료 구조라면 당연히 내부를 노출해야 하므로 디미터 법칙을 적용할 필요가 없음

    ~~~ java
    // PeriodCondition의 코드 중 screening의 내부 상태를 가져와 사용하기 때문에 캡슐화를 위반한다 생각 했을 때 
    
    // isDiscountable()을 Screening으로 이동시키고 PeriodCondtion에서 호출되도록 변경
    public class Screening {
        public boolean isDiscountable(...) {
            return ...
        } 
    }
    
    public class PeriodCondition implements DiscountCondition {
        public boolean isSatisfiedBy(Screening screening) {
            return screening.isDiscountable(...);
        }
    }
    ~~~

    - 수정 후, Screening은 기간에 따른 할인 조건을 판단하는 책임을 가지게 됨.

      -> 본질적인 책임은 영화를 예매하는 것으로, 결과적으로 응집도가 낮아짐

    - 또한 PeriodCondition의 인스턴스 변수 목록이 변경될 경우 Screening이 영향을 받게 됨

      -> 결합도 역시 높아짐

    ~~~java
    for(Movie each : movies) {
        total += each.getFee();
    }
    // 이 코드에서 Movie에게 묻지 않고 movies 컬렉션에 포함된 전체 영화의 가격을 계산하는 방법이 있을까? No
    
    // Q. 그 이유가 movies는 자료 구조이기 때문인가요? 
    ~~~

  - 경우에 따라 다르므로 잘 판단하여 사용!



#### 명령-쿼리 분리 원칙

- 퍼블릭 인터페이스에 오퍼레이션을 정의할 때 참고할 수 있는 지침 제공
- 루틴 : 어떤 절차를 묶어 호출 가능하도록 이름을 부여한 기능 모듈
  - 프로시저와 함수로 구분
  - 프로시저 : 정해진 절차에 따라 내부의 상태를 변경하는 루틴의 한 종류
  - 프로시저는 부수효과를 발생시킬 수 있지만 값을 반환할 수 없음
  - 함수는 값을 반환할 수 있지만 부수효과를 발생시킬 수 없음
  - 부수 효과 : 함수 내의 실행으로 인해 함수 외부가 영향을 받는 것
- 명령 : 객체의 상태를 수정하는 오퍼레이션 // 프로시저
- 쿼리 : 객체와 관련된 정보를 반환하는 오퍼레이션 // 함수
- 명령과 쿼리를 분리하기 위한 규칙
  - 객체의 상태를 변경하는 명령은 반환값을 가질 수 없음 (부수효과 발생 o, 리턴 가능 x)
  - 객체의 정보를 반환하는 쿼리는 상태를 변경할 수 없음 (부수효과 발생 x, 리턴 가능 o)

- 명령-쿼리 인터페이스 : 명령-쿼리 분리 원칙에 따라 작성된 객체의 인터페이스

- 기계 메타포

  - 인터페이스는 두 가지 형태의 버튼을 가짐

  - 둥근 버튼 : 기계의 현재 상태를 출력하지만 상태는 변경하지 않음 -> 쿼리 메소드

  - 네모 버튼 : 기계의 상태를 변경하지만 변경된 상태의 어떠한 정보도 외부로 공개하지 않음 -> 명령 메소드

    - insert 버튼을 눌렀다 -> 정보 삽입 -> 내부 변경 -> 부수효과 발생 -> 보여지는 값은 없음 -> 명령 !

    - first 버튼을 눌렀다 -> 데이터가 바뀐 것이 없으므로 계속 눌러도 같은 값 -> 부수효과 발생 x -> 보여지는 값이 있음 -> 쿼리 !

      

- 반복 일정의 명령과 쿼리 분리하기

  ~~~java
  public class Event {
      
      private String subject;
      private LocalDateTime from;
      private Duration duration;
      
      ...
      
      // 생성된 스케쥴이 반복 일정에 부합하는지에 따라 true, false 만을 반환해 주면 됨 (쿼리)
      public boolean isSatisfied(RecurringSchedule schedule) {
          if(반복 일정과 생성된 스케쥴의 요일, 시작 시간, 소요 시간 중 하나라도 맞지 않는다면) {
              // 요놈봐라? 쿼리에 상태를 수정시키는 명령이 숨어있다 !!
              reschedule(schedule);
              // 명령 처리 후 false를 반환 -> 즉, 상태가 변한 후에 값을 반환해버렸다
              return false;
          }
          return true;
      }
      
      private void reschedule(RecurringSchedule schedule) {
          
          from = LocalDateTime.of(from.toLocalDate().plusDays(dayDistance(schedule)),
                                  schedule.getFrim());
          duration = schedule.getDuration();
      }
      
      // 반복 일정과 생성된 스케쥴의 날짜 차이
      private long daysDistance(RecurringSchedule schedule) {
          return schedule.getDayOfWeek().getValue() = from.getDayOfWeek().getValue();
      }
  }
  ~~~

  - isSatisfied()가 명령, 쿼리 두 가지의 역할을 동시엥 수행하고 있었기에 버그가 발생했던 것
    - Event가 RecurringSchedule의 조건에 부합하는지를 판단 후 true, false 반환 - 쿼리
    - Event가 RecurringSchedule의 조건에 부합하지 않을 경우 Event의 상태를 조건에 부합하도록 변경 - 명령

  ~~~java
  public class Event {
       
      // reschedule(schedule);를 제거함으로써 순수한 쿼리가 됨
      public boolean isSatisfied(RecurringSchedule schedule) {
          if(반복 일정과 생성된 스케쥴의 요일, 시작 시간, 소요 시간 중 하나라도 맞지 않는다면) {
              reschedule(schedule);
              return false;
          }
          return true;
      }
      
      // public으로 바꾸어 인터페이스만 보고도 isSatisfied는 쿼리, reschedule은 명령이라는 것을 한번에 알 수 있게 됨
      public void reschedule(RecurringSchedule schedule) {
          
          from = LocalDateTime.of(from.toLocalDate().plusDays(dayDistance(schedule)),
                                  schedule.getFrim());
          duration = schedule.getDuration();
      }
     
      private long daysDistance(RecurringSchedule schedule) {
          return schedule.getDayOfWeek().getValue() = from.getDayOfWeek().getValue();
      }
  }
  
  // 조건을 만족시키지 않을 경우 Event를 사용하는 쪽에서 reschedule 메소드를 호출할지 결정
  if(!event.isSatisfied(schedule)) {
      event.reschedule(schedule);
  }
  ~~~

- 명령-쿼리 분리와 참조 투명성

  - 참조 투명성 : 어떤 표현식 e가 있을 때 e의 값으로 e가 나타나는 모든 위치를 교체하더라도 결과가 달라지지 않는 특성

    ~~~Math
    f(1) + f(1) = 6
    f(1) * 2 = 6
    f(1) - 1 = 2
    
    여기서 f(1) 값은 3 이라는 것을 알 수 있고, f(1)을 모두 3으로 바꿔도 값이 동일하다.
    f(1) = e, 3 = e의 값
    이처럼 참조 투명성은 식을 값으로 치환하는 방법을 통해 결과를 쉽게 계산 가능
    이렇게 말할 수 있는 이유는 f(1) = 3라는 것이 변하지 않기 때문 (불변성)
    즉, 불변성은 부수효과가 발생하지 않는다는 말(참조 투명성을 만족시킨다)과 동일 !
    
    또한 참조 투명성의 장점은 식의 순서를 변경하더라도 결과가 달라지지 않는다는 점
    ~~~

  - 참조 투명성의 장점

    - 모든 함수를 이미 알고 있는 하나의 결과값으로 대체할 수 있기 때문에 식을 쉽게 계산 가능
    - 모든 곳에서 함수의 결과 값이 동일하기 때문에 식의 순서를 변경하더라도 각 식의 결과는 달라지지 않음

  - 객체지향 패러다임은 객체의 상태 변경이라는 부수효과를 기반으로 하기 때문에 참조 투명성은 예외에 가까움

  - 그러므로 명령과 쿼리를 분리함으로써 명령형 언어의 틀 안에서 참조 투명성(referntal transparency)의 장점을 제한적이나마 누릴 수 있게 됨

  - 명령형 프로그래밍 : 부수효과를 기반으로 하는 프로그래밍 방식

  - 함수형 프로그래밍 : 부수효과가 존재하지 않는 수학적인 함수에 기반 

    -> 참조 투명성의 장점 극대화, 명령형보다 실행 결과를 이해하고 예측하기 더 쉬움

    

- 책임에 초점을 맞춰라

  - 메시지를 먼저 선택하는 방식의 긍정적인 영향

    - 메시지를 먼저 결정하면 두 객체 사이의 구조적인 결합도를 낮출 수 있음.

      -> 의도적으로 디미터 법칙을 위반할 위험 최소화

    - 클라이언트 관점에서 메시지를 선택하기 때문에 필요한 정보를 물을 필요 없이 메시지를 전송

      -> '묻지 말고 시켜라' 스타일에 따라 협력을 구조화하게 됨

    - 메시지를 먼저 선택한다는 것은 메시지를 전송하는 클라이언트 관점에서 메시지의 이름을 정한다는 것

      -> 그 이름에는 클라이언트가 무엇을 원하는지 포함 - 의도를 드러내는 인터페이스

    - 메시지를 먼저 선택하면, 협력 속에서 객체의 상태를 예측하고 이해하기 쉽게 만들기 위한 방법을 고민하게 됨

      -> 예측 가능한 협력을 만들기 위해 명령-쿼리를 분리하게 됨

  - 계약에 의한 설계(Design by Contract, DBC)

    - 실행 시점에 필요한 구체적인 제약이나 조건을 명확하게 표현할 수 없기 때문에 제안된 개념
    - 즉, 어떤 조건일 때 오퍼레이션을 호출할 수 있고 어떤 조건일 때 결과를 반환 받을 수 없는지 표현 불가하기 때문에 이를 해결하기 위한 개념
    - 프로그램 모듈들의 책임을 문서화하는데 초점을 맞춤
    - 각각의 모듈이 가져야 하는 기능만큼만 동작하도록 함
    - 선행조건 : 모듈을 호출하기 전 참이어야 하는 것
    - 후행조건 : 모듈이 자기가 할 것이라고 보장하는 것
    - 불변식 : 호출자의 입장에서 이 조건이 언제나 참이라고 보장하는 것

    ~~~java
    // 선행조건 : 인자로 받아오는 name이 null값이 되어서는 안됨
    // 후행조건 : 모듈이 실행되고 나서 Person 클래스의 name이 제대로 변경되는 것
    
    class Person{
    	private String name;
    	pulbic void setName(final String name){
    		this.name = name;
    	}
    
    	public String getName(){
    		return this.name;
    	}
    }
    
    //setName 메소드에 대한 DBC를 아래와 같이 구현
    ...
    	/**
    	 *  @pre    name != null
    	 *  @post   getName() == name
    	 */
    	pulbic void setName(final String name){
    		this.name = name;
    	}
    ...
    ~~~

    [출처](https://kevinx64.net/198)

    



















