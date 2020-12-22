<#include "../import.ftl" />
<html lang="IT">
<#include "../layout/header.ftl" />
<body>
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
							Link partita:
						</h5>
						<p class="card-testo">
                            <#if data.stanza.idStanza??>
								<a href="<@spring.url '/cartella/new/${data.stanza.idStanza}' />">http://tombola-semplice.herokuapp.com<@spring.url '/cartella/new/${data.stanza.idStanza}' /></a>
                            </#if>
						</p>
					</div>
				</div>
			</div>
		</div>
    </#if>

<#--    <#if data?? && data.numeroEstratto??>
		<div class="card numero-estratto">
			<div class="card-body">
				<div class="row">
					<div class="col-sm">
						<h5 class="card-titolo">
							Numero estratto:
						</h5>
						<p class="card-testo">
                            ${data.numeroEstratto}
						</p>
					</div>
				</div>
			</div>
		</div>
    </#if>-->

    <#if data?? && data.numeroUscitoList?? && data.numeroUscitoList?has_content>
		<div class="card">
			<div class="card-body">
				<h5 class="card-title">
					Numero usciti:
				</h5>
				<p class="card-text lista-numeri-usciti">
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
				</p>
			</div>
		</div>
    </#if>

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
	                                    <#if numeroRiga.uscito>
		                                    <span class="numero-uscito-tabella">
                                                ${numeroRiga.numero}
		                                    </span>
	                                    <#else>
                                            ${numeroRiga.numero}
	                                    </#if>
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

	<a href="<@spring.url '/tabellone/stanza/${data.stanza.idStanza}/numero' />" class="btn btn-danger estrai-numero">Estrai numero</a>
</div>
</body>
</html>
