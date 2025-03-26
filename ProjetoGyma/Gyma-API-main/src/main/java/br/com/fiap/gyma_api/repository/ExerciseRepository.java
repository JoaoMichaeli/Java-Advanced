package br.com.fiap.gyma_api.repository;
import br.com.fiap.gyma_api.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
}
