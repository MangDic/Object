## 8장

#### 의존성 이해하기

###### 변경과 의존성

- 시점에 따른 다른 의미

  - 실행 시점
    - 의존하는 객체가 정상적으로 동작하기 위해서는 실행 시에 의존 대상 객체가 반드시 존재해야 함
  - 구현 시점
    - 의존 대상 객체가 변경될 경우 의존하는 객체도 함께 변경됨

  ~~~ java
  public class PeriodCondition implements DiscountCondition {
      private DayOfWeek dayOfWeek;
      private LocalTime startTime;
      private LocalTime endTime;
          
      // screening의 인스턴스가 없거나 getStartTime 메시지를 이해할 수 없다면 이 메소드는 예상대로 동작하지 않음
      public boolean isSatisfiedBy(Screening screening) {
          return screening.getStartTime().getDatOfWeek(). ... ;
      }
  }
  ~~~

  - PeriodCondition과 screening 사이에 의존성이 존재

  - 의존성은 방향을 가지며 항상 단방향

    - Screening 변경 -> PeriodCondition 영향 o
    - PeriodCondition 변경 -> Screening 영향 x

  - PeriodCondition은 DayOfWeek, LocalTime, Screening에 의존성을 가짐

    - DayOfWeek, LocalTime은 인스턴스 변수로 사용됨 (실선 표기)
    - Screening은 메소드 인자로 사용됨 (점선 표기)

    

###### 의존성 전이

- 3단논법처럼 생각
  - A가 B에 의존
  - B가 C에 의존
  - 따라서 A는 C에 잠재적으로 의존
- 의존성은 함께 변경될 수 있는 가능성을 의미
  - 따라서 모든 경우에 의존성이 전이되는 것은 아님
  - 전이 여부는 변경의 방향과 캡슐화 정도에 따라 달라짐
- 의존성의 종류
  - 직접 의존성
    - 한 요소가 다른 요소에 직접 의존
    - PeriodCondition이 screening에 의존
  - 간접 의존성
    - 직접적 관계 존재 x, 의존성 전이에 의해 영향이 전파되는 경우
    - 이 경우 의존성은 PeriodCondition의 코드 안에 명시적으로 드러나지 않음

###### 런타임 의존성과 컴파일타임 의존성











