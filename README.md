# 도메인 설명
핵심 도메인은 Trainer와 Pokemon이다.

Trainer는 트레이너의 이름, 성별, 나이를 저장하고, Pokemon은 포켓몬의 이름, 타입, 레벨을 저장합니다.

또한 포켓몬이 어떤 트레이너에게 잡혔는지를 표현하기 위해 Pokemon 엔티티에 Trainer와의 연관관계를 설정하였습니다.

이를 통해 한 명의 트레이너가 여러 마리의 포켓몬을 가질 수 있는 1:N 관계를 구현하였습니다.
포켓몬이 트레이너에게 잡히는 기능은 catchBy 메서드를 통해 구현하여, 단순한 setter보다 도메인 의미가 잘 드러나도록 하였습니다.

```text
Trainer 1 ─── N Pokemon
```
<img width="1192" height="792" alt="image" src="https://github.com/user-attachments/assets/2c64e3b4-1b49-4c3d-b7ff-09cbdb00d299" />

# API 명세

### Trainer API

# API 명세

# API 명세

### Trainer API

| 기능 | 메서드 | URI | 요청 DTO | 응답 DTO | 상태 코드 |
|---|---|---|---|---|---|
| 트레이너 등록 | POST | `/api/trainer` | `TrainerRequestDto` | `TrainerResponseDto` | `200 OK` |
| 트레이너 단건 조회 | GET | `/api/trainer/{id}` | 없음 | `TrainerSearchResponseDto` | `200 OK` |
| 트레이너 전체 조회 | GET | `/api/trainer` | 없음 | `List<TrainerSearchResponseDto>` | `200 OK` |
| 트레이너 삭제 | DELETE | `/api/trainer/{id}` | 없음 | 없음 또는 문자열 | `200 OK` |

---

### Pokemon API

| 기능 | 메서드 | URI | 요청 DTO | 응답 DTO | 상태 코드 |
|---|---|---|---|---|---|
| 포켓몬 등록 | POST | `/api/pokemon` | `PokemonRequestDto` | `PokemonResponseDto` | `200 OK` |
| 포켓몬 단건 조회 | GET | `/api/pokemon/{id}` | 없음 | `PokemonSearchResponseDto` | `200 OK` |
| 포켓몬 전체 조회 | GET | `/api/pokemon` | 없음 | `List<PokemonSearchResponseDto>` | `200 OK` |
| 포켓몬 삭제 | DELETE | `/api/pokemon/{id}` | 없음 | 없음 또는 문자열 | `200 OK` |
| 포켓몬 포획 | PATCH | `/api/pokemon/{pokemonId}/{trainerId}` | 없음 | `PokemonCatchResponseDto` | `200 OK` |

## PostMan 사용
<img width="1209" height="758" alt="image" src="https://github.com/user-attachments/assets/abe1a154-e463-4237-af99-4ef34c47bba7" />
포켓몬을 잡았기 때문에 trainerId에 값이 들어갔다. 처음에는 NULL이었음, 즉  리자몽은 트레이너 1에게 잡힌 것 이다.

---

### 강의에서 배우 내용 중  이번에 직접 써본 것 3가지
1. HTTP강의에서 API URL은 행위가 아니라 리소스를 기준으로 만들어야 한다고 배웠다. 그래서 트레이너 관련 기능은  `@RequestMapping(”/api/trainer”)` , 포켓몬 관련 기능은 `@RequestMapping("/api/pokemon")` 와 같이 공통 URL을 리소스 기준으로 분리하였다.
2. 트레이너 등록 API는 클라이언트로부터 이름, 성별, 나이와 같은 데이터를 JSON 형식으로 전달받아야한다. 이때 요청 Body에 들어온 Json를 알맞는 형식으로 반환하기 위해`@RequestBody TrainerRequestDto request` 를 사용하였다.
3. 리포지토리를 사용할 때는 Spring Data JPA의 JpaRepository를 상속하면 save, findById, findAll, deleteById와 같은 기본 CRUD 메서드를 사용할 수 있다.
    - 강의에서는 `findbyname`을 사용하떄문에 추가적인 조치를 해 주었지만 나는 추가하고 싶지 않아 `findbyname`이 아닌 `findbyid`를 사용했다.
### 왜 Controller에서 엔티티를 직접 받지 않고 DTO로 분리했는지 본인의 언어로 답하기
DTO를 사용하면서 Entity와 요청/응답 구조를 분리하게 되었다. 그래서 클라이언트가 보내는 데이터와 응답하는 데이터를 Entity와 독립적으로 관리할 수 있게 된거 같다. 중간에 많이 수정하게 되었는데 그때 entity 구조가 바뀌어도 dto코드는 수정하지 않아도 되서 독립적으로 관리하는것의 소중함을 느꼈다.
### 막혔던 부분 1가지와 어떻게 해결했는지
<aside>
GETTER을 쓰지 않아서 발생한 문제

- TrainerResponseDto를 JSON으로 바꾸려면 Jackson이 객체 안의 값을 읽을 수 있어야 해서 getter를 추가 현재 응답 DTO는 원래 이렇게 생겼다.

public class TrainerResponseDto {
String message;

```
  public TrainerResponseDto(String message) {
      this.message = message;
  }
```

}

message 필드는 private은 아니지만, 외부에서 읽을 수 있는 getMessage() 메서드가 없다. Spring Boot는 응답 객체를 JSON으로 만들 때 보통
Jackson을 쓰고, Jackson은 기본적으로 getMessage() 같은 public getter를 보고 JSON 필드를 만든다.

그래서 getter가 없으면 Jackson 입장에서는:

{
}

이렇게 아무것도 보이지 않는다.

@Getter를 붙이면 Lombok이 컴파일 시점에 아래 메서드를 만들어준다. :

```java
public String getMessage() {
return this.message;
}
```

그러면 Spring이 TrainerResponseDto를 JSON으로 정상 변환할 수 있어서:

```java
{
"message": "받아라"
}
```

처럼 응답

</aside>

<aside>
❓

조회 응답 DTO에 @Getter가 없음
src/main/java/appcenter/spring_study/dto/TrainerSearchReponseDto.java:3

```java
public class TrainerSearchReponseDto {
String trainerName;
String gender;
Integer age;
}
```

@RestController가 객체를 JSON으로 바꿀 때 getter가 필요한다.

```java
@Getter
public class TrainerSearchReponseDto {
private String trainerName;
private String gender;
private Integer age;
}
```

</aside>
