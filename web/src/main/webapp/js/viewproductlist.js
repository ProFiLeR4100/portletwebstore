(function($){
    // Check if there any buttons on web-page
    if($(".productlist-wrapper .cartActionBtn").length) {
        $(".productlist-wrapper .cartActionBtn").on("click", function (e) {
            var $button = $(this),
                $toggleContainer = $button.parents('.cart-action-toggle');

            $.get($button.data('url'), function (responseData) {
                if ($("#cartSelectedItemCount").length)
                    $("#cartSelectedItemCount").text(responseData);

                $toggleContainer.attr('data-selected', parseInt($toggleContainer.attr('data-selected')) == 0 ? 1 : 0);
            }).fail(function () {
                alert("Action failed!");
            });
        });
    }
})(jQuery);