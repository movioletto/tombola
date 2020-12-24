<#include "../import.ftl" />
<html lang="IT">
<#include "../layout/header.ftl" />
<script type="text/javascript">
	$(function () {

		let url = '<@spring.url '/tabellone/stanza/${data.stanza.idStanza}/numero' />';

		$("#estrai-numero").click(function () {
			$(this).attr('disabled', 'true');

			$.ajax({
				dataType: "json",
				url: url,
				success: function (data) {
					let objNumeriUsciti = $('#numeri-usciti');

					let nessunNumeroUscito = objNumeriUsciti.find('#nessun-numero-uscito');
					if (nessunNumeroUscito.length !== 0) {
						objNumeriUsciti.find('#nessun-numero-uscito').remove();
					}

					if (objNumeriUsciti.find('.separatore').length !== 0) {
						objNumeriUsciti.find('.separatore').remove();
					}

					$('.lista-numeri-usciti').prepend('<span class="numero-uscito">' + data.numeroUscito + '</span>' + (nessunNumeroUscito.length !== 0 ? '' : '<span class="separatore"></span>'));

					$('#numero-' + data.numeroUscito).addClass('numero-uscito-tabella');

					$('#estrai-numero').removeAttr('disabled');
				}
			});
		});

		function aggiornaGiocatori(data) {
			let numeroGiocatori = $('#numero-giocatori');

			if (Number.isInteger(parseInt(numeroGiocatori.html()))) {
				numeroGiocatori.html(parseInt(numeroGiocatori.html()) + 1);
			}

			let objGiocatoriPresenti = $('#giocatori-presenti');

			if (objGiocatoriPresenti.find('#nessun-giocatore-presente').length !== 0) {
				objGiocatoriPresenti.find('#nessun-giocatore-presente').remove();
			}

			if(objGiocatoriPresenti.find('#giocatore-' + data.idTabella).length === 0) {
				$('.lista-giocatori-presenti').prepend('<span id="giocatore-' + data.idTabella + '" class="giocatore-presente btn btn-primary">' + camelCaseInTestoNormale(data.idTabella) + '</span>');
			}
		}

		let socket = new SockJS('/tombola/giocatore');
		let stompClient = Stomp.over(socket);
		stompClient.connect({}, function (frame) {
			stompClient.subscribe('/partita/giocatore/${data.stanza.idStanza}', function (data) {
				aggiornaGiocatori(JSON.parse(data.body));
			});
		});

	});
</script>
<body>
<h1 class="titolo">Tombola</h1>
<div class="container">
    <#if data?? && data.stanza??>
		<div class="card">
			<div class="card-body">
				<div class="row">
					<div class="col-sm">
						<h5 class="card-titolo">
							Nome partita:
						</h5>
						<p class="card-testo">
                            <#if data.stanza.nome??>
                                ${data.stanza.nome}
                            <#else>
								-
                            </#if>
						</p>
					</div>
					<div class="col-sm">
						<h5 class="card-titolo">
							Numero giocatori:
						</h5>
						<p id="numero-giocatori" class="card-testo">
                            <#if data.stanza.giocatorePresenteList??>
                                ${data.stanza.giocatorePresenteList?size}
                            <#else>
								0
                            </#if>
						</p>
					</div>
					<div class="col-sm">
						<h5 class="card-titolo">
							ID partita:
						</h5>
						<p class="card-testo">
                            <#if data.stanza.idStanza??>
                                ${data.stanza.idStanza}
                            <#else>
								-
                            </#if>
						</p>
					</div>
				</div>
				<div class="row">
					<div class="col-sm">
						<h5 class="card-titolo">
							Link partita:
						</h5>
						<p class="card-testo">
                            <#if data.stanza.idStanza??>
								<a href="<@spring.url '/cartella/new/${data.stanza.idStanza}' />" id="link-stanza"></a>
                            <#else>
								-
                            </#if>
						</p>
					</div>
				</div>
			</div>
		</div>
    </#if>

	<div id="numeri-usciti" class="card">
		<div class="card-body">
			<h5 class="card-title">
				Numero usciti:
			</h5>
			<p class="card-text lista-numeri-usciti">
                <#if data?? && data.numeroUscitoList?? && data.numeroUscitoList?has_content>
                    <#list data.numeroUscitoList as numeroUscito>
                        <#if numeroUscito.numero??>
							<span class="numero-uscito">
								${numeroUscito.numero}
							</span>
                            <#if numeroUscito?is_first>
								<span class="separatore"></span>
                            </#if>
                        </#if>
                    </#list>
                <#else>
					<span id="nessun-numero-uscito">Nessun numero estratto</span>
                </#if>
			</p>
		</div>
	</div>

    <#if data?? && data.tabellaList?? && data.tabellaList?has_content>
        <#list data.tabellaList as tabella>
            <#if tabella?is_odd_item>
				<div class="row">
            </#if>
			<div class="col-sm">
				<table>
					<tbody>
                    <#list tabella.sequenza as riga>
						<tr>
                            <#list riga as numeroRiga>
								<td>
                                    <#if numeroRiga.numero != 0>
										<span id="numero-${numeroRiga.numero}"
										      class="${numeroRiga.uscito?string('numero-uscito-tabella', '')}">
                                            ${numeroRiga.numero}
                                        </span>
                                    </#if>
								</td>
                            </#list>
						</tr>
                    </#list>
					</tbody>
				</table>
			</div>

            <#if !tabella?is_odd_item>
				</div>
            </#if>
        </#list>
    </#if>

	<button id="estrai-numero" class="btn btn-danger estrai-numero">Estrai numero</button>

	<div id="giocatori-presenti" class="card">
		<div class="card-body">
			<h5 class="card-title">
				Giocatori presenti:
			</h5>
			<p class="card-text lista-giocatori-presenti">
                <#if data?? && data.stanza?? && data.stanza.giocatorePresenteList?? && data.stanza.giocatorePresenteList?has_content>
                    <#list data.stanza.giocatorePresenteList as giocatorePresente>
                        <#if giocatorePresente??>
							<span id="giocatore-${giocatorePresente}" class="giocatore-presente btn btn-primary">
								${utility.camelCaseInStringaNormale(giocatorePresente)}
							</span>
                        </#if>
                    </#list>
                <#else>
					<span id="nessun-giocatore-presente">Nessun giocatore presente</span>
                </#if>
			</p>
		</div>
	</div>

    <#include "../layout/footer.ftl" />
</div>
</body>
</html>
