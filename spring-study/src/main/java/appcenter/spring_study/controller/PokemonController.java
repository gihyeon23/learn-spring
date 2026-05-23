package appcenter.spring_study.controller;

import appcenter.spring_study.dto.pokemon.PokemonCatchResponseDto;
import appcenter.spring_study.dto.pokemon.PokemonRequestDto;
import appcenter.spring_study.dto.pokemon.PokemonResponseDto;
import appcenter.spring_study.dto.pokemon.PokemonSearchResponseDto;
import appcenter.spring_study.service.PokemonService;
import appcenter.spring_study.service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pokemon")
@RequiredArgsConstructor

public class PokemonController {

    private final PokemonService pokemonservice;

    @PostMapping("")
    public PokemonResponseDto regester(@RequestBody PokemonRequestDto request) {
        return pokemonservice.create(request);
    }

    @GetMapping("")
    public List<PokemonSearchResponseDto> searchAll() {
        return pokemonservice.searchAll();
    }

    @GetMapping("/{id}")
    public PokemonSearchResponseDto search(@PathVariable Long id) {
        return pokemonservice.search(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        pokemonservice.delete(id);
        return "포켓몬 삭제 완료";
    }

    @PatchMapping("/{pokemonId}/{trainerId}")
    public PokemonCatchResponseDto catchPokemon(
            @PathVariable Long pokemonId,
            @PathVariable Long trainerId
    ) {
        return pokemonservice.catchPokemon(trainerId, pokemonId);
    }
}
