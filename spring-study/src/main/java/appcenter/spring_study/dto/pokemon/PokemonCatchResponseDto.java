package appcenter.spring_study.dto.pokemon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class PokemonCatchResponseDto {
    private String pokemonName;
    private String trainerName;

    public PokemonCatchResponseDto(String pokemonName, String trainerName) {
        this.pokemonName = pokemonName;
        this.trainerName = trainerName;
    }
}