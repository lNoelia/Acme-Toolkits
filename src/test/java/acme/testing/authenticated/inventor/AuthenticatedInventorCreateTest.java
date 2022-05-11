package acme.testing.authenticated.inventor;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedInventorCreateTest extends TestHarness {
	
			// Test cases -------------------------------------------------------------
			@ParameterizedTest
			@CsvFileSource(resources = "/authenticated/inventor/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
			@Order(10)
			public void positiveTest(final String username, final String password, final String company, final String statement, final String link) {
				super.signIn(username, password);
				
				super.clickOnMenu("Account", "Become an inventor");
			
	            super.fillInputBoxIn("company", company);
	            super.fillInputBoxIn("statement", statement);
	            super.fillInputBoxIn("link", link);
	            super.clickOnSubmit("Register");
	            
	            super.clickOnMenu("Account", "Inventor data");
	            
	            super.checkInputBoxHasValue("company", company);
	            super.checkInputBoxHasValue("statement", statement);
	            super.checkInputBoxHasValue("link", link);
				
				super.signOut();
			}
			
			@ParameterizedTest
			@CsvFileSource(resources = "/authenticated/inventor/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
			@Order(20)
			public void negativeTest(final String username, final String password, final String company, final String statement, final String link) {
				super.signIn(username, password);
				
				super.clickOnMenu("Account", "Become an inventor");
				
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
				super.navigate("/authenticated/inventor/create");
				super.checkPanicExists();
				
				super.signIn("inventor1", "inventor1");
				super.navigate("/authenticated/inventor/create");
				super.checkPanicExists();
				super.signOut();
			}
			
			
}
