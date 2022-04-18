package acme.testing.any.toolkit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyToolkitListTest extends TestHarness{

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------
		@ParameterizedTest
		@CsvFileSource(resources = "/any/toolkit/list.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void positiveTestWithLog(final int recordIndex, final String code, final String title, final String description, final String assemblyNotes, final String link, final String price) {
			super.signIn("patron1", "patron1");

			super.clickOnMenu("Authenticated", "List of Toolkits");
			super.checkListingExists();
			super.sortListing(0, "asc");
			
			super.checkColumnHasValue(recordIndex, 0, code);
			super.checkColumnHasValue(recordIndex, 1, title);
			super.checkColumnHasValue(recordIndex, 2, description);

			super.signOut();
		}
		
		@ParameterizedTest
		@CsvFileSource(resources = "/any/toolkit/list.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void positiveTestAnonymous(final int recordIndex, final String code, final String title, final String description) {
			
			super.clickOnMenu("Anonymous", "List of Toolkits");
			super.checkListingExists();
			super.sortListing(0, "asc");
			
			super.checkColumnHasValue(recordIndex, 0, code);
			super.checkColumnHasValue(recordIndex, 1, title);
			super.checkColumnHasValue(recordIndex, 2, description);
		}
		
		@ParameterizedTest
		@CsvFileSource(resources = "/any/toolkit/searchList.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void searchPositiveTest(final int recordIndex, final String code, final String title, final String description, final String assemblyNotes, final String link, final String price) {

			super.clickOnMenu("Anonymous", "List of Toolkits");
			
			super.fillInputBoxIn("keyword", "screw");
			super.clickOnSubmit("Search");
			
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
			
			super.fillInputBoxIn("keyword", "xxxx");
			super.clickOnSubmit("Search");
			
			super.checkListingEmpty();
		}
}
