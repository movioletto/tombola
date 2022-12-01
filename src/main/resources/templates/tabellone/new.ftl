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
          <form method="post" action="<@spring.url '/tabellone/newAct'/>">
            <div class="mb-3">
              <label for="nome" class="form-label"><@spring.message "form.nome-partita" /></label>
            </div>
            <div class="mb-3">
              <input id="nome" name="nome" type="text" class="form-control" maxlength="200"
                     required>
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