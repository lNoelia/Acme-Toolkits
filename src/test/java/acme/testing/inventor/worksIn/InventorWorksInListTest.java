package acme.testing.inventor.worksIn;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorWorksInListTest extends TestHarness {

	// Test cases -------------------------------------------------------------
		@ParameterizedTest
		@CsvFileSource(resources = "/inventor/works-in/list.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void positiveTest(final int recordIndex,final int toolkitIndex,final String amount,final String name,final String type,final String code,final String price,final String convertedPrice,final String technology,final String description,final String link) {
			super.signIn("inventor2", "inventor2");
			
			super.clickOnMenu("Inventor", "List of Toolkits");
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
			super.checkInputBoxHasValue("convertedPrice", convertedPrice);
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
			
			// HINT+ a) list artefacts(worksIn) of a toolkit as an anonymous principal.
			// HINT+ b) show an artefact(worksIn) of a toolkit as an anonymous principal.
			
			// HINT+ c) list artefacts(worksIn) of a toolkit as an authenticated principal that is not an inventor.
			// HINT+ d) show an artefact(worksIn) of a toolkit as an authenticated principal that is not an inventor.
			
			// HINT+ e) list artefacts(worksIn) of a toolkit as a patron that is not an inventor.
			// HINT+ f) show an artefact(worksIn) of a toolkit as a patron that is not an inventor.
			
			// HINT+ g) list artefacts(worksIn) of a toolkit as an administrator that is not an inventor.
			// HINT+ h) show an artefact(worksIn) of a toolkit as an administrator that is not an inventor.
			
			// HINT+ i) list artefacts(worksIn) of a toolkit as an inventor that does not own that toolkit.
			// HINT+ j) show an artefact(worksIn) of a toolkit as an inventor that does not own that toolkit.
		}
		
}
