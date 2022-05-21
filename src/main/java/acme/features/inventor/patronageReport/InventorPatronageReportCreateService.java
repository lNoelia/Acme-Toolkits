package acme.features.inventor.patronageReport;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronageReport.PatronageReport;
import acme.entities.patronages.Patronage;
import acme.features.inventor.patronage.InventorPatronageRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorPatronageReportCreateService implements AbstractCreateService<Inventor, PatronageReport> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorPatronageReportRepository repository;
	
	@Autowired
	protected InventorPatronageRepository repositoryPatronage;

	// AbstractListService<Inventor, PatronageReport> interface --------------


	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;
		final boolean result;
		Patronage patronage;
		int id;
		
		id = request.getModel().getInteger("masterId");
		patronage = this.repository.findPatronageReportById(id);
		result = request.isPrincipal(patronage.getInventor());

		return result;
	}
	
	@Override
	public PatronageReport instantiate(final Request<PatronageReport> request) {
		assert request != null;

		PatronageReport result;
		Date creationDate;
        Patronage patronage;
        List<String> sequenceNumbers;
        String number;
        Integer lastNumber;
		result = new PatronageReport();
		
        
        patronage = this.repositoryPatronage.findPatronageById(request.getModel().getInteger("masterId"));
        sequenceNumbers = this.repository.findSequenceNumberByPatronageId(patronage.getId());
        if(sequenceNumbers.isEmpty()) {
            result.setSequenceNumber(patronage.getCode() + ":0001");
        }else {
            lastNumber = Integer.valueOf(sequenceNumbers.get(0).split(":")[1]);
            lastNumber = lastNumber + 1;
            number = "000" + lastNumber.toString();
            result.setSequenceNumber(patronage.getCode() + ":" + number.charAt(number.length()-4) + number.charAt(number.length()-3) + number.charAt(number.length()-2) + number.charAt(number.length()-1));
        }
        

		creationDate = new Date(System.currentTimeMillis() - 1);
		
		result.setCreationDate(creationDate);
		result.setLink("");
		result.setMemorandum("");
		result.setPatronage(patronage);
		result.setPatron(patronage.getPatron());
		result.setInventor(patronage.getInventor());

		return result;
	}

	@Override
	public void bind(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "memorandum", "link");
	}
	
	@Override
	public void validate(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean confirmation;

		confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");
	}
	
	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "memorandum", "link");
		model.setAttribute("confirmation", false);
		model.setAttribute("readonly", false);
		model.setAttribute("masterId", request.getModel().getAttribute("masterId"));
	}
	

	@Override
    public void create(final Request<PatronageReport> request, final PatronageReport entity) {
        assert request != null;
        assert entity != null;

        Date creationDate;

        creationDate = new Date(System.currentTimeMillis() - 1);
        entity.setCreationDate(creationDate);
        
        this.repository.save(entity);
    }
    
	

}