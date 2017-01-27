(function($){
    $(document).ready(function() {
        var InitClick = function () {

            var $button = $(this);
            var itemId = $button.data("id");
            var selected = parseInt($button.data("selected"));

            if (selected == 0) {
                $.get($button.data('add-product') + itemId, function (responseData) {
                    $("#cartSelectedItemCount").text(responseData);
                    $button.data("selected", 1);
                    $button.text("Remove from cart");
                    console.log("1");
                });
            } else {
                $.get($button.data('remove-product') + itemId, function (responseData) {
                    $("#cartSelectedItemCount").text(responseData);
                    $button.data("selected", 0);
                    $button.text("Add item to cart");
                    console.log("2");
                });
            }

        };

        $(".productlist-wrapper .cartActionBtn").on("click",InitClick);

    });
})(jQuery);