package acme.testing.any.userAccount;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyUserAccountCreateTest extends TestHarness {

		// Test cases -------------------------------------------------------------
		@ParameterizedTest
		@CsvFileSource(resources = "/any/user-account/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void positiveTest(final String username, final String password, final String confirmation, final String name, final String surname, final String email) {
			
			super.signUp(username, password, name, surname, email);
			super.signIn(username, password);
			
			super.signOut();
		}
		
		@ParameterizedTest
		@CsvFileSource(resources = "/any/user-account/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(20)
		public void negativeTest(final String username, final String password, final String confirmation, final String name, final String surname, final String email, final String checkbox) {
			super.clickOnMenu("Sign up");	
			super.fillInputBoxIn("username", username);
			super.fillInputBoxIn("password", password);
			super.fillInputBoxIn("confirmation", confirmation);
			super.fillInputBoxIn("identity.name", name);
			super.fillInputBoxIn("identity.surname", surname);
			super.fillInputBoxIn("identity.email", email);		
			super.fillInputBoxIn("accept", checkbox);
			super.clickOnSubmit("Sign up");
			
			super.checkErrorsExist();
		}
		
		@Test
		@Order(30)
		public void hackingTest() {
			// HINT: there's no hacking test case for this create, this feature
			// HINT+ is available for all principals.
		}
}
