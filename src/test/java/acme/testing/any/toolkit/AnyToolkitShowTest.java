
package acme.testing.any.toolkit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyToolkitShowTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------
	@ParameterizedTest
	@CsvFileSource(resources = "/any/toolkit/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTestWithLog(final int recordIndex, final String code, final String title, final String description, final String assemblyNotes, final String link, final String price, final String amount, final String name, final String codeA, final String technology,
		final String descriptionA, final String retailPrice, final String linkA, final String type) {
		super.signIn("patron1", "patron1");

		super.clickOnMenu("Authenticated", "List of Toolkits");

		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("price", price);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("assemblyNotes", assemblyNotes);
		super.checkInputBoxHasValue("link", link);

		super.clickOnButton("Artefacts");

		super.checkListingExists();
		super.sortListing(2, "asc");

		super.checkColumnHasValue(0, 0, amount);
		super.checkColumnHasValue(0, 1, type);
		super.checkColumnHasValue(0, 2, name);
		super.checkColumnHasValue(0, 3, codeA);
		super.checkColumnHasValue(0, 4, retailPrice);

		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.checkInputBoxHasValue("amount", amount);
		super.checkInputBoxHasValue("artefact.name", name);
		super.checkInputBoxHasValue("artefact.code", codeA);
		super.checkInputBoxHasValue("artefact.technology", technology);
		super.checkInputBoxHasValue("artefact.description", descriptionA);
		super.checkInputBoxHasValue("artefact.retailPrice", retailPrice);
		super.checkInputBoxHasValue("artefact.link", linkA);
		super.checkInputBoxHasValue("artefact.type", type);

		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/any/toolkit/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTestAnonymous(final int recordIndex, final String code, final String title, final String description, final String assemblyNotes, final String link, final String price, final String amount, final String name, final String codeA, final String technology,
		final String descriptionA, final String retailPrice, final String linkA, final String type) {

		super.clickOnMenu("Anonymous", "List of Toolkits");

		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("price", price);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("assemblyNotes", assemblyNotes);
		super.checkInputBoxHasValue("link", link);

		super.clickOnButton("Artefacts");

		super.checkListingExists();
		super.sortListing(2, "asc");

		super.checkColumnHasValue(0, 0, amount);
		super.checkColumnHasValue(0, 1, type);
		super.checkColumnHasValue(0, 2, name);
		super.checkColumnHasValue(0, 3, codeA);
		super.checkColumnHasValue(0, 4, retailPrice);

		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.checkInputBoxHasValue("amount", amount);
		super.checkInputBoxHasValue("artefact.name", name);
		super.checkInputBoxHasValue("artefact.code", codeA);
		super.checkInputBoxHasValue("artefact.technology", technology);
		super.checkInputBoxHasValue("artefact.description", descriptionA);
		super.checkInputBoxHasValue("artefact.retailPrice", retailPrice);
		super.checkInputBoxHasValue("artefact.link", linkA);
		super.checkInputBoxHasValue("artefact.type", type);

	}

}
