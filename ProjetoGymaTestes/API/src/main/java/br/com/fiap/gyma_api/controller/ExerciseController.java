package br.com.fiap.gyma_api.controller;

import br.com.fiap.gyma_api.model.Exercise;
import br.com.fiap.gyma_api.repository.ExerciseRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import org.slf4j.Logger;

@RestController
@RequestMapping("exercises")
public class ExerciseController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ExerciseRepository repository;

    @GetMapping
    public List<Exercise> index() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Exercise create(@RequestBody Exercise exercise) {
        log.info("Cadastrando exercício: " + exercise);
        repository.save(exercise);
        return exercise;
    }

    @GetMapping("{id}")
    public ResponseEntity<Exercise> get(@PathVariable Long id) {
        log.info("Buscando exercício com ID " + id);
        return ResponseEntity.ok(getExercise(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Exercise> delete(@PathVariable Long id) {
        log.info("Deletando exercício com ID " + id);
        repository.delete(getExercise(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Exercise> update(@PathVariable Long id, @RequestBody Exercise exercise) {
        log.info("Atualizando exercício " + id + " com dados " + exercise);
        getExercise(id); // Verifica se o exercício existe
        exercise.setId(id);
        repository.save(exercise);
        return ResponseEntity.ok(exercise);
    }

    private Exercise getExercise(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercício não encontrado"));
    }
}
