package acme.testing.any.userAccount;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyUserAccountCreateTest extends TestHarness {

		// Test cases -------------------------------------------------------------
		@ParameterizedTest
		@CsvFileSource(resources = "/any/user-account/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void positiveTest(final String username, final String password, final String confirmation, final String name, final String surname, final String email, final String phone) {
			
			super.signUp(username, password, name, surname, email);
			super.signIn(username, password);
			
			super.signOut();
		}
		
		@ParameterizedTest
		@CsvFileSource(resources = "/any/user-account/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(20)
		public void negativeTest(final String username, final String password, final String confirmation, final String name, final String surname, final String email, final String phone) {
			super.clickOnMenu("Sign up");	
			super.fillInputBoxIn("username", username);
			super.fillInputBoxIn("password", password);
			super.fillInputBoxIn("confirmation", password);
			super.fillInputBoxIn("identity.name", name);
			super.fillInputBoxIn("identity.surname", surname);
			super.fillInputBoxIn("identity.email", email);		
			super.fillInputBoxIn("accept", "true");
			super.clickOnSubmit("Sign up");
			
			super.checkErrorsExist();
		}
}
