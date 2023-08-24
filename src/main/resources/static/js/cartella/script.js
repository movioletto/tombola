$(function () {

  $('.numero-tabella').click(function () {
    if ($(this).hasClass('numero-uscito-tabella')) {
      $(this).removeClass('numero-uscito-tabella');
    } else {
      $(this).addClass('numero-uscito-tabella');
    }
  });

  let urlPremio = $('#url-premio-corrente').val();
  let idStanza = $('#id-stanza').val();
  let idTabella = $('#id-tabella').val();

  let abilitaSuoni = $('#abilitaSuoni');
  abilitaSuoni.change(function() {
    if ($(this).is(":checked")) {
      $("#numeroUscitoAudio")[0].play();
    }
  });

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

  let aggiornaNumeriUsciti = function (data) {
    disegnaNumeriUsciti(data);
    suonaNuovoNumero();

    if (autoSelezione.is(":checked")) {
      $('#numero-' + data.numeroUscito).addClass('numero-uscito-tabella');
    }
  }

  let aggiornaPremio = function (data) {
    let nessunPremioUscito = $('#nessun-premio-vinto');
    if (nessunPremioUscito.length !== 0) {
      nessunPremioUscito.remove();
    }

    $('.lista-premi-vinti').append(
        '<strong class="badge bg-success ms-2">' + data.nomePremio
        + '</strong> ' + $('#separatore-premio-vinto').val()
        + camelCaseInTestoNormale(data.idTabella));

    $.ajax({
      dataType: "json",
      url: urlPremio,
      success: function (data) {

        let dichiarazionePremio = $('#dichiarazione-premio');

        if (data != null) {
          dichiarazionePremio.data('id-premio', data.idPremio);
          dichiarazionePremio.data('nome-premio', data.nomePremio);
          dichiarazionePremio.html(
              $('#bottone-cartella-premio').val() + ' ' + data.nomePremio);

          dichiarazionePremio.removeAttr("disabled");
        } else {
          dichiarazionePremio.remove();
        }

      }
    });

  }

  let suonaNuovoNumero = function () {
    if (abilitaSuoni.is(":checked")) {
      const audioPlayer = $("#numeroUscitoAudio")[0];
      audioPlayer.play();
    }
  }

  let socket = new SockJS('/tombola/stanza');
  let stompClient = Stomp.over(socket);
  stompClient.connect({}, function () {
    stompClient.send('/partita/giocatore/' + idStanza, {}, JSON.stringify({
      'azione': 1,
      'idTabella': idTabella
    }));
    stompClient.subscribe('/partita/stanza/' + idStanza, function (data) {
      let body = JSON.parse(data.body);

      if (body.azione === 2) {
        aggiornaNumeriUsciti(body);
      } else if (body.azione === 4) {
        aggiornaPremio(body);
      }
    });
  });

  $('#dichiarazione-premio').click(function () {
    $(this).attr("disabled", "disabled");

    stompClient.send('/partita/giocatore/' + idStanza, {}, JSON.stringify({
      'azione': 3,
      'idTabella': idTabella,
      'idPremio': $(this).data('id-premio'),
      'nomePremio': $(this).data('nome-premio')
    }));
  });
});