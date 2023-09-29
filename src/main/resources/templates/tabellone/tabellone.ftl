<#include "../import.ftl" />
<html lang="IT">
<#include "../layout/header.ftl" />
<script type="text/javascript"
        src="<@spring.url '/resources/static/js/tabellone/script.js' />"></script>
<body>

<input type="hidden" id="separatore-premio-vinto"
       value="<@spring.message "separatore.premio-vinto" />">
<input type="hidden" id="notifica-premio" value="<@spring.message "notifica-premio" />">

<#if data?? && data.stanza?? && data.stanza.codice??>
  <input type="hidden" id="url-numero"
         value="<@spring.url '/tabellone/stanza/${data.stanza.codice}/numero' />">
  <input type="hidden" id="url-giocatore" value="<@spring.url '/tabellone/stanza/' />">
  <input type="hidden" id="url-premio"
         value="<@spring.url '/tabellone/stanza/${data.stanza.codice}/premio/' />">

  <input type="hidden" id="id-stanza" value="${data.stanza.codice}">
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
                  <#else>
                      <@spring.message "nessun-dato.string" />
                  </#if>
              </p>
            </div>
            <div class="col-sm">
              <h5 class="card-titolo">
                  <@spring.message "form.numero-giocatori" />
              </h5>
              <p id="numero-giocatori" class="card-testo">
                  <#if data.stanza.giocatorePresenteList??>
                      ${data.stanza.giocatorePresenteList?size}
                  <#else>
                      <@spring.message "nessun-dato.integer" />
                  </#if>
              </p>
            </div>
            <div class="col-sm">
              <h5 class="card-titolo">
                  <@spring.message "form.id-partita" />
              </h5>
              <p class="card-testo">
                  <#if data.stanza.codice??>
                      ${data.stanza.codice}
                  <#else>
                      <@spring.message "nessun-dato.string" />
                  </#if>
              </p>
            </div>
          </div>
          <div class="row">
            <div class="col-sm">
              <h5 class="card-titolo">
                  <@spring.message "form.link-partita" />
              </h5>
              <p class="card-testo">
                  <span id="container-url">
                  <#if data.stanza.codice??>
                    <a href="<@spring.url '/cartella/new/${data.stanza.codice}' />" id="link-stanza"></a>
                  <#else>
                      <@spring.message "nessun-dato.string" />
                  </#if>
                  </span>
                <a href="javascript:void(0)" id="modifica-url"><i class="far fa-edit"></i></a>
                <a href="javascript:void(0)" id="salva-url" class="d-none"><i
                      class="far fa-save"></i></a>
              </p>
            </div>
            <div class="col-sm">
              <h5 class="card-titolo">
                <label for="visualizza-qrcode">
                    <@spring.message "form.qrcode-partita" />
                </label>
              </h5>
              <p class="card-testo">
                <input id="visualizza-qrcode" type="checkbox" class="apple-switch">
              </p>
            </div>
          </div>
          <div class="row d-none">
            <div class="col-sm" id="qrcode-partita">
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

    <#if data?? && data.tabellaList?? && data.tabellaList?has_content>
        <#list data.tabellaList as tabella>
            <#if tabella?is_odd_item>
              <div class="row">
            </#if>
            <@layout.cartella tabella.sequenza '' />
            <#if !tabella?is_odd_item>
              </div>
            </#if>
        </#list>
    </#if>

  <button id="estrai-numero" class="btn btn-danger estrai-numero">
      <@spring.message "bottone.tabellone.estrai-numero" />
  </button>

  <div id="giocatori-presenti" class="card">
      <@layout.giocatoriPresenti data.stanza.giocatorePresenteList data.stanza.codice/>
  </div>

    <#include "../layout/footer.ftl" />
</div>
</body>
</html>
