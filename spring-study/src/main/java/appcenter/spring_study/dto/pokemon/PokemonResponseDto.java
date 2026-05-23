package appcenter.spring_study.dto.pokemon;

import lombok.Getter;

@Getter
public class PokemonResponseDto {

    private final String message;

    public PokemonResponseDto(String message) {
        this.message = message;
    }
}
