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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<acme:form readonly="${readonly}">
	<acme:input-textbox code="patron.patronage.list.label.code" path="code"/>
	
	<acme:input-textbox code="patron.patronage.list.label.status" path="status" readonly="true"/>
	
	<acme:input-moment code="patron.patronage.list.label.creationDate" path="creationDate" readonly="true"/>
	
	<jstl:if test="${create || update}">
        <acme:input-select code="patron.patronage.list.label.inventor" path="inventorId">
        	<c:forEach var="inventorOption" items="${inventors}">
            	<acme:input-option selected="${inventorOption.id==inventor.id}" code="${inventorOption.userAccount.identity.name} ${inventorOption.userAccount.identity.surname}" value="${inventorOption.id}"/>
        	</c:forEach>
    	</acme:input-select>
    </jstl:if>
	
	<acme:input-moment code="patron.patronage.list.label.startDate" path="startDate"/>
	<acme:input-moment code="patron.patronage.list.label.endDate" path="endDate"/>
	
	<acme:input-money code="patron.patronage.list.label.budget" path="budget"/>
	<acme:input-textarea code="patron.patronage.list.label.legalStuff" path="legalStuff"/>
	<acme:input-url code="patron.patronage.list.label.link" path="link"/>

	<jstl:if test="${!create && !update}">
		<acme:input-textbox code="patron.patronage.list.label.inventorFullName" path="inventorFullName"  readonly="true"/>
		<acme:input-textbox code="patron.patronage.list.label.inventorCompany" path="inventorCompany"  readonly="true"/>
	</jstl:if>
	<jstl:if test="${update}">
		<acme:submit code="patron.patronage.form.button.update.save" action="/patron/patronage/update"/>
	</jstl:if>
	
	<jstl:if test="${create}">
		<acme:submit code="patron.patronage.form.button.create" action="/patron/patronage/create"/>
	</jstl:if>
	
	<jstl:if test="${draftMode}">
		<acme:button code="patron.patronage.form.button.update" action="/patron/patronage/update?id=${id}"/>
		<acme:submit code="patron.patronage.form.button.delete" action="/patron/patronage/delete"/>
		<acme:submit code="patron.patronage.form.button.publish" action="/patron/patronage/publish"/>
	</jstl:if>
</acme:form>

