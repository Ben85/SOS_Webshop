dom = {
    init: function () {
        dom.addToCartButtons();
    },

    addToCartButtons: function () {
        let buttons = document.getElementsByClassName("btn-add-to-cart");
        for (let button of buttons) {
            button.addEventListener('click', function () {
                let productId = button.getAttribute("id");
                $.post('/shopping-cart', {
                    productId: productId
                })
            })
        }
    }
};

dom.init();