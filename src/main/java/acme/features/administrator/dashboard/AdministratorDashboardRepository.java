package acme.features.administrator.dashboard;

import java.util.Collection;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository{

		//total number of components ---------------------------------------------------------------------------------------------
		@Query("select count(a) from Artefact a where a.type = acme.entities.artefact.ArtefactType.COMPONENT")
		int totalNumberOfComponents();
	
		//average, deviation, minimum and maximum retail price of components, grouped by technology and currency -------------------------------------------------
		@Query("select a.technology, a.retailPrice.currency, avg( a.retailPrice.amount ) from Artefact a where a.type = acme.entities.artefact.ArtefactType.COMPONENT group by a.technology, a.retailPrice.currency")
		Collection<Tuple> averageRetailPriceOfComponentsByTechnologyAndCurrency();
	
		@Query("select a.technology, a.retailPrice.currency, stddev( a.retailPrice.amount ) from Artefact a where a.type = acme.entities.artefact.ArtefactType.COMPONENT group by a.technology, a.retailPrice.currency")
		Collection<Tuple> deviationRetailPriceOfComponentsByTechnologyAndCurrency();
		
		@Query("select a.technology, a.retailPrice.currency, min( a.retailPrice.amount ) from Artefact a where a.type = acme.entities.artefact.ArtefactType.COMPONENT group by a.technology, a.retailPrice.currency")
		Collection<Tuple> minimumRetailPriceOfComponentsByTechnologyAndCurrency();
		
		@Query("select a.technology, a.retailPrice.currency, max( a.retailPrice.amount ) from Artefact a where a.type = acme.entities.artefact.ArtefactType.COMPONENT group by a.technology, a.retailPrice.currency")
		Collection<Tuple> maximumRetailPriceOfComponentsByTechnologyAndCurrency();
		
		
		//total number of tools --------------------------------------------------------------------------------------------------
		@Query("select count(a) from Artefact a where a.type = acme.entities.artefact.ArtefactType.TOOL")
		int totalNumberOfTools();
		
		//average, deviation, minimum and maximum retail price of components, grouped by currency -------------------------------------------------
		@Query("select a.retailPrice.currency, avg( a.retailPrice.amount ) from Artefact a where a.type = acme.entities.artefact.ArtefactType.TOOL group by a.retailPrice.currency")
		Collection<Tuple> averageRetailPriceOfToolsByCurrency();
		
		@Query("select a.retailPrice.currency, stddev( a.retailPrice.amount ) from Artefact a where a.type = acme.entities.artefact.ArtefactType.TOOL group by a.retailPrice.currency")
		Collection<Tuple> deviationRetailPriceOfToolsByCurrency();
		
		@Query("select a.retailPrice.currency, min( a.retailPrice.amount ) from Artefact a where a.type = acme.entities.artefact.ArtefactType.TOOL group by a.retailPrice.currency")
		Collection<Tuple> minimumRetailPriceOfToolsByCurrency();
		
		@Query("select a.retailPrice.currency, max( a.retailPrice.amount ) from Artefact a where a.type = acme.entities.artefact.ArtefactType.TOOL group by a.retailPrice.currency")
		Collection<Tuple> maximumRetailPriceOfToolsByCurrency();
		
		
		//total number of patronages by status --------------------------------------------------------------------------------------------------
		@Query("select p.status, count(p) from Patronage p group by p.status")
		Collection<Tuple> totalNumberOfPatronagesByStatus();
		
		//average, deviation, minimum and maximum budget of components, grouped by status -------------------------------------------------
		@Query("select p.status, p.budget.currency, avg(p.budget.amount) from Patronage p group by p.status, p.budget.currency")
		Collection<Tuple> averageBudgetOfPatronagesByStatusAndCurrency();
		
		@Query("select p.status, p.budget.currency, stddev(p.budget.amount) from Patronage p group by p.status, p.budget.currency")
		Collection<Tuple> deviationBudgetOfPatronagesByStatusAndCurrency();
		
		@Query("select p.status, p.budget.currency, min(p.budget.amount) from Patronage p group by p.status, p.budget.currency")
		Collection<Tuple> minimumBudgetOfPatronagesByStatusAndCurrency();
		
		@Query("select p.status, p.budget.currency, max(p.budget.amount) from Patronage p group by p.status, p.budget.currency")
		Collection<Tuple> maximumBudgetOfPatronagesByStatusAndCurrency();
		
		
}
