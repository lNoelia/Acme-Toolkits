package acme.testing.inventor.worksIn;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorWorksInListMineTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/works-in/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final int toolkitIndex, final String quantity, final String artefactType, final String name, final String code, final String price, final String technology, final String description, final String link) {
		super.signIn("inventor2", "inventor2");

		super.clickOnMenu("Inventor", "List of Toolkits");
		super.checkListingExists();
		super.sortListing(1, "asc");
		super.clickOnListingRecord(toolkitIndex);
		super.checkFormExists();
		super.clickOnButton("Artefacts");
		
		super.checkListingExists();
		super.sortListing(1, "asc");;
		super.checkColumnHasValue(recordIndex, 0, quantity);
		super.checkColumnHasValue(recordIndex, 1, artefactType);
		super.checkColumnHasValue(recordIndex, 2, name);
		super.checkColumnHasValue(recordIndex, 3, code);
		super.checkColumnHasValue(recordIndex, 4, price);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("amount", quantity);
		super.checkInputBoxHasValue("artefact.name", name);
		super.checkInputBoxHasValue("artefact.type", artefactType);
		super.checkInputBoxHasValue("artefact.code", code);
		super.checkInputBoxHasValue("artefact.retailPrice", price);
		super.checkInputBoxHasValue("artefact.technology", technology);
		super.checkInputBoxHasValue("artefact.description", description);
		super.checkInputBoxHasValue("artefact.link", link);
		
		super.signOut();
	}
	
	@Test
	@Order(20)
	public void negativeTest() {
		// HINT: there aren't any negative tests for this feature since it's a listing that
		// HINT+ doesn't involve entering any data into any forms.
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		super.checkNotLinkExists("Account");
		super.navigate("/inventor/works-in/list");
		super.checkPanicExists();

		super.signIn("patron1", "patron1");
		super.navigate("/inventor/works-in/list");
		super.checkPanicExists();
		super.signOut();
	}
}
