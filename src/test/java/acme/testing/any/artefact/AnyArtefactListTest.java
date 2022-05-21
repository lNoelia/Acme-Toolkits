package acme.testing.any.artefact;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyArtefactListTest extends TestHarness{
	
	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------
	@ParameterizedTest
	@CsvFileSource(resources = "/any/artefact/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String name, final String code, final String technology, final String description, final String retailPrice, final String link, final String type) {

		super.clickOnMenu("Anonymous", "List of Artefacts");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 1, technology);
		super.checkColumnHasValue(recordIndex, 2, retailPrice);
		super.checkColumnHasValue(recordIndex, 3, type);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("link", link);
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
	
}
