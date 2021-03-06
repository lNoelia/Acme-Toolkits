<%--
- form.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	<acme:input-textbox code="any.artefact.form.label.name" path="name"/>
	<acme:input-textbox code="any.artefact.form.label.code" path="code"/>
	<acme:input-textbox code="any.artefact.form.label.technology" path="technology"/>
	<acme:input-textarea code="any.artefact.form.label.description" path="description"/>
	<acme:input-money code="any.artefact.form.label.retailPrice" path="retailPrice"/>
	<acme:input-money code="any.artefact.form.label.convertedPrice" path="convertedPrice" readonly="true"/>
	<acme:input-url code="any.artefact.form.label.link" path="link"/>		
	<acme:input-select code="any.artefact.form.label.type" path="type">
		<acme:input-option code="TOOL" value="TOOL" selected="${type == 'TOOL'}"/>
		<acme:input-option code="COMPONENT" value="COMPONENT" selected="${type == 'COMPONENT'}"/>
	</acme:input-select>
</acme:form>

