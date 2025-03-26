package br.com.fiap.gyma_api.controller;

import br.com.fiap.gyma_api.model.Exercise;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ExerciseController {
    public List<Exercise> repository = new ArrayList<>();

    @GetMapping("/exercises")
    public List<Exercise> index () {
        return repository;
    }

    @PostMapping("/exercises")
    @ResponseStatus (code = HttpStatus.CREATED)
    public Exercise create(@RequestBody Exercise exercise) {
        System.out.println("Nome do exercício: " + exercise.name);
        repository.add(exercise);
        return exercise;
    }

    @GetMapping("/exercises/{id}")
    public ResponseEntity<Exercise> get(@PathVariable Long id){
        System.out.println("Buscando exercício de id: " + id);
        Optional<Exercise> first = repository.stream()
                .filter(ex -> ex.getId().equals(id))
                .findFirst();

        if (first.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(first.get());
    }

    /*
    @DeleteMapping("{id}")
    public ResponseEntity<Exercise> delete(@PathVariable Long id){
        repository.delete(getExercise(id));
        return ResponseEntity.noContent().build();
    }

    private Exercise getExercise(Long id){
        return repository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercicio não encontrada")
                );
    }
    
     */

}
