package acme.testing.any.toolkit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyToolkitListTest extends TestHarness{

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------
		@ParameterizedTest
		@CsvFileSource(resources = "/any/toolkit/list.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void positiveTest(final int recordIndex, final String code, final String title, final String description, final String assemblyNotes, final String link, final String price) {
			super.signIn("patron3", "patron3");

			super.clickOnMenu("Authenticated", "List of Toolkits");
			super.checkListingExists();
			super.sortListing(0, "asc");
			
			super.checkColumnHasValue(recordIndex, 0, code);
			super.checkColumnHasValue(recordIndex, 1, title);
			super.checkColumnHasValue(recordIndex, 2, price);

			super.clickOnListingRecord(recordIndex);
			super.checkFormExists();
			super.checkInputBoxHasValue("code", code);
			super.checkInputBoxHasValue("title", title);
			super.checkInputBoxHasValue("description", description);
			super.checkInputBoxHasValue("assemblyNotes", assemblyNotes);
			super.checkInputBoxHasValue("link", link);
			super.checkInputBoxHasValue("price", price);

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
			// HINT: there's no hacking test case for this listing, this feature
			// HINT+ is available for all principals.
		}
		
		/*
		@ParameterizedTest
		@CsvFileSource(resources = "/any/toolkit/searchList.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void searchPositiveTest(final int recordIndex, final String code, final String title, final String description) {

			super.clickOnMenu("Anonymous", "List of Toolkits");
			
			super.fillInputBoxIn("payload", "screw");
			
			super.checkListingExists();
			super.checkColumnHasValue(recordIndex, 0, code);
			super.checkColumnHasValue(recordIndex, 1, title);
			super.checkColumnHasValue(recordIndex, 2, description);
		}
		
		@ParameterizedTest
		@CsvFileSource(resources = "/any/toolkit/searchList.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void searchNegativeTest(final int recordIndex, final String code, final String title, final String description, final String assemblyNotes, final String link, final String price) {

			super.clickOnMenu("Anonymous", "List of Toolkits");
			super.sortListing(0, "asc");
			
			super.fillInputBoxIn("payload", "xxxx");
			super.clickOnSubmit("Search");
			
			super.checkListingEmpty();
		}*/
}
