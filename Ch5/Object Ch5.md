## 5장



###### 책임 주도 설계를 향해

- 데이터 중심의 설계 → 책임 주도 설계 
  - 데이터보다 행동을 먼저 결정
  - 협력이라는 문맥 안에서 책임을 결정



###### 데이터보다 행동을 먼저 결정하라

- 클라이언트의 관점에서 객체가 수행하는 행동이란 객체의 책임을 의미

- 데이터는 객체가 책임을 수행하는 데 필요한 재료를 제공할 뿐

- 질문의 순서를 바꾸기

  > 데이터 중심 설계 
  >
  > - '이 객체가 포함해야 하는 데이터가 무엇인가'
  > - 그 후 '데이터를 처리하는 데 필요한 오퍼레이션이 무엇인가' 를 결정
  >
  > 책임 중심 설계 (책임(행동) 결정 후 상태 결정)
  >
  > - 이 객체가 수행해야 하는 책임은 무엇인가
  > - 그 후 '이 책임을 수행하는 데 필요한 데이터는 무엇인가' 를 결정



###### 협력이라는 문맥 안에서 책임을 결정하라

- 책임의 품질 : 협력에 적합한 정도로 결정 (책임은 객체의 입장이 아니라 객체가 참여하는 협력에 적합해야 함)

- 협력을 시작하는 주체는 메시지 전송자. 따라서 협력에 적합한 책임이란 수신자가 아닌 전송자에게 적함한 책임을 뜻함

- 메시지가 존재하기 때문에 그 메시지를 처리할 객체가 필요 -> 메시지 결정 후 객체 선택

  -> '메시지를 전송해야 하는데 누구에게 전송하지?!'

- 메시지를 먼저 결정하기 때문에 송신자는 수신자에 대한 어떠한 가정도 할 수 없음

  -> 전송자의 관점에서 수신자가 캡슐화 되는 것

- 정리

  > 책임을 할당하기 위해서 협력이라는 문맥을 고려
  >
  > 협력이라는 문맥에서 적절한 책임이란 곧 클라이언트 관점에서 적절한 책임을 의미
  >
  > 올바른 객체지향 설계는 클라이언트가 전송할 메시지를 결정한 후에 객체의 상태를 저장하는 데 필요한 데이터에 관해 생각



###### 책임 주도 설계

- 3장 내용 中.. (예매하라! 부터 시작했었지..)

  > - 시스템이 사용자에게 제공해야 하는 기능인 시스템 책임을 파악
  > - 시스템 책임을 더 작은 책임으로 분할
  > - 분할된 책임을 수행할 수 있는 적절한 객체 또는 역할을 찾아 책임을 할당
  > - 객체가 책임을 수행하는 도중 다른 객체의 도움이 필요한 경우 이를 책임질 적절한 객체 또는 역할을 찾음
  > - 해당 객체 또는 역할에게 책임을 할당함으로써 두 객체가 협력하게 함



###### 책임 할당을 위한 GRASP 패턴

- General Responsibility Assignment Software Pattern (일반적인 책임 할당을 위한 소프트웨어 패턴)

- 객체에게 책임을 할당할 때 지침으로 삼을 수 있는 원칙들의 집합을 패턴 형식으로 정리

- 도메인 개념에서 출발하기

  - 어떤 책임을 할당해야 할 때 가장 먼저 생각해야 하는 유력한 후보는 도메인 개념 (예매, 상영, 영화, 할인 조건 ..)

- 정보 전문가에게 책임을 할당하라

  > 메시지는 메시지를 수신할 객체가 아니라 메시지를 전송할 객체의 의도를 반영해서 결정해야 함
  >
  > - 이에 따른 첫 번째 질문 : '메시지를 전송할 객체는 무엇을 원하는가?'
  > - 객체가 원하는 것은 영화를 예매하는 것으로 메시지 이름은 '예매하라'
  >
  > 메시지를 결정했으므로 메시지에 적합한 객체를 선택
  >
  > - 이에 따른 두 번째 질문 : '메시지를 수신할 적합한 객체는 누구인가?'
  >
  > - 질문에 답하기 위해 객체가 상태와 행동을 통합한 캡슐화의 단위라는 사실에 집중
  >
  > - 객체의 책임과 책임을 수행하는 데 필요한 상태는 동일한 객체 내에 존재해야 함
  >
  > - 책임 할당 첫 번째 원칙은 책임을 수행할 정보를 알고 있는 객체에게 책임을 할당
  >
  >   -> GRASP에서는 이를 정보 전문가라 부름
  >
  > 
  >
  > 책임을 수행하는 객체가 정보를 알고있다고 해서 그 정보를 저장하고 있을 필요는 없음 
  >
  >  -> 여기서 말하는 정보는 데이터와 다름.
  >
  > 영화 예매에 필요한 정보를 가장 많이 알고 있는 Screening이 적합  
  >
  > 이후 Screening은 가격을 계산하기 위해 외부 객체에게 도움을 요청 (가격을 계산하라)
  >
  > ...
  >
  > 정보 전문가 패턴을 따르는 것만으로도 자율성이 높은 객체들로 구성된 협력 공동체를 구축할 가능성이 높아짐

- 높은 응집도와 낮은 응집도

  - 설계는 트레이드오프 활동. 따라서 설계 진행 도중 몇 가지 설계에서 선택해야 하는 경우가 빈번히 발생

    >LOW COUPLING 패턴 (낮은 결합도)
    >
    >- 어떻게 하면 의존성을 낮추고 변화의 영향을 줄이며 재사용성을 증가시킬 수 있을까?
    >- 설계의 전체적인 결합도가 낮게 유지되도록 책임을 할당
    >
    >HIGH COHESION 패턴 (높은 응집도)
    >
    >- 어떻게 복잡성을 관리할 수 있는 수준으로 유지할 것인가?

- 창조자에게 객체 생성 책임을 할당하라

  - 영화 예매 최종 결과물은 Reservation 인스턴스를 생성하는 것으로, 협력에 참여하는 어떤 객체는 Reservation 인스턴스를 생성할 책임을 할당해야 한다는 것을 의미

  - CREATOR(창조자) 패턴은 객체를 생성할 책임을 어떤 객체에게 할당할지에 대한 지침 제공

    > 객체 A를 생성해야 할 때 어떤 객체에게 객체 생성 책임을 할당해야 하는가?
    >
    > - B가 A 객체를 포함하거나 참조
    > - B가 A 객체를 기록
    > - B가 A 객체를 긴밀하게 사용
    > - B가 A 객체를 초기화하는데 필요한 데이터를 가지고 있음(이 경우 B는 A에 대한 정보 전문가)
    >
    > 위 조건을 최대한 많이 만족하는 B에게 객체 생성 책임을 할당
    >
    > CREATOR 패턴은 이미 존재하는 객체 사이의 관계를 이용하기 때문에 설계가 낮은 결합도를 유지
    >
    > ㅇ
    >
    > Reservation에 관해 위 조건을 가장 많이 만족하는 객체는 Screening 이다.
    >
    > 따라서 Screening을 Reservation의 CREATOR로 선택하는 것이 적절

###### 구현을 통한 검증

- Screening

  ~~~ Screening
  public class Screening {
  	// movie에게 메시지를 보내기 위해 movie에 대한 참조 포함
  	private Movie movie;
  	private int sequence;
  	private LocalDateTime whenScreened;
  	
  	// 협력 관점에서 '예매하라'라는 메시지에 응답할 수 있어야 함
  	public Reservation reserve (Customer customer, int audienceCount) {
  		return new Reservation(customer, this, calculateFee(audienceCount), audienceCount);
  
  	}
  	
  	// 예매를 위한 가격을 계산하기 위해 movie에게 '가격을 계산하라'라는 메시지를 전송
  	// Movie에 대한 내부 구현을 고려하지 않고 필요한 메시지를 결정 -> Movie 내부 구현 캡슐화
  	private Money calculateFee(int audienceCount) {
  		return movie.calculateFee(this).times(audienceCount);
  	}
  }
  ~~~

  - Screening과 Movie의 유일한 연결 고리는 메시지 뿐 

    -> 메시지가 변경되지 않는 한 Moive에 어떤 수정을 가하더라도 Screening에는 영향 x

    이처럼 메시지 기반 협력을 구성하면 결합도를 느슨하게 유지 가능

- Movie

  ~~~ Movie
  public class Movie {
  	/* 요금 계산을 위해 fee, discountConditions, 할인 정책 등의 정보 필요
  	현재 설계에서 할인 정책을 Movie의 일부로 구현 -> 할인 금액, 할인 정책을 Movie의 인스턴스로 선언*/
  	private String title;
  	private Duration runningTime;
  	private Money fee;
  	private List<DiscountCondition> discountConditions;
  	
  	private MovieType movieType;
  	private Money discountAmount;
  	private double discountPercent;
  	
  	//Screening에서 보내는 메시지에 응답하기 위한 메소드
  	public Money calculateFee(Screening screening) {
  		if(isDiscountable(Screening screening)) {
  			return fee.minus(calculateDiscountAmount());
  		}
  		
  		return fee;
  	}
  	
  	// discountConditions 원소를 차례로 순회하면서 DiscountCondition 인스턴스를 isSatisfiedBy 메시지를 전송해서 할인 여부 판단하도록 요청
  	private boolean isDiscountable(Screening screening) {
  		return discountConditions.stream()
  			.anyMatch(condition -> condition.isSatisfiedBy(Screening));
  	}
  	
  	// Movie 타입에 따라 적절한 메소드 호출
  	private Money calculateDiscountAmount() {
  		switch(movieType) {
  			case AMOUNT_DISCOUNT : return calculateAmountDiscountAmount();
  			case PERCENT_DISCOUNT : return calculatePercentDiscountAmount();
  			case NONE_DISCOUNT : return calculateNoneDiscountAmount();
  		}
  		throw new IllegalStateException();
  	}
  	
  	private Money calculateAmountDiscountAmount() {...}
  	private Money calculatePercentDiscountAmount() {...}
  	private Money calculatenoneDiscountAmount() {...}
  	
  }
  ~~~

  - Stream

    > allMatch() : 모든 요소들이 매개값으로 주어진 조건을 만족하는지 조사
    >
    > anyMatch() : 최소한 한 개의 요소가 주어진 조건에 만족하는지 조사
    >
    > noneMatch() : 모든 요소들이 주어진 조건을 만족하지 않는지 조사
    >
    > ~~~ 예제
    > public static void main(String[] args){
    >         int[] intArr = {2, 4, 6};
    > 
    >         boolean result = Arrays.stream(intArr)
    >                 .allMatch(a -> a%2 == 0);
    >         System.out.println("2의 배수? " + result);
    > 
    >         result = Arrays.stream(intArr)
    >                 .anyMatch(a -> a%3 == 0);
    >         System.out.println("3의 배수가 하나라도 있나? " + result);
    > 
    >         result = Arrays.stream(intArr)
    >                 .noneMatch(a -> a%3 == 0);
    >         System.out.println("3의 배수가 없나? " + result);
    > } // true, true, false
    > ~~~

- DiscountCondition

  ~~~ DiscountCondition
  public class DiscountCondition {
  
  	//할인 조건을 판단하기 위해 Screening의 상영 시간과 상영 순번이 필요
  	private int sequence;
  	private DayOFWeek dayOfWeek;
  	private LocalTime startTime;
  	private LocalTime endTime;
  
  	// Movie의 '할인 여부를 판단하라'라는 메시지에 응답하는 메소드
  	public boolean isSatisfiedBy(Screening screening) {
  		if(type == DiscountConditionType.PERIOD)
  			return ...;
  	}
  	
  	private boolean isSatisfiedByPeriod(Screening screeing) {
  		return dayOfWeek.equals() ... ;
  	}
  	
  	private boolean isSatisfiedBySequence(Screening screeing) {
  		return sequence == screening.getSequence;
  	}
  }
  ~~~

  

###### DiscountCondition 개선하기

> DiscountCondition이 변경되는 경우
>
> - 새로운 할인 조건 추가
>
>   - isSatisfiedBy 메소드 안의 if ~ else 구문 수정
>
>   - 새로운 할인 조건이 새로운 데이터를 요구한다면 속성 추가 작업 역시 필요
>
> - 순번 조건을 판단하는 로직 변경
>
>   - isSatisfiedBySequence 메소드 내부 구현 수정
>   - 순번 조건을 판단하는데 필요한 데이터 변경 -> sequnce 속성 역시 변경
>
> - 기간 조건을 판단하는 로직 변경
>
>   - isSatisfiedByPeriod 메소드 내부 구현 수정
>   - 기간 조건을 판단하는데 필요한 데이터 변경 -> dayOfWeek, startTime, endTime 속성 역시 변경
>
> -> 변경에 취약한 코드!! 따라서 낮은 응집도가 초래하는 문제를 해결하기 위해 변경의 이유에 따라 클래스를 분리해야 함

- 코드를 통해 변경의 이유 파악하기

  > 1. 인스턴스 변수가 초기화되는 시점 찾아보기
  >
  > - 응집도 높은 클래스는 인스턴스를 생성할 때 모든 속성을 함께 초기화
  >
  > - 반면 응집도가 낮은 클래스는 객체의 속성 중 일부만 초기화하고 일부는 초기화되지 않은 상태로 남겨짐
  >
  >   > 순번 조건을 표현하는 경우 sequence만 초기화
  >   >
  >   > 기간 조건을 표현하는 경우 dayOfWeek, startTime, endTime만 초기화
  >   >
  >   > 이처럼 클래스 속성이 서로 다른 시점에 초기화되거나 일부만 초기화된다는 것은 응집도가 낮은 것
  >   >
  >   > 함께 초기화되는 속성을 기준으로 코드를 분리해야 함!!
  >
  > 2. 메소드들이 인스턴스 변수를 사용하는 방식을 살펴포기
  >
  > - 모든 메소드가 객체의 모든 속성을 사용한다면 클래스의 응집도가 높은 것
  >
  > - 반면 메소드들이 사용하는 속성에 따라 그룹이 나뉜다면 클래스의 응집도가 낮은 것
  >
  >   > isSatisfiedBySequence 메소드는 sequence를 사용하지만 dayOfWeek, startTime, endTime는 사용 x
  >   >
  >   > isSatisfiedByPeriod 메소드는 dayOfWeek, startTime, endTime를 사용하지만 sequence 사용 x
  >   >
  >   > 속성 그룹과 해당 그룹에 접근하는 메소드 그룹을 기준으로 코드를 분리해야 함 !!

- 클래스 응집도 판단

  - 클래스가 하나 이상의 이유로 변경돼야 한다면 응집도가 낮은 것 -> 변경의 이유 기준으로 클래스 분리
  - 클래스의 인스턴스를 초기화하는 시점에, 경우에 따라 서로 다른 속성들을 초기화하고 있다면 응집도가 낮은 것 -> 초기화되는 속성의 그룹을 기준으로 클래스 분리
  - 메소드 그룹이 속성 그룹을 사용하는지 여부로 나뉜다면 응집도가 낮은 것
  - DiscountCondition 에는 이 세 가지 징후 모두 포함 -> 응집도가 낮음

- 타입 분리하기

  - DiscountCondition의 가장 큰 문제는 순번 조건과 기간 조건이라는 두 개의 독립적인 클래스가 공존하는 것

    > 두 개의 클래스를 분리하면,
    >
    > ~~~ PeriodCondition
    > public class PeriodCondition {
    > 
    > 	private DayOfWeek dayOfWeek;
    > 	private LocalTime startTime;
    > 	private LocalTime endTime;
    > 	
    > 	...
    > }
    > ~~~
    >
    > ~~~ sequece
    > public class SequenceCondition {
    > 	
    > 	private int sequence;
    > 	
    > 	...
    > }
    > ~~~
    >
    > - 두 클래스는 자신의 모든 인스턴스 변수를 함께 초기화 가능
    > - 클래스에 있는 모든 메소드는 동일한 인스턴스 변수 그룹 사용

  - 하지만 Movie의 인스턴스와 분리된 두 클래스의 인스턴스 모두 협력할 수 있어야 함

    - 해결을 위해 Movie 클래스 안에서 두 클래스의 목록을 따로 유지

      -> Movie 클래스가 두 클래스 모두에 결합. 원래는 1개의 클래스에 결합되었으므로 결합도가 높아짐

      -> 수정 후 새로운 할인 조건 추가하기 어려워짐

      > 먼저 새로운 할인 조건을 담을 List를 Movie의 인스턴스 변수로 추가해야 함
      >
      > 또, 조건 만족여부를 판단하는 메소드도 추가
      >
      > 마지막으로 이 메소드를 호출하도록 isDiscountable 메소드 수정
      >
      > -
      >
      > 분리 전에는 DiscountCondition 내부 구현만 수정하면 Movie에는 아무런 영향 미치지 않았음
      >
      > 수정 후에는 할인 조건 추가시 Movie도 함께 수정

- 다형성을 통해 분리하기

  - Movie의 입장에서는 어떤 할인 조건이든 할인 가능 여부만 반환해 주면 됨

  - 앞에서 배웠던 역할의 개념이 새록새록 등장

  - DiscountCondition이라는 슬롯에 두 클래스를 넣으면 Movie는 구체적인 클래스는 알지 못함

    -> 오직 역할에 대해서만 결합되도록 의존성을 제한

  - 역할을 구현하기 위해 추상 클래스나 인터페이스 사용

    -> 구현 공유 필요 없이 역할을 대체하는 객체들의 책임만 정의하고 싶다면 인터페이스 사용

  ~~~ DiscountCondition
  // 할인 조건의 경우에는 두 클래스가 구현을 공유할 필요 없으므로 인터페이스를 통해 역할 구현 !
  public interface DiscountCondition {
  	boolean isSatisfiedBy(Screening screening);
  }
  ~~~

  ~~~ condition
  public class PeriodCondition implements DiscountCondition {...}
  
  public class SequenceCondition implements DiscountCondition {...}
  ~~~

  ~~~ Movie
  public class Movie {
  	
  	private List<DiscountCondition> discountConditions;
  	...
  	// 협력하는 구체적인 타입 상관 없이 DiscountCondition 역할을 수행할 수 있고 isSatisfiedBy 메시지를 이해할 수만 있다는 사실만 알고있으면 됨
  	private boolean isDiscountable(Screening screening) {
  		return discountConditionstream().anyMatch(condition 
  			-> condition.isSatisfiedBy(screening));
  	}
  	
  }
  ~~~

  메시지를 수신한 객체에 따라 실행되는 메소드가 다름 

  -> Movie와 DiscountCondition 사이의 협력은 다형적!!

  - POLYMORPHISM(다형성) 패턴

    -> 객체의 타입에 따라 변하는 행동이 있다면 타입을 분리하고 변화하는 행동을 각 타입의 책임으로 할당

    

- 변경으로부터 보호하기

  - DiscountCondition의 두 서브클래스는 서로 다른 이유로 변경됨

    - SequenceCondition은 순번 조건의 구현 방법이 변경될 경우에만 수정
    - PeriodCondition은 기간 조건의 구현 방법이 변경될 경우에만 수정

    -> 두 개의 서로 다른 변경이 두개의 서로 다른 클래스 안으로 캡슐화

  - DiscountCondition 역할이 Movie로 부터 두 서브클래스를 감춤

    - 새로운 할인 조건이 추가되더라도 Movie에는 영향을 주지 않음을 의미
    - 오직 DiscountCondition 인터페이스를 실체화하는 클래슬를 추가하는 것으로 종류 확장 가능

  - PROTECTED VARIATIONS(변경 보호) 패턴

    - 변경을 캡슐화하도록 책임을 할당
    - 설계에서 변하는 것이 무엇인지 고려하고 변하는 개념을 캡슐화

  - 하나의 클래스가 여러 타입의 행동을 구현하고 있다면 클래스를 분해하고 다형성 패턴으로 책임 분리

  - 예측 가능한 변경으로 인해 여러 클래스들이 불안정해진다면 변경 보호 패턴으로 안정적인 인터페이스 뒤로 캡슐화

  

- Movie 클래스 개선하기

  - 금액 할인 정책 영화 과 비율 할인 정책 영화라는 두 가지 타입을 하나의 클래스에 구현

    -> 하나 이상 이유로 변경 가능으로 응집도가 낮음

  - DiscountCondition과 마찬가지로 다형적으로 분리하자

    ~~~ Movie
    // DiscountCondition은 역할을 수행할 클래스들 사이에 구현을 공유할 필요가 없었기에 인터페이스 
      사용. Movie는 구현을 공유할 필요 없으므로 추상 클래스 사용
    public abstract class Movie {
    	...
    	abstract protected Money calculateDiscountAmount();
    }
    ~~~

    ~~~ java
    public class AmountDiscountMovie extends Movie {
        
        private Money discountAmount;
        
        public AmountDiscountMovie(...) {
            ...
        }
        
        @Override
        protected Money calculateDiscountAmount() {
            return discountAmount;
        }
    }
    ~~~

    ~~~java
    public class PercentDiscountMovie extends Movie {
        
        private double percent;
        
        public PercentDiscountMovie(...) {
            ...
        }
        
        @Override
        protected Money calculateDiscountAmount() {
            return getFee().times(percent);
        }
        
        
    }
    ~~~

    

  

