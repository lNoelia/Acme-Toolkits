package acme.testing.authenticated.patron;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedPatronUpdateTest extends TestHarness {

	// Test cases -------------------------------------------------------------
		@ParameterizedTest
		@CsvFileSource(resources = "/authenticated/patron/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void positiveTest(final String company, final String statement, final String link) {
			super.signIn("patron1", "patron1");

			super.clickOnMenu("Account", "Patron data");

			super.fillInputBoxIn("company", company);
			super.fillInputBoxIn("statement", statement);
			super.fillInputBoxIn("link", link);
			super.clickOnSubmit("Update");

			super.clickOnMenu("Account", "Patron data");

			super.checkInputBoxHasValue("company", company);
			super.checkInputBoxHasValue("statement", statement);
			super.checkInputBoxHasValue("link", link);

			super.signOut();
		}
		
		@ParameterizedTest
		@CsvFileSource(resources = "/authenticated/patron/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(20)
		public void negativeTest(final String company, final String statement, final String link) {
			super.signIn("patron1", "patron1");
			
			super.clickOnMenu("Account", "Patron data");
			
	        super.fillInputBoxIn("company", company);
	        super.fillInputBoxIn("statement", statement);
	        super.fillInputBoxIn("link", link);
	        super.clickOnSubmit("Update");
			
	        super.checkErrorsExist();
	        
			super.signOut();
		}
		
		@Test
		@Order(30)
		public void hackingTest() {
			super.checkNotLinkExists("Account");
			super.navigate("/authenticated/patron/update");
			super.checkPanicExists();
			
			super.signIn("inventor1", "inventor1");
			super.navigate("/authenticated/patron/update");
			super.checkPanicExists();
			super.signOut();
			
			super.signIn("user1", "user1");
			super.navigate("/authenticated/patron/update");
			super.checkPanicExists();
			super.signOut();
			
			super.signIn("administrator2", "administrator2");
			super.navigate("/authenticated/patron/update");
			super.checkPanicExists();
			super.signOut();
		}
		
}
