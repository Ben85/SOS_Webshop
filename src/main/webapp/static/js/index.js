dom = {
    init: function () {
        dom.addToCartButtons();
        dom.addProductToModal();
    },

    addToCartButtons: function () {
        let buttons = document.getElementsByClassName("btn-add-to-cart");
        for (let button of buttons) {
            button.addEventListener('click', function () {
                let productId = button.getAttribute("id");
                let productName = document.getElementById("product-name-" + productId).innerText;
                let productColor = document.getElementById("color-" + productId).innerText;
                let productSize = document.getElementById("size-" + productId).innerText;
                let productPrice = document.getElementById("price-" + productId).innerText;

                let modalBody = document.getElementById("product-to-cart");
                modalBody.innerHTML = "";

                let imageDiv = document.createElement("div");
                imageDiv.setAttribute("class", "col-sm");
                let image = document.createElement("img");
                image.setAttribute("class", "group list-group-image");
                image.setAttribute("src", "/static/img/product_" + productId + ".jpg");
                imageDiv.appendChild(image);
                modalBody.appendChild(imageDiv);

                let infoDiv = document.createElement("div");
                infoDiv.setAttribute("class", "caption col-sm");

                let prodNameTag = document.createElement("h4");
                prodNameTag.innerHTML = productName;
                infoDiv.appendChild(prodNameTag);

                let infoRow = document.createElement("div");
                infoRow.setAttribute("class", "row");

                let sizeDiv = document.createElement("div");
                sizeDiv.setAttribute("class", "col-xs-12 col-md");
                let sizeP = document.createElement("p");
                sizeP.setAttribute("class", "lead");
                sizeP.innerHTML = productSize;
                sizeDiv.appendChild(sizeP);

                let colorDiv = document.createElement("div");
                colorDiv.setAttribute("class", "col-xs-12 col-md");
                let colorP = document.createElement("p");
                colorP.setAttribute("class", "lead");
                colorP.innerHTML = productColor;
                colorDiv.appendChild(colorP);

                // let priceRow = document.createElement("div");
                // priceRow.setAttribute("class", "row");

                let priceDiv = document.createElement("div");
                priceDiv.setAttribute("class", "col-xs-12 col-md");

                let priceP = document.createElement("p");
                priceP.setAttribute("class", "lead");
                priceP.innerHTML = productPrice;

                priceDiv.appendChild(priceP);
                // priceRow.appendChild(priceDiv);


                infoDiv.appendChild(sizeDiv);
                infoDiv.appendChild(colorDiv);
                infoDiv.appendChild(priceDiv);
                //infoDiv.appendChild(priceRow);

                modalBody.appendChild(infoDiv);


                $.post('/shopping-cart', {
                    productId: productId
                })
            })
        }
    },

    addProductToModal: function() {
        let modalBody = document.getElementById("product-to-cart");
        let paragraph = document.createElement("p");
        let content = document.createTextNode(dom._productId);
        paragraph.appendChild(content);
        modalBody.appendChild(paragraph);

    }
};

dom.init();