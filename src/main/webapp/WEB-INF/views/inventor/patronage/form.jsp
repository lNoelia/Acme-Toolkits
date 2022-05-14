<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox readonly="true" code="inventor.patronage.form.label.code" path="code"/>
	<jstl:if test="${readonly}">
		<acme:input-textbox code="inventor.patronage.form.label.status" path="status"/>
	</jstl:if>
	<jstl:if test="${!readonly}">
		<acme:input-select code="patron.patronage.list.label.status" path="status">
			<acme:input-option code="PROPOSED" value="PROPOSED" selected="${status == 'PROPOSED'}"/>
			<acme:input-option code="ACCEPTED" value="ACCEPTED" selected="${status == 'ACCEPTED'}"/>
			<acme:input-option code="DENIED" value="DENIED" selected="${status == 'DENIED'}"/>
		</acme:input-select>
	</jstl:if>
	
	<acme:input-money  readonly="true" code="inventor.patronage.form.label.budget" path="budget"/>
	<acme:input-money code="inventor.patronage.form.label.convertedPrice" path="convertedPrice" readonly="true"/>
	<acme:input-textbox readonly="true" code="inventor.patronage.form.label.patron" path="patron"/>
	<acme:input-textbox readonly="true" code="inventor.patronage.form.label.legalStuff" path="legalStuff"/>
	<acme:input-url readonly="true" code="inventor.patronage.form.label.link" path="link"/>
	<acme:input-textbox readonly="true" code="inventor.patronage.form.label.creationDate" path="creationDate"/>
	<acme:input-textbox readonly="true" code="inventor.patronage.form.label.startDate" path="startDate"/>
	<acme:input-textbox readonly="true" code="inventor.patronage.form.label.endDate" path="endDate"/>
	
	<jstl:if test="${canBeUpdated}">
		<acme:button code="inventor.patronage.form.button.update" action="/inventor/patronage/update?id=${id}"/>
	</jstl:if>
	<jstl:if test="${!readonly}">
		<acme:submit code="inventor.patronage.form.button.update.save" action="/inventor/patronage/update"/>
	</jstl:if>
</acme:form>
	
	<acme:button code="inventor.patronage.form.button.patronage-reports" action="/inventor/patronage-report/create?masterId=${id}"/>




