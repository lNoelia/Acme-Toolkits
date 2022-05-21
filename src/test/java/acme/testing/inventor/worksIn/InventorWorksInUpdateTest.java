package acme.testing.inventor.worksIn;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorWorksInUpdateTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/works-in/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final int toolkitIndex, final String reference, final String amount) {
		
		super.signIn("inventor2", "inventor2");
		
		super.clickOnMenu("Inventor", "List of Toolkits");
		super.checkListingExists();	
		super.sortListing(0,"asc");
		super.clickOnListingRecord(toolkitIndex);
		super.checkFormExists();
		super.clickOnButton("Artefacts");
		
		super.checkListingExists();
		super.sortListing(1,"asc");
		super.checkColumnHasValue(recordIndex, 1, reference);
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		if (reference.equals("COMPONENT")) {
			super.fillInputBoxIn("amount", amount);
			super.clickOnSubmit("Update");
		} else {
			super.checkNotSubmitExists("Update");
		}
		
		super.checkNotErrorsExist();
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/works-in/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final int toolkitIndex, final String reference, final String amount) {
		
		super.signIn("inventor2", "inventor2");
		
		super.clickOnMenu("Inventor", "List of Toolkits");
		super.checkListingExists();	
		super.sortListing(0,"asc");
		super.clickOnListingRecord(toolkitIndex);
		super.checkFormExists();
		super.clickOnButton("Artefacts");
		
		super.checkListingExists();
		super.sortListing(1,"asc");
		super.checkColumnHasValue(recordIndex, 1, reference);
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		super.fillInputBoxIn("amount", amount);
		super.clickOnSubmit("Update");
		
		super.checkErrorsExist();
		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {	

		// HINT: the framework doesn't provide enough support to implement this test case,
		// HINT+ so it must be performed manually:
		// HINT+ a) update an artefact (worksIn) of a toolkit as an inventor 
		// HINT+    that does not own that toolkit.
	}
}
