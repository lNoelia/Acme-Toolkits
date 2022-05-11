package acme.testing.authenticated.patron;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedPatronCreateTest extends TestHarness {
	
			// Test cases -------------------------------------------------------------
			@ParameterizedTest
			@CsvFileSource(resources = "/authenticated/patron/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
			@Order(10)
			public void positiveTest(final String username, final String password, final String company, final String statement, final String link) {
				super.signIn(username, password);
				
				super.clickOnMenu("Account", "Become a patron");
			
	            super.fillInputBoxIn("company", company);
	            super.fillInputBoxIn("statement", statement);
	            super.fillInputBoxIn("link", link);
	            super.clickOnSubmit("Register");
	            
	            super.clickOnMenu("Account", "Patron data");
	            
	            super.checkInputBoxHasValue("company", company);
	            super.checkInputBoxHasValue("statement", statement);
	            super.checkInputBoxHasValue("link", link);
				
				super.signOut();
			}
			
			@ParameterizedTest
			@CsvFileSource(resources = "/authenticated/patron/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
			@Order(20)
			public void negativeTest(final String username, final String password, final String company, final String statement, final String link) {
				super.signIn(username, password);
				
				super.clickOnMenu("Account", "Become a patron");
				
	            super.fillInputBoxIn("company", company);
	            super.fillInputBoxIn("statement", statement);
	            super.fillInputBoxIn("link", link);
	            super.clickOnSubmit("Register");
				
	            super.checkErrorsExist();
	            
				super.signOut();
			}
			
			@Test
			@Order(30)
			public void hackingTest() {
				super.checkNotLinkExists("Account");
				super.navigate("/authenticated/patron/create");
				super.checkPanicExists();
				
				super.signIn("patron1", "patron1");
				super.navigate("/authenticated/patron/create");
				super.checkPanicExists();
				super.signOut();
			}
}
