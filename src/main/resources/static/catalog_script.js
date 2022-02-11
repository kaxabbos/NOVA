$(function () {
    $("#slider_range").slider({
        range: true,
        min: 1952,
        max: 2025,
        values: [1952, 2025],
        slide: function (event, ui) {
            $("#date_range").val(ui.values[0] + " - " + ui.values[1]);
        }
    });
    $("#date_range").val($("#slider_range").slider("values", 0) +
        " - " + $("#slider_range").slider("values", 1));
});