<#include "../import.ftl" />
<html lang="IT">
<#include "../layout/header.ftl" />
<script type="text/javascript" src="<@spring.url '/resources/static/js/stats/script.js' />"></script>
<body>

<input type="hidden" id="url-giocatore-presente" value="<@spring.url '/tabellone/stanza/' />"/>

<h1 class="titolo">Tombola</h1>
<div class="container">

    <#if data?? && data.stanzaList?? && data.stanzaList?has_content>
        <#list data.stanzaList as stanza>
			<div class="card">
				<div class="card-header">
                    <#if stanza.nome??>
                        ${stanza.nome}
                    </#if>
				</div>
				<div class="card-body">
					<h5 class="card-title">
						ID stanza:
					</h5>
					<p class="card-text">
                        <#if stanza.idStanza??>
                            ${stanza.idStanza}
                        <#else>
							Nessun ID stanza
                        </#if>
					</p>
				</div>
				<div class="card-body">
					<h5 class="card-title">
						Giocatori Presenti:
					</h5>
					<p class="card-text">
                        <#if stanza.giocatorePresenteList?? && stanza.giocatorePresenteList?has_content>
                            <#list stanza.giocatorePresenteList as giocatorePresente>
                                <#if giocatorePresente??>
									<span id="giocatore-${giocatorePresente}" class="giocatore-presente btn btn-primary"
									      data-giocatore="${giocatorePresente}" data-stanza="${stanza.idStanza}">
										${utility.camelCaseInStringaNormale(giocatorePresente)}
									</span>
                                </#if>
                            </#list>
                        <#else>
							Nessun giocatore presente
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
							</div>
						</div>
					</h5>
					<p class="card-text"></p>
				</div>
				<div class="card-body">
					<h5 class="card-title">
						Numeri usciti:
					</h5>
					<p class="card-text">
                        <#if stanza.numeroUscitoList?? && stanza.numeroUscitoList?has_content>
                            <#list stanza.numeroUscitoList as numeroUscito>
                                <#if numeroUscito.numero??>
									<span class="numero-uscito">
										${numeroUscito.numero}
									</span>
                                </#if>
                            </#list>
                        <#else>
							Nessun numero estratto
                        </#if>
					</p>
				</div>
			</div>
        </#list>
    <#else>
		<div class="card">
			<div class="card-body">
				<h5 class="card-title">
					Nessun stanza
				</h5>
			</div>
		</div>
    </#if>

    <#include "../layout/footer.ftl" />
</div>
</body>
</html>


