package acme.testing.inventor.worksIn;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorWorksInDeleteTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/works-in/delete-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final int toolkitIndex, final String reference) {
		
		super.signIn("inventor2", "inventor2");
		
		super.clickOnMenu("Inventor", "List of Toolkits");
		super.checkListingExists();	
		super.sortListing(0,"asc");
		super.clickOnListingRecord(toolkitIndex);
		super.checkFormExists();
		super.clickOnButton("Artefacts");
		
		super.checkListingExists();
		super.sortListing(3,"asc");
		super.checkColumnHasValue(recordIndex, 3, reference);
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		super.clickOnSubmit("Delete");
		super.checkNotErrorsExist();
		
		super.signOut();
	}
	
	@Test
	@Order(20)
	public void negativeTest() {
		
		// there is not negative test because all works-in artefacts are published,
		// so the data is always consistent
	}
	
	@Test
	@Order(30)
	public void hackingTest() {	
		
		// HINT: the framework doesn't provide enough support to implement this test case,
		// HINT+ so it must be performed manually:
		// HINT+ a) delete an artefact (worksIn) of a toolkit as an inventor 
		// HINT+    that does not own that toolkit.
	}
}
