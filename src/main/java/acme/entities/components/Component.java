package acme.entities.components;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

/*
 * @author Rosa Molina
 */

@Entity
@Getter
@Setter
public class Component extends AbstractEntity {
    
	//A component is an invention that is not expected to work standalone, but in conjunction with other components.
    
	// Serialisation identifier -----------------------------------------------
	
	private static final long serialVersionUID = 1L;
    
    // Attributes
    @NotBlank
    @Length(min = 0, max = 100)
    protected String         name;
    
    @Column(unique = true)
    @NotNull
    @Pattern(regexp="^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
    protected String         code;
    
    @NotBlank
    @Length(min = 1, max = 100)
    protected String         tecnology;
    
    @NotBlank
    @Length(min = 1, max = 255)
    protected String         description; 
    
    @Min(1)
    @NotNull
    protected Money         retailPrice;
    
    @URL
    protected String 		link;
}