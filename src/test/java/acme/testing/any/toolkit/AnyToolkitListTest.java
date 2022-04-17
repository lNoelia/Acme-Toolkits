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
		
		@ParameterizedTest
		@CsvFileSource(resources = "/any/toolkit/list2.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void positiveTestAnonymous(final int recordIndex, final String code, final String title, final String description, 
			final String assemblyNotes, final String link, final String price, final String name, final String codeA, final String technology,
			final String descriptionA, final String retailPrice, final String type) {
			
			super.clickOnMenu("Anonymous", "List of Toolkits");
			super.checkListingExists();
			super.sortListing(0, "asc");
			
			super.checkColumnHasValue(recordIndex, 0, code);
			super.checkColumnHasValue(recordIndex, 1, title);
			super.checkColumnHasValue(recordIndex, 2, description);
			
			super.clickOnListingRecord(recordIndex);
			super.checkFormExists();
			super.checkInputBoxHasValue("code", code);
			super.checkInputBoxHasValue("title", title);
			super.checkInputBoxHasValue("description", description);
			super.checkInputBoxHasValue("assemblyNotes", assemblyNotes);
			super.checkInputBoxHasValue("link", link);
			super.checkInputBoxHasValue("price", price);
			
			super.clickOnButton("Artefacts");
			super.checkListingExists();
			
			super.clickOnListingRecord(0);
			super.checkFormExists();	
			super.checkInputBoxHasValue("code", codeA);
			super.checkInputBoxHasValue("name", name);
			super.checkInputBoxHasValue("description", descriptionA);
			super.checkInputBoxHasValue("technology", technology);
			super.checkInputBoxHasValue("type", type);
			super.checkInputBoxHasValue("retailPrice", retailPrice);
		}
}
