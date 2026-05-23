package appcenter.spring_study.dto.trainer;

import lombok.Getter;

@Getter //컨트롤러에서 Json으로 변환할 때 Getter이 필요하다.
public class TrainerResponseDto {

    private final String message;

    public TrainerResponseDto(String message) {
        this.message = message;
    }
}
