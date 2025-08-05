package br.com.fiap.fin_money_api.specification;

import br.com.fiap.fin_money_api.controller.TransactionController;
import br.com.fiap.fin_money_api.model.Transaction;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import java.util.ArrayList;

public class TransactionSpecification {

    public static Specification<Transaction> withFilters(TransactionController.TransactionFilters filters){
        return (root, query, cb) -> { // lambda expresion

            var predicates = new ArrayList<>();
            if(filters.description() != null){
                predicates.add(
                        cb.like(
                                cb.lower(root.get("description")), "%" + filters.description().toLowerCase() + "%"
                        )
                );
            }

            if(filters.startDate() != null && filters.endDate() != null){
                predicates.add(
                        cb.between(root.get("date"), filters.startDate(), filters.endDate())
                );
            }

            if(filters.startDate() != null && filters.endDate() == null){
                predicates.add(
                        cb.equal(root.get("date"), filters.startDate())
                );
            }

            var arrayPredicates = predicates.toArray(new Predicate[0]);
            return cb.and(arrayPredicates);
        };
    }
}
