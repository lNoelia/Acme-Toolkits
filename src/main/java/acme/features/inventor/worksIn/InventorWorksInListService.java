/*
 * EmployerApplicationListService.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.inventor.worksIn;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.Toolkit;
import acme.entities.worksIn.WorksIn;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.helpers.CollectionHelper;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorWorksInListService implements AbstractListService<Inventor, WorksIn> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorWorksInRepository repository;

	// AbstractListService<Inventor, WorksIn> interface -------------------


	@Override
	public boolean authorise(final Request<WorksIn> request) {
		assert request != null;

		final int masterId;
		int activeRolId;
		Toolkit toolkit;
		
		masterId = request.getModel().getInteger("masterId");
		activeRolId = request.getPrincipal().getActiveRoleId();
		toolkit = this.repository.findOneToolkitById(masterId);
		
		return activeRolId == toolkit.getInventor().getId();
	}

	@Override
	public Collection<WorksIn> findMany(final Request<WorksIn> request) {
		assert request != null;

		Collection<WorksIn> result;
		final int masterId;

		masterId = request.getModel().getInteger("masterId");
		result = this.repository.findAllWorksInByToolkit(masterId);

		return result;
	}

	@Override
	public void unbind(final Request<WorksIn> request, final Collection<WorksIn> entities, final Model model) {
		assert request != null;
		assert !CollectionHelper.someNull(entities);
		assert model != null;

		int masterId;
		Toolkit toolkit;
		final boolean showCreate;
		
		masterId = request.getModel().getInteger("masterId");
		toolkit = this.repository.findOneToolkitById(masterId);
		showCreate = (!toolkit.isPublished() && request.isPrincipal(toolkit.getInventor()));
		
		model.setAttribute("masterId", masterId);
		model.setAttribute("showCreate", showCreate);
	}
	
	@Override
	public void unbind(final Request<WorksIn> request, final WorksIn entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "amount", "artefact.type", "artefact.name","artefact.code","artefact.retailPrice");
	}

}
