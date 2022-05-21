package acme.testing.patron.patronageReport;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PatronPatronageReportListTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage-report/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String sequenceNumber, final String creationDate, final String patronageCode, final String memorandum, final String link) {
		super.signIn("patron3", "patron3");

		super.clickOnMenu("Patron", "List of patronage reports");
		super.checkListingExists();
		super.sortListing(1, "asc");
		super.checkColumnHasValue(recordIndex, 0, patronageCode);
		super.checkColumnHasValue(recordIndex, 1, sequenceNumber);
		super.checkColumnHasValue(recordIndex, 2, creationDate);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("sequenceNumber",sequenceNumber);
		super.checkInputBoxHasValue("creationDate", creationDate);
		super.checkInputBoxHasValue("patronageCode", patronageCode);
		super.checkInputBoxHasValue("memorandum",memorandum);
		super.checkInputBoxHasValue("link",link);

		super.signOut();
	}
	
	@Test
	@Order(10)
	public void negativeTest() {
		super.signIn("patron2", "patron2");
		
		super.clickOnMenu("Patron", "List of patronage reports");
		super.checkListingEmpty();
		
		super.signOut();
	}

	@Test
	@Order(10)
	public void hackingTest() {
		super.checkNotLinkExists("Account");
		super.navigate("/patron/patronage-report/list");
		super.checkErrorsExist();
		
		super.signIn("inventor1","inventor1");
		super.navigate("/patron/patronage-report/list");
		super.checkErrorsExist();
		super.signOut();
		
		super.signIn("user1","user1");
		super.navigate("/patron/patronage-report/list");
		super.checkErrorsExist();
		super.signOut();
		
		super.signIn("administrator2","administrator2");
		super.navigate("/patron/patronage-report/list");
		super.checkErrorsExist();
		super.signOut();
	}

}
