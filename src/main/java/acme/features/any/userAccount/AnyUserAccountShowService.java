package acme.features.any.userAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.UserAccount;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyUserAccountShowService implements AbstractShowService<Any, UserAccount> {

	@Autowired
	private AnyUserAccountRepository repository;

	@Override
	public boolean authorise(final Request<UserAccount> request) {
		assert request != null;
		
		int id;
		UserAccount userAccount;
		
		id = request.getModel().getInteger("id");
		userAccount = this.repository.findUserAccountById(id);

		return !userAccount.getAuthorityString().contains("Administrator") &&
			   !userAccount.getAuthorityString().contains("Anonymous");
	}

	@Override
	public UserAccount findOne(final Request<UserAccount> request) {
		assert request != null;

		UserAccount result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findUserAccountById(id);

		return result;
	}

	@Override
	public void unbind(final Request<UserAccount> request, final UserAccount entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model);
		model.setAttribute("name", entity.getIdentity().getName());
		model.setAttribute("surname", entity.getIdentity().getSurname());
		model.setAttribute("email", entity.getIdentity().getEmail());
		model.setAttribute("roles", entity.getAuthorityString());
	}

}
