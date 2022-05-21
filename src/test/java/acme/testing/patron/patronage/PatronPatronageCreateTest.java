package acme.testing.patron.patronage;

import java.text.Format;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;


public class PatronPatronageCreateTest extends TestHarness {


	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String code, final String inventorUsername, final String startDate, final String endDate, final String budget, final String legalStuff, final String link, final String nameSurname) {
		super.signIn("patron1", "patron1");

		super.clickOnMenu("Patron", "Create patronage");
		super.checkFormExists();
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("inventorUsername", inventorUsername);
		super.fillInputBoxIn("startDate", startDate);
		super.fillInputBoxIn("endDate", endDate);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("legalStuff", legalStuff);
		super.fillInputBoxIn("link", link);
		final Format f = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	    final String creationDate = f.format(System.currentTimeMillis()-1);
		super.clickOnSubmit("Create");

		super.clickOnMenu("Patron", "List of patronage");
		super.checkListingExists();
		super.sortListing(0, "desc");
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, budget);
		super.checkColumnHasValue(recordIndex, 2, startDate);
		super.checkColumnHasValue(recordIndex, 3, endDate);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("inventorUsername", nameSurname);
		super.checkInputBoxHasValue("creationDate", creationDate);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("endDate", endDate);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("legalStuff", legalStuff);
		super.checkInputBoxHasValue("link", link);

		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String code, final String inventorUsername, final String startDate, final String endDate, final String budget, final String legalStuff, final String link) {
		super.signIn("patron2", "patron2");

		super.clickOnMenu("Patron", "Create patronage");
		super.checkFormExists();
		
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("inventorUsername", inventorUsername);
		super.fillInputBoxIn("startDate", startDate);
		super.fillInputBoxIn("endDate", endDate);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("legalStuff", legalStuff);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Create");

		super.checkErrorsExist();

		super.signOut();
	}

	@Test
	@Order(30)
	public void hackingTest() {
		super.checkNotLinkExists("Account");
		super.navigate("/patron/patronage/create");
		super.checkPanicExists();
		
	}
	
	// Ancillary methods ------------------------------------------------------

}