package acme.testing.inventor.artefact;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;


public class InventorArtefactPublishTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/artefact/publish-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTestEditing(final int recordIndex,final String name, final String code, final String technology, final String description, final String retailPrice, final String link, final String type) {
		super.signIn("inventor2", "inventor2");

		super.clickOnMenu("Inventor", "List of Artefacts");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("technology", technology);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("type", type);
		super.clickOnSubmit("Publish");
		
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("link", link);

		super.signOut();
	}
	
	@Test
	@Order(20)
	public void positiveTestWithoutEditing() {
		super.signIn("inventor2", "inventor2");

		super.clickOnMenu("Inventor", "List of Artefacts");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.clickOnListingRecord(3);
		super.checkFormExists();
		super.clickOnSubmit("Publish");
		super.checkNotErrorsExist();

		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/artefact/publish-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(30)
	public void negativeTest(final int recordIndex, final String name, final String code, final String technology, final String description, final String retailPrice, final String link, final String type) {
		super.signIn("inventor2", "inventor2");

		super.clickOnMenu("Inventor", "List of Artefacts");
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("technology", technology);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("type", type);
		super.clickOnSubmit("Publish");

		super.checkErrorsExist();

		super.signOut();
	}
	
	@Test
	@Order(40)
	public void hackingTest() {
		// HINT: the framework doesn't currently provide enough support ot hack
		// HINT+ this feature, so the hacking tests must be performed manually.\
		
		//HINT+ a) try to publish an artefact as a patron 
		
		//HINT+ b) try to publish an artefact of another inventor as an inventor
		
		//HINT+ a) try to publish an artefact as an anonymous principal 
		
		//HINT+ b) try to publish an artefact of another inventor as an administrator
	}
}