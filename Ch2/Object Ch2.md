## 2장



######  영화 예매 시스템 요구사항

+ 할인조건(할인 여부) : 순서 조건(ex 10번째 상영 영화 할인), 기간 조건(요일, 시작 시간, 종료 시간)
+ 할인정책(할인 요금 결정) : 금액 할인 정책, 비율 할인 정책



###### 객체지향

+ 어떤 클래스가 필요한지 고민하기 전 어떤 객체들이 필요할지 생각

+ 클래스는 공통적인 상태와 행동을 공유하는 객체들을 추상화 한 것

+ 객체들은 독립적인 존재가 아니라 기능을 구현하기 위해 협력하는 공동체의 일원으로 생각

+ 객체란 다른 객체에게 도움을 주거나 의존하면서 살아가는 협력적인 존재

+ OOP (Object Oriented Programming)

  > 컴퓨터 프로그래밍 패러다임중 하나로, 프로그래밍에서 필요한 데이터를 추상화시켜 상태와 행위를 가지는 객체를 만들고 그 객체들 간의 유기적인 상호작용을 통해 로직을 구성하는 프로그래밍
  >
  >  유기적 : 많은 조직, 요소 등이 모여 하나를 이루고 서로 긴밀히 연관되어 서로 떼어 낼 수 없는 것
  >
  > - 키워드 : 클래스 + 인스턴스, 추상화, 캡슐화, 상속, 다형성
  >
  > - 장점 
  >   - 코드 재사용 용이 : 생성되어 있는 클래스를 가져와서 이용가능하며, 상속을 통해 확장 가능
  >   - 유지보수가 쉬움 
  >   - 대형 프로젝트에 적합 : 클래스 단위로 모듈화시켜 개발할 수 있기 때문
  > - 단점
  >   - 처리속도가 상대적으로 느림
  >   - 객체가 많으면 용량이 커질 수 있음
  >   - 설계시 많은 시간과 노력 필요

  [출처](https://jeong-pro.tistory.com/95)

  

###### 도메인

- 문제를 해결하기 위해 사용자가 프로그램을 사용하는 분야

- 세부 영역으로 생각 (서비스면 서비스, 제조면 제조처럼 <span style="color:red">SW를 개발하는 대상 영역</span>)

  

###### 접근 제어자 (Access Modifier)

- private
  - private가 붙은 변수, 메소드는 해당 클래스에서만 접근 가능
- default
  - 접근제어자를 별도로 설정하지 않는다면 접근제어자가 없는 메소드는 default 접근제어자가 되어 해당 패키지 내에서만 접근 가능
- protected
  - protected가 붙은 변수, 메소드는 동일 패키지내의 클래스 또는 해당 클래스를 상속받은(extends) 외부 패키지의 클래스에서 접근 가능
- public
  - 어떤 클래스에서라도 접근이 가능



###### 추상 클래스

- 여러 클래스들 안에는 각자의 필드와 메소드가 존재. 그 중 <span style="color:red">클래스 간 비슷한 필드와 메소드를 공통적으로 추출해 만들어진 클래스</span>

  - 키보드 A(누르면 불빛), 키보드 B(누르면 소리가 남) 키보드 C(잘 눌림) 가 있다고 생각. 이 세가지 키보드의 공통점은 키보드를 누르는 것. 공통적인 메소드(여기서는 키보드를 누르는 행위)를 추출하여 추상클래스 안에 두면 된다.
  - 실체 클래스는 실체가 드러나는 클래스, 추상 클래스는 실체 클래스의 공통적인 부분을 추출하여 어느정도 규격을 잡아놓은 추상적인 클래스
  - 실체 클래스는 실제 객체를 생성할 정도의 구체성을 가지는 반면,  <span style="color:red">추상 클래스는 아직 메소드와 내용이 추상적이기 때문에 객체 생성 불가</span>
  - 추상 클래스와 실체 클래스는 상속 관계

- 사용 목적

  - 공통된 필드와 메소드를 통일할 목적 - 유지 보수성을 높이고 통일성 유지

  - 실체 클래스 구현시, 시간 절약 - 강제로 주어지는 필드와 메소드를 이용하여 구현
  - 규격에 맞는 실체 클래스 구현

- 문법

  - 클래스 앞 abstract 키워드 붙임 (public abstract class 클래스명)

  - 추상 메소드 역시 메소드 리턴 타입 앞에 abstract 키워드 붙임 ([public | protected] abstract void 메소드명) - 구체적인 구현부는 없음

  - 해당 추상 클래스를 상속받는 실체 클래스들은 반드시 추상 메소드를 상속받아 재정의 해야 함

  - 추상 메소드는 실체 클래스에 맞게 구현 - 다형성 발생

  - 타입의 다형성 - 자동 타입변환을 발생시켜 실체 클래스 인스턴스처럼 사용 가능

    실체1 A = new 실체1();

    실체2 B = new 실체2();

    A.추상메소드();  // 자동 타입 변환

    B.추상메소드(); // 자동 타입 변환

  
> 메시지와 메소드는 다른 개념
  >
  > Movie는 DiscountPolicy의 인스턴스에 calculateDiscountAmount 메시지를 전송한다
  >
  > Movie 와 협력하는 객체가 AmountDiscountPolicy의 인스턴스라면 AmountDiscountPolicy에서 오버라이딩한 메소드 실행.
  >
  > Movie 와 협력하는 객체가 PercentDiscountPolicy의 인스턴스라면 PercentDiscountPolicy에서 오버라이딩한 메소드 실행.
  >
  > 이것이 다형성 !
  >
  > - 다형성(polymorphism)
  >
  >   > 같은 자료형에 여러가지 객체를 대입하여 다양한 결과를 얻어내는 성질
  >   >
  >   > 하나의 타입으로 다양한 실행 결과를 얻을 수 있으며 객체를 부품화하여 유지보수를 용이하게 함
  >   >
  >   > 컴파일 시간 의존성과 실행 시간 의존성이 다를 수 있다는 사실을 기반
  >   >
  >   > 이러한 객체지향의 특성을 이용해 서로 다른 메소드를 실행 가능하게 함
  >   >
  >   > 즉, <span style="color:red">동일한 메시지를 수신했을 때 객체의 타입에 따라 다르게 응답할 수 있는 능력</span>
  
  [출처](https://hosa.tistory.com/214)



###### 캡슐화

- <span style="color:red">객체의 필드(속성), 메소드를 하나로 묶고, 실제 구현 내용을 외부에 감추는 것</span>
- 외부 객체는 객체 내부의 구조를 얻지 못하며, 객체가 노출해서 제공하는 필드와 메소드만 이용 가능
- 외부의 잘못된 사용으로 인해 객체가 손상되는 것을 방지
- <span style="color:red">자바에서는 캡슐화된 멤버를 노출시킬 것인지 숨길 것인지를 결정하기 위해 접근 제한자(access modifier) 사용</span>



###### 상속

- 부모 클래스를 재사용하여 자식 클래스를 빠르게 개발 가능

- 반복된 코드의 중복을 감소

- 유지 보수의 편리성 제공 - 부모 클래스를 한 번만 수정하여 자식 클래스를 수정할 필요 없음

- 객체의 다형성을 구현 가능

- 일반적으로 상속의 목적은 메소드나 인스턴스 변수를 재사용하는 것이라 생각하지만, <span style="color:red">부모 클래스가 제공하는 모든 인터페이스를 자식 클래스가 물려받을 수 있다는 점도 있다</span>

  > 인터페이스는 객체가 이해할 수 있는 메시지의 목록을 정의
  >
  > 부모 클래스가 수신하는 메시지는 자식 클래스에서도 모두 수신 가능 
  >
  > 외부 객체는 두 클래스 모두 동일한 타입으로 간주

  [출처](https://webclub.tistory.com/156)



###### 상속의 종류

- 구현 상속(Implementation Inheritance) = 서브 클래스 : 순수하게 코드를 재사용하기 위한 목적 

- 인터페이스 상속 : 다형적인 협력을 위해 부모 클래스와 자식 클래스가 인터페이스를 공유할 수 있도록 상속

  > <span style="color:red">상속은 구현 상속이 아니라 인터페이스 상속을 위해 사용해야 함</span>
  >
  > 인터페이스 재사용 목적이 아니라 구현 재사용을 목적으로 상속하게 되면 변경에 취약한 코드 유발 가능
  >
  > - 자바의 인터페이스 
  >
  >   구현에 대한 고려 없이 다형적인 협력에 참여하는 클래스들이 공유 가능한 외부 인터페이스를 정의한 것



###### 협력

- <span style="color:red">인터페이스에 정의된 메시지를 통해서만 코드를 재사용하는 방법</span>

- 요청(request) : 객체는 다른 객체의 인터페이스에 공개된 행동을 수행하도록 요청

- 응답(response) : 요청 받은 객체는 자율적인 방법에 따라 요청을 처리한 후 응답을 보냄

  > <span style="color:red">객체가 다른 객체와 상호작용할 수 있는 유일한 방법은 메시지 전송 뿐</span>
  >
  > 메시지를 수신한 객체는 스스로의 결정에 따라 자발적으로 메시지를 처리할 방법 결정
  >
  > 이처럼 수신된 메시지를 처리하기 위한 자신만의 방법을 메소드라 함
  >
  > - 메소드의 구분으로 부터 다형성의 개념 시작
  > - 메소드를 호출한다 - 메시지를 전송한다
  >
  > > Movie는 DiscountPolicy 인터페이스에 정의된 calculateDiscountAmount 메시지를 전송
  > >
  > > 사실 AmountDiscountPolicy와 PercentDiscountPolicy도 이 오퍼레이션에 포함되어 있지만, Movie의 입장에서는 자신과 협력하는 객체가 누군지가 중요한 것이 아니라 calculateDiscountAmount 라는 메시지를 수신하는 것이 중요



###### 프로그래머의 역할

- 클래스 작성자(Class Creator) : 새로운 데이터 타입을 프로그램에 추가

  - 클라이언트 프로그래머에게 필요한 부분만 공개하고 나머지는 숨겨야 함

- 클라이언트 프로그래머 : 클래스 작성자가 추가한 데이터 타입을 사용

  - 필요한 클래스들을 엮어서 애플리케이션을 빠르고 안정적으로 구축시켜야 함

- 접근제어 메커니즘은 프로그래밍 언어 차원에서 클래스의 내부와 외부를 명확하게 경계 지을 수 있게
   하는 동시에, 클래스 작성자가 내부 구현을 은닉할 수 있게 해줌

  - 구현 은닉의 사용

    > 클래스 작성자는 내부의 구현을 무시하고 인터페이스만  알고 있어도 클래스 사용 가능
    >
    > - 알아야 할 지식의 양 감소
    >
    > 클라이언트 프로그래머는 인터페이스를 바꾸지 않는 한 외부에 미치는 영향을 걱정하지 않고도 내부 구현을 수정 가능
    >
    > - public 영역을 수정하지 않는다면 코드를 자유롭게 수정 가능



######  TEMPLATE METHOD 패턴 (GOF94)

- 부모 클래스에 기본적인 알고리즘의 흐름 구현. 중간에 처리를 자식 클래스에 위임하는 디자인 패턴

  > DiscountPolicy는 할인 여부와 요금 계산에 필요한 전체적인 흐름은 제공하지만, 실제로 요금을 계산하는 부분은 추상 메소드인 getDiscountAmount 메소드에 위임



###### 오버라이딩 vs 오버로딩

- 오버라이딩 

  > <span style="color:red">부모 클래스에 정의된 같은 이름, 같은 파라미터 목록을 가진 메소드</span>를 자식 클래스에서 재정의
  >
  > 자식 클래스의 메소드는 오버라이딩한 부모 클래스의 메소드를 가리기 때문에 외부에서는 부모 클래스가 보이지 않음(캡슐화)
  >
  > 코드 제공 클래스 : [슈퍼클래스]= 부모 클래스 = 부모 = 직계 조상 = 직접적인 조상 
  >
  > 코드 제공 받는 클래스 : [서브 클래스] = 자식 클래스 = 자식 = 직계 자손 = 직접적인 자손

- 오버로딩

  ><span style="color:red">메소드의 이름은 같지만, 제공되는 파라미터 목록이 다름</span>. 오버로딩한 메소드는 원래의 메소드를 가리지 않기 때문에 이 메소드들은 사이좋게 공존
  >
  >- 예시로 print()가 있다 - 인자로 String, int 등 다양한 형식이 와도 타입을 구분하지 않고 출력해준다.
  >
  >  1장 中 Money에서 하나는 Money 타입을, 하나는 long 타입의 파라미터를 받도록 정의 
  >
  >오버로딩은 상속의 개념이 아니다!



###### 업캐스팅과 다운캐스팅

- 업캐스팅
  - 자식 클래스가 <span style="color:red">부모 클래스를 대신</span> 하는 것
  - 묵시적 - 부모와 자식이 1:다 - 굳이 부모가 누구다 알아야 할 필요가 없음
- 다운캐스팅
  - 업캐스팅의 반대 개념
  - 명시적 - 업캐스팅과 반대로 부모에서 자식으로 가는 방향 - 자식이 많으므로 특정한 자식을 선택해야 함



###### 합성

- <span style="color:red">인터페이스에 정의된 메시지를 통해서만 </span> 코드를 재사용하는 방법

- 인터페이스에 정의된 메시지를 통해서만 재사용 가능 - 효과적인 캡슐화

 - 의존하는 인스턴스를 교체하는 것이 비교적  쉬움 - 설계의 유연

   > 상속은 <span style="color:red">클래스</span>를 통해 <span style="color:red">강하게 결합</span>
   >
   > 합성은 <span style="color:red">메시지</span>를 통해 <span style="color:red">느슨한 결합</span>
   >
   > - 상속이 부모 클래스의 코드와 자식 클래스의 코드를 컴파일 시점에 하나의 단위로 강하게 결합하는 반면, 합성은 인터페이스를 통해 약하게 결합. 
   >
   >   Movie는 DiscountPolicy가 외부에 calculateDiscountAmount 메소드를 제공한다는 사실만 알고 있고 내부 구현에 대해서는 알지 못함
   >
   > - 코드를 재사용하는 경우에는 상속보다 합성을 선호하는 것이 맞지만, 다형성을 위해인터페이스를 재사용하는 경우에는 상속과 합성을 조합해서 사용할 수 밖에 없음.
