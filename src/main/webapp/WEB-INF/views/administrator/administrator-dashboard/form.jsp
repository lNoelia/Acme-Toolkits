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
		<acme:message code="administrator.dashboard.form.title.general-indicators"/>
	</h2>

	<table class="table table-sm">
		<tr>
			<th scope="row">
				<acme:message code="administrator.dashboard.form.label.total-number-of-components"/>
			</th>
			<td>
				<acme:print value="${totalNumberOfComponents}"/>
			</td>
		</tr>

		<tr>
			<th scope="row">
				<acme:message code="administrator.dashboard.form.label.total-number-of-tools"/>
			</th>
			<td>
				<acme:print value="${totalNumberOfTools}"/>
			</td>
		</tr>
	</table>
	
	<h2>
		<acme:message code="administrator.dashboard.form.title.average-retail-price-of-components-by-technology-and-currency"/>
	</h2>

	<table class="table table-sm">
		<jstl:forEach var="map" items="${averageRetailPriceOfComponentsByTechnologyAndCurrency}" >	
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
		<acme:message code="administrator.dashboard.form.title.deviation-retail-price-of-components-by-technology-and-currency"/>
	</h2>
	
	<table class="table table-sm">
		<jstl:forEach var="map" items="${deviationRetailPriceOfComponentsByTechnologyAndCurrency}" >	
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
		<acme:message code="administrator.dashboard.form.title.minimum-retail-price-of-components-by-technology-and-currency"/>
	</h2>
	
	<table class="table table-sm">
		<jstl:forEach var="map" items="${minimumRetailPriceOfComponentsByTechnologyAndCurrency}" >	
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
		<acme:message code="administrator.dashboard.form.title.maximum-retail-price-of-components-by-technology-and-currency"/>
	</h2>
	
	<table class="table table-sm">
		<jstl:forEach var="map" items="${maximumRetailPriceOfComponentsByTechnologyAndCurrency}" >	
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
		<acme:message code="administrator.dashboard.form.title.average-retail-price-of-tools-by-currency"/>
	</h2>
	
	<table class="table table-sm">
		<jstl:forEach var="map" items="${averageRetailPriceOfToolsByCurrency}" >	
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
		<acme:message code="administrator.dashboard.form.title.deviation-retail-price-of-tools-by-currency"/>
	</h2>
	
	<table class="table table-sm">
		<jstl:forEach var="map" items="${deviationRetailPriceOfToolsByCurrency}" >	
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
		<acme:message code="administrator.dashboard.form.title.minimum-retail-price-of-tools-by-currency"/>
	</h2>
	
	<table class="table table-sm">
		<jstl:forEach var="map" items="${minimumRetailPriceOfToolsByCurrency}" >	
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
		<acme:message code="administrator.dashboard.form.title.maximum-retail-price-of-tools-by-currency"/>
	</h2>
	
	<table class="table table-sm">
		<jstl:forEach var="map" items="${maximumRetailPriceOfToolsByCurrency}" >	
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
		<acme:message code="administrator.dashboard.form.title.total-number-of-patronages-by-status"/>
	</h2>
	
	<table class="table table-sm">
		<jstl:forEach var="map" items="${totalNumberOfPatronagesByStatus}" >	
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
		<acme:message code="administrator.dashboard.form.title.average-budget-of-patronages-by-status"/>
	</h2>

	<table class="table table-sm">
		<jstl:forEach var="map" items="${averageBudgetOfPatronagesByStatus}" >	
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
		<acme:message code="administrator.dashboard.form.title.deviation-budget-of-patronages-by-status"/>
	</h2>
	
	<table class="table table-sm">
		<jstl:forEach var="map" items="${deviationBudgetOfPatronagesByStatus}" >	
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
		<acme:message code="administrator.dashboard.form.title.minimum-budget-of-patronages-by-status"/>
	</h2>
	
	<table class="table table-sm">
		<jstl:forEach var="map" items="${minimumBudgetOfPatronagesByStatus}" >	
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
		<acme:message code="administrator.dashboard.form.title.maximum-budget-of-patronages-by-status"/>
	</h2>
	
	<table class="table table-sm">
		<jstl:forEach var="map" items="${maximumBudgetOfPatronagesByStatus}" >	
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


