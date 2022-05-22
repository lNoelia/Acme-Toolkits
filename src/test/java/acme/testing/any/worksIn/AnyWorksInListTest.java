package acme.testing.any.worksIn;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyWorksInListTest extends TestHarness {

	// Test cases -------------------------------------------------------------
		@ParameterizedTest
		@CsvFileSource(resources = "/any/works-in/list.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void positiveTestAnonymous(final int recordIndex,final int toolkitIndex,final String amount,final String name,final String type,final String code,final String price,final String technology,final String description,final String link) {
			
			super.clickOnMenu("Anonymous", "List of Toolkits");
			super.checkListingExists();
			super.clickOnListingRecord(toolkitIndex);
			super.clickOnButton("Artefacts");
			super.checkListingExists();
			super.sortListing(0, "asc");
			
			super.checkColumnHasValue(recordIndex, 0, amount);
			super.checkColumnHasValue(recordIndex, 1, type);
			super.checkColumnHasValue(recordIndex, 2, name);
			super.checkColumnHasValue(recordIndex, 3, code);
			super.checkColumnHasValue(recordIndex, 4, price);
			
			super.clickOnListingRecord(recordIndex);
			super.checkFormExists();
			super.checkInputBoxHasValue("amount", amount);
			super.checkInputBoxHasValue("artefact.name", name);
			super.checkInputBoxHasValue("artefact.type", type);
			super.checkInputBoxHasValue("artefact.code", code);
			super.checkInputBoxHasValue("artefact.retailPrice", price);
			super.checkInputBoxHasValue("artefact.technology", technology);
			super.checkInputBoxHasValue("artefact.description", description);
			super.checkInputBoxHasValue("artefact.link", link);
		}
		
		@ParameterizedTest
		@CsvFileSource(resources = "/any/works-in/list.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void positiveTestWithLog(final int recordIndex,final int toolkitIndex,final String amount,final String name,final String type,final String code,final String price,final String technology,final String description,final String link) {
			
			super.signIn("user1", "user1");
			
			super.clickOnMenu("Authenticated", "List of Toolkits");
			super.checkListingExists();
			super.clickOnListingRecord(toolkitIndex);
			super.clickOnButton("Artefacts");
			super.checkListingExists();
			super.sortListing(0, "asc");
			
			super.checkColumnHasValue(recordIndex, 0, amount);
			super.checkColumnHasValue(recordIndex, 1, type);
			super.checkColumnHasValue(recordIndex, 2, name);
			super.checkColumnHasValue(recordIndex, 3, code);
			super.checkColumnHasValue(recordIndex, 4, price);
			
			super.clickOnListingRecord(recordIndex);
			super.checkFormExists();
			super.checkInputBoxHasValue("amount", amount);
			super.checkInputBoxHasValue("artefact.name", name);
			super.checkInputBoxHasValue("artefact.type", type);
			super.checkInputBoxHasValue("artefact.code", code);
			super.checkInputBoxHasValue("artefact.retailPrice", price);
			super.checkInputBoxHasValue("artefact.technology", technology);
			super.checkInputBoxHasValue("artefact.description", description);
			super.checkInputBoxHasValue("artefact.link", link);
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
			// HINT: the framework doesn't currently provide enough support to hack
			// HINT+ this feature, so the hacking tests must be performed manually.
			
			// HINT+ a) list artefacts(worksIn) of a toolkit that is not published.
			// HINT+ b) show an artefact(worksIn) of a toolkit that is not published.
		}
		
}
