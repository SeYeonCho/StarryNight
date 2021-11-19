# JAVA 11로 전환해야 하는 이유

## var 구문



Java에 var 구문이 처음 생긴 것이 Java 10부터입니다.

현재 LTS인 Java 11부터는 이를 통한 람다 타입 지원도 생겼습니다.

먼저 오해를 바로 잡자면, javascript의 var와 jaJava a의 var는 굉장히 다릅니다.

당연한 이야기지만,  javascript의 var에 대한 선입견으로 Java 의 var에 거부감을 가질 필요 또한 없습니다.

여하튼 각설하고, Java 의 최신 버전에서는 타입 추론 개념을 도입하였고, 사용할 수 있습니다.

그 올바른 사용 예시에 대해서 조금 말씀드리고자 합니다.

### for문

기본적으로 Java 8까지에서, 우리는 for문을

```java
for (Order order : orders){
    //...
}
```

와 같이 사용했다. 이것을, 

```java
for(var order : orders){
    //...
}
```

와 같이 사용할 수 있다. (타이핑의 간결함을 추구할 수 있다.)

### 람다

Java 11에서는 람다 인자에도 var를 허용한다.

이것이 중요한 이유가 무엇이냐면, 일반 람다의 경우에는 파라미터 어노테이션을 넣을 수 없었다.

만약 어노테이션을 넣고 싶다면 별도의 메소드를 사용하거나 익명 클래스를 정의해야했다.

```java
(@NotEmpty var email)->{
    // email이 NotEmpty인지 먼저 체크한다.
}
```

이러한 활용이 가능해졌다.

### 익명 클래스

사실 일반적인 상황에서의 var 사용은 지양되어야 한다고 생각한다. 왜냐하면, 가독성이 떨어지기 때문이다. 일반적으로 Java를 읽을 때 var로 타입이 적혀있다면, IDE를 사용하고 있지않다면 가독성이 현저히 떨어질 것이다.

그러나 익명클래스의 경우, var타입은 굉장히 매력적인 요소라고 생각한다.

```java
var supply = new Supplier<String>() {
    @Override
    public String get() {
    }
}
```

선언한 뒤 변수가 바뀔일도 없기때문에, 코드를 굉장히 편하게 작성할 수 있다.

### String 메서드 추가

클래스 기능 추가

- `isBlank`, `lines`, `strip`, `stripLeading`, `stripTrailing`

- isBlank() : 공백인지 아닌지 확인 (String.trim().isEmpty()의 결과와 같음)

- strip() : 문자열 앞, 뒤의 공백 제거 (trim에 비해 성능이 훨씬 좋고, trim이 U+0020 이하의 값만을 공백으로 인식해서 제거하는 반면 유니코드의 다양한 공백문자를 제거해주므로 이것을 사용하는 것을 권고)

- stripLeading() : 문자열 앞 공백 제거
- stripTrailing() : 문자열 뒤의 공백 제거
- lines() : 문자열을 라인 단위로 쪼개는 스트림을 반환
- repeat(n) : 지정된 수만큼 문자열을 반복하여 붙여서 반환

### java.nio.file.Files 클래스 유틸 메서드 추가

- Path writeString(Path, String, Charset, OpenOption): 파일에 문자열을 작성하고 Path로 반환한다. 파일 오픈 옵션에 따라 작동 방식을 달리하며, charset을 지정하지 않으면 utf-8 이 사용된다. (오버로딩 메서드로 writeString(Path, String, OpenOption) 존재.)

- String readString(Path, Charset): 파일 전체 내용을 읽어서 String으로 반환하고, 파일 내용을 모두 읽거나 예외가 발생하면 알아서 close 한다. charset을 지정하지 않으면 utf-8 이 사용된다. (오버로딩 메서드로 readString(Path) 존재.)

- boolean isSameFile(Path, Path): 두 Path 가 같은 파일을 가리키면 true, 아니면 false 를 반환한다. 파일이 실제로 존재하지 않아도, Path 를 기준으로 해서 같은 위치면 true 로 판단한다.

### Pattern.asMatchPredicate()

- Java 8 의 asPredicate는 matcher().find() 를 사용하는 것에 반해, asMatchPredicate() 는 matcher().match() 를 사용하는 Predicate를 반환한다.

### Predicate.not(Predicate)

- 인자로 받은 Predicate의 부정형 Predicate를 반환한다.

### Optional.isEmpty()

- Optional이 비어있을 때 true 반환.

### HTTP Client (JEP 321)

- Java 표준 HTTP 클라이언트 API.

- 그간 HTTP 통신을 위해 사용된 코드보다 성능이 개선됨.

- HTTP/1.1, HTTP/2, WebSocket을 지원한다. 

