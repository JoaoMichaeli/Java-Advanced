package br.com.fiap.fin_money_api.controller;

import br.com.fiap.fin_money_api.model.Transaction;
import br.com.fiap.fin_money_api.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transactions")
@Slf4j
public class TransactionController {

    @Autowired
    private TransactionRepository repository;

    @GetMapping
    public Page<Transaction> index(
            @PageableDefault(size = 10, sort = "date", direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam String description
    ){
        log.info("Buscando transações com descrição {}", description);

        if(description != null) return repository.findByDescription(description, pageable);

        return repository.findAll(pageable);
    }


}
