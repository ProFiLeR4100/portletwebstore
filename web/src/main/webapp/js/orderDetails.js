(function($){
    // Check if there any buttons on web-page
    if($(".orderdetails-wrapper .product").length) {
        $(".btn-delete").on("click", function () {
            var $button = $(this);

            $.get($button.data("url"), function (responseData) {
                $button.parents(".row-fluid .product").remove();
            })

        })
    }

    if($(".product-options-checkbox").length) {
        $(".product-options-checkbox").on("change", function () {
            var $checkbox = $(this);
            console.log($checkbox.prop("checked"));
        })
    }

})(jQuery);