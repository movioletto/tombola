<#include "import.ftl" />
<html lang="IT">
<#include "layout/header.ftl" />
<body>
<h1 class="titolo-home"><@spring.message "app.titolo" /></h1>
<div class="container-lg">
  <div class="row">
    <div class="col-sm">
      <div class="card bottone-home">
        <a href="<@spring.url '/tabellone/new'/>">
          <i class="fas fa-table fa-10x"></i> <br>
            <@spring.message "home.bottoni.tabellone" />
        </a>
      </div>
    </div>
    <div class="col-sm">
      <div class="card bottone-home">
        <a href="<@spring.url '/tabellone/custom'/>">
          <i class="fas fa-table fa-10x"></i> <br>
            <@spring.message "home.bottoni.tabellone.custom" />
        </a>
      </div>
    </div>
    <div class="col-sm">
      <div class="card bottone-home">
        <a href="<@spring.url '/cartella/new'/>">
          <i class="fas fa-receipt fa-10x"></i> <br>
            <@spring.message "home.bottoni.cartella" />
        </a>
      </div>
    </div>
    <div class="col-sm">
      <div class="card bottone-home">
        <a href="<@spring.url '/cartella/custom'/>">
          <i class="fas fa-receipt fa-10x"></i> <br>
            <@spring.message "home.bottoni.cartella.custom" />
        </a>
      </div>
    </div>
  </div>
  <div class="row">
    <div class="col-sm">
      <div class="card bottone-home" style="min-height: 30px;">
        <a href="<@spring.url '/stats/'/>">
          <i class="fas fa-stream"></i>
            <@spring.message "home.bottoni.statistiche" />
        </a>
      </div>
    </div>
  </div>
    <#include "layout/footer.ftl" />
</div>
</body>
</html>