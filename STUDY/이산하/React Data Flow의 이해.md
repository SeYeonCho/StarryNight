# React Data Flow의 이해

## Redux

만든 사람 : Dan Abramov, Kent C. Dodds 

![image-20210906090639390](React Data Flow의 이해.assets/image-20210906090639390.png)

![image-20210906090911542](React Data Flow의 이해.assets/image-20210906090911542.png)

- 멘토스 : 비동기 코드
- 콜라 : 상태변화 변화 코드

![image-20210906091034878](React Data Flow의 이해.assets/image-20210906091034878.png)

![image-20210906091056328](React Data Flow의 이해.assets/image-20210906091056328.png)

![image-20210906091123219](React Data Flow의 이해.assets/image-20210906091123219.png)

![image-20210906091154794](React Data Flow의 이해.assets/image-20210906091154794.png)

![image-20210906091236621](React Data Flow의 이해.assets/image-20210906091236621.png)

![image-20210906091252859](React Data Flow의 이해.assets/image-20210906091252859.png)

![image-20210906091314007](React Data Flow의 이해.assets/image-20210906091314007.png)

![image-20210906091329574](React Data Flow의 이해.assets/image-20210906091329574.png)

![image-20210906091349740](React Data Flow의 이해.assets/image-20210906091349740.png)

- 순수 함수 : 어떤 파라미터를 넣어도 리턴해주는 상태가 항상 동일.
- 리듀서는 놀랍게도 맵 리듀스와 비슷한 그것이 맞음

![image-20210906091442844](React Data Flow의 이해.assets/image-20210906091442844.png)

![image-20210906091514212](React Data Flow의 이해.assets/image-20210906091514212.png)

- 한번에 이해할 수는 없다

- 전체 플로우를 한번 살펴보고 부분에 대한 이해로 넘어가자

![image-20210906091549139](React Data Flow의 이해.assets/image-20210906091549139.png)

![image-20210906091610775](React Data Flow의 이해.assets/image-20210906091610775.png)

뷰.js 와 뷰엑스는 리액트에 비해 단계가 간략화되어있고(리듀서가 없음) 좀더 간단하지만 비슷한 flow를 가지고 있다.