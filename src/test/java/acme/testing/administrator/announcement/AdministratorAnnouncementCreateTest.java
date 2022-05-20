package acme.testing.administrator.announcement;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorAnnouncementCreateTest extends TestHarness{
		
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/announcement/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String title, final String body, final String critical, final String link) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Create announcement");
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("critical", critical);
		super.fillInputBoxIn("body", body);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("confirmation", "true");
		
		super.clickOnSubmit("Create");
		
		super.clickOnMenu("Authenticated", "Announcement list");
		super.checkListingExists();
		super.sortListing(2, "asc");
		
		super.checkColumnHasValue(recordIndex, 1, critical);
		super.checkColumnHasValue(recordIndex, 2, title);
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("critical", critical);
		super.checkInputBoxHasValue("body", body);
		super.checkInputBoxHasValue("link", link);
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/announcement/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String title, final String body, final String critical, final String link) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Create announcement");
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("critical", critical);
		super.fillInputBoxIn("body", body);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("confirmation", "true");
		
		super.clickOnSubmit("Create");
		super.checkErrorsExist();
		
		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		super.checkNotLinkExists("Account");
		super.navigate("/administrator/announcement/create");
		super.checkPanicExists();
		
		super.signIn("inventor2", "inventor2");
		super.navigate("/administrator/announcement/create");
		super.checkPanicExists();
		super.signOut();
		
		super.signIn("patron2", "patron2");
		super.navigate("/administrator/announcement/create");
		super.checkPanicExists();
		super.signOut();
	}
}
