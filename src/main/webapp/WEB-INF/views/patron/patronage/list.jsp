<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="patron.patronage.list.label.code" path="code" width="20%"/>
	<acme:list-column code="patron.patronage.list.label.budget" path="budget" width="20%"/>	
	<acme:list-column code="patron.patronage.list.label.startDate" path="startDate" width="20%"/>
	<acme:list-column code="patron.patronage.list.label.endDate" path="endDate" width="20%"/>
</acme:list>