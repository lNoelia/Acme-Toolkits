package acme.features.administrator.dashboard;

import java.util.Collection;
import java.util.List;

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
		Collection<List<String>> averageRetailPriceOfComponentsByTechnologyAndCurrency();
	
		@Query("select a.technology, a.retailPrice.currency, stddev( a.retailPrice.amount ) from Artefact a where a.type = acme.entities.artefact.ArtefactType.COMPONENT group by a.technology, a.retailPrice.currency")
		Collection<List<String>> deviationRetailPriceOfComponentsByTechnologyAndCurrency();
		
		@Query("select a.technology, a.retailPrice.currency, min( a.retailPrice.amount ) from Artefact a where a.type = acme.entities.artefact.ArtefactType.COMPONENT group by a.technology, a.retailPrice.currency")
		Collection<List<String>> minimumRetailPriceOfComponentsByTechnologyAndCurrency();
		
		@Query("select a.technology, a.retailPrice.currency, max( a.retailPrice.amount ) from Artefact a where a.type = acme.entities.artefact.ArtefactType.COMPONENT group by a.technology, a.retailPrice.currency")
		Collection<List<String>> maximumRetailPriceOfComponentsByTechnologyAndCurrency();
		
		
		//total number of tools --------------------------------------------------------------------------------------------------
		@Query("select count(a) from Artefact a where a.type = acme.entities.artefact.ArtefactType.TOOL")
		int totalNumberOfTools();
		
		//average, deviation, minimum and maximum retail price of components, grouped by currency -------------------------------------------------
		@Query("select a.retailPrice.currency, avg( a.retailPrice.amount ) from Artefact a where a.type = acme.entities.artefact.ArtefactType.TOOL group by a.retailPrice.currency")
		Collection<List<String>> averageRetailPriceOfToolsByCurrency();
		
		@Query("select a.retailPrice.currency, stddev( a.retailPrice.amount ) from Artefact a where a.type = acme.entities.artefact.ArtefactType.TOOL group by a.retailPrice.currency")
		Collection<List<String>> deviationRetailPriceOfToolsByCurrency();
		
		@Query("select a.retailPrice.currency, min( a.retailPrice.amount ) from Artefact a where a.type = acme.entities.artefact.ArtefactType.TOOL group by a.retailPrice.currency")
		Collection<List<String>> minimumRetailPriceOfToolsByCurrency();
		
		@Query("select a.retailPrice.currency, max( a.retailPrice.amount ) from Artefact a where a.type = acme.entities.artefact.ArtefactType.TOOL group by a.retailPrice.currency")
		Collection<List<String>> maximumRetailPriceOfToolsByCurrency();
		
		
		//total number of patronages by status --------------------------------------------------------------------------------------------------
		@Query("select p.status, count(p) from Patronage p group by p.status")
		Collection<List<String>> totalNumberOfPatronagesByStatus();
		
		//average, deviation, minimum and maximum budget of components, grouped by status -------------------------------------------------
		@Query("select p.status, avg(p.budget.amount) from Patronage p group by p.status")
		Collection<List<String>> averageBudgetOfPatronagesByStatus();
		
		@Query("select p.status, stddev(p.budget.amount) from Patronage p group by p.status")
		Collection<List<String>> deviationBudgetOfPatronagesByStatus();
		
		@Query("select p.status, min(p.budget.amount) from Patronage p group by p.status")
		Collection<List<String>> minimumBudgetOfPatronagesByStatus();
		
		@Query("select p.status, max(p.budget.amount) from Patronage p group by p.status")
		Collection<List<String>> maximumBudgetOfPatronagesByStatus();
		
		
}
