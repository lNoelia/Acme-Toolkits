package acme.testing.inventor.toolkit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorToolkitDeleteTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/delete-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String reference) {
		
		super.signIn("inventor2", "inventor2");
		super.clickOnMenu("Inventor", "List of Toolkits");
		super.checkListingExists();
		
		super.sortListing(0,"asc");
		super.checkColumnHasValue(recordIndex, 0, reference);
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		super.clickOnSubmit("Delete");
		super.checkNotErrorsExist();
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/delete-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String reference) {
		
		super.signIn("inventor3", "inventor3");
		super.clickOnMenu("Inventor", "List of Toolkits");
		super.checkListingExists();
		
		super.sortListing(0,"asc");
		super.checkColumnHasValue(recordIndex, 0, reference);
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		super.checkNotSubmitExists("Delete");
		
		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {	
		
		// HINT: the framework doesn't provide enough support to implement this test case,
		// HINT+ so it must be performed manually:
		// HINT+ a) delete a toolkit with a role other than "Inventor".
		// HINT+ b) delete an unpublished toolkit as an inventor that does not own that toolkit.
		// HINT+ c) delete a published toolkit as an inventor that owns that toolkit.
		// HINT+ d) delete a published toolkit as an inventor that does not own that toolkit.
	}
}
