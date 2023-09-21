$(function () {

  let linkStanza = $('#link-stanza');

  let port = '';
  if (window.location.port !== '') {
    port = ':' + window.location.port;
  }

  let linkFinale = window.location.protocol + '//' + window.location.hostname
      + port + linkStanza.attr('href');

  linkStanza.html(linkFinale);
  linkStanza.attr('href', linkFinale);
});

let creaQrCode = function () {
  new QRCode(document.getElementById('qrcode-partita'),
      $('#link-stanza').text());
  $('#qrcode-partita img').addClass("mx-auto");
}

let notBlank = function (stringa) {
  return stringa !== null && stringa !== "" && stringa !== undefined;
}

var bindClickGiocatorePresente = function (url) {

  $('.giocatore-presente').unbind('click').bind('click', function () {
    let idTabella = $(this).data('giocatore');
    let idStanza = $(this).data('stanza');
    let nomeTabella = $(this).data('giocatore-nome');
    let aggettivoTabella = $(this).data('giocatore-aggettivo');
    let iconaTabella = $(this).data('giocatore-icona');
    let giocatorePresente = $(this);

    $.ajax({
      dataType: "json",
      url: url + idStanza + '/tabella/' + idTabella,
      success: function (data) {
        let cartellaGiocatorePresente = giocatorePresente.parent().parent().parent().find(
            '#cartella-giocatore-presente');

        cartellaGiocatorePresente.find('.card-title').find(
            '#id-cartella-giocatore-presente').html((notBlank(iconaTabella)
                ? '<img src="/tombola/resources/static/image/' + iconaTabella
                + '.svg" alt="' + iconaTabella + '" width="50px">' : '')
            + nomeTabella + ' ' + ((notBlank(aggettivoTabella)
                ? aggettivoTabella : '')));

        $('#id-cartella-giocatore-presente').data('id-tabella', idTabella)

        let confermaPremio = $('#conferma-premio');
        if ($('#giocatore-' + idTabella).find('.badge').length !== 0) {
          confermaPremio.data('id-tabella', idTabella);
          confermaPremio.removeClass('d-none');
        } else {
          confermaPremio.addClass('d-none');
        }

        $('#chiudi-cartella-giocatore-presente').click(function () {
          cartellaGiocatorePresente.addClass('d-none');
          $('#id-cartella-giocatore-presente').data('id-tabella', '')
        });

        let tabella = '<table>' +
            '   <tbody>';

        data.sequenza.forEach(function (riga) {
          tabella += '        <tr>';

          riga.forEach(function (numero) {
            tabella += '           <td style="width: 11.11%;" class="d-table-cell '
                + (numero.uscito ? 'numero-uscito-tabella' : '') + '">';
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

  $('.lista-numeri-usciti').prepend(
      '<span class="numero-uscito">' + data.numeroUscito + '</span>'
      + (nessunNumeroUscito.length !== 0 ? ''
          : '<span class="separatore"></span>'));
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