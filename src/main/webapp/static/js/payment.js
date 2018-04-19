

// Initializer:
const Initializers = {
    initalizePaypal: () => {
        const CREATE_PAYMENT_URL  = '/paypal/create-payment';
        const EXECUTE_PAYMENT_URL = '/paypal/execute-payment';

        paypal.Button.render({
            env: 'production',

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
                    paymentID: data.paymentID,
                    payerID:   data.payerID
                }).then(function() {

                    // The payment is complete!
                    // You can now show a confirmation message to the customer
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

(() => {
    Initializers.initalizePaypal();
})();