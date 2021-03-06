package acme.entities.artefact;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import acme.roles.Inventor;
import lombok.Getter;
import lombok.Setter;

/*
 * @author Rosa Molina
 */

@Entity
@Getter
@Setter
public class Artefact extends AbstractEntity {
    
	//A component is an invention that is not expected to work standalone, but in conjunction with other components.
    
	// Serialisation identifier -----------------------------------------------
	
	@Override
	public String toString() {
		return "" + this.name + ", " + this.code + ", " + this.technology + ", " + this.retailPrice + ", " + this.type + "";
	}

	private static final long serialVersionUID = 1L;
    
    // Attributes
    @NotBlank
    @Length(min = 1, max = 100)
    protected String         name;
    
    @Column(unique = true)
    @NotBlank
    @Pattern(regexp="^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
    protected String         code;
    
    @NotBlank
    @Length(min = 1, max = 100)
    protected String         technology;
    
    @NotBlank
    @Length(min = 1, max = 255)
    protected String         description; 
    
    
    protected Money         retailPrice;
    
    @URL
    protected String 		link;

	@NotNull
	protected ArtefactType 	type;
	
	
	protected boolean 	published;
    
	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Inventor inventor;
	
	// Methods ----------------------------------------------------------------
	
}
