## 4장



###### 3장에서는...

- 객체지향 설계의 핵심은 역할, 책임, 협력
- 협력 : 애플리케이션 기능을 구현하기 위해 메시지를 주고받는 객체들 사이의 상호작용
- 책임 : 객체가 다른 객체와 협력하기 위해 수행하는 행동
- 역할 : 대체 가능한 책임의 집합
- 객체의 상태가 아니라 객체의 행동에 초점을 맞추는 것이 중요
  - 객체를 단순한 데이터의 집합으로 바라보는 시각은 객체의 내부 구현을 퍼블릭 인터페이스에 노출시키는 결과를 낳기 때문에 결과적으로 변경에 취약해짐



###### 데이터 중심의 영화 예매 시스템

- 객체 지향 설계에서는 두 가지 방법을 이용해 시스템을 객체로 분할 가능

  > - 상태(데이터)를 분할의 중심축으로 (일반적으로 객체의 상태는 객체가 저장해야 하는 데이터의 집합을 의미하기 때문)
  >
  >   > 객체는 자신이 포함하고 있는 데이터를 조작하는데 필요한 오퍼레이션을 정의
  >   >
  >   > 즉, 객체의 상태에 초점
  >   >
  >   > 객체를 독립된 데이터 덩어리로 바라봄
  >   >
  >   > 
  >   >
  >   > 상태를 객체 분할의 중심으로 삼으면 구현에 관한 세부사항이 객체의 인터페이스에 스며들게 되어 캡슐화의 원칙이 무너짐
  >   >
  >   > 결과적으로 상태 변경은 인터페이스의 변경을 초래하며, 이 인터페이스에 의존하는 모든 객체에게 변경의 영향이 퍼지게 됨
  >   >
  >   > 따라서 데이터에 초점을 맞추는 설계는 변경에 취약
  >
  >   
  >
  > - 책임을 분할의 중심축으로
  >
  >   > 객체는 다른 객체가 요청할 수 있는 오퍼레이션을 위해 필요한 상태를 보관
  >   >
  >   > 즉, 객체의 행동에 초점
  >   >
  >   > 객체를 협력하는 공동체의 일원으로 바라봄
  >   >
  >   > 객체의 책임은 인터페이스에 속함. 객체는 책임을 드러내는 안정적인 인터페이스 뒤로 책임을 수행하는 데 필요한 상태를 캡슐화함으로써 구현 변경에 대한 파장이 외부로 퍼져나가는 것을 방지
  >   >
  >   > 따라서 책임에 초점을 맞추면 상대적으로 변경에 안정적인 설계를 얻을 수 있음
  >   
  >   



- 데이터를 준비하자

  - 데이터 중심의 설계는 객체가 내부에 저장해야 하는 '데이터가 무엇인가'를 묻는 것으로 시작

  - 먼저 Movie에 저장될 데이터를 결정하는 것으로 설계 시작

    > ~~~ Movie
    > public class Movie {
    > 	private String title;
    > 	private Duration runningTime;
    > 	private Money fee;
    > 	private List<DiscountCondition> discountCondition;
    > 	
    > 	private MovieType movieType;
    > 	private Money discountAmount;
    > 	private double discountPercent;
    > }
    > ~~~
    >
    > title, runningTime, fee 를 인스턴스 변수로 포함하는 것 까지는 이전과 동일
    >
    > 가장 두드러지는 차이점은 할인 조건의 목록(discountConditions)이 인스턴스 변수로 Movie 안에 직접 포함되어 있음
    >
    > 또한 할인 정책을 DiscountPolicy라는 별도 클래스로 분리했던 이전 예제와 달리 금액 할인 정책에 사용되는 할인 금액(discountAmount)과 비율 할인 정책에 사용되는 할인 비율(discountPercent)을 Movie에서 직접 정의하고 있음
    >
    > 할인 정책은 영화별로 오직 하나만 지정 가능. 여기서 할인 정책의 종류를 결정하는 것이 바로 movieType
    >
    > ~~~ movieType
    > public enum MovieType {
    > 	AMOUNT_DISCOUNT,
    > 	PERCENT_DISCOUNT,
    > 	NONE_DISCOUNT
    > }
    > ~~~
    >
    > Movie가 할인 금액을 계산하는데 필요한 데이터?
    >
    > - 금액 할인 정책의 경우 할인 금액 필요, 비율 할인 정책의 경우 할인 비율 필요
    >
    > 예매 가격을 계산하기 위해서 필요한 데이터 ?
    >
    > - MovieType을 정의하고 이 타입의 인스턴스를 속성으로 포함시켜 이 값에 따라 어떤 데이터를 사용할지 결정
    >
    > Movie 클래스처럼 객체의 종류를 저장하는 인스턴스 변수(MovieType)와 인스턴스의 종류에 따라 배타적으로 사용될 인스턴스 변수(discountAmount, discountPercent)를 하나의 클래스 안에 함께 포함시키는 방식은 데이터 중심의 설계 안에서 흔히 볼 수 있는 패턴

    

  - 필요한 데이터를 준비했으면 접근자와 수정자를 이용하여 객체지향의 중요한 원칙인 캡슐화를 진행

    > ~~~ Movie
    > public class Movie {
    >     public String getTitle() {
    >         return title;
    >     }
    > 
    >     public void setTitle(String title) {
    >         this.title = title;
    >     }
    > 
    >     public Duration getRunningTime() {
    >         return runningTime;
    >     }
    > 
    >     public void setRunningTime(Duration runningTime) {
    >         this.runningTime = runningTime;
    >     }
    > 
    >     public Money getFee() {
    >         return fee;
    >     }
    > 
    >     public void setFee(Money fee) {
    >         this.fee = fee;
    >     }
    > 
    >     public List<DiscountCondition> getDiscountCondition() {
    >         return Collections.unmodifiableList(discountCondition);
    >     }
    > 
    >     public void setDiscountCondition(List<DiscountCondition> discountCondition) {
    >         this.discountCondition = discountCondition;
    >     }
    > 
    >     public MovieType getMovieType() {
    >         return movieType;
    >     }
    > 
    >     public void setMovieType(MovieType movieType) {
    >         this.movieType = movieType;
    >     }
    > 
    >     public Money getDiscountAmount() {
    >         return discountAmount;
    >     }
    > 
    >     public void setDiscountAmount(Money discountAmount) {
    >         this.discountAmount = discountAmount;
    >     }
    > 
    >     public double getDiscountPercent() {
    >         return discountPercent;
    >     }
    > 
    >     public void setDiscountPercent(double discountPercent) {
    >         this.discountPercent = discountPercent;
    >     }
    > }
    > ~~~
    >
    > - Collections.unmodifiableList
    >
    >   자바 컬렉션에서 리스트에 데이터를 추가한 뒤 더 이상 데이터 삭제, 추가를 막기 위해 사용
    >
    >   사용한 배열은 Read-Only가 된다
    >
    >   null 값이나 다른 배열을 통해서 초기화는 가능

    

  - 할인 조건 구하기

    - 할인 조건을 구현하는 데 필요한 데이터 ? 

      먼저 현재의 할인 조건의 종류를 저장할 데이터(DiscountConditionType) 필요

      ~~~ DiscountConditionType
      public enum DiscountConditionType {
      	SEQUENCE,	// 순번 조건
      	PERIOD		// 기간 조건
      }
      ~~~

    - 할인 조건을 구현하는 DiscountCondition은 할인 조건의 타입을 저장할 인스턴스 변수인 type 포함

      ~~~ DiscountCondition
      public class DiscountCondition {
      	private DiscountConditionType type;
      	
      	// 순번 조건에 필요한 데이터
      	private int sequence;
      	
      	// 기간 조건에 필요한 데이터
      	private DayOfWeek dayOfWeek;
      	private LocalTime startTime;
      	private LocalTime endTime;
      }
      ~~~

    - 마찬가지로 캡슐화를 위해 메소드 추가

      ~~~ DiscountCondition
      public class DiscountCondition {
      
          public DiscountConditionType getType() {
              return type;
          }
      
          public void setType(DiscountConditionType type) {
              this.type = type;
          }
      
          public int getSequence() {
              return sequence;
          }
      
          public void setSequence(int sequence) {
              this.sequence = sequence;
          }
      
          public DayOfWeek getDayOfWeek() {
              return dayOfWeek;
          }
      
          public void setDayOfWeek(DayOfWeek dayOfWeek) {
              this.dayOfWeek = dayOfWeek;
          }
      
          public LocalTime getStartTime() {
              return startTime;
          }
      
          public void setStartTime(LocalTime startTime) {
              this.startTime = startTime;
          }
      
          public LocalTime getEndTime() {
              return endTime;
          }
      
          public void setEndTime(LocalTime endTime) {
              this.endTime = endTime;
          }
      }
      ~~~

    - 이어서 Screening 클래스도 위와 같은 방법(어떤 데이터를 포함해야하는지 결정, 캡슐화를 위한 메소드 추가)으로 구현

      ~~~ Screening
      public class Screening {
          private Movie movie;
          private int sequence;
          private LocalDateTime whenScreening;
      
          public Movie getMovie() {
              return movie;
          }
      
          public void setMovie(Movie movie) {
              this.movie = movie;
          }
      
          public int getSequence() {
              return sequence;
          }
      
          public void setSequence(int sequence) {
              this.sequence = sequence;
          }
      
          public LocalDateTime getWhenScreening() {
              return whenScreening;
          }
      
          public void setWhenScreening(LocalDateTime whenScreening) {
              this.whenScreening = whenScreening;
          }
      }
      ~~~

    - 영화 예매 시스템의 목적은 영화를 예매하는 것. Reservation 클래스 추가

      ~~~ Reservation
      public class Reservation {
          private Customer customer;
          private Screening screening;
          private Money fee;
          private int audienceCount;
      
          public Reservation(Customer customer, Screening screening, Money fee, int audienceCount) {
              this.customer = customer;
              this.screening = screening;
              this.fee = fee;
              this.audienceCount = audienceCount;
          }
      
          public Customer getCustomer() {
              return customer;
          }
      
          public void setCustomer(Customer customer) {
              this.customer = customer;
          }
      
          public Screening getScreening() {
              return screening;
          }
      
          public void setScreening(Screening screening) {
              this.screening = screening;
          }
      
          public Money getFee() {
              return fee;
          }
      
          public void setFee(Money fee) {
              this.fee = fee;
          }
      
          public int getAudienceCount() {
              return audienceCount;
          }
      
          public void setAudienceCount(int audienceCount) {
              this.audienceCount = audienceCount;
          }
      }
      ~~~

    - 고객의 정보를 저장하는 Customer

      ~~~ Customer
      public class Customer {
      	private String name;
      	private String id;
      	
      	public Customer(String name, String id) {
      		this.name = name;
      		this.id = id;
      	}
      }
      ~~~

    

- 영화를 예매하자

  - ReservationAgency는 데이터 클래스들을 조합하여 영화 예매 절차를 구현하는 클래스
  
  - reserve 메소드
  
    - Movie에 설정된 DiscountCondition 목록을 차례대로 탐색하면서 영화의 할인 여부 판단
    - DiscountCondition의 타입이 기간 조건이라면 기간을 이용해 적용여부 판단
    - DiscountCondition의 타입이 순번 조건이라면 상영 순번을 이용해 조건 판단
    - 할인 여부는 discountable에 저장
  
    

###### 설계 트레이드 오프

- 데이터 중심 설계와 책임 중심 설계의 장단점을 비교하기 위해 캡슐화, 응집도, 결합도 사용

- 캡슐화

  - 상태와 행동을 하나의 객체 안에 모으는 이유는 객체의 내부 구현을 외부로부터 감추기 위함

  - 여기서 구현이란, 나중에 변경될 가능성이 높은 어떤 것

    > 구현 : 변경 가능성이 높은 부분
    >
    > 인터페이스 : 상대적으로 안정적인 부분
    >
    > 객체지향 설계의 가장 중요한 원리는 불안정한 구현 세부사항을 안정적인 인터페이스 뒤로 캡슐화 하는 것

  - 훌륭한 프로그래밍 기술을 적용해서 캡슐화를 향상시킬 수는 있겠지만 객체지향 프로그래밍을 통해 전반적으로 얻을 수 있는 장점은 오직 설계 과정 동안 캡슐화를 목표로 인식할 때만 달성될 수 있다.

    Q. 좋은 기술을 사용하여 캡슐화 하는 것보다 설계를 하는동안 어떻게 캡슐화를 할지 생각하는게 좋다는 부분? 

  - 설계가 필요한 이유는 요구사항이 변경되기 때문이고, 캡슐화가 중요한 이유는 불안정한 부분과 안정적인 부분을 분리해서 변경의 영향을 통제할 수 있기 때문

  - 즉, 캡슐화란 변경 가능성이 높은 부분을 객체 내부로 숨기는 추상화 기법

- 응집도와 결합도

  - 응집도 

    > 모듈에 포함된 내부 요소들이 연관돼 있는 정도를 나타냄
    >
    > 모듈 내 요소들이 하나의 목적을 위해 긴밀하게 협력한다면 응집도가 높은 것
    >
    > 객체지향의 관점에서 응집도는 객체 또는 클래스에 얼마나 관련 높은 책임들을 할당했는지를 나타냄
    >
    > 
    >
    > 응집도가 높을수록 변경의 대상과 법위가 명확해지기 때문에 코드 변경 쉬움
    >
    > 변경을 반영하기 위해 오직 하나의 모듈만 수정하면 된다.
    >
    > 퍼블릭 인터페이스를 수정했을 대만 다른 모듈에 영향을 미치는 경우에는 결합도가 낮다고 함

  - 결합도

    > 의존성의 정도를 나타내며, 다른 모듈에 대해 얼마나 많은 지식을 갖고 있는지 나타냄
    >
    > 어떤 모듈이 다른 모듈에 대해 너무 자세한 부분까지 알고 있다면 두 모듈은 높은 결합도를 가짐
    >
    > 객체지향의 관점에서 결합도는 객체 또는 클래스가 협력에 필요한 적절한 수준의 관계만을 유지하는지를 나타냄
    >
    > 
    >
    > 한 모듈이 변경되기 위해서 다른 모듈의 변경을 요구하는 정도
    >
    > 결합도가 높으면 함께 변경해야하는 모듈이 늘어나기 때문에 변경이 어려워짐
    >
    > 하지만 표준 라이브러리에 포함된 모듈이나 성숙단계에 접어든 프레임워크에 의존하는 경우(String이나 ArrayList 등..)는 변경될 가능성이 낮기 때문에 결합도에 대해 고민할 필요가 없음
    >
    > 
    >
    > 반면 직접 작성한 코드의 경우에는 코드내 버그가 존재할 수 있고 갑자기 요구사항이 변경될 수 있기 때문에 낮은 결합도를 유지하려고 노력해야 함

  - Good Application

    - app을 구성하는 각 요소의 응집도가 높고 서로 느슨하게 결합돼 있음
    - 이러한 설계를 추구하는 것이 설계를 변경하기 쉽게 만든다.
    - 변경의 관점에서 응집도란 변경이 발생할 때 모듈 내부에서 발생하는 변경의 정도로 측정
    - 하나의 변경을 수용하기 위해 모든 모듈이 변경된다면 응집도가 높은 것..? 결합도가 높은게 아니라?
    - 하나의 변경에 대해 하나의 모듈만 변경된다면 응집도가 높은 것
    - 캡슐화를 위반하면 모듈 안의 응집도는 낮아지고 묘듈 사이의 결합도는 높아짐

    

###### 데이터 중심의 영화 예매 시스템의 문제점

- 데이터 중심 설계

  - 캡슐화를 위반하고 객체의 내부 구현을 인터페이스의 일부로 만든다

- 책임 중심의 설계

  - 객체 내부의 구현을 안정적인 인터페이스 뒤로 캡슐화

- 캡슐화 위반 !

  > 	public class Movie {
  > 		private Money fee;
  > 		
  > 		public Money getFee() {
  > 			return fee;
  > 		}
  > 		
  > 		public void setFee() {
  > 			this.fee = fee;
  > 		}
  > 	}
  >
  > 코드를 보면 직접 객체에 접근할 수 없기 때문에 캡슐화의 원칙을 지킨 것처럼 보임
  >
  > 하지만 접근자와 수정자 메소드는 객체 내부의 상태에 대한 어떤 정보도 캡슐화하지 못함
  >
  > fetFee()와 setFee()는 Movie 내부에 Money 타입의 fee라는 이름의 인스턴스 변수가 존재한다는 사실을 퍼블릭 인터페이스에 노골적으로 드러냄
  >
  > 책임이 아닌 저장할 데이터에 초점을 맞췄기 때문
  >
  > - 추측에 의한 설계(design-by-guessing strategy)
  >   - 객체가 사용될 협력을 고려하지 않고 객체가 다양한 상황에서 사용될 수 있을 것이라는 막연한 추측을 기반으로 설계
  >   - 대부분의 내부 구현이 퍼블릭 인터페이스에 그대로 노출되어 캡슐화의 원칙을 위반하는 변경에 취약한 설계를 얻음

- 높은 결합도

  - 데이터 중심 설계는 접근자와 수정자를 통해 내부 구현을 인터페이스의 일부로 만들기 때문에 캡슐화 위반

  - 객체의 구현이 객체의 인터페이스에 드러난다는 것은 클라이언트가 구현에 강하게 결합된다는 것을 의미

  - 내부 구현을 변경했음에도 이 인터페이스에 의존하는 모든 클라이언트들도 함께 변경해야 함

    > ~~~ ReservationAgency
    > public class ReservationAgency {
    > 	public Reservation reserve(Screening screening, Customer customer, int audienceCount) {
    > 		...
    > 		Money fee;
    > 		if(discountable) {
    > 			...
    > 			fee = movie.getFee().minus(discountedAmount).times(audienceCount);
    > 		}
    > 		else {
    > 			fee = movie.getFee();
    > 		}
    > 	}
    > }
    > ~~~
    >
    > ReservationAgency는 한 명의 예매 요금을 계산하기 위해 Movie의 getFee 메소드를 호출하며, 계산된 결과를 Money 타입의 fee에 저장
    >
    > 만약 fee의 타입을 변경하려고 한다면 getFee의 변환 타입과 getFee()를 호출하는 ReservationAgency의 구현도 변경된 타입에 맞게 함께 수정해야 함 
    >
    > getFee()를 사용하는 것은 인스턴스 변수 fee의 가시성을 private에서 public으로 변경하는 것과 거의 동일

  - 결합도 측면에서 데이터 중심 설계가 가지는 또 다른 단점은 여러 데이터 객체들을 사용하는 제어 로직이 특정 객체 안에 집중되어 있기 때문에 하나의 제어 객체가 다수의 데이터 객체에 강하게 결합된다는 것

    > DiscountCondition의 데이터가 변경되면 DiscountCondition 뿐만 아니라 ReservationAgency도 함께 수정.
    >
    > Screening의 데이터가 변경되면 Screening 뿐만 아니라 ReservationAgency도 함께 수정
    >
    > 대부분의 제어 로직을 가지고 있는 제어 객체인 ReservationAgency는 모든 의존성이 모이는 결합도의 집결지로, 시스템 안의 어떤 변경도 ReservationAgency의 변경을 유발

- 낮은 응집도

  - 서로 다른 이유로 변경되는 코드가 하나의 모듈 안에 공존할 때 모듈의 응집도가 낮다고 말함

    > 다음과 같은 수정사항이 발생하는 경우 ReservationAgency의 코드를 수정해야 함
    >
    > - 할인 정책이 추가될 경우
    > - 할인 정책별로 할인 요금을 계산하는 방법이 변경된 경우
    > - 할인 조건이 추가되는 경우
    > - 할인 조건별로 할인 여부를 판단하는 방법이 변경될 경우
    > - 예매 요금을 계산하는 방법이 변경된 경우
    >
    > > 변경의 이유가 서로 다른 코드들을 하나의 모듈 안에 뭉쳐놓았기 때문에 변경과 아무 상관이 없는 코드들이 영향을 받게 됨
    > >
    > > ReservationAgency 안에 할인 정책을 선택하는 코드와 할인 조건을 판단하는 코드가 함께 존재하기 때문

  - 현재 상황의 경우 새로운 할인 정책을 추가하거나 새로운 할인 조건을 추가하기 위해 하나 이상의 클래스를 동시에 수정해야 함 - 응집도가 낮다는 증거

    > 단일 책임의 원칙( Single Responsibility Principle)
    >
    > - 클래스는 단 한 가지의 변경 이유만 가져야 함

    

###### 자율적인 객체를 향해

- 캡슐화를 지켜라
  - 속성의 가시성을 private로 지정했더라도 접근자와 수정자를 통해 속성을 외부로 제공하고 있다면 캡슐화를 위반한 것
  - 































