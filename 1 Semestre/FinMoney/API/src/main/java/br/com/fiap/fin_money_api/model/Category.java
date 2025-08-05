package br.com.fiap.fin_money_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Não pode estar em branco")
    @Size(min = 3)
    private String name;
    
    @NotBlank(message = "Não pode estar em branco")
    @Pattern(regexp = "^[A-Z].*", message = "Deve começar com letra maiúscula")
    private String icon;

    @ManyToOne
    @JsonIgnore
    private User user;

}
