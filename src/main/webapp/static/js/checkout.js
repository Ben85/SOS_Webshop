dom = {
    init: function () {
        this.validateData();
    },

    validateData() {
        let submitBtn = document.getElementById("submit");
        submitBtn.addEventListener("click", function () {
            dom.validateName();
            dom.validateCity();
            dom.validateEmail();
        })
    },

    validateText(fieldId, errorId) {
        const textRegex = /^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆŠŽ∂ð ,.'-]+$/u;
        let field = document.getElementById(fieldId);
        let value = field.value;
        let errorField = document.getElementById(errorId);
        if (!textRegex.test(value)) {
            errorField.innerText = `Please enter a valid ${fieldId}!`;
        } else {
            errorField.innerText = "";
        }
    },

    validateName() {
        let fieldId = "name";
        let errorId = "nameError";
        validateText(fieldId, errorId);
    }
    ,

    validateCity() {
        let fieldId = "city";
        let errorId = "cityError";
        validateText(fieldId, errorId);
    }
    ,

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

}
;

dom.init();