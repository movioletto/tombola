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

					if (objNumeriUsciti.find('.separatore')) {
						objNumeriUsciti.find('.separatore').remove();
					}

					$('.lista-numeri-usciti').prepend('<span class="numero-uscito">' + data.numeroUscito + '</span><span class="separatore"></span>');

					if (objNumeriUsciti.hasClass('d-none')) {
						objNumeriUsciti.removeClass('d-none');
					}

					$('#numero-' + data.numeroUscito).addClass('numero-uscito-tabella');

					$('#estrai-numero').removeAttr('disabled');
				}
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
							Link partita:
						</h5>
						<p class="card-testo">
                            <#if data.stanza.idStanza??>
								<a href="<@spring.url '/cartella/new/${data.stanza.idStanza}' />" id="link-stanza"></a>
                            </#if>
						</p>
					</div>
				</div>
			</div>
		</div>
    </#if>

	<div id="numeri-usciti"
	     class="card ${ (data?? && data.numeroUscitoList?? && data.numeroUscitoList?has_content)?string('', 'd-none') }">
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

            <#if !tabella?is_odd_item>
				</div>
            </#if>
        </#list>
    </#if>

	<button id="estrai-numero" class="btn btn-danger estrai-numero">Estrai numero</button>
</div>
</body>
</html>
