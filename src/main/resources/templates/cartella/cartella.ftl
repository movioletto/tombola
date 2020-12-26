<#include "../import.ftl" />
<html lang="IT">
<#include "../layout/header.ftl" />
<script type="text/javascript" src="<@spring.url '/resources/static/js/cartella/script.js' />"></script>
<body>

<#if data?? && data.stanza?? && data.stanza.idStanza??>
	<input type="hidden" id="url-premio-corrente"
	       value="<@spring.url '/tabellone/stanza/${data.stanza.idStanza}/premioCorrente/' />">

	<input type="hidden" id="id-stanza" value="${data.stanza.idStanza}">
</#if>
<#if data?? && data.tabella?? && data.tabella.idTabella??>
	<input type="hidden" id="id-tabella" value="${data.tabella.idTabella}">
</#if>

<h1 class="titolo">Tombola</h1>
<div class="container">
    <#if data?? && data.stanza??>
		<div class="card">
			<div class="card-body">
				<div class="row">
					<div class="col-sm">
						<h5 class="card-titolo">
							Nome partita:
						</h5>
						<p class="card-testo">
                            <#if data.stanza.nome??>
                                ${data.stanza.nome}
                            </#if>
						</p>
					</div>
					<div class="col-sm">
						<h5 class="card-titolo">
							ID partita:
						</h5>
						<p class="card-testo">
                            <#if data.stanza.idStanza??>
                                ${data.stanza.idStanza}
                            </#if>
						</p>
					</div>
				</div>
				<div class="row">
					<div class="col-sm">
						<h5 class="card-titolo">
							Nome cartella:
						</h5>
						<p class="card-testo">
                            <#if data.tabella.idTabella??>
                                ${utility.camelCaseInStringaNormale(data.tabella.idTabella)}
                            </#if>
						</p>
					</div>
					<div class="col-sm">
						<h5 class="card-titolo">
							<label for="auto-selezione">
								Selezione automatica:
							</label>
						</h5>
						<p class="card-testo">
							<input id="auto-selezione" type="checkbox" class="apple-switch">
						</p>
					</div>
				</div>
			</div>
		</div>
    </#if>

	<div id="premi-vinti" class="card">
		<div class="card-body">
			<h5 class="card-title">
				Premi vinti:
			</h5>
			<p class="card-text lista-premi-vinti">
                <#if data?? && data.vincitaList?? && data.vincitaList?has_content>
                    <#list data.vincitaList as vincita>
                        <#if vincita??>
							<strong class="badge bg-success ms-2">
                                ${vincita.nomePremio}
							</strong>
							- ${utility.camelCaseInStringaNormale(vincita.idTabella)}
                        </#if>
                    </#list>
                <#else>
					<span id="nessun-premio-vinto">Nessun premio vinto</span>
                </#if>
			</p>
		</div>
	</div>

	<div id="numeri-usciti" class="card">
		<div class="card-body">
			<h5 class="card-title">
				Numero usciti:
			</h5>
			<p class="card-text lista-numeri-usciti">
                <#if data?? && data.numeroUscitoList?? && data.numeroUscitoList?has_content>
                    <#list data.numeroUscitoList as numeroUscito>
                        <#if numeroUscito.numero??>
							<span class="numero-uscito">
								${numeroUscito.numero}
							</span>
                            <#if numeroUscito?is_first>
								<span class="separatore"></span>
                            </#if>
                        </#if>
                    </#list>
                <#else>
					<span id="nessun-numero-uscito">Nessun numero estratto</span>
                </#if>
			</p>
		</div>
	</div>

    <#if data?? && data.tabella?? && data.tabella.sequenza?? && data.tabella.sequenza?has_content>
		<div class="row">
			<div class="col-sm">
				<table>
					<tbody>
                    <#list data.tabella.sequenza as riga>
						<tr>
                            <#list riga as numeroRiga>
								<td style="width: 11.11%;">
                                    <#if numeroRiga.numero != 0>
										<span id="numero-${numeroRiga.numero}"
										      class="numero-tabella ${numeroRiga.uscito?string('numero-uscito-tabella', '')}">
                                            ${numeroRiga.numero}
	                                    </span>
                                    </#if>
								</td>
                            </#list>
						</tr>
                    </#list>
					</tbody>
				</table>
			</div>
		</div>
    </#if>

    <#if data?? && data.premioCorrente?? && data.premioCorrente.codice?? && data.premioCorrente.valore??>
		<button id="dichiarazione-premio" class="btn btn-danger dichiarazione-premio"
		        data-id-premio="${data.premioCorrente.codice}" data-nome-premio="${data.premioCorrente.valore}">
			Ho fatto ${data.premioCorrente.valore}!
		</button>
    </#if>

    <#include "../layout/footer.ftl" />
</div>
</body>
</html>


