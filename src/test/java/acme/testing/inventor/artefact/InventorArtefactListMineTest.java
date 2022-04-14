package acme.testing.inventor.artefact;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;


public class InventorArtefactListMineTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/artefact/list-mine.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String name, final String code, final String technology, final String description, final String retailPrice, final String link, final String type) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Inventor", "List of Artefacts");
		super.checkListingExists();
		super.sortListing(1, "asc");
		
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
		super.checkInputBoxHasValue("type", type);
		
		super.signOut();
	}
	
	@Test
	@Order(10)
	public void negativeTest() {
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Inventor", "List of Artefacts");
		super.checkListingExists();
		super.sortListing(1, "asc");

		super.clickOnListingRecord(0);
		super.checkNotFormExists();
	}
}
