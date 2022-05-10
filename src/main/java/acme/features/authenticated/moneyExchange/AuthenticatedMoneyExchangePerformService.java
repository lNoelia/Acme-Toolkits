package acme.features.authenticated.moneyExchange;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import acme.components.ExchangeRateRecord;
import acme.entities.exanchageRate.ExchangeRate;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.helpers.StringHelper;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractPerformService;

@Service
public class AuthenticatedMoneyExchangePerformService implements AbstractPerformService<Authenticated, MoneyExchange> {

	@Autowired
	protected AuthenticatedMoneyExchangeRepository repository;
	
	@Override
	public boolean authorise(final Request<MoneyExchange> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<MoneyExchange> request, final MoneyExchange entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "source", "targetCurrency", "date", "target");
	}

	@Override
	public void unbind(final Request<MoneyExchange> request, final MoneyExchange entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "source", "targetCurrency", "date", "target");
	}

	@Override
	public MoneyExchange instantiate(final Request<MoneyExchange> request) {
		assert request != null;

		MoneyExchange result;

		result = new MoneyExchange();

		return result;
	}

	@Override
	public void validate(final Request<MoneyExchange> request, final MoneyExchange entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void perform(final Request<MoneyExchange> request, final MoneyExchange entity, final Errors errors) {
		assert request != null;
		assert entity != null;

		Money source, target;
		String targetCurrency;
		Date date;
		MoneyExchange exchange;

		source = request.getModel().getAttribute("source", Money.class);
		targetCurrency = request.getModel().getAttribute("targetCurrency", String.class);
		exchange = this.computeMoneyExchange(source, targetCurrency);
		errors.state(request, exchange != null, "*", "authenticated.money-exchange.form.label.api-error");
		
		if (exchange == null) {
			entity.setTarget(null);
			entity.setDate(null);
		} else {
			target = exchange.getTarget();
			entity.setTarget(target);
			date = exchange.getDate();
			entity.setDate(date);
		}
		
	}
	
	
	// Ancillary methods ------------------------------------------------------

		public MoneyExchange computeMoneyExchange(final Money source, final String targetCurrency) {
			assert source != null;
			assert !StringHelper.isBlank(targetCurrency);
			
			MoneyExchange result;
			ExchangeRate exchangeRate;
			String sourceCurrency;
			Money target;
			Date exchangeRateDate;
			Double amount;

			sourceCurrency = source.getCurrency();
			exchangeRate = this.findExchangeRate(sourceCurrency, targetCurrency);
			
			result = new MoneyExchange();
			result.setSource(source);
			result.setTargetCurrency(targetCurrency);
			
			
			target = new Money();
			amount = source.getAmount() * exchangeRate.getRate();
			target.setAmount(amount);
			target.setCurrency(targetCurrency);
			result.setTarget(target);
			
			exchangeRateDate = exchangeRate.getDate();
			result.setDate(exchangeRateDate);
			
			return result;
		}
		
		public ExchangeRate findExchangeRate(final String sourceCurrency, final String targetCurrency) {
			
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
				result.setDate(apiData.getDate());
				result.setRate(apiData.getRate());
				this.repository.save(result);
			}
			
			
			return result;
		}
		
		public ExchangeRate consumeExchangeRateApi(final String source, final String target) {
			
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
