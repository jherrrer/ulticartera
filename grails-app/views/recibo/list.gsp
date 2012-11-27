
<%@ page import="prafin.Recibo" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'recibo.label', default: 'Recibo')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-recibo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-recibo" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="CC" title="${message(code: 'recibo.CC.label', default: 'CC')}" />
					
						<g:sortableColumn property="comentarios" title="${message(code: 'recibo.comentarios.label', default: 'Comentarios')}" />
					
						<g:sortableColumn property="entra" title="${message(code: 'recibo.entra.label', default: 'Entra')}" />
					
						<g:sortableColumn property="sale" title="${message(code: 'recibo.sale.label', default: 'Sale')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${reciboInstanceList}" status="i" var="reciboInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${reciboInstance.id}">${fieldValue(bean: reciboInstance, field: "CC")}</g:link></td>
					
						<td>${fieldValue(bean: reciboInstance, field: "comentarios")}</td>
					
						<td>${fieldValue(bean: reciboInstance, field: "entra")}</td>
					
						<td>${fieldValue(bean: reciboInstance, field: "sale")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${reciboInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
