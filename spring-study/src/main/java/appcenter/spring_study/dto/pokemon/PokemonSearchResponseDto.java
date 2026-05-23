package appcenter.spring_study.dto.pokemon;

import lombok.Getter;

@Getter
public class PokemonSearchResponseDto {
    Long pokemonId;
    Long trainerId;
    String pokemonName;
    String type;
    Integer level;

    public PokemonSearchResponseDto(Long pokemonId, Long trainerId, String pokemonName, String type, Integer level) {
        this.pokemonId = pokemonId;
        this.trainerId = trainerId;
        this.pokemonName = pokemonName;
        this.type = type;
        this.level = level;
    }
}
