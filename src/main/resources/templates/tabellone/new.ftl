<#include "../import.ftl" />
<html lang="IT">
<#include "../layout/header.ftl" />
<body>
<script type="text/javascript"
        src="<@spring.url '/resources/static/js/tabellone/new.js' />"></script>
<h1 class="titolo"><@spring.message "app.titolo" /></h1>
<div class="container">
  <div class="card">
    <div class="card-body">
      <div class="row">
        <div class="col-sm">
          <form method="post" action="<@spring.url '/tabellone/newAct'/>">
            <div class="mb-3">
              <label for="nome" class="form-label"><@spring.message "form.nome-partita" /></label>
            </div>
            <div class="mb-3">
              <input id="nome" name="nome" type="text" class="form-control" maxlength="200"
                     required>
            </div>
            <div class="mb-3">
              <div class="accordion" id="optionalAccordion">
                <div class="accordion-item">
                  <h2 class="accordion-header" id="optionalHeading">
                    <button class="accordion-button" type="button" data-bs-toggle="collapse"
                            data-bs-target="#optionalCollapse" aria-expanded="true"
                            aria-controls="optionalCollapse">
                      Opzioni
                    </button>
                  </h2>
                  <div id="optionalCollapse" class="accordion-collapse collapse"
                       aria-labelledby="optionalHeading" data-bs-parent="#optionalAccordion">
                    <div class="accordion-body">
                      <div class="mb-3">
                        <label for="codiceStanzaCustom"
                               class="form-label"><@spring.message "form.opzioni.abilitazione-codice-stanza-custom" /></label>
                        <input id="codiceStanzaCustom" name="opzioniStanza.codiceStanzaCustom"
                               type="checkbox" class="form-control apple-switch">
                      </div>
                      <div class="mb-3 d-none" id="contenitore-codice-stanza-custom">
                        <label for="codice"
                               class="form-label"><@spring.message "form.opzioni.codice-stanza-custom" /></label>
                        <input id="codice" name="codice" type="text" class="form-control">
                      </div>
                      <div class="mb-3">
                        <label for="nomiTabellaCustom"
                               class="form-label"><@spring.message "form.opzioni.nomi-tabelle-custom" /></label>
                        <input id="nomiTabellaCustom" name="opzioniStanza.nomiTabellaCustom"
                               type="checkbox" class="form-control apple-switch">
                      </div>
                      <div class="mb-3 d-none" id="contenitore-icone-tabelle-custom">
                        <label for="iconeTabella"
                               class="form-label"><@spring.message "form.opzioni.icone-tabelle-custom" /></label>
                        <input id="iconeTabella" name="opzioniStanza.iconeTabella" type="checkbox"
                               class="form-control apple-switch">
                      </div>
                      <div class="mb-3">
                        <label for="tombolino"
                               class="form-label"><@spring.message "form.opzioni.premio-tombolino" /></label>
                        <input id="tombolino" name="opzioniStanza.tombolino" type="checkbox"
                               class="form-control apple-switch">
                      </div>
                      <div class="mb-3 d-none">
                        <label for="controlloRigaGiaVinta"
                               class="form-label"><@spring.message "form.opzioni.blocco-premi-consecutivi" /></label>
                        <input id="controlloRigaGiaVinta" name="opzioniStanza.controlloRigaGiaVinta"
                               type="checkbox" class="form-control apple-switch">
                      </div>

                      <div class="mb-3">
                        <label for="abilitaPremiCustom"
                               class="form-label"><@spring.message "form.opzioni.abilita-nomi-premi-custom" /></label>
                        <input id="abilitaPremiCustom" type="checkbox"
                               class="form-control apple-switch">
                      </div>
                      <div class="mb-3 d-none" id="contenitore-nomi-premi-custom">
                        <label
                            class="form-label"><@spring.message "form.opzioni.nomi-premi-custom" /></label>
                        <div class="accordion-body">
                          <div class="mb-3">
                            <label for="premiCustomAmbo"
                                   class="form-label"><@spring.message "form.opzioni.nomi-premi-ambo" /></label>
                            <input id="premiCustomAmbo" name="opzioniStanza.premiCustom[1]"
                                   type="text" class="form-control premi-personalizzati">
                          </div>
                          <div class="mb-3">
                            <label for="premiCustomTerzo"
                                   class="form-label"><@spring.message "form.opzioni.nomi-premi-terno" /></label>
                            <input id="premiCustomTerzo" name="opzioniStanza.premiCustom[2]"
                                   type="text" class="form-control premi-personalizzati">
                          </div>
                          <div class="mb-3">
                            <label for="premiCustomQuaterna"
                                   class="form-label"><@spring.message "form.opzioni.nomi-premi-quaterna" /></label>
                            <input id="premiCustomQuaterna" name="opzioniStanza.premiCustom[3]"
                                   type="text" class="form-control premi-personalizzati">
                          </div>
                          <div class="mb-3">
                            <label for="premiCustomCinquina"
                                   class="form-label"><@spring.message "form.opzioni.nomi-premi-cinquina" /></label>
                            <input id="premiCustomCinquina" name="opzioniStanza.premiCustom[4]"
                                   type="text" class="form-control premi-personalizzati">
                          </div>
                          <div class="mb-3">
                            <label for="premiCustomTombola"
                                   class="form-label"><@spring.message "form.opzioni.nomi-premi-tombola" /></label>
                            <input id="premiCustomTombola" name="opzioniStanza.premiCustom[5]"
                                   type="text" class="form-control premi-personalizzati">
                          </div>
                          <div class="mb-3 d-none" id="contenitore-premi-custom-tombolino">
                            <label for="premiCustomTombolino"
                                   class="form-label"><@spring.message "form.opzioni.nomi-premi-tombolino" /></label>
                            <input id="premiCustomTombolino" name="opzioniStanza.premiCustom[6]"
                                   type="text" class="form-control premi-personalizzati">
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="mb-3">
              <button type="submit"
                      class="btn btn-primary"><@spring.message "bottone.tabellone.crea" /></button>
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