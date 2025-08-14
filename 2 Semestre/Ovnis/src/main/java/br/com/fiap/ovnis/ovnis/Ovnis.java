package br.com.fiap.ovnis.ovnis;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ovnis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String local;
    @Column(name = "date_time")
    private LocalDateTime datetime;
    private String color;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "shape_enum")
    private Shapes shape;
    private Long quantity;
}
