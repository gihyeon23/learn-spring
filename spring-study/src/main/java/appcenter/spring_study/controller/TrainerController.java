package appcenter.spring_study.controller;

import appcenter.spring_study.dto.trainer.TrainerRequestDto;
import appcenter.spring_study.dto.trainer.TrainerResponseDto;
import appcenter.spring_study.dto.trainer.TrainerSearchResponseDto;
import appcenter.spring_study.service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Responsebody랑 Controller 합치고 있는 것이다.
@RequestMapping("/api/trainer")
@RequiredArgsConstructor //final 친구들만 생성자를 만들어 준다.
public class TrainerController {

    private final TrainerService trainerService;

    @PostMapping("") //Create하기
    public TrainerResponseDto register(@RequestBody TrainerRequestDto request) {
        //@RequestBody는 일단 짝궁이다.!!!!
        return trainerService.create(request);
    }

    @GetMapping("")
    public List<TrainerSearchResponseDto> searchAll() {
        return trainerService.searchAll();
    }

    @GetMapping("/{id}")
    public TrainerSearchResponseDto search(@PathVariable Long id) {
        return trainerService.search(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        trainerService.delete(id);
        return "트레이너 삭제 완료";
    }
}
