package acme.testing.inventor.toolkit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorToolkitListMineTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String code, final String title, final String description, final String assemblyNotes, final String link, final String price) {
		super.signIn("inventor2", "inventor2");

		super.clickOnMenu("Inventor", "List of Toolkits");
		super.checkListingExists();
		super.sortListing(1, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, title);
		super.checkColumnHasValue(recordIndex, 2, price);
		
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("price", price);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("assemblyNotes", assemblyNotes);
		super.checkInputBoxHasValue("link", link);
		
		super.clickOnButton("Artefacts");
		super.checkListingExists();
		
		super.signOut();
	}
	
	
	@Test
	@Order(20)
	public void negativeTest() {
		// HINT: there's no negative test case for this listing, since it doesn't
		// HINT+ involve filling in any forms.
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		super.checkNotLinkExists("Account");
		super.checkNotLinkExists("Inventor");
		super.navigate("/inventor/toolkit/list");
		super.checkPanicExists();
		
		super.signIn("user1", "user1");
		super.checkNotLinkExists("Inventor");
		super.navigate("/inventor/toolkit/list");
		super.checkPanicExists();
		super.signOut();
		
		super.signIn("patron2", "patron2");
		super.checkNotLinkExists("Inventor");
		super.navigate("/inventor/toolkit/list");
		super.checkPanicExists();
		super.signOut();
		
		super.signIn("administrator2", "administrator2");
		super.checkNotLinkExists("Inventor");
		super.navigate("/inventor/toolkit/list");
		super.checkPanicExists();
		super.signOut();
	}
}
