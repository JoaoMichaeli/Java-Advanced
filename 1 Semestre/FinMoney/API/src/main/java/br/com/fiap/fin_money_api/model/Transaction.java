package br.com.fiap.fin_money_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Campo obrigatório")
    @Size(min = 5, max = 255, message = "Deve ter pelo menos 5 caracteres")
    private String description;

    @Positive(message = "Deve ser positivo")
    private BigDecimal amount;

    @PastOrPresent(message = "Não pode ser no futuro")
    @JsonIgnore
    private LocalDate date;

    @NotNull(message = "Campo obrigatório")
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @NotNull(message = "Campo obrigatório")
    @ManyToOne
    private Category category;
}
