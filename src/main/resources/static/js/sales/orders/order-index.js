var list =[{cod:1,description:"Pedido de Placares",category:"Electrodméstico", fPublication:"12-03-19",state:"CERRADO"},
    {cod:2,description:"Pedido de Bicicletas",category:"Deportes", fPublication:"11-05-19",state:"ABIERTO"}];

var vmSalesOrders = new Vue({
    el: '#order-index',
    data: {
        listOrdersSales: list
    },
    created() {

    },
    computed: {

    },
    methods: {
        classState: function (state) {
            switch (state.toUpperCase()) {
                case "CERRADO":
                    return "label-warning";
                case "ABIERTO":
                    return "label-primary";
                case "CERRADO":
                    return "label-default";
                case "FINALIZADO":
                    return "label-success";
                default:
                    return "";
            }
        }
    }
});