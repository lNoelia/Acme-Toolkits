package acme.testing.inventor.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorPatronageListTest extends TestHarness{

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String code, final String status, final String budget,final String convertedPrice, final String legalStuff, final String patron,final String link,final String creationDate,final String startDate,final String endDate) {
		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Inventor", "List of patronages");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, status);
		super.checkColumnHasValue(recordIndex, 2, budget);
		super.checkColumnHasValue(recordIndex, 3, patron);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("status", status);
		super.checkInputBoxHasValue("budget",budget);
		super.checkInputBoxHasValue("convertedPrice", convertedPrice);
		super.checkInputBoxHasValue("patron", patron);
		super.checkInputBoxHasValue("legalStuff",legalStuff);
		super.checkInputBoxHasValue("link",link);
		super.checkInputBoxHasValue("creationDate",creationDate);
		super.checkInputBoxHasValue("startDate",startDate);
		super.checkInputBoxHasValue("endDate",endDate);

		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String sequenceNumber, final String creationDate, final String patronageCode, final String memorandum, final String link) {
		super.signIn("patron4", "patron4");

		super.clickOnMenu("Inventor", "List of patronages");
		super.checkListingEmpty();

		super.signOut();
	}
	
}
