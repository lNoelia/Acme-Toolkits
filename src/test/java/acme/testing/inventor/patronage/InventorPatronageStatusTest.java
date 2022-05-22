package acme.testing.inventor.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;


public class InventorPatronageStatusTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage/status-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String reference, final String status) {
		super.signIn("inventor2", "inventor2");

		super.clickOnMenu("Inventor", "List of patronages");
		super.checkListingExists();
		super.checkColumnHasValue(recordIndex, 0, reference);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.fillInputBoxIn("status", status);
		super.clickOnSubmit("Save update");
		super.checkColumnHasValue(recordIndex, 1, status);
		super.checkNotErrorsExist();

		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage/status-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String reference, final String status) {
		super.signIn("inventor3", "inventor3");

		super.clickOnMenu("Inventor", "List of patronages");
		super.checkListingExists();
		super.sortListing(1, "asc");

		super.checkColumnHasValue(recordIndex, 0, reference);
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkNotSubmitExists("Publish");

		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		// HINT: the framework doesn't currently provide enough support ot hack
		// HINT+ this feature, so the hacking tests must be performed manually.\
		
		//HINT+ a) try to update a patronage status as a patron 
		
		//HINT+ b) try to update a patronage status of a patronage from another inventor as an inventor
		
		//HINT+ a) try to update a patronage status as an anonymous principal 
		
		//HINT+ b) try to update a patronage status of another inventor as an administrator
	}
}