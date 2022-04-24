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

<div class="container">
	<div class="row">
		<div class="col-sm">
			<table class="table table-sm" aria-describedby="Total number of components">
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
		</div>
	</div>
	<div class="row">
		<div class="col-sm">
			<h3>
				<acme:message code="administrator.dashboard.form.title.total-number-of-patronages-by-status"/>
			</h3>	
			<table class="table table-sm"  aria-describedby="Total number of patronages by status">
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
		</div>
	</div>
</div>
	
<hr>
	
<h2>
	<acme:message code="administrator.dashboard.form.title.retail-price-of-components-by-technology-and-currency"/>
</h2>

<div class="container">
	<div class="row">
		<div class="col-sm">
			<h2>
				<acme:message code="administrator.dashboard.form.title.average"/>
			</h2>
			<table class="table table-sm"  aria-describedby="Average retail price of components by technology and currency">
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
		</div>
	
		<div class="col-sm">
			<h2>
				<acme:message code="administrator.dashboard.form.title.deviation"/>
			</h2>
			<table class="table table-sm" aria-describedby="Deviation retail price of components by technology and currency">
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
		</div>
	
		<div class="col-sm">
			<h2>
				<acme:message code="administrator.dashboard.form.title.minimum"/>
			</h2>
			<table class="table table-sm" aria-describedby="Minimum retail price of components by technology and currency">
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
		</div>
		
		<div class="col-sm">
			<h2>
				<acme:message code="administrator.dashboard.form.title.maximum"/>
			</h2>
			<table class="table table-sm" aria-describedby="Maximum retail price of components by technology and currency">
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
		</div>
	</div>
</div>

<hr>

<h2>
	<acme:message code="administrator.dashboard.form.title.retail-price-of-tools-by-currency"/>
</h2>
<div class="container">
	<div class="row">
		<div class="col-sm">
			<h2>
				<acme:message code="administrator.dashboard.form.title.average"/>
			</h2>
			<table class="table table-sm" aria-describedby="Average retail price of tools by currency">
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
		</div>
		
		<div class="col-sm">
			<h2>
				<acme:message code="administrator.dashboard.form.title.deviation"/>
			</h2>
			<table class="table table-sm" aria-describedby="Deviation retail price of tools by currency">
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
		</div>
	
		<div class="col-sm">
			<h2>
				<acme:message code="administrator.dashboard.form.title.minimum"/>
			</h2>
			<table class="table table-sm" aria-describedby="Minimum retail price of tools by currency">
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
		</div>
	
		<div class="col-sm">
			<h2>
				<acme:message code="administrator.dashboard.form.title.maximum"/>
			</h2>
			<table class="table table-sm" aria-describedby="Maximum retail price of tools by currency">
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
		</div>
	</div>
</div>		

<hr>

<h2>
	<acme:message code="administrator.dashboard.form.title.budget-of-patronages-by-status-and-currency"/>
</h2>
<div class="container">
	<div class="row">
		<div class="col-sm">
			<h2>
				<acme:message code="administrator.dashboard.form.title.average"/>
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
		</div>
		
		<div class="col-sm">
			<h2>
				<acme:message code="administrator.dashboard.form.title.deviation"/>
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
		</div>
	

		<div class="col-sm">
			<h2>
				<acme:message code="administrator.dashboard.form.title.minimum"/>
			</h2>
			<table class="table table-sm" aria-describedby="Minimum budget of patronages by status and currency">
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
		</div>
		
		<div class="col-sm">
			<h2>
				<acme:message code="administrator.dashboard.form.title.maximum"/>
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
		</div>
	</div>
</div>