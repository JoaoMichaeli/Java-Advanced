package br.com.fiap.gyma.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.gyma.model.Exercises;

@RestController
public class ExercisesController {

    private List<Exercises> repository = new ArrayList<>();
    
    @GetMapping("/Exercises")
    public List<Exercises> index(){
        return repository;
    }

    @PostMapping("/Exercises")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Exercises create(@RequestBody Exercises exercises){
        System.out.println("Cadastrando exercício " + exercises.getName());
        repository.add(exercises);
        return exercises;
    }

    @GetMapping("/exercises/{id}")
    public ResponseEntity<Exercises> get(@PathVariable Long id){
        System.out.println("Buscando exercício " + id);
        var Exercises = repository.stream()
            .filter(c -> c.getId().equals(id))
            .findFirst();
        if (exercises.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(exercises.get());
        }

}
