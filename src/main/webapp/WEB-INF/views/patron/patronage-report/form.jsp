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
	<acme:input-textbox code="patron.patronageReport.form.label.patronageCode" path="patronageCode"/>
	<acme:input-textbox code="patron.patronageReport.form.label.sequenceNumber" path="sequenceNumber"/>
	<acme:input-moment code="patron.patronageReport.form.label.creationDate" path="creationDate"/>
	<acme:input-textarea code="patron.patronageReport.form.label.memorandum" path="memorandum"/>
	<acme:input-url code="patron.patronageReport.form.label.link" path="link"/>
</acme:form>

