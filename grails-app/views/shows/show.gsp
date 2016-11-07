
<%@ page import="core.Shows" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'shows.label', default: 'Shows')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-shows" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-shows" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list shows">
			
				<g:if test="${showsInstance?.quantidadePessoas}">
				<li class="fieldcontain">
					<span id="quantidadePessoas-label" class="property-label"><g:message code="shows.quantidadePessoas.label" default="Quantidade Pessoas" /></span>
					
						<span class="property-value" aria-labelledby="quantidadePessoas-label"><g:fieldValue bean="${showsInstance}" field="quantidadePessoas"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${showsInstance?.data}">
				<li class="fieldcontain">
					<span id="data-label" class="property-label"><g:message code="shows.data.label" default="Data" /></span>
					
						<span class="property-value" aria-labelledby="data-label"><g:formatDate date="${showsInstance?.data}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${showsInstance?.humoristas}">
				<li class="fieldcontain">
					<span id="humoristas-label" class="property-label"><g:message code="shows.humoristas.label" default="Humoristas" /></span>
					
						<g:each in="${showsInstance.humoristas}" var="h">
						<span class="property-value" aria-labelledby="humoristas-label"><g:link controller="humorista" action="show" id="${h.id}">${h?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${showsInstance?.local}">
				<li class="fieldcontain">
					<span id="local-label" class="property-label"><g:message code="shows.local.label" default="Local" /></span>
					
						<span class="property-value" aria-labelledby="local-label"><g:fieldValue bean="${showsInstance}" field="local"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${showsInstance?.nome}">
				<li class="fieldcontain">
					<span id="nome-label" class="property-label"><g:message code="shows.nome.label" default="Nome" /></span>
					
						<span class="property-value" aria-labelledby="nome-label"><g:fieldValue bean="${showsInstance}" field="nome"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:showsInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${showsInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
