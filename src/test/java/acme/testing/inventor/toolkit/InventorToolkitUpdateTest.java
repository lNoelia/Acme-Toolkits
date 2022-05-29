package acme.testing.inventor.toolkit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;


public class InventorToolkitUpdateTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String title, final String code, final String price, final String description, final String assemblyNotes, final String link) {
		
		super.signIn("inventor2", "inventor2");
		super.clickOnMenu("Inventor", "List of Toolkits");
		super.checkListingExists();
		
		super.sortListing(0,"asc");
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("assemblyNotes", assemblyNotes);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Update");
		
		super.checkListingExists();
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("price", price);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("assemblyNotes", assemblyNotes);
		super.checkInputBoxHasValue("link", link);
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String title, final String code, final String description, final String assemblyNotes, final String link) {
		
		super.signIn("inventor2", "inventor2");
		super.clickOnMenu("Inventor", "List of Toolkits");
		super.checkListingExists();
		
		super.sortListing(0,"asc");
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("assemblyNotes", assemblyNotes);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Update");
		
		super.checkErrorsExist();
		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		
		// HINT: the framework doesn't provide enough support to implement this test case,
		// HINT+ so it must be performed manually:
		// HINT+ a) update a toolkit with a role other than "Inventor".
		// HINT+ b) update an unpublished toolkit as an inventor that does not own that toolkit.
		// HINT+ c) update a published toolkit as an inventor that owns that toolkit.
		// HINT+ d) update a published toolkit as an inventor that does not own that toolkit.
	}
}
