<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	<acme:input-textbox code="any.chirp.form.label.title" path="title"/>
	<acme:input-moment code="any.chirp.form.label.creationDate" path="creationDate"/>
	<acme:input-textbox code="any.chirp.form.label.author" path="author"/>
	<acme:input-textarea code="any.chirp.form.label.body" path="body"/>
	<acme:input-email code="any.chirp.form.label.email" path="email"/>
</acme:form>