package acme.testing.patron.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;


public class PatronPatronagePublishTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/publish-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String reference) {
		super.signIn("patron2", "patron2");

		super.clickOnMenu("Patron", "List of patronage");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, reference);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.clickOnSubmit("Publish");
		super.checkNotErrorsExist();

		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/publish-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String reference) {
		super.signIn("patron3", "patron3");

		super.clickOnMenu("Patron", "List of patronage");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.checkColumnHasValue(recordIndex, 0, reference);
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkNotSubmitExists("Publish");

		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		// 1. publish a patronage with a role other than "Patron";
		// 2. publish an published patronage that was registered by the principal;
		// 3. publish an published patronage that wasn't registered by the principal;
		// 4. publish an unpublished patronage that wasn't registered by the principal.
	}

	// Ancillary methods ------------------------------------------------------
	

}