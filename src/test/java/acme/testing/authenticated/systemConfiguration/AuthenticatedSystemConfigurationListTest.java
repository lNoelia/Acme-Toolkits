package acme.testing.authenticated.systemConfiguration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;


public class AuthenticatedSystemConfigurationListTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/system-configuration/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String acceptedCurrency, final String currentCurrency) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Authenticated", "Currency information");
		super.checkListingExists();
		super.sortListing(1, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, acceptedCurrency);
		super.checkColumnHasValue(recordIndex, 1, currentCurrency);

		super.signOut();
	}
	
	@Test
	@Order(20)
	public void negativeTest() {
		// HINT: there's no negative test case for this listing, since it doesn't
		// HINT+ involve filling in any forms.
	}
	
	
	@Test
	@Order(10)
	public void hackingTest() {
		super.checkNotLinkExists("Account");
		super.navigate("/authenticated/system-configuration/list");
		super.checkErrorsExist();
	}
}
