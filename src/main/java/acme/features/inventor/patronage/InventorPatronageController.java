package acme.features.inventor.patronage;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.patronages.Patronage;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorPatronageController extends AbstractController<Inventor, Patronage>{
	
	@Autowired
	protected InventorPatronageListAllService listAllService;
	
	@Autowired
	protected InventorPatronageShowService showService;
	
	@Autowired
	protected InventorPatronageUpdateService updateService;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listAllService);
		super.addCommand("show", this.showService);
		super.addCommand("update",this.updateService);
	}
	
	
}
