package acme.components.moneyExchange;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.exchangeRate.ExchangeRate;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface MoneyExchangeRepository extends AbstractRepository {

	@Query("select er from ExchangeRate er where er.source= :source and er.target= :target")
	Optional<ExchangeRate> findExchangeRate(String source, String target);
	
	@Query("select sc.systemCurrency from SystemConfiguration sc")
	String findSystemCurrency();
}
