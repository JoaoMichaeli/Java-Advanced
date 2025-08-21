package br.com.fiap.aicharactercreator.character;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Character {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{NotBlank.character.name}")
    @Size(min = 3, max = 40, message = "{Size.character.name}")
    private String name;

    @NotBlank(message = "{NotBlank.character.speciesGroup}")
    @Pattern(
            regexp = "HUMAN|CYBORG|ALIEN|ANDROID",
            message = "{Pattern.character.speciesGroup}"
    )
    private String speciesGroup;

    @Min(value = 1, message = "{Min.character.startingLevel}")
    @Max(value = 100, message = "{Max.character.startingLevel}")
    private Long startingLevel;

    @Size(max = 280, message = "{Size.character.bio}")
    private String bio;

    @PastOrPresent(message = "{PastOrPresent.character.creationDate}")
    private LocalDate creationDate;
}
