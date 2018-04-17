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
                $.post('/route', {
                    productId: productId
                })
            })
        }
    },

    modifyButtons: function() {
        let modifyButtons = document.getElementsByClassName("set");
        for (let button of modifyButtons) {
            button.addEventListener('click', function() {
                let productId = button.dataset.id;
                let quantity = button.value;
                $.post('/route', {
                    productId: productId,
                    quantity: quantity
                })
            })
        }
    }
};

dom.init();