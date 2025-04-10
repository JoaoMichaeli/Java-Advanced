package br.com.fiap.fin_money_api.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import br.com.fiap.fin_money_api.model.Transaction;
import br.com.fiap.fin_money_api.model.TransactionType;
import br.com.fiap.fin_money_api.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.fiap.fin_money_api.model.Category;
import br.com.fiap.fin_money_api.repository.CategoryRepository;
import jakarta.annotation.PostConstruct;

@Component
public class DatabaseSeeder {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @PostConstruct
    public void init(){

        var categories = List.of(
                Category.builder().name("Educação").icon("Book").build(),
                Category.builder().name("Lazer").icon("Dices").build(),
                Category.builder().name("Transporte").icon("Bus").build()
        );
        
        categoryRepository.saveAll(categories);

        var descriptions = List.of(
                "Aluguel",
                "Casa de aposta",
                "Casa de Swing",
                "Drogas",
                "Desmanche",
                "Hotel",
                "Farmácia",
                "Cafeteria",
                "Escola",
                "Clínica",
                "Loja de roupas",
                "Supermercado",
                "Pet shop",
                "Banco",
                "Cinemas",
                "Teatro",
                "Boliche",
                "Boate",
                "Discoteca",
                "Consultório odontológico",
                "Pizzaria",
                "Sorveteria",
                "Churrascaria",
                "Feira",
                "Shopping",
                "Estúdio de gravação",
                "Escritório",
                "Fábrica",
                "Oficina mecânica",
                "Laboratório",
                "Loja de móveis",
                "Lanchonete",
                "Lavanderia",
                "Armazém",
                "Biblioteca",
                "Museu",
                "Galeria de arte",
                "Parque de diversões",
                "Clube de futebol",
                "Loja de informática",
                "Mercado municipal",
                "Serralheria",
                "Banca de jornal",
                "Casa de show",
                "Restaurante vegano",
                "Sushi bar",
                "Loja de brinquedos"
        );

        List<Transaction> transactions = new ArrayList<>();

        for(int i=0; i<50; i++){
            transactions.add(
                    Transaction.builder()
                            .description(descriptions.get(new Random().nextInt(descriptions.size())))
                            .amount(BigDecimal.valueOf(10 + new Random().nextDouble() * 1000))
                            .date(LocalDate.now().minusDays(new Random().nextInt(30)))
                            .type(TransactionType.EXPENSE)
                            .category(categories.get(new Random().nextInt(categories.size())))
                            .build()
            );
        }
        transactionRepository.saveAll(transactions);
    }
    
}
