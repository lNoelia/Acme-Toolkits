package acme.entities.worksIn;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
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

		@NotBlank
		protected Integer idToolkit;
		
		@NotBlank
		protected Integer idArtefact;
		
		@NotNull
		protected ArtefactType type;
		
		// Relationships ----------------------------------------------------------
		
		@NotNull
		@Valid
		@ManyToOne(optional = false)
		protected Artefact artefact;
		
		@NotNull
		@Valid
		@ManyToOne(optional = false)
		protected Toolkit toolkit;
		
}
