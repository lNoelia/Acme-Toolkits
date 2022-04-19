
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
	public void positiveTestWithLog(final int recordIndex, final String code, final String title, final String description, final String assemblyNotes, final String link, final String price, final String name, final String codeA, final String technology,
		final String descriptionA, final String retailPrice, final String linkA, final String type) {
		super.signIn("patron1", "patron1");

		super.clickOnMenu("Authenticated", "List of Toolkits");

		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("assemblyNotes", assemblyNotes);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("price", price);

		super.clickOnButton("Artefacts");

		super.checkListingExists();
		super.sortListing(0, "asc");

		super.checkColumnHasValue(0, 0, name);
		super.checkColumnHasValue(0, 1, technology);
		super.checkColumnHasValue(0, 2, retailPrice);
		super.checkColumnHasValue(0, 3, type);

		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("code", codeA);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("description", descriptionA);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("link", linkA);
		super.checkInputBoxHasValue("type", type);

		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/any/toolkit/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTestAnonymous(final int recordIndex, final String code, final String title, final String description, final String assemblyNotes, final String link, final String price, final String name, final String codeA, final String technology,
		final String descriptionA, final String retailPrice, final String linkA, final String type) {

		super.clickOnMenu("Anonymous", "List of Toolkits");

		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("assemblyNotes", assemblyNotes);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("price", price);

		super.clickOnButton("Artefacts");

		super.checkListingExists();
		super.sortListing(0, "asc");

		super.checkColumnHasValue(0, 0, name);
		super.checkColumnHasValue(0, 1, technology);
		super.checkColumnHasValue(0, 2, retailPrice);
		super.checkColumnHasValue(0, 3, type);

		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("code", codeA);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("description", descriptionA);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("link", linkA);
		super.checkInputBoxHasValue("type", type);

	}

}
