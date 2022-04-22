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

<acme:form readonly="true">
	<acme:input-money code="administrator.systemConfiguration.form.label.acceptedCurrencies" path="acceptedCurrencies"/>
	<acme:input-money code="administrator.systemConfiguration.form.label.systemCurrency" path="systemCurrency"/>
	<acme:input-textbox code="administrator.systemConfiguration.form.label.strongSpamWords" path="strongSpamWords"/>
	<acme:input-double code="administrator.systemConfiguration.form.label.strongSpamThreshold" path="strongSpamThreshold"/>
	<acme:input-textbox code="administrator.systemConfiguration.form.label.weakSpamWords" path="weakSpamWords"/>
	<acme:input-double code="administrator.systemConfiguration.form.label.weakSpamThreshold" path="weakSpamThreshold"/>
</acme:form>

