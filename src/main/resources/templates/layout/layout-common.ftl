<#macro premiVinti vincitaList animaleList>
  <div class="card-body">
    <h5 class="card-title">
        <@spring.message "form.premi-vinti"/>
    </h5>
    <p class="card-text lista-premi-vinti">
        <#if vincitaList?? && vincitaList?has_content>
            <#list vincitaList as vincita>
                <#if vincita??>
                  <strong class="badge bg-success ms-2">
                      ${vincita.nomePremio}
                  </strong>
                    <@spring.message "separatore.premio-vinto" />
                    <#if utility.isNomeAnimale(vincita.idTabella, animaleList)>
                      <img src="<@spring.url '/resources/static/image/' + utility.nomeAnimaleDaNomeGiocatore(vincita.idTabella) + '.svg' />" alt="${utility.nomeAnimaleDaNomeGiocatore(vincita.idTabella)}" width="50px">
                    </#if>
                    ${utility.camelCaseInStringaNormale(vincita.idTabella)}
                </#if>
            </#list>
        <#else>
          <span id="nessun-premio-vinto">
					<@spring.message "nessun-dato.premio-vinto" />
				</span>
        </#if>
    </p>
  </div>
</#macro>

<#macro numeriUsciti numeroUscitoList separatore>
  <div class="card-body">
    <h5 class="card-title">
        <@spring.message "form.numeri-usciti"/>
    </h5>
    <p class="card-text lista-numeri-usciti">
        <#if numeroUscitoList?? && numeroUscitoList?has_content>
            <#list numeroUscitoList as numeroUscito>
                <#if numeroUscito.numero??>
                  <span class="numero-uscito">
								${numeroUscito.numero}
							</span>
                    <#if separatore && numeroUscito?is_first>
                      <span class="separatore"></span>
                    </#if>
                </#if>
            </#list>
        <#else>
          <span id="nessun-numero-uscito">
					<@spring.message "nessun-dato.numero-estratto" />
				</span>
        </#if>
    </p>
  </div>
</#macro>

<#macro cartella sequenza stile>
  <div class="col-sm">
    <table>
      <tbody>
      <#list sequenza as riga>
        <tr>
            <#list riga as numeroRiga>
              <td ${stile}>
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
</#macro>

<#macro giocatoriPresenti giocatorePresenteList idStanza animaleList>
  <div class="card-body">
    <h5 class="card-title">
        <@spring.message "form.giocatori-presenti" />
    </h5>
    <p class="card-text lista-giocatori-presenti">
        <#if giocatorePresenteList?? && giocatorePresenteList?has_content>
            <#list giocatorePresenteList as giocatorePresente>
                <#if giocatorePresente??>
                  <span id="giocatore-${giocatorePresente}"
                        class="giocatore-presente btn btn-primary"
                        data-giocatore="${giocatorePresente}" data-stanza="${idStanza}">
                    <#if utility.isNomeAnimale(giocatorePresente, animaleList)>
                      <img src="<@spring.url '/resources/static/image/' + utility.nomeAnimaleDaNomeGiocatore(giocatorePresente) + '.svg' />" alt="${utility.nomeAnimaleDaNomeGiocatore(giocatorePresente)}" width="50px">
                    </#if>
							      ${utility.camelCaseInStringaNormale(giocatorePresente)}
						      </span>
                </#if>
            </#list>
        <#else>
          <span id="nessun-giocatore-presente">
					<@spring.message "nessun-dato.giocatore-presente" />
				</span>
        </#if>
    </p>
  </div>
  <div id="cartella-giocatore-presente" class="card-body d-none">
    <h5 class="card-title">
      <div class="row">
        <div class="col-sm">
            <@spring.message "form.giocatore-presente.cartella" />
          <span class="id-cartella-giocatore-presente"></span>
        </div>
        <div class="col-sm text-right">
					<span id="chiudi-cartella-giocatore-presente" class="btn btn-danger"
                style="float: right;">
						<@spring.message "bottone.cartella-giocatore.chiudi"/>
		            </span>
          <span id="conferma-premio" class="btn btn-primary d-none"
                style="float: right; margin-right: 10px;">
						<@spring.message "bottone.tabellone.conferma-premio" />
						 <span class="nome-premio-giocatore-presente"></span>
					</span>
        </div>
      </div>
    </h5>
    <p class="card-text"></p>
  </div>
</#macro>