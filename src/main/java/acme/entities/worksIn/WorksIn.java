package acme.entities.worksIn;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import acme.entities.artefact.Artefact;
import acme.entities.toolkits.Toolkit;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class WorksIn extends AbstractEntity{
	
		// Serialisation identifier -----------------------------------------------

		protected static final long	serialVersionUID	= 1L;

		// Attributes -------------------------------------------------------------

		@NotNull
		@Min(1)
		protected Integer 			amount;
	    
		// Derived attributes -----------------------------------------------------

		// Relationships ----------------------------------------------------------
		
		@NotNull
		@Valid
		@ManyToOne(optional = true)
		protected Artefact artefact;
		
		@NotNull
		@Valid
		@ManyToOne(optional = true)
		protected Toolkit toolkit;
		
		// Methods ----------------------------------------------------------------
		
}
