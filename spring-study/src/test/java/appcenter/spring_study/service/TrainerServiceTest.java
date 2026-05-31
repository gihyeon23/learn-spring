package appcenter.spring_study.service;

import appcenter.spring_study.entity.Trainer;
import appcenter.spring_study.repository.TrainerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
@Transactional
public class TrainerServiceTest {

    @Autowired TrainerService trainerService;
    @Autowired TrainerRepository trainerRepository;



    @Test
    void 트레이너등록() {
        //given
        Trainer trainer = new Trainer("유기현","남성" , 22);
        //when
        Trainer trainer1 = trainerRepository.save(trainer);
        //then
        assertThat(trainer1.getTrainerId()).isNotNull();
        assertThat(trainer1.getTrainerName()).isEqualTo("유기현");
        assertThat(trainer1.getGender()).isEqualTo("남성");
        assertThat(trainer1.getAge()).isEqualTo(22);
    }
}
