package acme.components.moneyExchange;

import java.util.Date;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExchangeRateRecord {
	public Map<String, Double>	rates;
	public String				base;
	public Date					date;
	public String				error;
}
