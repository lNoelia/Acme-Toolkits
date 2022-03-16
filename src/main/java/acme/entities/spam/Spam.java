package acme.entities.spam;

import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Spam extends AbstractEntity {
	
	protected static final long serialVersionUID = 1L;
	
	// Attributes

	@NotNull
	protected Language language;
	
	@NotNull
	protected SpamType spamType;
	
	@NotBlank
	protected String term;
	
	protected double threshold;
	
	// Methods
	
	@PrePersist
	protected void assignThreshold() {
		if (this.spamType.equals(SpamType.STRONG)) {
			this.threshold = 0.1;
		} else {
			this.threshold = 0.25;
		}
	}
}
