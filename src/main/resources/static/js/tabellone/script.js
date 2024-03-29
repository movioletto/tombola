$(function () {

  let urlNumero = $('#url-numero').val();
  let urlGiocatore = $('#url-giocatore').val();
  let urlPremio = $('#url-premio').val();

  let idStanza = $('#id-stanza').val();

  bindClickGiocatorePresente(urlGiocatore);

  bindClickEstraiNumero(urlNumero);

  creaQrCode();

  $('#visualizza-qrcode').change(function () {
    if ($(this).is(':checked')) {
      $('#qrcode-partita').parent().removeClass('d-none');
    } else {
      $('#qrcode-partita').parent().addClass('d-none');
    }
  });

  $('#modifica-url').bind('click', function () {
    let urlCompleto = $('#link-stanza').attr('href');
    let inizioUrl = urlCompleto.substring(0, urlCompleto.indexOf('/tombola'));
    let fineUrl = urlCompleto.substring(urlCompleto.indexOf('/tombola'));

    let inizioUlrHtml = `<input id="inizio-modifica-url" name="inizio-modifica-url" type="text" value="${inizioUrl}">`;
    let fineUrlHtml = `<span id="fine-modifica-url">${fineUrl}</span>`;

    $('#container-url').html(inizioUlrHtml + fineUrlHtml);

    $(this).addClass('d-none');
    $('#salva-url').removeClass('d-none');
  });

  $('#salva-url').bind('click', function () {
    let inizioUrl = $('#inizio-modifica-url').val();
    let fineUrl = $('#fine-modifica-url').text();

    let a = `<a href="${inizioUrl}${fineUrl}" id="link-stanza">${inizioUrl}${fineUrl}</a>`;
    $('#container-url').html(a);

    $(this).addClass('d-none');
    $('#modifica-url').removeClass('d-none');

    $('#qrcode-partita').html('');
    creaQrCode();
  });

  let bindClickConfermaPremio = function (url) {
    $('#conferma-premio').unbind('click').bind('click', function () {
      let idTabella = $(this).data('id-tabella');
      let idPremio = $(this).data('id-premio');

      $.ajax({
        dataType: 'json',
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

          $('.lista-premi-vinti').append(
              '<strong class="badge bg-success ms-2">' + data.nomePremio
              + '</strong> ' + $('#separatore-premio-vinto').val()
              + (notBlank(data.iconaTabella)
                  ? '<img src="/tombola/resources/static/image/'
                  + data.iconaTabella + '.svg" alt="' + data.iconaTabella
                  + '" width="50px">'
                  : '')
              + data.nomeTabella + (notBlank(data.aggettivoTabella)
                  ? ' ' + data.aggettivoTabella : ''));

          stompClient.send('/partita/stanza/' + idStanza, {}, JSON.stringify({
            'azione': data.azione,
            'idTabella': data.idTabella,
            'nomeTabella': data.nomeTabella,
            'aggettivoTabella': data.aggettivoTabella,
            'iconaTabella': data.iconaTabella,
            'nomePremio': data.nomePremio,
            'idPremio': data.idPremio
          }));

        }
      });
    });
  }

  let aggiornaGiocatori = function (data) {
    let objGiocatoriPresenti = $('#giocatori-presenti');

    if (objGiocatoriPresenti.find('#nessun-giocatore-presente').length !== 0) {
      objGiocatoriPresenti.find('#nessun-giocatore-presente').remove();
    }

    if (objGiocatoriPresenti.find('#giocatore-' + data.idTabella).length
        === 0) {

      let numeroGiocatori = $('#numero-giocatori');

      if (Number.isInteger(parseInt(numeroGiocatori.html()))) {
        numeroGiocatori.html(parseInt(numeroGiocatori.html()) + 1);
      }

      $('.lista-giocatori-presenti').prepend(
          '<span id="giocatore-' + data.idTabella
          + '" class="giocatore-presente btn btn-primary" data-giocatore="'
          + data.idTabella + '" data-stanza="' + idStanza
          + '" data-giocatore-nome="' + data.nomeTabella + '"'
          + ' data-giocatore-aggettivo="' + (notBlank(data.aggettivoTabella)
              ? data.aggettivoTabella : '') + '"'
          + ' data-giocatore-icona="' + (notBlank(data.iconaTabella)
              ? data.iconaTabella : '') + '">'
          + (notBlank(data.iconaTabella)
              ? '<img src="/tombola/resources/static/image/'
              + data.iconaTabella + '.svg" alt="'
              + data.iconaTabella + '" width="50px">' : '')
          + data.nomeTabella + ' ' + (notBlank(data.aggettivoTabella)
              ? data.aggettivoTabella : '') + '</span>');
    }

    bindClickGiocatorePresente(urlGiocatore);
  }

  let aggiornaPremio = function (data) {
    let giocatorePresenteSelezionato = $('#giocatore-' + data.idTabella);
    let nomeGiocatore = giocatorePresenteSelezionato.html();
    giocatorePresenteSelezionato.html(
        nomeGiocatore + '<span class="badge rounded-pill bg-danger ms-2">' + $(
            '#notifica-premio').val() + '</span>');

    let confermaPremio = $('#conferma-premio');
    confermaPremio.data('id-premio', data.idPremio);
    confermaPremio.data('id-tabella', data.idTabella);
    confermaPremio.find('.nome-premio-giocatore-presente').html(
        data.nomePremio);

    if($('#id-cartella-giocatore-presente').data('id-tabella') == data.idTabella) {
      confermaPremio.removeClass('d-none');
    }

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