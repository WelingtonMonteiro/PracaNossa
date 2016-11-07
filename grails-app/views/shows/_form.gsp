<%@ page import="core.Shows" %>



<div class="fieldcontain ${hasErrors(bean: showsInstance, field: 'quantidadePessoas', 'error')} required">
	<label for="quantidadePessoas">
		<g:message code="shows.quantidadePessoas.label" default="Quantidade Pessoas" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="quantidadePessoas" type="number" min="1" value="${showsInstance.quantidadePessoas}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: showsInstance, field: 'data', 'error')} required">
	<label for="data">
		<g:message code="shows.data.label" default="Data" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="data" precision="day"  value="${showsInstance?.data}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: showsInstance, field: 'humoristas', 'error')} ">
	<label for="humoristas">
		<g:message code="shows.humoristas.label" default="Humoristas" />
		
	</label>
	

</div>

<div class="fieldcontain ${hasErrors(bean: showsInstance, field: 'local', 'error')} required">
	<label for="local">
		<g:message code="shows.local.label" default="Local" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="local" required="" value="${showsInstance?.local}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: showsInstance, field: 'nome', 'error')} required">
	<label for="nome">
		<g:message code="shows.nome.label" default="Nome" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nome" required="" value="${showsInstance?.nome}"/>

</div>

