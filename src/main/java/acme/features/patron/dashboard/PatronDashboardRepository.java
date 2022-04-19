/*
 * AdministratorDashboardRepository.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.patron.dashboard;

import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronages.PatronageStatus;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronDashboardRepository extends AbstractRepository {

	
	@Query("select count(p) FROM Patronage p WHERE p.status =  :status")
	int totalNumberOfPatronagesByStatus(PatronageStatus status);
	
	@Query("select new map(p.budget.currency,avg(p.budget.amount)) from Patronage p where p.status = :status group by p.budget.currency")
	Map<String,Double> averageBudgetOfPatronagesByStatusAndCurrency(PatronageStatus status);

	@Query("select new map(p.budget.currency,stddev(p.budget.amount)) from Patronage p where p.status = :status group by p.budget.currency")
	Map<String,Double> deviationBudgetOfPatronagesByStatusAndCurrency(PatronageStatus status);

	@Query("select new map(p.budget.currency,min(p.budget.amount)) from Patronage p where p.status = :status group by p.budget.currency")
	Map<String,Double> minimumBudgetOfPatronagesByStatusAndCurrency(PatronageStatus status);

	@Query("select new map(p.budget.currency,max(p.budget.amount)) from Patronage p where p.status = :status group by p.budget.currency")
	Map<String,Double> maximumBudgetOfPatronagesByStatusAndCurrency(PatronageStatus status);

}
