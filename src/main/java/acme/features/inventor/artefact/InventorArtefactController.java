package acme.features.inventor.artefact;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.artefact.Artefact;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorArtefactController extends AbstractController<Inventor, Artefact>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorArtefactListMineService		listMineService;
	
	@Autowired
	protected InventorArtefactListByToolkitService listByToolkitService;

	@Autowired
	protected InventorArtefactShowService		showService;
	
	@Autowired
	protected InventorArtefactCreateService		createService;
	
	@Autowired
	protected InventorArtefactUpdateService		updateService;

	@Autowired
	protected InventorArtefactPublishService		publishService;
	
	@Autowired
	protected InventorArtefactDeleteService		deleteService;

	// Constructors -----------------------------------------------------------
 
	@PostConstruct
	protected void initialise() {
		
		super.addCommand("list-mine","list", this.listMineService);
		super.addCommand("list-by-toolkit","list", this.listByToolkitService);
		super.addCommand("show", this.showService);
		
		super.addCommand("create", this.createService);
		super.addCommand("update", this.updateService);
		super.addCommand("delete", this.deleteService);
		super.addCommand("publish", "update", this.publishService);
	}
}