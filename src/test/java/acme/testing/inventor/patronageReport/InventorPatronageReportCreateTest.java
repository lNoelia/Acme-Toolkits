package acme.testing.inventor.patronageReport;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorPatronageReportCreateTest extends TestHarness{

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage-report/create.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String sequenceNumber, final String creationDate, final String patronageCode, final String memorandum, final String link,final String confirmation) {
		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Inventor", "List of patronages");
		super.clickOnListingRecord(0);
		
		super.clickOnButton("Create a patronage report");
		super.fillInputBoxIn("memorandum", memorandum);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("confirmation", confirmation);
		super.clickOnSubmit("Create");

		super.clickOnMenu("Inventor", "List of patronage reports");
		super.sortListing(1, "desc");
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage-report/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String sequenceNumber, final String creationDate, final String patronageCode, final String memorandum, final String link,final String confirmation) {
		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Inventor", "List of patronages");
		super.clickOnListingRecord(0);
		
		super.clickOnButton("Create a patronage report");
		super.fillInputBoxIn("memorandum", memorandum);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("confirmation", confirmation);
		super.clickOnSubmit("Create");
		
		super.checkErrorsExist();

		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		// HINT: the framework doesn't currently provide enough support to hack
        // HINT+ this feature, so the hacking tests must be performed manually.\
		
		//HINT+ a) try to create a patronage report of a patronage as an anonymous principal
		
		//HINT+ b) try to create a patronage report of a patronage as a patron 
		
		//HINT+ c) try to create a patronage report of a patronage as an administrator
		
		//HINT+ d) try to create a patronage report of a patronage as an inventor that is not associated with the patronage
	}
	
	
	
}
