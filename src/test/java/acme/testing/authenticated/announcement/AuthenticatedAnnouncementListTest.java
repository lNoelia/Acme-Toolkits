package acme.testing.authenticated.announcement;

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

import acme.entities.announcements.Announcement;
import acme.features.authenticated.announcement.AuthenticatedAnnouncementRepository;
import acme.framework.helpers.FactoryHelper;
import acme.testing.TestHarness;

public class AuthenticatedAnnouncementListTest extends TestHarness{
	
	// Lifecycle management ---------------------------------------------------

	@Autowired
	private AuthenticatedAnnouncementRepository repository;
	
	// Test cases -------------------------------------------------------------
	
	@Override
	@BeforeAll
	public void beforeAll() {
	    super.beforeAll();
	    FactoryHelper.autowire(this);
	    this.patchAnnouncements();
	}
	
		@ParameterizedTest
		@CsvFileSource(resources = "/authenticated/announcement/list.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void positiveTest(final int recordIndex, final int deltaDays, final String title,  final String body,final String critical, final String link) {
			super.signIn("inventor2", "inventor2");
			super.clickOnMenu("Authenticated", "Announcement list");
			super.checkListingExists();
			super.checkCurrentPath("/authenticated/announcement/list");
			super.checkNotListingEmpty();
			super.sortListing(0, "desc");
			
			 String moment;

	         moment = this.computeDeltaMoment(deltaDays);
			

			super.checkColumnHasValue(recordIndex, 0, moment);
			super.checkColumnHasValue(recordIndex, 1, critical);
			super.checkColumnHasValue(recordIndex, 2, title);

			super.clickOnListingRecord(recordIndex);
			super.checkFormExists();
			
			super.checkInputBoxHasValue("title", title);
			super.checkInputBoxHasValue("critical", critical);
			super.checkInputBoxHasValue("body", body);
			super.checkInputBoxHasValue("creationDate", moment);
			super.checkInputBoxHasValue("link", link);
			
			super.signOut();
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
			super.checkNotLinkExists("Account");
			super.navigate("/authenticated/announcement/list");
			super.checkErrorsExist();
		}
		
		//Métodos para recalculo de tiempo
		
		protected void patchAnnouncements() {
		    Collection<Announcement> announcements;
		    Date moment;

		    announcements = this.repository.findAnnouncementsToPatch();
		    for (final Announcement announcement : announcements) {
		        moment = this.adjustMoment(announcement.getCreationDate());
		        announcement.setCreationDate(moment);
		        this.repository.save(announcement);
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
