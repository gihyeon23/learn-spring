package appcenter.spring_study.service;

import appcenter.spring_study.dto.trainer.TrainerRequestDto;
import appcenter.spring_study.dto.trainer.TrainerResponseDto;
import appcenter.spring_study.dto.trainer.TrainerSearchResponseDto;
import appcenter.spring_study.entity.Trainer;
import appcenter.spring_study.repository.TrainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TrainerService {

    private final TrainerRepository trainerRepository;

    public TrainerResponseDto create(TrainerRequestDto request) { //트레이너 등록
        Trainer trainer = new Trainer(
                request.getTrainerName(),
                request.getGender(),
                request.getAge()
        );

        trainerRepository.save(trainer);
        return new TrainerResponseDto("트레이너가 등장했다.");
    }

    public TrainerSearchResponseDto search(Long id) { //단건 조회
        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 포켓몬이 없습니다. id=" + id));
        return new TrainerSearchResponseDto(
                trainer.getTrainerId(),
                trainer.getTrainerName(),
                trainer.getGender(),
                trainer.getAge()
        );
    }

    public List<TrainerSearchResponseDto> searchAll() { //전체 조회
        List<Trainer> trainers = trainerRepository.findAll();

        List<TrainerSearchResponseDto> temp = new ArrayList<>();

        for( Trainer t : trainers) {
                TrainerSearchResponseDto searchResponseDto = new TrainerSearchResponseDto(
                        t.getTrainerId(),
                        t.getTrainerName(),
                        t.getGender(),
                        t.getAge()
                );
                temp.add(searchResponseDto);
        }
        return temp;
    }

    public void delete(Long id) { //삭제
            Trainer trainer = trainerRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("해당 트레이너가 없습니다. id=" + id));
        trainerRepository.delete(trainer);
    }
}
