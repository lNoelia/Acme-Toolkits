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

<h2>
	<acme:message code="patron.dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm" aria-describedby="Total number of proposed patronages">
	<tr>
		<th scope="row">
			<acme:message code="patron.dashboard.form.label.total-number-of-proposed-patronages"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfProposedPatronages}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="patron.dashboard.form.label.total-number-of-accepted-patronages"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfAcceptedPatronages}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="patron.dashboard.form.label.total-number-of-denied-patronages"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfDeniedPatronages}"/>
		</td>
	</tr>	
</table>

<h2>
	<acme:message code="patron.dashboard.form.title.general-indicators-average-budgets"/>
</h2>

<table class="table table-sm" aria-describedby="Average budget of patronages by status and currency">
	<jstl:forEach var="map" items="${averageBudgetOfPatronagesByStatusAndCurrency}" >	
		<tr>
			<th scope="row">
				<jstl:out  value="${map.key}"/>
			</th>
			
			<td>
				<jstl:out value="${map.value}"/>
			</td>
		</tr>
	</jstl:forEach>
</table>

<h2>
	<acme:message code="patron.dashboard.form.title.general-indicators-deviation-budgets"/>
</h2>

<table class="table table-sm" aria-describedby="Deviation budget of patronages by status and currency">
	<jstl:forEach var="map" items="${deviationBudgetOfPatronagesByStatusAndCurrency}" >	
		<tr>
			<th scope="row">
				<jstl:out  value="${map.key}"/>
			</th>
			
			<td>
				<jstl:out value="${map.value}"/>
			</td>
		</tr>
	</jstl:forEach>
</table>

<h2>
	<acme:message code="patron.dashboard.form.title.general-indicators-minimum-budgets"/>
</h2>
<table class="table table-sm" aria-describedby="Mninimum budget of patronages by status and currency">
	<jstl:forEach var="map" items="${minimumBudgetOfPatronagesByStatusAndCurrency}" >	
		<tr>
			<th scope="row">
				<jstl:out  value="${map.key}"/>
			</th>
			
			<td>
				<jstl:out value="${map.value}"/>
			</td>
		</tr>
	</jstl:forEach>
</table>

<h2>
	<acme:message code="patron.dashboard.form.title.general-indicators-maximum-budgets"/>
</h2>
<table class="table table-sm" aria-describedby="Maximum budget of patronages by status and currency">
	<jstl:forEach var="map" items="${maximumBudgetOfPatronagesByStatusAndCurrency}" >	
		<tr>
			<th scope="row">
				<jstl:out  value="${map.key}"/>
			</th>
			
			<td>
				<jstl:out value="${map.value}"/>
			</td>
		</tr>
	</jstl:forEach>
</table>



