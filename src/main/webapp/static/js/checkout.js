dom = {
    init: function () {
        this.validateData();
    },

    validateData() {
        let submitBtn = document.getElementById("submit");
        submitBtn.addEventListener("click", function () {
            // dom.validateText();
            dom.validateEmail();
        })
    },

    // validateText() {
    //     const textRegex = /^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$/u;
    //     let name = document.getElementById("name");
    // },

    validateEmail() {
        const emailRegex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        let emailField = document.getElementById("email");
        let emailValue = emailField.value;
        let emailError = document.getElementById("emailError");
        if (!emailRegex.test(emailValue)) {
            emailError.innerText = "Please enter a valid e-mail address!";
        } else {
            emailError.innerText = "";
        }
    }

};

dom.init();