package appcenter.spring_study.dto.trainer;

import lombok.Getter;

@Getter
public class TrainerSearchResponseDto {
    Long trainerId;
    String trainerName;
    String gender;
    Integer age;

    public TrainerSearchResponseDto(Long trainerId, String trainerName, String gender, Integer age) {
        this.trainerId = trainerId;
        this.trainerName = trainerName;
        this.gender = gender;
        this.age = age;
    }
}
