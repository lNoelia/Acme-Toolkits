package acme.entities.currency;

import javax.persistence.Entity;
import javax.validation.constraints.Digits;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Currency extends AbstractEntity {

	protected static final long serialVersionUID = 1L;
	
	// Attributes
	
	protected CurrencyType currencyType;
	
	@Digits(integer = 10, fraction = 2)	
	protected Double amount;
}
