package acme.testing.any.chirp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
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
	public void positiveTestAuthenticated(final int recordIndex, final String title, final String author, final String body, final String email, final int deltaDays) {
		super.signIn("inventor2", "inventor2");
		super.clickOnMenu("Authenticated", "List of Chirps");
		super.checkListingExists();
		super.checkCurrentPath("/any/chirp/list");
		super.sortListing(1, "desc");
		
		 String moment;

         moment = this.computeDeltaMoment(deltaDays);
         
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, moment);
		super.checkColumnHasValue(recordIndex, 2, author);
		super.checkColumnHasValue(recordIndex, 3, body);
		super.checkColumnHasValue(recordIndex, 4, email);

		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/chirp/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTestAnonymous(final int recordIndex, final String title, final String author, final String body, final String email, final int deltaDays) {
		super.navigate("/any/chirp/list");
		super.checkListingExists();
		super.checkCurrentPath("/any/chirp/list");
		super.sortListing(1, "desc");

		 String moment;

         moment = this.computeDeltaMoment(deltaDays);
         
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, moment);
		super.checkColumnHasValue(recordIndex, 2, author);
		super.checkColumnHasValue(recordIndex, 3, body);
		super.checkColumnHasValue(recordIndex, 4, email);

	}
	
	@Test
	@Order(20)
	public void negativeTest() {
		// HINT: there's no negative test case for this listing, since it doesn't
		// HINT+ involve filling in any forms.
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		// HINT: there's no hacking test case for this listing, this feature
		// HINT+ is available for all principals.
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
	
	protected String computeDeltaMoment(final int deltaDays) {
		assert deltaDays <= 0;

		String result;
		Calendar calendar;
		SimpleDateFormat formatter;

		calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR, 00);
		calendar.set(Calendar.MINUTE, 00);
		calendar.set(Calendar.SECOND, 00);
		calendar.set(Calendar.MILLISECOND, 000);
		calendar.add(Calendar.DAY_OF_MONTH, deltaDays);
		formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");

		result = formatter.format(calendar.getTime());

		return result;
	}
	
}
