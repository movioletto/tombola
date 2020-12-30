<#include "../import.ftl" />
<html lang="IT">
<#include "../layout/header.ftl" />
<script type="text/javascript" src="<@spring.url '/resources/static/js/stats/script.js' />"></script>
<body>

<input type="hidden" id="url-giocatore-presente" value="<@spring.url '/tabellone/stanza/' />"/>

<h1 class="titolo"><@spring.message "app.titolo" /></h1>
<div class="container">

    <#if data?? && data.stanzaList?? && data.stanzaList?has_content>
		<div class="accordion" id="accordionStanze">
            <#list data.stanzaList as stanza>
				<div class="card">
					<div class="card-header">
						<h2 class="mb-0">
							<button class="btn btn-link d-block text-start" type="button"
							        data-bs-toggle="collapse"
							        data-bs-target="#corpo-<#if stanza.idStanza??>${stanza.idStanza}</#if>"
							        aria-expanded="false"
							        aria-controls="corpo-<#if stanza.idStanza??>${stanza.idStanza}</#if>"
							        style="width: 100%;">
                                <#if stanza.nome??>
                                    ${stanza.nome}
                                </#if>
							</button>
						</h2>
					</div>
					<div id="corpo-<#if stanza.idStanza??>${stanza.idStanza}</#if>" class="collapse multi-collapse">
						<div class="card-body d-none">
							<h5 class="card-title">
                                <@spring.message "form.id-partita" />
							</h5>
							<p class="card-text">
                                <#if stanza.idStanza??>
                                    ${stanza.idStanza}
                                <#else>
									<@spring.message "nessun-dato.id-stanza" />
                                </#if>
							</p>
						</div>
                        <@layout.premiVinti stanza.vincitaList />

                        <@layout.giocatoriPresenti stanza.giocatorePresenteList stanza.idStanza/>

                        <@layout.numeriUsciti stanza.numeroUscitoList false />
					</div>
				</div>
            </#list>
		</div>
    <#else>
		<div class="card">
			<div class="card-body">
				<h5 class="card-title">
					<@spring.message "nessun-dato.stanza" />
				</h5>
			</div>
		</div>
    </#if>

    <#include "../layout/footer.ftl" />
</div>
</body>
</html>


