<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<acme:form>
	<jstl:choose>
	
		<jstl:when test="${acme:anyOf(command, 'show, update, delete')}">
		
			<acme:input-integer code="inventor.worksin.form.label.amount" path="amount" readonly="${readonly}"/>
			<acme:input-textbox code="inventor.worksin.form.label.name" path="artefact.name" readonly="true"/>
			<acme:input-textbox code="inventor.worksin.form.label.type" path="artefact.type" readonly="true"/>
			<acme:input-textbox code="inventor.worksin.form.label.code" path="artefact.code" readonly="true"/>
			<acme:input-money code="inventor.worksin.form.label.price" path="artefact.retailPrice" readonly="true"/>
			<acme:input-money code="any.worksIn.form.label.convertedPrice" path="convertedPrice" readonly="true"/>
			<acme:input-textbox code="inventor.worksin.form.label.technology" path="artefact.technology" readonly="true"/>
			<acme:input-textarea code="inventor.worksin.form.label.description" path="artefact.description" readonly="true"/>
			<acme:input-url code="inventor.worksin.form.label.link" path="artefact.link" readonly="true"/>
		
			<acme:submit test="${!readonly}" code="inventor.worksin.form.button.update" action="/inventor/works-in/update"/>
			<acme:submit test="${!published}" code="inventor.worksin.form.button.delete" action="/inventor/works-in/delete"/>
		</jstl:when>
		
		<jstl:when test="${command == 'create'}">
		
			<acme:input-select code="inventor.worksin.form.label.artefact" path="artefactId" readonly="false">
	        	<c:forEach var="artefact" items="${artefacts}">
	            	<acme:input-option selected="null" code="${artefact.toString()}" value="${artefact.id}"/>
	        	</c:forEach>
			</acme:input-select>
			<acme:input-integer code="inventor.worksin.form.label.amount" path="amount" readonly="false"/>
			
			<acme:submit code="inventor.worksin.form.button.add" action="/inventor/works-in/create"/>
		</jstl:when>	
			
	</jstl:choose>
	
</acme:form>
