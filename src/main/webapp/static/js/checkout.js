let validLastName = false;
let validFirstName = false;
let validEmail = false;
let validPassword = false;
let validPhoneNumber = false;

dom = {
    init: function () {
        this.addSameAddressListener();
        this.addDataValidationListeners();
        disableSubmit();
    },

    addDataValidationListeners() {
        let lastName = document.getElementById("lastName");
        lastName.addEventListener("blur", function () {
            dom.validateLastName();
        });
        let firstName = document.getElementById("firstName");
        firstName.addEventListener("blur", function () {
            dom.validateFirstName();
        });
        let email = document.getElementById("email");
        email.addEventListener("blur", function () {
            dom.validateEmail();
        });
        let passwordConfirmation = document.getElementById("passwordConfirmation");
        passwordConfirmation.addEventListener("blur", function () {
            dom.validatePassword();
        });
        let phoneNumber = document.getElementById("phoneNum");
        phoneNumber.addEventListener("blur", function () {
            dom.validatePhoneNumber();
        })
    },

    addSameAddressListener() {
        let sameAddressCheckbox = document.getElementById("sameAddress");
        let isChecked = sameAddressCheckbox.checked;
        sameAddressCheckbox.addEventListener("change", function () {
            isChecked = !isChecked;
            dom.sameAddress(isChecked);
        })
    },

    validateText(fieldId) {
        const textRegex = /^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆŠŽ∂ð ,.'-]+$/u;
        let field = document.getElementById(fieldId);
        let value = field.value;
        let errorField = document.getElementById(`${fieldId}Error`);
        if (!textRegex.test(value)) {
            if (fieldId === "lastName") {
                errorField.innerText = "Kérjük valós vezetéknevet adjon meg!";
                validLastName = false;
            } else if (fieldId === "firstName") {
                errorField.innerText = "Kérjük valós keresztnevet adjon meg!";
                validFirstName = false;
            }
            checkIfAllValid();
        } else {
            errorField.innerText = "";
            if (fieldId === "lastName") {
                validLastName = true;
            } else if (fieldId === "firstName") {
                validFirstName = true;
            }
            checkIfAllValid();
        }
    },

    validateLastName() {
        let lastNameFieldId = "lastName";
        dom.validateText(lastNameFieldId)
    },

    validateFirstName() {
        let firstNameFieldId = "firstName";
        dom.validateText(firstNameFieldId)
    },

    validateEmail() {
        const emailRegex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        let emailField = document.getElementById("email");
        let emailValue = emailField.value;
        let emailError = document.getElementById("emailError");
        if (!emailRegex.test(emailValue)) {
            emailError.innerText = "Kérjük valós email címet adjon meg!";
            validEmail = false;
            checkIfAllValid();
        } else {
            emailError.innerText = "";
            validEmail = true;
            checkIfAllValid();
        }
    },

    validatePassword() {
        let password = document.getElementById("password");
        let passwordConfirmation = document.getElementById("passwordConfirmation");
        let passwordError = document.getElementById("passwordError");
        if (!(password.value === passwordConfirmation.value)) {
            passwordError.innerText = "A megadott jelszavak nem egyeznek meg!";
            validPassword = false;
            checkIfAllValid();
        } else {
            passwordError.innerText = "";
            validPassword = true;
            checkIfAllValid();
        }
    },

    validatePhoneNumber() {
        const digitsRegex = /^\d+$/;
        let phoneNumber = document.getElementById("phoneNum");
        let phoneNumberError = document.getElementById("phoneNumError");
        if (!digitsRegex.test(phoneNumber.value)) {
            phoneNumberError.innerText = "Kérjük kizárólag számjegyeket adjon meg!";
            validPhoneNumber = false;
            checkIfAllValid();
        } else {
            phoneNumberError.innerText = "";
            validPhoneNumber = true;
            checkIfAllValid();
        }
    },

    sameAddress(isChecked) {
        let AddressDiv = document.getElementById("AddressDiv");
        if (isChecked) {
            AddressDiv.setAttribute("hidden", "hidden");
        } else {
            AddressDiv.removeAttribute("hidden");
        }
    }
};

function disableSubmit() {
    let submitBtn = document.getElementById("submitBtn");
    submitBtn.setAttribute("disabled", "disabled");
}

function enableSubmit() {
    let submitBtn = document.getElementById("submitBtn");
    submitBtn.removeAttribute("disabled");
}

function checkIfAllValid() {
    if (validLastName && validFirstName && validEmail && validPassword && validPhoneNumber) {
        enableSubmit();
    } else {
        disableSubmit();
    }
}

dom.init();
