<#include "../import.ftl" />
<html lang="IT">
<#include "../layout/header.ftl" />
<body>
<h1 class="titolo"><@spring.message "app.titolo" /></h1>
<div class="container">
  <div class="card">
    <div class="card-body">
      <div class="row">
        <div class="col-sm">
          <form method="post" action="<@spring.url '/cartella/customAct'/>">
            <div class="mb-3">
              <label for="idStanza" class="form-label"><@spring.message "form.id-partita" /></label>
            </div>
            <div class="mb-3">
                <#if data?? && data.stanza?? && data.stanza.idStanza??>
                  <input id="idStanza" name="idStanza" type="text" class="form-control"
                         maxlength="10" value="${data.stanza.idStanza}" readonly required>
                <#else>
                  <input id="idStanza" name="idStanza" type="text" class="form-control"
                         maxlength="10" required>
                </#if>
            </div>
            <div class="mb-3">
              <label for="idTabella"
                     class="form-label"><@spring.message "form.id-cartella" /></label>
            </div>
            <div class="mb-3">
              <input id="idTabella" name="idTabella" type="text" class="form-control" maxlength="50"
                     required>
            </div>
            <div class="mb-3">
              <button type="submit"
                      class="btn btn-primary"><@spring.message "bottone.cartella.entra" /></button>
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