package br.com.fiap.fin_money_api.controller;

import br.com.fiap.fin_money_api.model.Transaction;
import br.com.fiap.fin_money_api.repository.TransactionRepository;
import br.com.fiap.fin_money_api.specification.TransactionSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;

@RestController
@RequestMapping("transactions")
@Slf4j
public class TransactionController {

    public record TransactionFilters (String description, LocalDate startDate, LocalDate endDate){}

    @Autowired
    private TransactionRepository repository;

    @GetMapping
    public Page<Transaction> index(
            TransactionFilters filters,
            @PageableDefault(size = 10, sort = "date", direction = Sort.Direction.DESC) Pageable pageable){

                var specification = TransactionSpecification.withFilters(filters);
                return repository.findAll(specification, pageable);
    }

}


        /*
        var probe = Transaction.builder()
                .description(description)
                .date(date)
                .build();

        var matcher = ExampleMatcher.matchingAll()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        var example = Example.of(probe, matcher);
        */