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
	<acme:input-textbox code="any.worksIn.form.label.amount" path="amount"/>
	<acme:input-textbox code="any.worksIn.form.label.artefact.name" path="artefact.name"/>
	<acme:input-textbox code="any.worksIn.form.label.artefact.code" path="artefact.code"/>
	<acme:input-textbox code="any.worksIn.form.label.artefact.technology" path="artefact.technology"/>
	<acme:input-textarea code="any.worksIn.form.label.artefact.description" path="artefact.description"/>
	<acme:input-money code="any.worksIn.form.label.artefact.retailPrice" path="artefact.retailPrice"/>
	<acme:input-money code="any.worksIn.form.label.convertedPrice" path="convertedPrice" readonly="true"/>
	<acme:input-url code="any.worksIn.form.label.artefact.link" path="artefact.link"/>		
	<acme:input-select code="any.worksIn.form.label.artefact.type" path="artefact.type">
		<acme:input-option code="TOOL" value="TOOL" selected="${type == 'TOOL'}"/>
		<acme:input-option code="COMPONENT" value="COMPONENT" selected="${type == 'COMPONENT'}"/>
	</acme:input-select>
</acme:form>

