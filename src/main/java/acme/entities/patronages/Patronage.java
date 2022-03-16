package acme.entities.patronages;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import acme.roles.Inventor;
import acme.roles.Patron;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Patronage extends AbstractEntity{
	
	// Serialisation identifier -----------------------------------------------
	
	private static final long serialVersionUID = 1L;
	
	// Attributes -------------------------------------------------------------
	
	@NotNull
	protected PatronageStatus status;
	
	@NotNull
	@Column(unique = true)
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	protected String code;
	
	@NotBlank
	@Length(max = 255)
	protected String legal;
	
	protected Money budget;
	
	@URL
	protected String link;
	
	@Temporal(TemporalType.DATE)
	@Past
	@NotNull
	protected Date creationDate;
	
	@Temporal(TemporalType.DATE)
	@NotNull
	protected Date startDate;
	
	@Temporal(TemporalType.DATE)
	@NotNull
	protected Date endDate;
	
	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
	
	@NotNull
	@Valid
	@ManyToOne(optional = true)
	protected Inventor inventor;
	
	@NotNull
	@Valid
	@ManyToOne(optional = true)
	protected Patron patron;
	
	// Methods ----------------------------------------------------------------
	
	@PrePersist
	protected void prePersist() throws Exception {
		if(this.creationDate instanceof Date) {
			this.creationDate = new Date();
			this.preUpdate();
		}
	}
	
	@PreUpdate
	protected void preUpdate() throws Exception {
		final long creationDateEpoch = this.creationDate.toInstant().toEpochMilli();
		final long startDateEpoch = this.startDate.toInstant().toEpochMilli();
		final long endDateEpoch = this.endDate.toInstant().toEpochMilli();

		final long startingDelayDays = TimeUnit.MILLISECONDS.toDays(startDateEpoch - creationDateEpoch);
		final long totalDurationDays = TimeUnit.MILLISECONDS.toDays(endDateEpoch - startDateEpoch);

		if(startingDelayDays < 30l) {
			throw new Exception("The start of the patronage must be at least 30 days after the patronage was created.");
		}
		
		if(totalDurationDays < 30l) {
			throw new Exception("The duration of the patronage must be at least 30 days long.");
		}
		
	}
	
}
