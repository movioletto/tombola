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

<h1 class="titolo"><@spring.message "app.titolo" /></h1>
<div class="container">
    <#if data?? && data.stanza??>
		<div class="card">
			<div class="card-body">
				<div class="row">
					<div class="col-sm">
						<h5 class="card-titolo">
                            <@spring.message "form.nome-partita" />
						</h5>
						<p class="card-testo">
                            <#if data.stanza.nome??>
                                ${data.stanza.nome}
                            </#if>
						</p>
					</div>
					<div class="col-sm">
						<h5 class="card-titolo">
                            <@spring.message "form.id-partita" />
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
							<@spring.message "form.nome-cartella" />
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
								<@spring.message "form.selezione-automatica" />
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
		<@layout.premiVinti data.vincitaList />
	</div>

	<div id="numeri-usciti" class="card">
        <@layout.numeriUsciti data.numeroUscitoList true />
	</div>

    <#if data?? && data.tabella?? && data.tabella.sequenza?? && data.tabella.sequenza?has_content>
		<div class="row">
            <@layout.cartella data.tabella.sequenza 'style="width: 11.11%;"' />
		</div>
    </#if>

    <#if data?? && data.premioCorrente?? && data.premioCorrente.codice?? && data.premioCorrente.valore??>
		<button id="dichiarazione-premio" class="btn btn-danger dichiarazione-premio"
		        data-id-premio="${data.premioCorrente.codice}" data-nome-premio="${data.premioCorrente.valore}">
			<@spring.message "bottone.cartella.premio" /> ${data.premioCorrente.valore}
		</button>
    </#if>

    <#include "../layout/footer.ftl" />
</div>
</body>
</html>


