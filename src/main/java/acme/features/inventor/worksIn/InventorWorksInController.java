package acme.features.inventor.worksIn;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.worksIn.WorksIn;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorWorksInController extends AbstractController<Inventor, WorksIn>{

	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected InventorWorksInListService listService;
	
	
	  @Autowired
	  protected InventorWorksInShowService showService;
	  
	  @Autowired
	  protected InventorWorksInCreateService createService;
	  
	  @Autowired
	  protected InventorWorksInUpdateService updateService;
	 
	  @Autowired
	  protected InventorWorksInDeleteService deleteService;
	  

	// Constructors -----------------------------------------------------------
 
	@PostConstruct
	protected void initialise() {
		
		super.addCommand("list", this.listService);
		
		super.addCommand("show", this.showService);
		
		super.addCommand("create", this.createService);
		super.addCommand("update", this.updateService);
		super.addCommand("delete", this.deleteService);
	}
}