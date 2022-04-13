<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:input-textbox code="inventor.patronage.form.label.code" path="code"/>
	<acme:input-textbox code="inventor.patronage.form.label.status" path="status"/>
	<acme:input-money code="inventor.patronage.form.label.budget" path="budget"/>
	<acme:input-textbox code="inventor.patronage.form.label.patron" path="patron"/>
	<acme:input-textbox code="inventor.patronage.form.label.legalStuff" path="legalStuff"/>
	<acme:input-url code="inventor.patronage.form.label.link" path="link"/>
	<acme:input-textbox code="inventor.patronage.form.label.creationDate" path="creationDate"/>
	<acme:input-textbox code="inventor.patronage.form.label.startDate" path="startDate"/>
	<acme:input-textbox code="inventor.patronage.form.label.endDate" path="endDate"/>
</acme:form>

