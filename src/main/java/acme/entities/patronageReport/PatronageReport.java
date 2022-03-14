package acme.entities.patronageReport;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.Patronage;
import acme.framework.entities.AbstractEntity;
import acme.roles.Inventor;
import acme.roles.Patron;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PatronageReport extends AbstractEntity {
	
	/*
	 * A patronage report consists of a series of messages exchanged between 
	 * an inventor and a patron regarding a particular patronage.
	 */
	
	protected static final long	serialVersionUID = 1L;
	
	// Attributes
	
	@NotBlank
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?:[0-9]{4}$")
	protected String sequenceNumber;
	
	@Temporal(TemporalType.DATE)
	@Past
	@NotNull
	protected Date creationDate;
	
	@Length(max = 255)
	@NotBlank
	protected String memorandum;
	
	@URL
	protected String link;
	
	// Relationships
	
	@ManyToOne(optional = false)
	@Valid
	@NotNull
	protected Patronage patronage;
	
	@ManyToOne(optional = false)
	@Valid
	@NotNull
	protected Patron patron;
	
	@ManyToOne(optional = false)
	@Valid
	@NotNull
	protected Inventor inventor;

}
