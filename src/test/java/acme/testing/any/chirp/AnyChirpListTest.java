package acme.testing.any.chirp;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.chirps.Chirp;
import acme.features.any.chirp.AnyChirpRepository;
import acme.framework.helpers.FactoryHelper;
import acme.testing.TestHarness;

public class AnyChirpListTest extends TestHarness{

	// Lifecycle management ---------------------------------------------------

	@Autowired
	private AnyChirpRepository repository;
	
	// Test cases -------------------------------------------------------------
	
	@Override
	@BeforeAll
	public void beforeAll() {
	    super.beforeAll();
	    FactoryHelper.autowire(this);
	    this.patchChirps();
	}
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/chirp/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTestAuthenticated(final int recordIndex, final String creationDate, final String title, final String author, final String body, final String email) {
		super.signIn("inventor2", "inventor2");
		super.clickOnMenu("Authenticated", "List of Chirps");
		super.checkListingExists();
		super.checkCurrentPath("/any/chirp/list");
		super.sortListing(0, "asc");
		

		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, creationDate);
		super.checkColumnHasValue(recordIndex, 2, author);
		super.checkColumnHasValue(recordIndex, 3, body);
		super.checkColumnHasValue(recordIndex, 4, email);

		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/chirp/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTestAnonymous(final int recordIndex, final String creationDate, final String title, final String author, final String body, final String email) {
		super.navigate("/any/chirp/list");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, creationDate);
		super.checkColumnHasValue(recordIndex, 2, author);
		super.checkColumnHasValue(recordIndex, 3, body);
		super.checkColumnHasValue(recordIndex, 4, email);
	}
	
	protected void patchChirps() {
	    Collection<Chirp> chirps;
	    Date moment;

	    chirps = this.repository.findChirpsToPatch();
	    for (final Chirp chirp : chirps) {
	        moment = this.adjustMoment(chirp.getCreationDate());
	        chirp.setCreationDate(moment);
	        this.repository.save(chirp);
	    }
	}
	
	protected Date adjustMoment(final Date currentMoment) {
		assert currentMoment != null;

		Date result;
		Calendar calendar;
		int day;

		calendar = Calendar.getInstance();

		calendar.setTime(currentMoment);
		day = calendar.get(Calendar.DAY_OF_MONTH);

		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR, 00);
		calendar.set(Calendar.MINUTE, 00);
		calendar.set(Calendar.SECOND, 00);
		calendar.set(Calendar.MILLISECOND, 000);
		calendar.add(Calendar.DAY_OF_MONTH, -day);

		result = calendar.getTime();

		return result;
	}
	
}
