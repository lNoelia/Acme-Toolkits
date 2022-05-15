package acme.entities.toolkits;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.components.moneyExchange.MoneyExchangeService;
import acme.entities.worksIn.WorksIn;
import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import acme.roles.Inventor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Toolkit extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Pattern(regexp="^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	@Column(unique = true)
	protected String code;
	
	@NotBlank
	@Length(min=1, max=100)
	protected String title;
	
	@NotBlank
	@Length(min=1, max=255)
	protected String description;
	
	@NotBlank
	@Length(min=1, max=255)
	protected String assemblyNotes;
	
	@URL
	protected String link;
	
	protected boolean published;

	// Derived attributes -----------------------------------------------------
	
	@Transient
	protected Money price;

	// Relationships ----------------------------------------------------------
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Inventor inventor;
	
	// Methods ----------------------------------------------------------------
	public void calculatePrice(final Collection<WorksIn> worksIns, final MoneyExchangeService moneyExchangeService) {
		assert worksIns != null;
		
		Money result;
		String systemCurrency;
		Double price;
		int artefactAmount;
		
		systemCurrency = moneyExchangeService.systemCurrency();
		price = 0.0;
		
		if(!worksIns.isEmpty()) {
			Money artefactPrice;
			
			for(final WorksIn worksIn: worksIns) {
				artefactPrice = worksIn.getArtefact().getRetailPrice();
				artefactAmount = worksIn.getAmount();
				price += ( moneyExchangeService.computeMoneyExchangeAmount(artefactPrice, systemCurrency)*artefactAmount );
			}
		}
		
		result = new Money();
		result.setCurrency(systemCurrency);
		result.setAmount(price);
		
		this.price = result;
	}

	
}
