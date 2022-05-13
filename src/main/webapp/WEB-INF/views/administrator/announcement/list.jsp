<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="administrator.announcement.list.label.creationDate" path="creationDate" width="20%"/>
	<acme:list-column code="administrator.announcement.list.label.critical" path="critical" width="10%"/>
	<acme:list-column code="administrator.announcement.list.label.title" path="title" width="70%"/>
</acme:list>

<acme:button code="administrator.announcement.list.button.create" action="/administrator/announcement/create"/>


