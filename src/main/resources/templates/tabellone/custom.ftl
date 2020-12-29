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
					<form method="post" action="<@spring.url '/tabellone/customAct'/>">
						<div class="mb-3">
							<label for="id" class="form-label">ID partita:</label>
						</div>
						<div class="mb-3">
							<input id="id" name="id" type="text" class="form-control" maxlength="200" required>
						</div>
						<div class="mb-3">
							<label for="nome" class="form-label">Nome partita:</label>
						</div>
						<div class="mb-3">
							<input id="nome" name="nome" type="text" class="form-control" maxlength="200" required>
						</div>
						<div class="mb-3">
							<button type="submit" class="btn btn-primary">Crea</button>
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