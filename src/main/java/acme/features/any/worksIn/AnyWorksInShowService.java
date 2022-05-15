package acme.features.any.worksIn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.moneyExchange.MoneyExchangeService;
import acme.entities.worksIn.WorksIn;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyWorksInShowService implements AbstractShowService<Any, WorksIn>{


	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyWorksInRepository repository;
	
	@Autowired
	protected MoneyExchangeService moneyExchangeService;


	@Override
	public boolean authorise(final Request<WorksIn> request) {
		assert request != null;
		
		int id;
		WorksIn worksIn;
		
		id = request.getModel().getInteger("id");
		worksIn = this.repository.findOneWorksInById(id);
		
		return worksIn.getToolkit().isPublished();
	}

	@Override
	public WorksIn findOne(final Request<WorksIn> request) {
		assert request != null;

		WorksIn result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneWorksInById(id);

		return result;
	}
	
	@Override
	public void unbind(final Request<WorksIn> request, final WorksIn entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		Money convertedPrice;
		Money retailPrice;
		
		retailPrice = entity.getArtefact().getRetailPrice();
		convertedPrice = this.moneyExchangeService.convertToSystemCurrency(retailPrice);

		request.unbind(entity, model,"amount", "artefact.name", "artefact.code", "artefact.technology", "artefact.description", "artefact.retailPrice", "artefact.link", "artefact.type");	
		model.setAttribute("convertedPrice", convertedPrice);
	}
	
}
