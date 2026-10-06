package io.github.kl.planning_api.infrastructure;

import io.github.kl.planning_api.domain.card.CardRepository;
import io.github.kl.planning_api.domain.card.model.CardBrand;
import io.github.kl.planning_api.domain.card.model.CardEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Sandbox implements CommandLineRunner {

    @Autowired
    private CardRepository repository;

    public void salvarCartao() {
        CardEntity card = new CardEntity();
        card.setName("Itaú teste");
        card.setCardBrand(CardBrand.MASTERCARD);

        repository.save(card);
    }

    @Override
    public void run(String... args) throws Exception {
        // salvarCartao();
    }
}
