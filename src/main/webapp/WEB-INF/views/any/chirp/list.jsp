<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="any.chirp.form.label.title" path="title" width="20%"/>
	<acme:list-column code="any.chirp.form.label.creationDate" path="creationDate" width="20%"/>
	<acme:list-column code="any.chirp.form.label.author" path="author" width="20%"/>
</acme:list>