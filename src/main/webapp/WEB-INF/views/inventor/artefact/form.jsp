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

<acme:form>
	<acme:input-textbox code="inventor.artefact.form.label.name" path="name"/>
	<acme:input-textbox code="inventor.artefact.form.label.code" path="code" placeholder="inventor.artefact.form.placeholder.code"/>
	<acme:input-textbox code="inventor.artefact.form.label.technology" path="technology"/>
	<acme:input-textarea code="inventor.artefact.form.label.description" path="description"/>
	<acme:input-double code="inventor.artefact.form.label.retail-price" path="retailPrice" placeholder="inventor.artefact.form.placeholder.retail-price"/>	
	<acme:input-url code="inventor.artefact.form.label.link" path="link"/>		
	<acme:input-select code="inventor.artefact.form.label.type" path="type">
		<acme:input-option code="TOOL" value="TOOL" selected="${type == 'TOOL'}"/>
		<acme:input-option code="COMPONENT" value="COMPONENT" selected="${type == 'COMPONENT'}"/>
	</acme:input-select>
	
	
	<jstl:choose>	 
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish') && (published != true)}">
			<acme:submit code="inventor.artefact.form.button.update" action="/inventor/artefact/update"/>
			<acme:submit code="inventor.artefact.form.button.delete" action="/inventor/artefact/delete"/>
			<acme:submit code="inventor.artefact.form.button.publish" action="/inventor/artefact/publish"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="inventor.artefact.form.button.create" action="/inventor/artefact/create"/>
		</jstl:when>		
	</jstl:choose>
	
	<acme:show-errors path="spam"/>
</acme:form>

