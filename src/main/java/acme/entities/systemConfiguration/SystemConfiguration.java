package acme.entities.systemConfiguration;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import acme.entities.currency.Currency;
import acme.entities.spam.Spam;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SystemConfiguration extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	
	// Attributes
	
	protected double spamThreshold;
	
	// Relationships
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Currency currency;
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Spam spam;
	
}
