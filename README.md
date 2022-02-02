# Class Diagram
<img width="1768" alt="2" src="https://user-images.githubusercontent.com/86584999/152181000-2176e632-eea4-40da-a68e-232892d462ab.png">
   
# SOLID
### SRP 단일책임 원칙   
하나의 클래스 혹은 메소드는 하나의 책임만 가져야 합니다.   
- **예) Tribe 추상클래스와 Human, Orc, Elf 하위 클래스**   
getTribeName() 메소드를 추상 클래스에서 구현했을 경우 분기처리가 진행되어야 하지만   
구현하지 않고 하위 클래스에서 구현했기 때문에 단일 책임 원칙을 지킬 수 있습니다.   
   
### OCP 개방-폐쇄 원칙   
소프트웨어 요소는 확장에는 열려 있으나 변경에는 닫혀 있어야 합니다.   
요구사항의 변경이나 추가 사항이 발생할 때, 기존의 코드 수정은 일어나지 않고 기존 구성요소를 쉽게 확장해서 재사용이 가능해야 합니다.   
- **예) WeaponServiceImpl equipWeapon() 메소드**   
파라미터로 받는 Tribe와 Weapon의 하위 클래스가 추가 혹은 변경이 되어도   
equipWeapon() 메소드에는 변경이 일어나지 않습니다.   
   
### LIP 리스코프 치환 원칙   
프로그램의 객체는 프로그램의 정확성을 깨뜨리지 않으면서   
하위 타입의 인스턴스로 바꿀 수 있어야 합니다.   
하위 클래스의 인스턴스는 상위형 객체 참조 변수에 대입해 상위 클래스의 인스턴스 역할을 하는데 문제가 없어야 합니다.   
- **예) Sword 추상클래스와 LongSword, ShortSword 하위 클래스**   
하위 타입의 인스턴스를 생성하고 오버라이딩된 메소드를 사용하는 코드   
<img width="360" alt="3" src="https://user-images.githubusercontent.com/86584999/152187987-46f7a2ac-2491-46f7-81cb-f111be23dd8c.png">   

<img width="437" alt="4" src="https://user-images.githubusercontent.com/86584999/152187968-9f71c1e4-67e6-41c5-bdbd-d88836ac5f7a.png">   

<img width="1171" alt="4" src="https://user-images.githubusercontent.com/86584999/152188816-5d3f7e0a-87a9-4baf-a314-c06f624d0adc.png">   
   
### ISP 인터페이스 분리 원칙   
특정 클라이언트를 위한 인터페이스 여러 개가 범용 인터페이스 하나보다 좋습니다.   
인터페이스를 분리하면 인터페이스 자체가 변해도 클라이언트에는 영향을 주지 않습니다.   
즉, 인터페이스가 명확해지고 대체 가능성이 높아집니다.   
- **예) WeaponService, SkillService 인터페이스**   
하위 클래스는 불필요한 기능까지 구현 해야하는 문제가 발생해서는 안됩니다.   
따라서, TribeService를 무기 관련 인터페이스와 스킬 관련 인터페이스로 분리하여 각각의 역할을 담당합니다.   
WeaponService는 무기와 관련된 역할을 맡고있고 SkillService는 스킬과 관련된 역할을 맡고있습니다.   
WeaponService 인터페이스 자체가 변해도 Human, Orc, Elf에는 영향을 주지 않습니다.   
   
### DIP 의존관계 역전 원칙   
추상화에 의존하고 구체화에 의존하면 안됩니다.   
구현 클래스가 아닌 인터페이스 혹은 추상클래스에 의존하라는 뜻으로   
구현체를 유연하게 변경 할 수 있도록 만들어줍니다.   
- **예) AppConfig객체, WeaponServiceTest**   
AppConfig를 통해 외부에서 Service의 하위 객체를 생성하고 main에서 사용하여   
Service 구현 클래스가 아닌 Service 인터페이스에 의존하게 만들었습니다.   
이러한 방식을 통해 Service의 구현 객체는 유연하게 변경될 수 있습니다.   
새로운 구현 클래스가 생성되었다면 AppConfig 내부의 메소드만 바꿔주면 쉽게 변경됩니다.   
