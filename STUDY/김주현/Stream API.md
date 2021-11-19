# Stream API

참조링크 : https://mangkyu.tistory.com/112

- ## 소개

  객체지향 언어라하면 대표적으로 떠오르는 두 언어가 있다.

  Java와 C++이다.

  당연한 이야기이지만, Java는 객체지향 언어이므로 함수형 프로그래밍이 불가능하다.

  하지만 JDK 8버전 부터 Stream API 와 Lambda, Functional Interface를 지원하게 되었고, Java를 이용하여 함수형으로 프로그래밍할 수 있는 API 들을 제공한다.

- ## Stream API

  데이터를 추상화 & 처리하는 데에 자주 사용되는 함수들을 정의해둔 API

  데이터의 추상화라 함은, 데이터의 종류에 상관 없이 같은 방식으로 데이터를 처리할 수 있다는 것을 의미하고, 그에 따라 재사용성을 높일 수 있다는 것을 의미한다.

  Stream API 사용 전의 코드

  ```java
  // Stream 사용 전 
  String[] nameArr = {"IronMan", "Captain", "Hulk", "Thor"} List<String> nameList = Arrays.asList(nameArr); // 원본의 데이터가 직접 정렬됨 
  Arrays.sort(nameArr); 
  Collections.sort(nameList); 
  for (String str: nameArr) { 
      System.out.println(str); 
  } 
  for (String str : nameList) { 
      System.out.println(str); 
  }
  
  // Stream 사용 후
  String[] nameArr = {"IronMan", "Captain", "Hulk", "Thor"} 
  List<String> nameList = Arrays.asList(nameArr); // 원본의 데이터가 아닌 별도의 Stream을 생성함 
  Stream<String> nameStream = nameList.stream(); 
  Stream<String> arrayStream = Arrays.stream(nameArr); // 복사된 데이터를 정렬하여 출력함 
  nameStream.sorted().forEach(System.out::println); arrayStream.sorted().forEach(System.out::println);
  ```

  

- ## 특징

  - 원본 데이터를 변경하지 않음

    ```java
    List<String> sortedList = nameStream.sorted().collect(Collections.toList())
    ```

    이 경우에, strStream의 원본에 변경이 일어나지 않는다.

  - 재사용이 불가능하다

    ```java
    userStream.sorted().forEach(System.out::print); // 스트림이 이미 사용되어 닫혔으므로 에러 발생 
    int count = userStream.count(); 
    // IllegalStateException 발생 
    java.lang.IllegalStateException: stream has already been operated upon or closed at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:229) at java.util.stream.ReferencePipeline.noneMatch(ReferencePipeline.java:459)
    ```

    

  - 내부 반복으로 작업 처리

    ```java
    // 반복문이 forEach라는 함수 내부에 숨겨져 있다.
    nameStream.forEach(System.out::println);
    ```

    

`