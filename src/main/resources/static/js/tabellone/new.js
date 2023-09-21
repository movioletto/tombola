$(function () {

  $('#codiceStanzaCustom').change(function () {
    if ($(this).is(":checked")) {
      $('#contenitore-codice-stanza-custom').removeClass("d-none");
    } else {
      $('#contenitore-codice-stanza-custom').addClass("d-none");
    }
  });

  $('#nomiTabellaCustom').change(function () {
    if ($(this).is(":checked")) {
      $('#contenitore-icone-tabelle-custom').removeClass("d-none");
    } else {
      $('#contenitore-icone-tabelle-custom').addClass("d-none");
    }
  });

  $('#tombolino').change(function () {
    if ($(this).is(":checked")) {
      $('#contenitore-premi-custom-tombolino').removeClass("d-none");
    } else {
      $('#contenitore-premi-custom-tombolino').addClass("d-none");
      $('#premiCustomTombolino').val("");
    }
  });

  $('#abilitaPremiCustom').change(function () {
    if ($(this).is(":checked")) {
      $('#contenitore-nomi-premi-custom').removeClass("d-none");
    } else {
      $('#contenitore-nomi-premi-custom').addClass("d-none");
      $('.premi-personalizzati').each(function () {
        $(this).val("");
      });
    }
  });

});