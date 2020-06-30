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







































