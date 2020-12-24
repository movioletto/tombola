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