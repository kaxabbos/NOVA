$(function() {
    $("#slider_range").slider({
        range: true,
        min: 1979,
        max: 2021,
        values: [1979, 2021],
        slide: function(event, ui) { $("#date_range").val(ui.values[0] + " - " + ui.values[1]); }
    });
    $("#date_range").val($("#slider_range").slider("values", 0) +
        " - " + $("#slider_range").slider("values", 1));
});