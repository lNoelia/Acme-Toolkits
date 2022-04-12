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
	protected InventorArtefactPerToolkitListService		listPerToolkitService;

	@Autowired
	protected InventorArtefactShowService		showService;

	// Constructors -----------------------------------------------------------
 
	@PostConstruct
	protected void initialise() {
		super.addCommand("list-mine","list", this.listMineService);
		super.addCommand("list-per-toolkit","list", this.listPerToolkitService);
		super.addCommand("show", this.showService);
	}
}