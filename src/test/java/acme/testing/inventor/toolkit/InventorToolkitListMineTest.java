package acme.testing.inventor.toolkit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorToolkitListMineTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String key, final String code, final String title, final String description, final String assemblyNotes, final String link, final String published,final String artefactName,final String artefactCode,final String artefactTechnology,final String artefactDescription,final String artefactRetailPrice,final String artefactLink,final String artefactType) {
		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Inventor", "List of Toolkits");
		super.checkListingExists();
		super.sortListing(1, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, code);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("assemblyNotes", assemblyNotes);
		super.checkInputBoxHasValue("link", link);
		
		super.clickOnButton("Artefacts");
		super.checkListingExists();
		
		super.signOut();
	}
}
