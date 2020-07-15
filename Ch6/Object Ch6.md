## 6장

###### 협력과 메시지

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



###### 인터페이스와 설계 품질

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



























