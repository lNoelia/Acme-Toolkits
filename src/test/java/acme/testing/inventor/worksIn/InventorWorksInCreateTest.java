package acme.testing.inventor.worksIn;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;


public class InventorWorksInCreateTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/works-in/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final int toolkitIndex, final String quantity, final String artefactType, final String name, final String code, final String price, final String technology, final String description, final String link) {
		
		super.signIn("patron4", "patron4");

		super.clickOnMenu("Inventor", "List of Toolkits");
		super.checkListingExists();
		super.sortListing(1, "asc");
		super.clickOnListingRecord(toolkitIndex);
		super.checkFormExists();
		super.clickOnButton("Artefacts");
		
		super.clickOnButton("Add artefact");
		super.checkFormExists();
		super.fillInputBoxIn("artefactId", code);
		super.fillInputBoxIn("amount", quantity);
		super.clickOnSubmit("Add");
		
		super.checkListingExists();
		super.sortListing(3, "asc");
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

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/works-in/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final int toolkitIndex, final String quantity, final String code) {
	
		super.signIn("patron4", "patron4");

		super.clickOnMenu("Inventor", "List of Toolkits");
		super.checkListingExists();
		super.sortListing(1, "asc");
		super.clickOnListingRecord(toolkitIndex);
		super.checkFormExists();
		super.clickOnButton("Artefacts");
		
		super.clickOnButton("Add artefact");
		super.checkFormExists();
		super.fillInputBoxIn("artefactId", code);
		super.fillInputBoxIn("amount", quantity);
		super.clickOnSubmit("Add");
		
		super.checkErrorsExist();
		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		
		super.checkNotLinkExists("Account");
		super.navigate("/inventor/works-in/create");
		super.checkPanicExists();
		
		super.signIn("patron1", "patron1");
		super.navigate("/inventor/works-in/create");
		super.checkPanicExists();
		super.signOut();
	}
}
