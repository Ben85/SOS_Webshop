

// Initializer:
const Initializers = {
    initalizePaypal: () => {
        const CREATE_PAYMENT_URL  = '/paypal/create-payment';
        const EXECUTE_PAYMENT_URL = '/paypal/execute-payment';

        paypal.Button.render({
            env: 'sandbox',
            commit: true,
            style: {
                color: 'gold',
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
                }).then(function() {

                });
            },

            onCancel: function (data, actions) {
                /*
                 * Buyer cancelled the payment
                 */
            },

            onError: function (err) {
                /*
                 * An error occurred during the transaction
                 */
            }
        }, '#paypal-button');
    }
};

