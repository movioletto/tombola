$(function () {

	let urlNumero = $('#url-numero').val();
	let urlGiocatore = $('#url-giocatore').val();
	let urlPremio = $('#url-premio').val();

	let idStanza = $('#id-stanza').val();

	bindClickGiocatorePresente(urlGiocatore);

	bindClickEstraiNumero(urlNumero);

	let bindClickConfermaPremio = function (url) {

		$('#conferma-premio').unbind('click').bind('click', function () {
			let idTabella = $(this).data('id-tabella');
			let idPremio = $(this).data('id-premio');

			$.ajax({
				dataType: "json",
				url: url + idTabella + '/' + idPremio,
				success: function (data) {
					$('#conferma-premio').addClass('d-none');

					$('.rounded-pill').each(function () {
						$(this).remove();
					});

					let nessunPremioUscito = $('#nessun-premio-vinto');
					if (nessunPremioUscito.length !== 0) {
						nessunPremioUscito.remove();
					}

					$('#cartella-giocatore-presente').addClass('d-none');

					$('.lista-premi-vinti').append('<strong class="badge bg-success ms-2">' + data.nomePremio + '</strong> <@spring.message "separatore.premio-vinto"> ' + camelCaseInTestoNormale(data.idTabella));

					stompClient.send('/partita/stanza/' + idStanza, {}, JSON.stringify({
						'azione': data.azione,
						'idTabella': data.idTabella,
						'nomePremio': data.nomePremio,
						'idPremio': data.idPremio
					}));

				}
			});
		});
	}

	let aggiornaGiocatori = function (data) {
		let numeroGiocatori = $('#numero-giocatori');

		if (Number.isInteger(parseInt(numeroGiocatori.html()))) {
			numeroGiocatori.html(parseInt(numeroGiocatori.html()) + 1);
		}

		let objGiocatoriPresenti = $('#giocatori-presenti');

		if (objGiocatoriPresenti.find('#nessun-giocatore-presente').length !== 0) {
			objGiocatoriPresenti.find('#nessun-giocatore-presente').remove();
		}

		if (objGiocatoriPresenti.find('#giocatore-' + data.idTabella).length === 0) {
			$('.lista-giocatori-presenti').prepend('<span id="giocatore-' + data.idTabella + '" class="giocatore-presente btn btn-primary" data-giocatore="' + data.idTabella + '" data-stanza="' + idStanza + '">' + camelCaseInTestoNormale(data.idTabella) + '</span>');
		}

		bindClickGiocatorePresente(urlGiocatore);
	}

	let aggiornaPremio = function (data) {
		let giocatorePresenteSelezionato = $('#giocatore-' + data.idTabella);
		let nomeGiocatore = giocatorePresenteSelezionato.html();
		giocatorePresenteSelezionato.html(nomeGiocatore + '<span class="badge rounded-pill bg-danger ms-2"><@spring.message "notifica-premio"></span>');

		let confermaPremio = $('#conferma-premio');
		confermaPremio.data('id-premio', data.idPremio);
		confermaPremio.data('id-tabella', data.idTabella);
		confermaPremio.find('.nome-premio-giocatore-presente').html(data.nomePremio);

		confermaPremio.removeClass('d-none');

		bindClickConfermaPremio(urlPremio);
	}

	let socket = new SockJS('/tombola/giocatore');
	let stompClient = Stomp.over(socket);
	stompClient.connect({}, function () {
		stompClient.subscribe('/partita/giocatore/' + idStanza, function (data) {
			let body = JSON.parse(data.body);

			if (body.azione === 1) {
				aggiornaGiocatori(body);
			} else if (body.azione === 3) {
				aggiornaPremio(body);
			}
		});
	});

});