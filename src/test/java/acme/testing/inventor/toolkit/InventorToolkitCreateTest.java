package acme.testing.inventor.toolkit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;


public class InventorToolkitCreateTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String title, final String code, final String price, final String description, final String assemblyNotes, final String link) {
		
		super.signIn("inventor2", "inventor2");
		super.clickOnMenu("Inventor", "List of Toolkits");
		super.checkListingExists();
		
		super.clickOnButton("Create new toolkit");
		super.checkFormExists();
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("price", price);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("assemblyNotes", assemblyNotes);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Create new toolkit");
		
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, code);
		super.checkColumnHasValue(recordIndex, 2, price);
		
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
	@CsvFileSource(resources = "/inventor/toolkit/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String title, final String code, final String price, final String description, final String assemblyNotes, final String link) {
		
		super.signIn("inventor2", "inventor2");
		super.clickOnMenu("Inventor", "List of Toolkits");
		super.checkListingExists();
		
		super.clickOnButton("Create new toolkit");
		super.checkFormExists();
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("price", price);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("assemblyNotes", assemblyNotes);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Create new toolkit");
		
		super.checkErrorsExist();
		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		
		super.checkNotLinkExists("Account");
		super.navigate("/inventor/toolkit/create");
		super.checkPanicExists();
		
		super.signIn("patron1", "patron1");
		super.navigate("/inventor/artefact/create");
		super.checkPanicExists();
		super.signOut();
	}
}
