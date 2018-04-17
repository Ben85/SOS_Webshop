dom = {
    init: function() {
        dom.removeButtons();
        dom.modifyButtons();
    },

    removeButtons: function() {
        let removeButtons = document.getElementsByClassName("remove");
        for (let button of removeButtons) {
            button.addEventListener('click', function() {
                let productId = button.dataset.id;
                $.ajax({
                    url: '/shopping-cart',
                    type: 'DELETE',
                    data: {productId: productId}
                });
            })
        }
    },

    modifyButtons: function() {
        let modifyButtons = document.getElementsByClassName("set");
        for (let button of modifyButtons) {
            button.addEventListener('click', function() {
                let productId = button.dataset.id;
                let quantity = button.value;
                $.ajax({
                    url: '/shopping-cart',
                    type: 'PATCH',
                    data: {productId: productId,
                           quantity: quantity}
                });
            })
        }
    }
};

dom.init();