package acme.testing.inventor.worksIn;

import org.junit.jupiter.api.Order;
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
		
}
