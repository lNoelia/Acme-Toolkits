package acme.testing.administrator.systemConfiguration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorSystemConfigurationUpdateTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/system-configuration/update.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String acceptedCurrencies, final String systemCurrency, final String strongSpamWords, final String strongSpamThreshold, final String weakSpamWords, final String weakSpamThreshold) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "System configuration");
		super.checkFormExists();
		super.fillInputBoxIn("acceptedCurrencies", acceptedCurrencies);
		super.fillInputBoxIn("systemCurrency", systemCurrency);
		super.fillInputBoxIn("strongSpamWords", strongSpamWords);
		super.fillInputBoxIn("strongSpamThreshold", strongSpamThreshold);
		super.fillInputBoxIn("weakSpamWords", weakSpamWords);
		super.fillInputBoxIn("weakSpamThreshold", weakSpamThreshold);
		super.clickOnSubmit("Update");
		
		super.clickOnMenu("Administrator", "System configuration");
		super.checkFormExists();
		super.checkInputBoxHasValue("acceptedCurrencies", acceptedCurrencies);
		super.checkInputBoxHasValue("systemCurrency", systemCurrency);
		super.checkInputBoxHasValue("strongSpamWords", strongSpamWords);
		super.checkInputBoxHasValue("strongSpamThreshold", strongSpamThreshold);
		super.checkInputBoxHasValue("weakSpamWords", weakSpamWords);
		super.checkInputBoxHasValue("weakSpamThreshold", weakSpamThreshold);
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/system-configuration/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String acceptedCurrencies, final String systemCurrency, final String strongSpamWords, final String strongSpamThreshold, final String weakSpamWords, final String weakSpamThreshold) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "System configuration");
		super.checkFormExists();
		super.fillInputBoxIn("acceptedCurrencies", acceptedCurrencies);
		super.fillInputBoxIn("systemCurrency", systemCurrency);
		super.fillInputBoxIn("strongSpamWords", strongSpamWords);
		super.fillInputBoxIn("strongSpamThreshold", strongSpamThreshold);
		super.fillInputBoxIn("weakSpamWords", weakSpamWords);
		super.fillInputBoxIn("weakSpamThreshold", weakSpamThreshold);
		super.clickOnSubmit("Update");
		
		super.checkErrorsExist();
	}
	

	@Test
	@Order(30)
	public void hackingTest() {
		super.checkNotLinkExists("Account");
		super.navigate("/administrator/system-configuration/update");
		super.checkPanicExists();
		
		super.signIn("patron1","patron1");
		super.navigate("/administrator/system-configuration/update");
		super.checkErrorsExist();
		super.signOut();
		
		super.signIn("user1","user1");
		super.navigate("/administrator/system-configuration/update");
		super.checkErrorsExist();
		super.signOut();
		
		super.signIn("inventor1","inventor1");
		super.navigate("/administrator/system-configuration/update");
		super.checkErrorsExist();
		super.signOut();
	}
}
