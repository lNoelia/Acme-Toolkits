package acme.testing.authenticated.announcement;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedAnnouncementListTest extends TestHarness{
	
	// Lifecycle management ---------------------------------------------------

		// Test cases -------------------------------------------------------------
		@ParameterizedTest
		@CsvFileSource(resources = "/authenticated/announcement/list.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void positiveTest(final int recordIndex, final String creationDate, final String title,  final String body,final String critical, final String link) {
			super.signIn("inventor2", "inventor2");
			super.clickOnMenu("Authenticated", "Announcement list");
			super.checkListingExists();
			super.checkCurrentPath("/authenticated/announcement/list");
			super.checkNotListingEmpty();
			super.sortListing(0, "asc");
			

			super.checkColumnHasValue(recordIndex, 0, creationDate);
			super.checkColumnHasValue(recordIndex, 1, critical);
			super.checkColumnHasValue(recordIndex, 2, title);

			super.clickOnListingRecord(recordIndex);
			super.checkFormExists();
			
			super.checkInputBoxHasValue("title", title);
			super.checkInputBoxHasValue("critical", critical);
			super.checkInputBoxHasValue("body", body);
			super.checkInputBoxHasValue("creationDate", creationDate);
			super.checkInputBoxHasValue("link", link);
			
			super.signOut();
		}

		@ParameterizedTest
		@CsvFileSource(resources = "/authenticated/announcement/list.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(20)
		public void negativeTest(final int recordIndex, final String creationDate, final String title,  final String body,final String critical, final String link) {
			super.navigate("/authenticated/announcement/list");
			super.checkErrorsExist();
		}
}
