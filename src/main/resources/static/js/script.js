$(function () {

	let linkStanza = $('#link-stanza');
	linkStanza.html(window.location.protocol + "//" + window.location.hostname + linkStanza.attr('href'));
});

var camelCaseInTestoNormale = function (string) {
	return string.replace(/([A-Z])/g, ' $1');
}

var bindClickGiocatorePresente = function (url) {

	$('.giocatore-presente').unbind('click').bind('click', function () {
		let idTabella = $(this).data('giocatore');
		let idStanza = $(this).data('stanza');
		let giocatorePresente = $(this);

		$.ajax({
			dataType: "json",
			url: url + idStanza + '/tabella/' + idTabella,
			success: function (data) {
				let cartellaGiocatorePresente = giocatorePresente.parent().parent().parent().find('#cartella-giocatore-presente');

				cartellaGiocatorePresente.find('.card-title').find('.id-cartella-giocatore-presente').html(camelCaseInTestoNormale(idTabella));

				let confermaPremio = $('#conferma-premio');
				if ($('#giocatore-' + idTabella).find('.badge').length !== 0) {
					confermaPremio.data('id-tabella', idTabella);
					confermaPremio.removeClass('d-none');
				} else {
					confermaPremio.addClass('d-none');
				}

				$('#chiudi-cartella-giocatore-presente').click(function () {
					cartellaGiocatorePresente.addClass('d-none');
				});

				let tabella = '<table>' +
					'   <tbody>';

				data.sequenza.forEach(function (riga) {
					tabella += '        <tr>';

					riga.forEach(function (numero) {
						tabella += '           <td style="width: 11.11%;" class="d-table-cell ' + (numero.uscito ? 'numero-uscito-tabella' : '') + '">';
						if (numero.numero !== 0) {
							tabella += '               ' + numero.numero;
						}
						tabella += '           </td>';
					});

					tabella += '        </tr>';
				});

				tabella += ' </tbody>' +
					'</table>';

				cartellaGiocatorePresente.find('.card-text').html(tabella);

				if (cartellaGiocatorePresente.hasClass('d-none')) {
					cartellaGiocatorePresente.removeClass('d-none');
				}

			}
		});
	});
}

function disegnaNumeriUsciti(data) {
	let objNumeriUsciti = $('#numeri-usciti');

	let nessunNumeroUscito = objNumeriUsciti.find('#nessun-numero-uscito');
	if (nessunNumeroUscito.length !== 0) {
		objNumeriUsciti.find('#nessun-numero-uscito').remove();
	}

	if (objNumeriUsciti.find('.separatore').length !== 0) {
		objNumeriUsciti.find('.separatore').remove();
	}

	$('.lista-numeri-usciti').prepend('<span class="numero-uscito">' + data.numeroUscito + '</span>' + (nessunNumeroUscito.length !== 0 ? '' : '<span class="separatore"></span>'));
}

var bindClickEstraiNumero = function (url) {
	$("#estrai-numero").click(function () {
		$(this).attr('disabled', 'true');

		$.ajax({
			dataType: "json",
			url: url,
			success: function (data) {
				disegnaNumeriUsciti(data);

				$('#numero-' + data.numeroUscito).addClass('numero-uscito-tabella');

				$('#estrai-numero').removeAttr('disabled');
			}
		});
	});
}