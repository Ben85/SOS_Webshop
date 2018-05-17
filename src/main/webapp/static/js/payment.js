

// Initializer:
const Initializers = {
    initalizePaypal: () => {
        const CREATE_PAYMENT_URL  = '/paypal/create-payment';
        const EXECUTE_PAYMENT_URL = '/paypal/execute-payment';

        const SUCCESS_URL = "/message?message-id=2";
        const CANCEL_URL = "/message?message-id=1";
        const ERROR_URL = "/message?message-id=0";

        paypal.Button.render({
            env: 'sandbox',
            commit: true,
            style: {
                color: 'blue',
                size: 'small'
            },

            payment: function (data, actions) {
                return paypal.request.post(CREATE_PAYMENT_URL).then(function(data) {
                    return data.id;
                });
            },

            onAuthorize: function (data, actions) {
                return paypal.request.post(EXECUTE_PAYMENT_URL, {
                    paymentId: data.paymentID,
                    payerId:   data.payerID
                }).then((data) => {
                    window.location.href = data.success ? SUCCESS_URL : ERROR_URL;
                })
            },

            onCancel: function (data, actions) {
                window.location.href = CANCEL_URL;
            },

            onError: function (err) {
                window.location.href = ERROR_URL;
            }
        }, '#paypal-button');
    }
};

Initializers.initalizePaypal();

