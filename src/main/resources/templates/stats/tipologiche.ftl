<#include "../import.ftl" />
<html lang="IT">
<#include "../layout/header.ftl" />
<body>

<h1 class="titolo"><@spring.message "app.titolo" /></h1>
<div class="container-lg">

    <#if data?? && ((data.animaleList?? && data.animaleList?has_content) || (data.aggettivoList?? && data.aggettivoList?has_content))>
        <#if data?? && data.animaleList?? && data.animaleList?has_content>
          <div class="accordion" id="accordionAnimali">
            <div class="card">
              <div class="card-header">
                <h2 class="mb-0">
                  <button class="btn btn-link d-block text-start" type="button"
                          data-bs-toggle="collapse"
                          data-bs-target="#corpo-animali"
                          aria-expanded="false"
                          aria-controls="corpo-animali"
                          style="width: 100%;">
                      <@spring.message "stats-tipologiche-animali" />
                  </button>
                </h2>
              </div>

              <div id="corpo-animali" class="collapse multi-collapse">
                <div class="d-flex justify-content-between flex-wrap">
                    <#if data?? && data.animaleList?? && data.animaleList?has_content>
                        <#list data.animaleList as animale>
                          <div class="col m-2 p-2 rounded-10">
                            <div class="text-center">
                              <img
                                  src="<@spring.url '/resources/static/image/' + animale.nome + '.svg' />"
                                  alt="${animale}" width="50px">
                              <p class="mt-2">${animale.nome}</p>
                            </div>
                          </div>
                        </#list>
                    </#if>
                </div>
              </div>
            </div>
          </div>
        </#if>
        <#if data?? && data.aggettivoList?? && data.aggettivoList?has_content>
          <div class="accordion" id="accordionAggettivi">
            <div class="card">
              <div class="card-header">
                <h2 class="mb-0">
                  <button class="btn btn-link d-block text-start" type="button"
                          data-bs-toggle="collapse"
                          data-bs-target="#corpo-aggettivi"
                          aria-expanded="false"
                          aria-controls="corpo-aggettivi"
                          style="width: 100%;">
                      <@spring.message "stats-tipologiche-aggettivi" />
                  </button>
                </h2>
              </div>

              <div id="corpo-aggettivi" class="collapse multi-collapse">
                <div class="d-flex justify-content-between flex-wrap">
                  <#if data?? && data.aggettivoList?? && data.aggettivoList?has_content>
                      <#list data.aggettivoList as aggettivo>
                        <div class="col m-2 p-2 rounded-10">
                          <div class="text-center">
                            <p class="mt-2">${aggettivo.maschile}<@spring.message "separatore.slash" /><br/>${aggettivo.femminile}</p>
                          </div>
                        </div>
                      </#list>
                  </#if>
                </div>
              </div>
            </div>
          </div>
        </#if>
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


