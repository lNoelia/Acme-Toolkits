package acme.entities.exanchageRate;

import java.time.Instant;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "source", "target" }) })
public class ExchangeRate extends AbstractEntity{
	
	// Serialisation identifier -----------------------------------------------
	
	private static final long serialVersionUID = 1L;
	
	
	// Attributes
	
	@NotBlank
	protected String				source;
	
	@NotBlank
	protected String				target;
	
	@NotNull
	protected Double				rate;
	
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date					date;

	
	// Constructors
	public ExchangeRate() {
		super();
	}
	
	public ExchangeRate(@NotBlank final String source, @NotBlank final String target) {
		super();
		this.source = source;
		this.target = target;
		this.rate = 0.0;
		this.date = Date.from(Instant.parse("1800-01-01T10:00:00.00Z"));
	}
	
	
}
