package acme.entities.systemConfiguration;

import javax.persistence.Entity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SystemConfiguration extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	
	// Attributes
	
	@NotBlank
	protected String acceptedCurrencies;
	
	@NotBlank
	protected String systemCurrency;
	
	@NotBlank
	protected String strongSpamWords;
	
	@Range(min = 0, max = 100)
	@Digits(fraction = 1, integer = 2)
	protected double strongSpamThreshold;
	
	@NotBlank
	protected String weakSpamWords;
	
	@Range(min = 0, max = 100)
	@Digits(fraction = 1, integer = 2)
	protected double weakSpamThreshold;
	
}
