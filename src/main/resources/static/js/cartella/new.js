$(function () {

  $('#bottone-cartella-entra').click(function () {
    if ($("#controllo-id-stanza").val() === "true") {
      $.ajax({
        dataType: "json",
        url: $("#url-stanza").val() + $("#idStanza").val(),
        success: function (data) {
          if (data != null && data.nomiTabellaCustom != null
              && data.nomiTabellaCustom) {
            $('#cartella-opzioni').html('<div class="mb-3">'
                + '   <label for="nome" class="form-label">' + $(
                    '#testo-form-id-cartella').val() + '</label>'
                + '</div>'
                + '<div class="mb-3">'
                + '   <input id="nome" name="nome" type="text" class="form-control" maxlength="50" required>'
                + '</div>');

            $("#controllo-id-stanza").val("false");
          }
        }
      });
    } else {
      $('#form').submit();
    }
  });

});