<#include "../import.ftl" />
<html lang="IT">
<#include "../layout/header.ftl" />
<body>
<h1 class="titolo">Tombola</h1>
<div class="container">
	<div class="card">
		<div class="card-body">
			<div class="row">
				<div class="col-sm">
					<form method="post" action="<@spring.url '/cartella/newAct'/>">
						<div class="mb-3">
							<label for="idStanza" class="form-label">ID Stanza:</label>
						</div>
						<div class="mb-3">
							<input id="idStanza" name="idStanza" type="text" class="form-control">
						</div>
						<div class="mb-3">
							<button type="submit" class="btn btn-primary">Entra</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>