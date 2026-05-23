package appcenter.spring_study.service;

import appcenter.spring_study.dto.pokemon.PokemonCatchResponseDto;
import appcenter.spring_study.dto.pokemon.PokemonRequestDto;
import appcenter.spring_study.dto.pokemon.PokemonResponseDto;
import appcenter.spring_study.dto.pokemon.PokemonSearchResponseDto;
import appcenter.spring_study.entity.Pokemon;
import appcenter.spring_study.entity.Trainer;
import appcenter.spring_study.repository.PokemonRepository;
import appcenter.spring_study.repository.TrainerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Pool;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor //lombok친구 덕분
public class PokemonService {

    private final PokemonRepository pokemonRepository;
    private final TrainerRepository trainerRepository;

    public PokemonResponseDto create(PokemonRequestDto request) {//포켓몬 등록
        Pokemon pokemon = new Pokemon(
                request.getPokemonName(),
                request.getType(),
                request.getLevel()
        );
        pokemonRepository.save(pokemon);
        return new PokemonResponseDto("포켓몬이 나타났다.");
    }

    public PokemonSearchResponseDto search(Long id) { //포켓몬 단건 조회
        Pokemon pokemon = pokemonRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 포켓몬이 없습니다. id=" + id));

        Trainer trainer = pokemon.getTrainer();

        return new PokemonSearchResponseDto(
                pokemon.getPokemonId(),
                pokemon.getTrainer() == null ? null : trainer.getTrainerId(), //값이 없으면 null을 집어넣어야함
                pokemon.getPokemonName(),
                pokemon.getPokemonType(),
                pokemon.getLevel()
        );
    }
    //처음에 optional로 반환해야 된다고 인텔리제이가 해서 optional로 했는데 값이 없을때 오류가 나서 예외처리를 해줬다.

    public List<PokemonSearchResponseDto> searchAll() { //포켓몬 전체 조회
        List<Pokemon> pokemons = pokemonRepository.findAll();

        List<PokemonSearchResponseDto> temp = new ArrayList<>();

        for( Pokemon p : pokemons) {
            PokemonSearchResponseDto searchResponseDto = new PokemonSearchResponseDto(
                    p.getPokemonId(),
                    p.getTrainer() == null ? null : p.getTrainer().getTrainerId(),
                    p.getPokemonName(),
                    p.getPokemonType(),
                    p.getLevel()
            );
            temp.add(searchResponseDto);
        }
        return temp;
    }

    public void delete(Long id) { //삭제
        Pokemon pokemon = pokemonRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 트레이너가 없습니다. id=" + id));
        pokemonRepository.delete(pokemon);
    }

    public PokemonCatchResponseDto catchPokemon(Long trainerId, Long pokemonId) {
        Trainer trainer = trainerRepository.findById(trainerId)
                .orElseThrow(() -> new IllegalArgumentException("해당 트레이너가 없습니다. id=" + trainerId));

        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(() -> new IllegalArgumentException("해당 포켓몬이 없습니다. id=" + pokemonId));

        pokemon.catchBy(trainer);

        return new PokemonCatchResponseDto(
                pokemon.getPokemonName(),
                trainer.getTrainerName()
        );
    }

}
