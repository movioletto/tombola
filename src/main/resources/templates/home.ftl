<#include "import.ftl" />
<html lang="IT">
<#include "layout/header.ftl" />
<body>
<h1 class="titolo-home">Tombola</h1>
<div class="container">
	<div class="row">
		<div class="col-sm">
			<div class="card bottone-home">
				<a href="<@spring.url '/tabellone/new'/>">
					<i class="fas fa-table fa-10x"></i> <br>
					Tabellone
				</a>
			</div>
		</div>
		<div class="col-sm">
			<div class="card bottone-home">
				<a href="<@spring.url '/cartella/new'/>">
					<i class="fas fa-receipt fa-10x"></i> <br>
					Cartella
				</a>
			</div>
		</div>
	</div>
</div>
</body>
</html>