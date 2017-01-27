(function($){
    $(".productlist-wrapper .cartActionBtn").on("click",function (e) {
        var $button = $(this),
            $toggleContainer = $button.parents('.cart-action-toggle');

        $.get($button.data('url'), function (responseData) {
            if($("#cartSelectedItemCount").length)
                $("#cartSelectedItemCount").text(responseData);

            $toggleContainer.data('selected', parseInt($toggleContainer.data('selected'))===0 ? 1 : 0);
        });
    });
})(jQuery);