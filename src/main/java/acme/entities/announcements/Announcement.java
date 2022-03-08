package acme.entities.announcements;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Announcement extends AbstractEntity {
	
	// An announcement is a formal piece of news.
		
	protected static final long serialVersionUID = 1L;
	
	// Attributes
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	protected Date creationDate;
	
	@Length(min=0, max=101)
	@NotBlank
	protected String title;
	
	@Length(min=0, max=256)
	@NotBlank
	protected String body;
	
	@NotBlank
	protected Boolean critical;
	
	@URL
	protected String link;
	
	
	

}
