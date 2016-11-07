
<%@ page import="core.Shows" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'shows.label', default: 'Shows')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-shows" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-shows" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="quantidadePessoas" title="${message(code: 'shows.quantidadePessoas.label', default: 'Quantidade Pessoas')}" />
					
						<g:sortableColumn property="data" title="${message(code: 'shows.data.label', default: 'Data')}" />
					
						<g:sortableColumn property="local" title="${message(code: 'shows.local.label', default: 'Local')}" />
					
						<g:sortableColumn property="nome" title="${message(code: 'shows.nome.label', default: 'Nome')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${showsInstanceList}" status="i" var="showsInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${showsInstance.id}">${fieldValue(bean: showsInstance, field: "quantidadePessoas")}</g:link></td>
					
						<td><g:formatDate date="${showsInstance.data}" /></td>
					
						<td>${fieldValue(bean: showsInstance, field: "local")}</td>
					
						<td>${fieldValue(bean: showsInstance, field: "nome")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${showsInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
