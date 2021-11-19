# 스프링 부트 테스트 



## 단위테스트의 정의

단위 테스트(Unit Test)는 하나의 모듈을 기준으로 독립적으로 수행되는 가장 작은 단위의 테스트라고 정의되어있다. 여기서의 모듈을 애플리케이션에서 작동하는 하나의 기능 / 메소드로 이해한다면, 로그인 메소드에 대한 독립적인 테스트가 하나의 단위 테스트가 될 수 있다.

또한 FIRST라는 5가지 규칙을 명심하며 코드를 작성할 필요가 있다.

- Fast: 테스트는 빠르게 동작하여 자주 돌릴 수 있어야 한다.

- Independent: 각각의 테스트는 독립적이며 서로 의존해서는 안된다.

- Repeatable: 어느 환경에서도 반복 가능해야 한다.

- Self-Validating: 테스트는 성공 또는 실패로 bool 값으로 결과를 내어 자체적으로 검증되어야 한다.

- Timely: 테스트는 적시에 즉, 테스트하려는 실제 코드를 구현하기 직전에 구현해야 한다.

이것은 이론이고, 스프링 부트에서의 테스트는 큰 범주로 보면 세 가지 테스트로 나뉜다고 할 수 있습니다.

- @SpringBootTest 어노테이션을 활용한 통합 테스트 
  - // 프로젝트에 달린 모든 빈을 다 올리고 시작하는 테스트
- @WebMvcTest, @DataJpaTest 등을 활용한 Test
  - 
- 어떠한 디펜던시도 존재하지 않는 상황에서도 실행 가능한 테스트

사실 @WebMvcTest를 단위테스트로 표현할 수 있는가 없는가에 대해서는 여러가지 갑론을박이 존재합니다.

일체의 다른 디펜던시가 전혀 없는 상황에서 실행할 수 있는 테스트만을 단위테스트라고 이야기하기도하며, @WebMvcTest와 같이 하나의 기능(메서드) 만을 테스트 할 수 있는 환경을 만들어 놓고 테스트 하는 것도 단위테스트에 포함된다고 이야기하기도 합니다.

저는 굳이 어느 것이 맞다라고 이야기하거나, 어떤 것이 더 올바른 지를 이야기하고 싶지는 않습니다.

(굳이 따지면, 저는 모든 디펜던시를 제외하고 하는 테스트만이 단위테스트라고 불리울만하다고 생각하긴합니다.)

주의사항 

- 스프링을 실행하지 않는 단위테스트를 하고 싶다면, DI 중 필드 주입은 존재해선 안됩니다. (스프링의 실행 없이 의존성 주입이 불가능하므로)
- @SpringBootTest를 사용할 경우, 정말 믿기지 않을 정도로 시간이 오래 걸리고 무겁습니다(서버 올리는데 천만년걸려요)

- 아무런 연관관계가 없는 단위테스트의 예시

  ```java
  public class SsafyServiceUnitTest{
      private SsafyRepository ssafyRepository;
      private SsafyService ssafyService;
      
      @Before
      public void setUp(){
          ssafyRepository = new SsafyRepository();
          ssafyRepository.add(new SsafyMember("김주현"));
          ssafyService = new SsafyService();
      }
      
      @Test
      public void ssafy멤버조회성공(){
          SsafyMember sm = ssafyService.findByName("김주현");
          assertEquals("김주현", sm.getName());
      }
  }
  ```

  그러나 이렇게 테스트를 할 경우, 데이터베이스 연동 로직이 있는 경우 에러가 발생합니다. 따라서 Mock객체를 사용해야합니다.

  ```java
  public class SsafyServiceUnitTest{
     
      
      @Test
      public void ssafy멤버조회성공(){
          //given
          SsafyRepository ssafyRepo = Mockito.mock(SsafyRepository.class);
          Mockito.when(ssafyRepo.findByName("김주현")).thenReturn(new SsafyMember("김주현"));
          ssafyService = new SsafyService(ssafyRepo);
          //when
          SsafyMember sm = ssafyService.findByName("김주현");
          
          //then
          assertEquals("김주현", sm.getName());
      }
  }
  ```

  이 경우 Mockito 내부의 인터셉터 클래스에서 interceptAbstract로 넘어가 올바른 결과를 돌려줍니다.


## Given-When-Then pattern

```java
@DisplayName("테스트 이름") 
@Test 
void Test() {    
    // given    
    
    // when    
    
    // then 
}
```

- @Test 해당 메소드가 단위 테스트임을 명시
- @DisplayName으로 이름 부여
- 라이브 템플릿으로 저장해서 빠르게 작성합시다.

### Given

테스트를 위해 준비하는 과정. 테스트에 사용하는 변수, 입력 값 등에 대한 정의 / Mock 객체를 정의하는 구문도 포함된다.

### When

실제로 액션을 하는 테스트를 실행하는 구간

### Then

테스트를 검증하는 과정. 예상한 값, 실행 값 등을 검증

## 알아두어야 하는 어노테이션

@SpringBootTest

- 통합 테스트를 위한 어노테이션. @Extention과 함께 사용해야한다.(@RunWith는 JUnit4에서 사용하던 어노테이션입니다.)

- 기본적으로 @ExtendWith(SpringExtension.class)를 내장하고 있으며, 필요에 따라서 커스터마이징해야할 때 @ExtendWith와 함께 사용한다.

- Spring Security 환경에서 사용시 @AutoConfigureMockMvc 어노테이션과 함께 사용합니다

- ```java
  //내장 메타 어노테이션
  @Target({ElementType.TYPE})
  @Retention(RetentionPolicy.RUNTIME)
  @Documented
  @Inherited
  @BootstrapWith(SpringBootTestContextBootstrapper.class)
  @ExtendWith({SpringExtension.class})
  ```

@ExtendWith

- 테스트에 필요한 것을 커스터마이징하기 위한 어노테이션. @ExtendWith(MockitoExtension.class)- Service 테스트 시 사용, @ExtendWith(RestDocumentationExtension.class) 등 다양한 확장을 제공한다. 필요한 것을 사용하시면 됩니다.

@Mock

- Mock 객체를 만들어 반환해주는 어노테이션

@Spy: Stub하지 않은 메소드들은 원본 메소드 그대로 사용하는 어노테이션

- 주로 Encrytion 메소드 같은 것들을 이 어노테이션을 붙여서 씁니다.

@InjectMocks: @Mock 또는 @Spy로 생성된 가짜 객체를 자동으로 주입시켜주는 어노테이션

- Mock 객체를 주입해서 사용할 수 있도록 만든 객체
- 예를들어, userService를 테스트한다고 가정하면, userRepo를 Mock객체로 만든 이후 userService에 @InjectMocks를 붙이면 자동으로 Mock객체가 주입된다.

@BeforeEach

- 테스트 시작 전 가장 먼저 실행하는 어노테이션
- init 용도로 많이 사용

@MockBean && @SpyBean

- 이건 Mockito에서 제공하는 어노테이션이 아니라 Spring에서 제공하는 어노테이션이다.
- 그러나 단위테스트시에는 가능하면 사용을 지양해야하는 어노테이션이다.
- 그러나 굉장히 비극적이게도, 안쓰는게 생각보다 쉽진 않습니다...
- 이 사항에 대해 봐두면 좋을 글 : https://jojoldu.tistory.com/320

**@WebMvcTest**(Controller 테스트 시 사용)

- @Controller, @RestController가 설정된 클래스들을 찾아 메모리에 생성한다. 

- @Service나 @Repository가 붙은 객체들은 테스트 대상이 아닌 것으로 처리되기 때문에 생성되지 않는다.

- @WebMvcTest가 설정된 테스트 케이스에서는 서블릿 컨테이너를 모킹한 MockMvc타입의 객체를 목업하여 컨트롤러에 대한 테스트코드를 작성할 수 있다.

- @WebMvcTest 어노테이션을 사용하면 MVC 관련 설정인 @Controller, @ControllerAdvice, @JsonComponent와 Filter, WebMvcConfigurer, HandlerMethodArgumentResolver만 로드되기 때문에, 실제 구동되는 애플리케이션과 똑같이 컨텍스트를 로드하는 @SpringBootTest 어노테이션보다 가볍게 테스트 할 수 있다.

- 단, Spring Security 사용 시에 기본 Spring Security 설정을 로드하고, 사용자가 WebSecurityConfigurerAdapter  추상 클래스를 extends 해서 custom 한 클래스를 사용하게 되는 경우, 그에 선언된 url filter를 등록해야만 한다.

  - 이에 따른 해결책은 두가지가 있다. (다른 방법도 있는데, 제가 시도해서 의미 있었던 방법)
    - 첫번째로는, secure = false 옵션을 줘서 Spring Security 인증을 우회하는 방법
    - 두번째로는, excludeFilter를 통해 SecurityConfig filter를 우회하고, @WithMockUser를 통해 가짜 인증 사용자를 생성하여 테스트하는 것이다.

  

이정도 어노테이션만 알아도 테스트에 전혀 문제 없습니다!





## 단위 테스트 작성 예시

참조 링크 : https://mangkyu.tistory.com/144



## API 단위 테스트

참조 링크 : https://mangkyu.tistory.com/145



# Spring rest docs

프로젝트를 진행하면서 Spring API 문서 자동화의 필요성을 느꼈다.

이것을 수행하기 위한 툴에는 대표적으로 Swagger와 Spring Rest Docs가 있다.

 두 가지의 장단점이 있겠지만 이번 프로젝트에서 Spring Rest Docs를 선택한 이유는 Swagger 사용시 비즈니스 로직과 관련 없는 어노테이션이 많이 추가되기 때문에, 프로덕션 코드(프로젝트 로직이 포함되며, 어플리케이션에서 실행되는 시스템의 일부분)이 오염된다고 생각했다.

단적인 예로, Swagger를 적용한 아래의 코드를 보자.

```java
@RestController
@Api(value = "SwaggerTestController")
@RequestMapping("/v1/test")
public class SwaggerTestController {
    @ApiOperation(value = "test", notes = "테스트입니다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "OK~!"),
        @ApiResponse(code = 404, message = "page not found!!!")
    })
    @GetMapping(value = "/board")
    public Map<String, String> selectBoard(@ApiParam(value = "게시판번호", required = true, example = "1") @RequestParam String no) {
        Map<String, String> result = new HashMap<>();
        result.put("test title", "테스트");
        result.put("test content", "테스트 내용");
        return result;
    }
}
```

가뜩이나 복잡한 컨트롤러 코드가 더없이 복잡해진 것을 확인할 수 있다.

따라서 Spring rest docs를 적용하고자 한다.

참조 자료

우아한 형제들 기술 블로그 : https://techblog.woowahan.com/2597/

Spring Rest Docs 공식 문서 : https://docs.spring.io/spring-restdocs/docs/current/reference/html5/#getting-started-using-the-snippets

### 1. build.gradle 설정

​	공식문서에 의하면, 추가해야할 코드들은 아래와 같이 정리할 수 있다.

```java
// 의존성 추가  
dependencies {
    asciidoctor 'org.springframework.restdocs:spring-restdocs-asciidoctor:{project-version}' 
    testCompile 'org.springframework.restdocs:spring-restdocs-mockmvc:{project-version}' 
}

// Asciidoctor 플러그인을 적용하여  AsciiDoc 파일을 컨버팅합니다.
plugins { 
    id "org.asciidoctor.convert" version "1.5.9.2"
}

// build/generated-snippets 경로에 스니펫이 생성됩니다.
ext { 
    snippetsDir = file('build/generated-snippets')
}


// gradle build시 test -> asciidoctor 순으로 실행됩니다.
asciidoctor { 
    inputs.dir snippetsDir 
    dependsOn test 
}


// gradle build시 ./build/asciidoc/html/ 에 html 파일이 생성됩니다.
bootJar {
    dependsOn asciidoctor
    from ("${asciidoctor.outputDir}/html5") {
        into 'static/docs'
    }
}

test { 
    outputs.dir snippetsDir
}
```

다만 이것을 그대로 적용하면 오류가 발생하는데, 

참고 자료

Gradle 공식문서 : https://docs.gradle.org/current/userguide/dependency_management_for_java_projects.html#sec:configurations_java_tutorial

StackOverFlow : https://stackoverflow.com/questions/44493378/whats-the-difference-between-implementation-api-and-compile-in-gradle

compile이 deprecated 되었으며, Gradle 최신 버전에서 implemetation이 compile을 대체한다고 공식문서에서 언급되어있다.

따라서 testcompile -> testimplements로 변경합니다.

```java
testCompile 'org.springframework.restdocs:spring-restdocs-mockmvc' 
=> testImplementation 'org.springframework.restdocs:spring-restdocs-mockmvc'
```

이렇게 환경설정이 완료되었다면, 테스트 코드를 작성합시다.





따라서 단위테스트를 진행하기 위해서 메소드별로 테스트를 진행해야한다고 생각했고,

각각의 Controller와 Service에 테스트를 진행하기로 결론 내렸다.

Fast의 원칙에 따라 테스트는 가능한 한 빠르게 동작해야하고, Controller 테스트에 있어서 가장 적합한 것은 @WebMvcTest라고 보았다.

따라서 Controller 단의 테스트에는 @WebMvcTest를, Service Layer의 테스트에는 @ExtendWith(MockitoExtension.class)를 사용할 예정이다.

(어노테이션에 대한 설명은 하지 않겠습니다.)

```java
@ExtendWith(RestDocumentationExtension.class) // JUnit 5 사용시 문서 스니펫 생성용
@WebMvcTest(controllers = UserApiController.class, excludeFilters = {
@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)}
)
@MockBean(JpaMetamodelMappingContext.class) // @EnableJPaAuditing 사용시 추가해야하는 어노테이션
@AutoConfigureRestDocs
```

이것은 Test 환경설정을 위해 사용하는 어노테이션이다.

@AutoConfigureRestDocs는 Spring Rest docs을 사용하기 위한 MockMvc 설정을 해주는 어노테이션인데,

```java
@ExtendWith(RestDocumentationExtension.class) 
@WebMvcTest(UserApiController.class)  
@ActiveProfiles("test") 
@MockBean(JpaMetamodelMappingContext.class) 
class UserApiControllerTest {

    private MockMvc mockMvc;

    @BeforeEach
    public void setup(WebApplicationContext webApplicationContext,
        RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .apply(documentationConfiguration(restDocumentation))
            .apply(sharedHttpSession())
            .build();
    }
}
```

이와같이 수동으로 설정해주는 방법도 있고,

```java
@ExtendWith(RestDocumentationExtension.class) 
@WebMvcTest(UserApiController.class)  
@ActiveProfiles("test") 
@MockBean(JpaMetamodelMappingContext.class) 
@AutoConfigureRestDocs 
class UserApiControllerTest {

    @Autowired
    private MockMvc mockMvc;
```

이렇게 자동으로 세팅할 수도 있다. 원하는 방식을 선택하면 된다. (단, 저는 utf-8 세팅을 위해서 @AutoConfigureRestDocs를 사용하지 않고 수동으로 세팅했습니다.)

```java
    @BeforeEach
    public void setup(WebApplicationContext webApplicationContext,
        RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .apply(documentationConfiguration(restDocumentation))
            .apply(sharedHttpSession())
            .addFilters(new CharacterEncodingFilter("UTF-8", true))
            .build();
    }
```



## Spring Rest Docs 적용법

올바른 단위테스트 코드를 작성하였고, 실행되었다면

```java
@ExtendWith(RestDocumentationExtension.class) // JUnit 5 사용시 문서 스니펫 생성용
@WebMvcTest(controllers = UserApiController.class, excludeFilters = {
    @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)}
)
@MockBean(JpaMetamodelMappingContext.class) // @EnableJPaAuditing 사용시 추가해야하는 어노테이션
@AutoConfigureMockMvc(addFilters = false)
class UserApiControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    public void setup(WebApplicationContext webApplicationContext,
        RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .apply(documentationConfiguration(restDocumentation))
            .apply(sharedHttpSession())
            .addFilters(new CharacterEncodingFilter("UTF-8", true))
            .build();
    }

    @DisplayName("회원가입 - 모든 유효성 검사에 통과했다면, 회원가입 성공")
    @Test
    public void createUser_success() throws Exception {
        //given
        SaveTeacherRequest teacherRequest = SaveTeacherRequest.builder()
            .email("hello@naver.com")
            .password("asdasd")
            .username("juhu")
            .nickname("juhu")
            .gender(0)
            .tel("010-0000-0000")
            .zipCode("12345")
            .street("road 17")
            .detailedAddress("juhu")
            .role("ROLE_PTTEACHER")
            .major("재활")
            .certificates(new ArrayList<>())
            .careers(new ArrayList<>())
            .price(1000)
            .description("설명설명")
            .snsAddrs(new ArrayList<>())
            .build();
        //when
        doNothing().when(userService).signup(teacherRequest);

        //then
        mockMvc.perform(post("/api/user/teacher")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(teacherRequest)))
            .andDo(print())
            .andExpect(status().isOk()) // 201 isCreated()
    }
}
```

이 코드가 나온다.

여기에

```java
            .andDo(document("userApi/signup/successful", requestFields(
                fieldWithPath("email").type(JsonFieldType.STRING)
                    .description("The user's email address"),
                fieldWithPath("password").type(JsonFieldType.STRING)
                    .description("The user's password"),
                fieldWithPath("username").type(JsonFieldType.STRING)
                    .description("The user's username"),
                fieldWithPath("nickname").type(JsonFieldType.STRING)
                    .description("The user's nickname"),
                fieldWithPath("gender").type(JsonFieldType.NUMBER)
                    .description("The user's gender"),
                fieldWithPath("tel").type(JsonFieldType.STRING)
                    .description("The user's tel"),
                fieldWithPath("zipCode").type(JsonFieldType.STRING)
                    .description("The user's zipCode"),
                fieldWithPath("street").type(JsonFieldType.STRING)
                    .description("The user's street"),
                fieldWithPath("detailedAddress").type(JsonFieldType.STRING)
                    .description("The user's detailedAddress"),
                fieldWithPath("role").type(JsonFieldType.STRING)
                    .description("The user's role"),
                fieldWithPath("major").type(JsonFieldType.STRING)
                    .description("The user's major"),
                fieldWithPath("certificates").type(JsonFieldType.ARRAY)
                    .description("The user's certificates"),
                fieldWithPath("careers").type(JsonFieldType.ARRAY)
                    .description("The user's careers"),
                fieldWithPath("price").type(JsonFieldType.NUMBER)
                    .description("The user's price"),
                fieldWithPath("description").type(JsonFieldType.STRING)
                    .description("The user's description"),
                fieldWithPath("snsAddrs").type(JsonFieldType.ARRAY)
                    .description("The user's snsAddrs")
            )));
```

이 코드만 붙이면, Spring Rest docs용으로 완성되었다고 볼 수 있다.
