package acme.testing.inventor.artefact;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.testing.TestHarness;


public class InventorArtefactDeleteTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@Test
	@Order(10)
	public void positiveTest() {
		super.signIn("inventor2", "inventor2");

		super.clickOnMenu("Inventor", "List of Artefacts");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.clickOnSubmit("Delete");
		super.checkNotErrorsExist();

		super.signOut();
	}

	@Test
	@Order(20)
	public void negativeTest() {
		super.signIn("inventor3", "inventor3");

		super.clickOnMenu("Inventor", "List of Artefacts");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.checkNotSubmitExists("Delete");
		super.checkNotErrorsExist();

		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		// HINT: the framework doesn't currently provide enough support ot hack
		// HINT+ this feature, so the hacking tests must be performed manually.\
		
		//HINT+ a) try to delete an artefact as a patron 
		
		//HINT+ b) try to delete an artefact of another inventor as an inventor
		
		//HINT+ a) try to delete an artefact as an anonymous principal 
		
		//HINT+ b) try to delete an artefact of another inventor as an administrator
	}
}