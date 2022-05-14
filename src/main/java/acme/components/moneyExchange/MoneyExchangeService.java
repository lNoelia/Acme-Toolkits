package acme.components.moneyExchange;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import acme.entities.exanchageRate.ExchangeRate;
import acme.framework.datatypes.Money;
import acme.framework.helpers.StringHelper;

@Service
public class MoneyExchangeService {

	@Autowired
	protected MoneyExchangeRepository repository;
	
	public Money convertToSystemCurrency(final Money money) {
		assert money != null;
		
		Money result;
		String systemCurrency;
		
		systemCurrency = this.repository.findSystemCurrency();
		result = this.computeMoneyExchange(money, systemCurrency);
		
		return result;
	}
	
	public Double computeMoneyExchangeAmount(final Money source, final String targetCurrency) {
		Double result;
		Money target;
		
		target = this.computeMoneyExchange(source, targetCurrency);
		result = target.getAmount();
		
		return result;
	}
	
	public Money computeMoneyExchange(final Money source, final String targetCurrency) {
		assert source != null;
		assert !StringHelper.isBlank(targetCurrency);
		
		Money result;
		String sourceCurrency;
		Double amount;
		ExchangeRate exchangeRate;

		sourceCurrency = source.getCurrency();
		if(sourceCurrency.equals(targetCurrency)) {
			result = source;
			return result;
		}
		
		exchangeRate = this.findExchangeRate(sourceCurrency, targetCurrency);
		
		result = new Money();
		amount = source.getAmount() * exchangeRate.getRate();
		result.setAmount(amount);
		result.setCurrency(targetCurrency);
		
		return result;
	}
	
	// Private methods -----------------------------------------------------------------------------
	
	private ExchangeRate findExchangeRate(final String sourceCurrency, final String targetCurrency) {
		
		Optional<ExchangeRate> storedExchangeRate;
		ExchangeRate result;
		ExchangeRate apiData;
		Calendar calendar;
		Date exchangeRateDate;
		Date rateExpirationDate;
		

		storedExchangeRate = this.repository.findExchangeRate(sourceCurrency, targetCurrency);
		
		if(storedExchangeRate.isPresent()) {
			result = storedExchangeRate.get();
		}else {
			result = new ExchangeRate(sourceCurrency, targetCurrency);
		}
		
		

		calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		rateExpirationDate = calendar.getTime();
		exchangeRateDate = result.getDate();
		
		if(exchangeRateDate.before(rateExpirationDate)) {
			apiData = this.consumeExchangeRateApi(sourceCurrency, targetCurrency);
			assert apiData != null;
			result.setDate(apiData.getDate());
			result.setRate(apiData.getRate());
			this.repository.save(result);
		}
		
		
		return result;
	}
	
	
	private ExchangeRate consumeExchangeRateApi(final String source, final String target) {
		
		ExchangeRate result;
		final ExchangeRateRecord record;
		RestTemplate api;
		Double rate;
		Date date;
		
		try {
			api = new RestTemplate();

			record = api.getForObject( //
				"https://api.exchangerate.host/latest?base={0}&symbols={1}", //
				ExchangeRateRecord.class, //
				source, //
				target //
			);
			assert record != null;
			rate = record.getRates().get(target);
			date = record.getDate();
			
			result = new ExchangeRate();
			result.setDate(date);
			result.setRate(rate);
			result.setSource(source);
			result.setTarget(target);
		} catch (final Throwable oops) {
			result = null;
		}

		return result;
	}
}
