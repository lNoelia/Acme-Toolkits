/*
 * EmployerApplicationLIstTest.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.testing.patron.patronageReport;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PatronPatronageReportListTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage-report/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String sequenceNumber, final String creationDate, final String patronageCode, final String memorandum, final String link) {
		super.signIn("patron1", "patron1");

		super.clickOnMenu("Patron", "List my applications");
		super.checkListingExists();
		super.sortListing(1, "asc");
		super.checkColumnHasValue(recordIndex, 0, patronageCode);
		super.checkColumnHasValue(recordIndex, 1, sequenceNumber);
		super.checkColumnHasValue(recordIndex, 2, creationDate);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("sequenceNumber",sequenceNumber);
		super.checkInputBoxHasValue("creationDate", creationDate);
		super.checkInputBoxHasValue("patronageCode", patronageCode);
		super.checkInputBoxHasValue("memorandum",memorandum);
		super.checkInputBoxHasValue("link",link);

		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage-report/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex,  final String sequenceNumber, final String creationDate, final String patronageCode, final String memorandum, final String link) {
		super.signIn("patron3", "patron3");
		
		super.clickOnMenu("Patron", "List of patronage reports");
		super.checkListingEmpty();
		
		super.signOut();
	}

	// Ancillary methods ------------------------------------------------------

}
