<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:input-textbox code="any.userAccount.form.label.name" path="name"/>
	<acme:input-textbox code="any.userAccount.form.label.surname" path="surname"/>
	<acme:input-email code="any.userAccount.form.label.email" path="email"/>
	<acme:input-textbox code="any.userAccount.form.label.roles" path="roles"/>
</acme:form>