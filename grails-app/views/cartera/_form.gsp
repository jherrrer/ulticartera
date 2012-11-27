<%@ page import="prafin.Cartera" %>



<div class="fieldcontain ${hasErrors(bean: carteraInstance, field: 'CC', 'error')} required">
	<label for="CC">
		<g:message code="cartera.CC.label" default="CC" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="CC" type="number" value="${carteraInstance.CC}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: carteraInstance, field: 'entra', 'error')} required">
	<label for="entra">
		<g:message code="cartera.entra.label" default="Entra" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="entra" type="number" value="${carteraInstance.entra}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: carteraInstance, field: 'sale', 'error')} required">
	<label for="sale">
		<g:message code="cartera.sale.label" default="Sale" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="sale" type="number" value="${carteraInstance.sale}" required=""/>
</div>

