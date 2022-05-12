package acme.testing.inventor.artefact;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;


public class InventorArtefactCreateTest extends TestHarness {


	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@Test
	@Order(10)
	public void SignUpForThisTest() {
		super.signUp("inventorTest", "inventorTest", "inventorTest", "inventorTest", "inventorTest@us.es");
		super.signIn("inventorTest", "inventorTest");
		super.clickOnMenu("Account", "Become an inventor");
		super.fillInputBoxIn("company", "inventorTest");
		super.fillInputBoxIn("statement", "inventorTest");
		super.clickOnSubmit("Register");
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/artefact/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void positiveTest(final int recordIndex, final String name, final String code, final String technology, final String description, final String retailPrice, final String link, final String type) {
		super.signIn("inventorTest", "inventorTest");

		super.clickOnMenu("Inventor", "List of Artefacts");
		super.checkListingExists();

		super.clickOnButton("Create new artefact");
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("technology", technology);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("type", type);
		super.clickOnSubmit("Create new artefact");

		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 1, technology);
		super.checkColumnHasValue(recordIndex, 2, retailPrice);
		super.checkColumnHasValue(recordIndex, 3, type.charAt(0) + type.substring(1, type.length()).toLowerCase());
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("type", type.charAt(0) + type.substring(1, type.length()).toLowerCase());

		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/artefact/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(30)
	public void negativeTest(final int recordIndex, final String name, final String code, final String technology, final String description, final String retailPrice, final String link, final String type) {
		super.signIn("inventorTest", "inventorTest");

		super.clickOnMenu("Inventor", "List of Artefacts");
		super.clickOnButton("Create new artefact");
		super.checkFormExists();
		
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("technology", technology);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("type", type);
		super.clickOnSubmit("Create new artefact");

		super.checkErrorsExist();

		super.signOut();
	}

	@Test
	@Order(40)
	public void hackingTest() {
		super.checkNotLinkExists("Account");
		super.navigate("/inventor/artefact/create");
		super.checkPanicExists();
		
	}

	// Ancillary methods ------------------------------------------------------

}