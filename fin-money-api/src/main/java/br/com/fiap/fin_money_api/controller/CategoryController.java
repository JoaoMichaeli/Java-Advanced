package br.com.fiap.fin_money_api.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.fin_money_api.model.Category;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private Logger log = LoggerFactory.getLogger(getClass());

    private List<Category> repository = new ArrayList<>();
    
    @GetMapping
    public List<Category> index(){
        return repository;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Category create(@RequestBody Category category){
        log.info("Cadastrando categoria " + category.getName());
        repository.add(category);
        return category;
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> get(@PathVariable Long id){

        log.info("Buscando categoria " + id);

        return ResponseEntity.ok(getCategory(id));
    }

    //apagar
    @DeleteMapping("{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id){

        log.info("Apagando categoria: " + id);

        repository.remove(getCategory(id));
        return ResponseEntity.noContent().build();
    }

    //editar
    @PutMapping("{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category){

        log.info("Atualizando categoria: " + id + " " + category);

        repository.remove(getCategory(id));
        category.setId(id);
        repository.add(category);
        return ResponseEntity.ok(category);
    }

    private Category getCategory(Long id) {
        return repository.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Categoria não encontrada"
                        )
                );
    }
}
