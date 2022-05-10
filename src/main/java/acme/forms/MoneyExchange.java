package acme.forms;

import java.io.Serializable;
import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.datatypes.Money;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoneyExchange implements Serializable {
	
		// Serialisation identifier -----------------------------------------------

		protected static final long	serialVersionUID	= 1L;

		// Query attributes -------------------------------------------------------

		@NotNull
		@Valid
		public Money	source;

		@NotBlank
		public String	targetCurrency;

		// Response attributes ----------------------------------------------------

		@Valid
		public Money	target;

		public Date		date;
}
