<#include "../import.ftl" />
<html lang="IT">
<#include "../layout/header.ftl" />
<script type="text/javascript">
	$(function () {
		let autoSelezione = $('#auto-selezione');
		autoSelezione.change(function () {
			if ($(this).is(":checked")) {
				$('.numero-uscito').each(function () {
					let casellaNumeroCorrente = $('#numero-' + $(this).html());
					if (!casellaNumeroCorrente.hasClass('numero-uscito-tabella')) {
						casellaNumeroCorrente.addClass('numero-uscito-tabella');
					}
				});
			}
		});

		function aggiornaNumeriUsciti(data) {
			let objNumeriUsciti = $('#numeri-usciti');

			let nessunNumeroUscito = objNumeriUsciti.find('#nessun-numero-uscito');
			if (nessunNumeroUscito.length !== 0) {
				objNumeriUsciti.find('#nessun-numero-uscito').remove();
			}

			if (objNumeriUsciti.find('.separatore').length !== 0) {
				objNumeriUsciti.find('.separatore').remove();
			}

			$('.lista-numeri-usciti').prepend('<span class="numero-uscito">' + data.numeroUscito + '</span>' + (nessunNumeroUscito.length !== 0 ? '' : '<span class="separatore"></span>'));

			if (autoSelezione.is(":checked")) {
				$('#numero-' + data.numeroUscito).addClass('numero-uscito-tabella');
			}
		}

		let socket = new SockJS('/tombola/stanza');
		let stompClient = Stomp.over(socket);
		stompClient.connect({}, function (frame) {
			stompClient.send("/partita/giocatore/${data.stanza.idStanza}", {}, JSON.stringify({'idTabella': "${data.tabella.idTabella}"}));
			stompClient.subscribe('/partita/stanza/${data.stanza.idStanza}', function (data) {
				aggiornaNumeriUsciti(JSON.parse(data.body));
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
                            </#if>
						</p>
					</div>
				</div>
				<div class="row">
					<div class="col-sm">
						<h5 class="card-titolo">
							Nome cartella:
						</h5>
						<p class="card-testo">
                            <#if data.tabella.idTabella??>
                                ${utility.camelCaseInStringaNormale(data.tabella.idTabella)}
                            </#if>
						</p>
					</div>
					<div class="col-sm">
						<h5 class="card-titolo">
							<label for="auto-selezione">
								Selezione automatica:
							</label>
						</h5>
						<p class="card-testo">
							<input id="auto-selezione" type="checkbox" class="apple-switch">
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

    <#if data?? && data.tabella?? && data.tabella.sequenza?? && data.tabella.sequenza?has_content>
		<div class="row">
			<div class="col-sm">
				<table>
					<tbody>
                    <#list data.tabella.sequenza as riga>
						<tr>
                            <#list riga as numeroRiga>
								<td style="width: 11.11%;">
                                    <#if numeroRiga.numero != 0>
										<span id="numero-${numeroRiga.numero}"
										      class="numero-tabella ${numeroRiga.uscito?string('numero-uscito-tabella', '')}">
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
		</div>
    </#if>
</div>
</body>
</html>


