$(function () {
	$('.numero-tabella').click(function () {
		if ($(this).hasClass('numero-uscito-tabella')) {
			$(this).removeClass('numero-uscito-tabella');
		} else {
			$(this).addClass('numero-uscito-tabella');
		}
	});

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
		let nomeTabella = $(this).html();

		$.ajax({
			dataType: "json",
			url: url + idStanza + '/tabella/' + idTabella,
			success: function (data) {
				let cartellaGiocatorePresente = $("#cartella-giocatore-presente");

				cartellaGiocatorePresente.find('.card-title').html(
					'<div class="row">' +
					'   <div class="col-sm">' +
					'       Cartella giocatore presente: ' + nomeTabella +
					'   </div>' +
					'   <div class="col-sm text-right">' +
					'       <span id="chiudi-cartella-giocatore-presente" class="btn btn-danger" style="float: right;">' +
					'           Chiudi cartella giocatore' +
					'       </span>' +
					'   </div>' +
					'</div>');

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