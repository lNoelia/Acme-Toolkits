<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="inventor.worksin.form.label.amount" path="amount"/>	
	<acme:input-textbox code="inventor.worksin.form.label.name" path="artefact.name"/>
	<acme:input-textbox code="inventor.worksin.form.label.type" path="artefact.type"/>
	<acme:input-textbox code="inventor.worksin.form.label.code" path="artefact.code"/>
	<acme:input-textbox code="inventor.worksin.form.label.price" path="artefact.retailPrice"/>
	<acme:input-textbox code="inventor.worksin.form.label.technology" path="artefact.technology"/>
	<acme:input-textarea code="inventor.worksin.form.label.description" path="artefact.description"/>
	<acme:input-url code="inventor.worksin.form.label.link" path="artefact.link"/>

</acme:form>

