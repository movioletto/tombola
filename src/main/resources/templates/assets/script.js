$(function () {
    $('.numero-tabella').click(function () {
        if ($(this).hasClass('numero-uscito-tabella')) {
            $(this).removeClass('numero-uscito-tabella');
        } else {
            $(this).addClass('numero-uscito-tabella');
        }
    });
});