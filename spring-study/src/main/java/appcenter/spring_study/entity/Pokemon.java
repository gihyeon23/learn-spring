package appcenter.spring_study.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "pokemon_id")
    private Long pokemonId;

    @ManyToOne(fetch = FetchType.LAZY) //TRAINER 1 ─── N POKEMON 을 위한 코드
    @JoinColumn(name = "trainer_id") //외래키 컬럼 이름 결정
    private Trainer trainer;

    public void catchBy(Trainer trainer) {
        this.trainer = trainer;
    }

    @Column(name= "pokemon_name")
    private String pokemonName;

    @Column(name= "type")
    private String pokemonType;

    @Column(name= "level")
    private Integer level;

    public Pokemon(String pokemonName, String pokemonType, Integer level) {
        this.pokemonName = pokemonName;
        this.pokemonType = pokemonType;
        this.level = level;
    } //내가 처음 등록


}
