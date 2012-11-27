
<%@ page import="prafin.Recibo" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'recibo.label', default: 'Recibo')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-recibo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-recibo" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list recibo">
			
				<g:if test="${reciboInstance?.CC}">
				<li class="fieldcontain">
					<span id="CC-label" class="property-label"><g:message code="recibo.CC.label" default="CC" /></span>
					
						<span class="property-value" aria-labelledby="CC-label"><g:fieldValue bean="${reciboInstance}" field="CC"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${reciboInstance?.comentarios}">
				<li class="fieldcontain">
					<span id="comentarios-label" class="property-label"><g:message code="recibo.comentarios.label" default="Comentarios" /></span>
					
						<span class="property-value" aria-labelledby="comentarios-label"><g:fieldValue bean="${reciboInstance}" field="comentarios"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${reciboInstance?.entra}">
				<li class="fieldcontain">
					<span id="entra-label" class="property-label"><g:message code="recibo.entra.label" default="Entra" /></span>
					
						<span class="property-value" aria-labelledby="entra-label"><g:fieldValue bean="${reciboInstance}" field="entra"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${reciboInstance?.sale}">
				<li class="fieldcontain">
					<span id="sale-label" class="property-label"><g:message code="recibo.sale.label" default="Sale" /></span>
					
						<span class="property-value" aria-labelledby="sale-label"><g:fieldValue bean="${reciboInstance}" field="sale"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${reciboInstance?.id}" />
					<g:link class="edit" action="edit" id="${reciboInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
