package acme.features.authenticated.moneyExchange;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.exanchageRate.ExchangeRate;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMoneyExchangeRepository extends AbstractRepository {

	@Query("select er from ExchangeRate er where er.source= :source and er.target= :target")
	Optional<ExchangeRate> findExchangeRate(String source, String target);
}
