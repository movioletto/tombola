<#include "../import.ftl" />
<html lang="IT">
<#include "../layout/header.ftl" />
<script type="text/javascript"
        src="<@spring.url '/resources/static/js/cartella/script.js' />"></script>
<body>

<audio controls id="numeroUscitoAudio" style="display: none;">
  <source src="<@spring.url '/resources/static/audio/numeroUscito.wav' />" type="audio/wav">
  Il tuo browser non supporta l'elemento audio.
</audio>

<input type="hidden" id="separatore-premio-vinto"
       value="<@spring.message "separatore.premio-vinto" />">
<input type="hidden" id="bottone-cartella-premio"
       value="<@spring.message "bottone.cartella.premio" />">

<#if data?? && data.stanza?? && data.stanza.codice??>
  <input type="hidden" id="url-premio-corrente"
         value="<@spring.url '/tabellone/stanza/${data.stanza.codice}/premioCorrente/' />">

  <input type="hidden" id="id-stanza" value="${data.stanza.codice}">
</#if>
<#if data?? && data.tabella?? && data.tabella.idTabella??>
  <input type="hidden" id="id-tabella" value="${data.tabella.idTabella}">
</#if>
<#if data?? && data.tabella?? && data.tabella.nome??>
  <input type="hidden" id="nome-tabella" value="${data.tabella.nome}">
</#if>
<#if data?? && data.tabella?? && data.tabella.aggettivo??>
  <input type="hidden" id="aggettivo-tabella" value="${data.tabella.aggettivo}">
</#if>
<#if data?? && data.tabella?? && data.tabella.icona??>
  <input type="hidden" id="icona-tabella" value="${data.tabella.icona}">
</#if>

<h1 class="titolo"><@spring.message "app.titolo" /></h1>
<div class="container-lg">
    <#if data?? && data.stanza??>
      <div class="card">
        <div class="card-body">
          <div class="row">
            <div class="col-lg-6 col-12">
              <h5 class="card-titolo col-lg-6 col-12">
                  <@spring.message "form.nome-partita" />
              </h5>
              <p class="card-testo col-lg-6 col-12">
                  <#if data.stanza.nome??>
                      ${data.stanza.nome}
                  </#if>
              </p>
            </div>
            <div class="col-lg-6 col-12">
              <h5 class="card-titolo col-lg-6 col-12">
                  <@spring.message "form.id-partita" />
              </h5>
              <p class="card-testo col-lg-6 col-12">
                  <#if data.stanza.codice??>
                      ${data.stanza.codice}
                  </#if>
              </p>
            </div>
          </div>
          <div class="row">
            <div class="col-lg-6 col-12">
              <h5 class="card-titolo col-lg-6 col-12">
                  <@spring.message "form.nome-cartella" />
              </h5>
              <p class="card-testo col-lg-6 col-12">
                  <#if data.tabella.nome??>
                      <#if data.tabella.icona??>
                        <img
                            src="<@spring.url '/resources/static/image/' + data.tabella.icona + '.svg' />"
                            alt="${data.tabella.icona}" width="50px">
                      </#if>
                      ${data.tabella.nome} <#if data.tabella.aggettivo??>${data.tabella.aggettivo}</#if>
                  </#if>
              </p>
            </div>
            <div class="col-lg-6 col-12">
              <h5 class="card-titolo col-lg-6 col-12">
                <label for="auto-selezione">
                    <@spring.message "form.selezione-automatica" />
                </label>
              </h5>
              <p class="card-testo col-lg-6 col-12">
                <input id="auto-selezione" type="checkbox" class="apple-switch">
              </p>
              <br>
              <h5 class="card-titolo col-lg-6 col-12">
                <label for="abilitaSuoni">
                    <@spring.message "form.abilita-suoni" />
                </label>
              </h5>
              <p class="card-testo col-lg-6 col-12">
                <input id="abilitaSuoni" type="checkbox" class="apple-switch">
              </p>
            </div>
          </div>
        </div>
      </div>
    </#if>

  <div id="premi-vinti" class="card">
      <@layout.premiVinti data.vincitaList/>
  </div>

  <div id="numeri-usciti" class="card">
      <@layout.numeriUsciti data.numeroUscitoList true />
  </div>

    <#if data?? && data.tabella?? && data.tabella.sequenza?? && data.tabella.sequenza?has_content>
      <button id="link-cartella-verticale"
              class="btn btn-primary cambio-layout-tabella col-lg-6 offset-lg-3 col-12">
        <i class="fas fa-grip-vertical"></i> Cartella verticale
      </button>
      <button id="link-cartella-orizzontale"
              class="btn btn-primary cambio-layout-tabella col-lg-6 offset-lg-3 col-12 d-none">
        <i class="fas fa-grip-horizontal"></i> Cartella orizzontale
      </button>

      <div id="cartella-orizzontale" class="row">
          <@layout.cartella data.tabella.sequenza 'style="width: 11.11%;"' />
      </div>

      <div id="cartella-verticale" class="row d-none">
          <@layout.cartellaVerticale data.tabella.sequenza 'style="width: 11.11%;"' />
      </div>
    </#if>

    <#if data?? && data.premioCorrente?? && data.premioCorrente.codice?? && data.premioCorrente.valore??>
      <button id="dichiarazione-premio"
              class="btn btn-danger dichiarazione-premio col-lg-6 offset-lg-3 col-12"
              data-id-premio="${data.premioCorrente.codice}"
              data-nome-premio="${data.premioCorrente.valore}">
          <@spring.message "bottone.cartella.premio" /> ${data.premioCorrente.valore}
      </button>
    </#if>

    <#include "../layout/footer.ftl" />
</div>
</body>
</html>


