package appcenter.spring_study.dto.pokemon;

import lombok.Getter;

@Getter
public class PokemonRequestDto {
    String pokemonName;
    String type;
    Integer level;
}
