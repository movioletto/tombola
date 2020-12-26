<#include "../import.ftl" />
<html lang="IT">
<#include "../layout/header.ftl" />
<script type="text/javascript" src="<@spring.url '/resources/static/js/tabellone/script.js' />"></script>
<body>

<#if data?? && data.stanza?? && data.stanza.idStanza??>
	<input type="hidden" id="url-numero" value="<@spring.url '/tabellone/stanza/${data.stanza.idStanza}/numero' />">
	<input type="hidden" id="url-giocatore" value="<@spring.url '/tabellone/stanza/' />">
	<input type="hidden" id="url-premio" value="<@spring.url '/tabellone/stanza/${data.stanza.idStanza}/premio/' />">

	<input type="hidden" id="id-stanza" value="${data.stanza.idStanza}">
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
                            <#else>
								-
                            </#if>
						</p>
					</div>
					<div class="col-sm">
						<h5 class="card-titolo">
							Numero giocatori:
						</h5>
						<p id="numero-giocatori" class="card-testo">
                            <#if data.stanza.giocatorePresenteList??>
                                ${data.stanza.giocatorePresenteList?size}
                            <#else>
								0
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
                            <#else>
								-
                            </#if>
						</p>
					</div>
				</div>
				<div class="row">
					<div class="col-sm">
						<h5 class="card-titolo">
							Link partita:
						</h5>
						<p class="card-testo">
                            <#if data.stanza.idStanza??>
								<a href="<@spring.url '/cartella/new/${data.stanza.idStanza}' />" id="link-stanza"></a>
                            <#else>
								-
                            </#if>
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

    <#if data?? && data.tabellaList?? && data.tabellaList?has_content>
        <#list data.tabellaList as tabella>
            <#if tabella?is_odd_item>
				<div class="row">
            </#if>
			<div class="col-sm">
				<table>
					<tbody>
                    <#list tabella.sequenza as riga>
						<tr>
                            <#list riga as numeroRiga>
								<td>
                                    <#if numeroRiga.numero != 0>
										<span id="numero-${numeroRiga.numero}"
										      class="${numeroRiga.uscito?string('numero-uscito-tabella', '')}">
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

            <#if !tabella?is_odd_item>
				</div>
            </#if>
        </#list>
    </#if>

	<button id="estrai-numero" class="btn btn-danger estrai-numero">Estrai numero</button>

	<div id="giocatori-presenti" class="card">
		<div class="card-body">
			<h5 class="card-title">
				Giocatori presenti:
			</h5>
			<p class="card-text lista-giocatori-presenti">
                <#if data?? && data.stanza?? && data.stanza.giocatorePresenteList?? && data.stanza.giocatorePresenteList?has_content>
                    <#list data.stanza.giocatorePresenteList as giocatorePresente>
                        <#if giocatorePresente??>
							<span id="giocatore-${giocatorePresente}" class="giocatore-presente btn btn-primary"
							      data-giocatore="${giocatorePresente}" data-stanza="${data.stanza.idStanza}">
								${utility.camelCaseInStringaNormale(giocatorePresente)}
							</span>
                        </#if>
                    </#list>
                <#else>
					<span id="nessun-giocatore-presente">Nessun giocatore presente</span>
                </#if>
			</p>
		</div>
		<div id="cartella-giocatore-presente" class="card-body d-none">
			<h5 class="card-title">
				<div class="row">
					<div class="col-sm">
						Cartella giocatore presente: <span class="id-cartella-giocatore-presente"></span>
					</div>
					<div class="col-sm text-right">
						<span id="chiudi-cartella-giocatore-presente" class="btn btn-danger"
						      style="float: right;">
							Chiudi cartella giocatore
			            </span>
						<span id="conferma-premio" class="btn btn-primary d-none"
						      style="float: right; margin-right: 10px;">
							Conferma <span class="nome-premio-giocatore-presente"></span>!
			            </span>
					</div>
				</div>
			</h5>
			<p class="card-text"></p>
		</div>
	</div>

    <#include "../layout/footer.ftl" />
</div>
</body>
</html>
