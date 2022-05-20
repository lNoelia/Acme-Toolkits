package acme.features.any.worksIn;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.worksIn.WorksIn;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyWorksInController extends AbstractController<Any, WorksIn>{

	// Internal state ---------------------------------------------------------
	@Autowired
	protected AnyWorksInShowService		showService;
	
	@Autowired
	protected AnyWorksInListByToolkitService listByToolkitService;

	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("list-by-toolkit", "list", this.listByToolkitService);
	}
}
