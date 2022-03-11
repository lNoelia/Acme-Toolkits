package acme.roles;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.roles.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractRol extends UserRole {
	// Serialisation identifier -----------------------------------------------

		protected static final long	serialVersionUID	= 1L;

		// Attributes -------------------------------------------------------------

			@NotBlank
			@Length(max=101)
			protected String			company;

			@NotBlank
			@Length(max=256)
			protected String			statement;

			@URL
			protected String			link;
			
		// Derived attributes -----------------------------------------------------
		// Relationships ----------------------------------------------------------
}
