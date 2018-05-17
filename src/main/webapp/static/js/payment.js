

// Initializer:
const Initializers = {
    initalizePaypal: () => {
        const CREATE_PAYMENT_URL  = '/paypal/create-payment';
        const EXECUTE_PAYMENT_URL = '/paypal/execute-payment';

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
                }).then(() => {
                    window.location.href = "/message?message-id=2"
                })
            },

            onCancel: function (data, actions) {
                window.location.href = "/message?message-id=1"
            },

            onError: function (err) {
                window.location.href = "/message?message-id=0"
            }
        }, '#paypal-button');
    }
};

Initializers.initalizePaypal();

