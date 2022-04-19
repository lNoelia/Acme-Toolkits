<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="any.userAccount.list.label.name" path="name" width="20%"/>
	<acme:list-column code="any.userAccount.list.label.surname" path="surname" width="20%"/>
	<acme:list-column code="any.userAccount.list.label.roles" path="roles" width="60%"/>
</acme:list>