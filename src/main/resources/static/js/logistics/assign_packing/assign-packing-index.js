var list = [{quality: 1, product: "Tv LG 32 LED HD", cod: 10001, description: "TV de 32 pulgadas"},
    {quality: 1, product: "Play Station 5", cod: 100012, description: "Consola Play Station 5 - 2 Tb"}]

var vmAssignPacking = new Vue({
    el: '#assign-packing-index',
    data: {
        forClient: {
            name: 'Helena Wayne',
            address: 'Féliz Pérez Cardozo 123',
            street: 'Lambaré, San Isidro',
            telephone: '(555) 539-1037',
            email: 'helena.wayne@batman.com'
        },
        pendingInvoice: {
            id: '4F3S8J',
            paymentDate: '2/22/2014',
            account: '968-34567'
        },
        listProduct: list
    },
    created() {

    },
    computed: {

    },
    methods: {

    }
});