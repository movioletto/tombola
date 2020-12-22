<#include "../import.ftl" />
<html lang="IT">
<#include "../layout/header.ftl" />
<script type="text/javascript">
	$(function () {

		function aggiornaNumeriUsciti(messageOutput) {
			let objNumeriUsciti = $('#numeri-usciti');

			if (objNumeriUsciti.find('.separatore')) {
				objNumeriUsciti.find('.separatore').remove();
			}

			$('.lista-numeri-usciti').prepend('<span class="numero-uscito">' + messageOutput.numeroUscito + '</span><span class="separatore"></span>');

			if (objNumeriUsciti.hasClass('d-none')) {
				objNumeriUsciti.removeClass('d-none');
			}
		}

		let socket = new SockJS('/tombola/stanza');
		let stompClient = Stomp.over(socket);
		stompClient.connect({}, function (frame) {
			stompClient.subscribe('/partita/stanza/${data.stanza.idStanza}', function (messageOutput) {
				aggiornaNumeriUsciti(JSON.parse(messageOutput.body));
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
                                ${data.tabella.idTabella}
                            </#if>
						</p>
					</div>
				</div>
			</div>
		</div>
    </#if>

	<div id="numeri-usciti" class="card d-none">
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
                </#if>
			</p>
		</div>
	</div>

    <#if data?? && data.tabella?? && data.tabella.sequenza?? && data.tabella.sequenza?has_content>
		<div class="row" style="width: 50%; margin: auto;">
			<div class="col-sm">
				<table>
					<tbody>
                    <#list data.tabella.sequenza as riga>
						<tr>
                            <#list riga as numeroRiga>
								<td style="width: 11.11%;">
                                    <#if numeroRiga.numero != 0>
										<span class="numero-tabella ${numeroRiga.uscito?string('numero-uscito-tabella', '')}">
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


