## 9장

#### 개방-폐쇄 원칙

- 확장 가능하고 변화에 유연하게 대응할 수 있는 설계를 만들 수 있는 제조 원칙 중 하나

> 소프트웨어 개발(클래스, 모듈, 함수 등...)는 확장에 대해 열려 있어야 하고, 수정에 대해서는 닫혀 있어야 한다.
>
> * 확장에 대해 열려 있다
>   - 애플리케이션 요구사항 변경시 변경에 맞게 새로운 '동작'을 추가해서 애플리케이션 기능을 확장할 수 있다.
> * 수정에 대해 닫혀 있다
>   - 기존 '코드'를 수정하지 않고도 애플리케이션 동작을 추가하거나 변경할 수 있다.



###### 컴파일타임 의존성을 고정시키고 런타임 의존성을 변경하라

- 개방-폐쇄 원칙을 수용하는 코드는 컴파일 의존성을 변경하지 않고도 런타임 의존성을 쉽게 확장 및 수정 가능

- 컴파일 의존성 : 코드에 드러나는 클래스들 사이의 관계

- 런타임 의존성 : 실행시에 협력에 참여하는 객체들 사이의 관계

- 할인 정책 설계는 이미 개방-폐쇄 원칙을 따르고 있음

  - 기존 코드를 수정하지 않고 OverappedDiscountPolicy, NoneDisocuntPolicy 클래스를 추가하는 것만으로 구현 가능
  - 단순히 새로운 클래스를 추가하는 것만으로 Movie를 새로운 컨텍스트에 사용되도록 확장할 수 있었던 것
  - OverappedDiscountPolicy 클래스를 추가하더라도 Movie는 여전히 DiscountPolicy 클래스에만 의존

  

###### 추상화가 핵심이다

- 개방-폐쇄 원칙의 핵심은 추상화에 의존하는 것
- 추상화 : 핵심적인 부분만 남기고 불필요한 부분은 생략함으로써 복잡성을 극복하는 기법

~~~java
public abstract class DiscountPolicy {
  private List<DiscountCondition> discountConditions = new ArrayList<>();
  
  public DiscountCondition(DiscountCondition ... conditions) {
    this.conditions = Arrays.asList(conditions);
  }
  
  // 변하지 않는 로직 (할인 여부 판단)
  public Money calculateDiscountAmount(...) {
    for(DiscountCondition each : conditions) {
      if(each.isSatisfiedBy(screening)) {
        return getDiscountAmount(screening);
      }
    }
    
    return screening.getMovieFee();
  }
  
  // 변하는 로직 (할인된 요금을 계산하는 방법)
  abstract protected Money getDiscountAmount(Screening screening);
}
~~~

- 추상화된 부분을 채워 새로운 문맥에 맞게 기능을 확장 가능
- 상속을 통해 생략된 부분을 구체화함으로써 할인 정책을 확장할 수 있는 것
- 추상화를 했다고 해서 수정에 대해 닫혀있는 설계를 만들 수 있는 것은 아님 -> 폐쇄를 가능하게 하는 것은 의존성의 방향
- 수정에 대한 영향을 최소화하기 위해서는 모든 요소가 추상화에 의존해야 함

~~~ java
public class Movie {
  ...
  private DiscountPolicy discountPolicy;
  
  public Movie(..., discountPolicy) {
    ...
    this.discountPolicy = discountPolicy
  }
  
  public Money calculateMovieFee(Screening screening) {
    return ...;
  }
}
~~~

- Movie는 DiscountPolicy에만 의존, DiscountPolicy는 변하지 않는 추상화 

- 할인 정책을 추가하기 위해 DiscountPolicy의 자식 클래스를 추가하더라도 영향을 받지 않음 

  -> Movie와 DiscountPolicy는 수정에 대해 닫혀 있음

- 올바른 추상화를 설계하고 추상화에 대해서만 의존하도록 관계를 제한함으로써 설계를 유연하게 확장 가능