<#include "../import.ftl" />
<html lang="IT">
<#include "../layout/header.ftl" />
<script type="text/javascript"
        src="<@spring.url '/resources/static/js/cartella/new.js' />"></script>
<body>

<input type="hidden" id="url-stanza" value="<@spring.url '/tabellone/opzioni/stanza/' />">
<input type="hidden" id="testo-form-id-cartella" value="<@spring.message "form.id-cartella" />">
<input type="hidden" id="controllo-id-stanza"
       value="<#if data?? && data.tabella??>false<#else>true</#if>">

<h1 class="titolo"><@spring.message "app.titolo" /></h1>
<div class="container">
  <div class="card">
    <div class="card-body">
      <div class="row">
        <div class="col-sm">
          <form id="form" method="post" action="<@spring.url '/cartella/newAct'/>">
            <div class="mb-3">
              <label for="codiceStanza"
                     class="form-label"><@spring.message "form.id-partita" /></label>
            </div>

            <div class="mb-3">
                <#if data?? && data.tabella?? && data.tabella.codiceStanza??>
                  <input id="codiceStanza" name="codiceStanza" type="text" class="form-control"
                         maxlength="10" value="${data.tabella.codiceStanza}" readonly required>
                <#else>
                  <input id="codiceStanza" name="codiceStanza" type="text" class="form-control"
                         maxlength="10" required>
                </#if>
            </div>

            <div id="cartella-opzioni">
                <#if data?? && data.opzioniStanza?? && data.opzioniStanza.nomiTabellaCustom?? && data.opzioniStanza.nomiTabellaCustom>
                  <div class="mb-3">
                    <label for="nome"
                           class="form-label"><@spring.message "form.id-cartella" /></label>
                  </div>
                  <div class="mb-3">
                    <input id="nome" name="nome" type="text" class="form-control" maxlength="50"
                           required>
                  </div>
                </#if>

                <#if data?? && data.opzioniStanza?? && data.opzioniStanza.iconeTabella?? && data.opzioniStanza.iconeTabella>
                  <div class="mb-3">
                    <label class="form-label"><@spring.message "form.icona" /></label>
                  </div>
                  <div class="d-flex justify-content-between mt-3 mb-3">
                      <#if data?? && data.animaleList?? && data.animaleList?has_content>
                          <#list data.animaleList as animale>
                            <button type="button"
                                    class="btn btn-outline-primary icon-animale<#if animale?index == 0> active</#if>" data-animale="${animale.nome}">
                              <img
                                  src="<@spring.url '/resources/static/image/' + animale.nome + '.svg' />"
                                  alt="${animale}" width="50px">
                            </button>
                          </#list>
                      </#if>
                    <input type="hidden" id="icona" name="icona" value="${data.animaleList[0].nome}">
                  </div>
                </#if>
            </div>

            <div class="mb-3">
              <button id="bottone-cartella-entra" type="button"
                      class="btn btn-primary"
                      onclick="controlloForm()"><@spring.message "bottone.cartella.entra" /></button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
    <#include "../layout/footer.ftl" />
</div>
</body>
</html>