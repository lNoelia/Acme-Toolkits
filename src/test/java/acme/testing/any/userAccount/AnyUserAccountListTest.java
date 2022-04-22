package acme.testing.any.userAccount;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyUserAccountListTest extends TestHarness{
	
	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------
	@ParameterizedTest
	@CsvFileSource(resources = "/any/user-account/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTestAnonymous(final int recordIndex,final String name, final String surname, final String email, final String roles) {

		super.clickOnMenu("Anonymous", "User accounts");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 1, surname);
		super.checkColumnHasValue(recordIndex, 2, roles);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("surname", surname);
		super.checkInputBoxHasValue("email", email);
		super.checkInputBoxHasValue("roles", roles);

	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/user-account/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTestWithLog(final int recordIndex,final String name, final String surname, final String email, final String roles) {
		
		super.signIn("patron1", "patron1");

		super.clickOnMenu("Authenticated", "User accounts");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 1, surname);
		super.checkColumnHasValue(recordIndex, 2, roles);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("surname", surname);
		super.checkInputBoxHasValue("email", email);
		super.checkInputBoxHasValue("roles", roles);

	}
	
}

