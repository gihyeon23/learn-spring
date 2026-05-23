package appcenter.spring_study.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //lombok관련 친구들
//JPA는 기본생성자가 없으면 못들어온다고 한다. 그래서 필요함
//아무런 매개변수가 없는 생성자
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="trainer_id")
    private Long trainerId;

    @Column(name="trainer_name")
    private String trainerName;

    private String gender;

    private Integer age;

    public Trainer(String trainerName, String gender, Integer age) {
        this.trainerName = trainerName;
        this.gender = gender;
        this.age = age;
    } //내가 처음 등록
}
