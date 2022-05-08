<%--
- list.jsp
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

<jstl:choose>
	<jstl:when test="${acme:anyOf(command, 'list-by-toolkit')}">
		<acme:list readonly="true">
			<acme:list-column code="inventor.artefact.list.label.name" path="name" width="20%"/>
			<acme:list-column code="inventor.artefact.list.label.technology" path="technology" width="20%"/>
			<acme:list-column code="inventor.artefact.list.label.retailPrice" path="retailPrice" width="20%"/>	
			<acme:list-column code="inventor.artefact.list.label.type" path="type" width="20%"/>
		</acme:list>
	</jstl:when>
	<jstl:when test="${acme:anyOf(command, 'list-mine')}">
		<acme:list>
			<acme:list-column code="inventor.artefact.list.label.name" path="name" width="20%"/>
			<acme:list-column code="inventor.artefact.list.label.technology" path="technology" width="20%"/>
			<acme:list-column code="inventor.artefact.list.label.retailPrice" path="retailPrice" width="20%"/>	
			<acme:list-column code="inventor.artefact.list.label.type" path="type" width="20%"/>
		</acme:list>
		<acme:button code="inventor.artefact.form.button.create" action="/inventor/artefact/create"/>
	</jstl:when>
</jstl:choose>
