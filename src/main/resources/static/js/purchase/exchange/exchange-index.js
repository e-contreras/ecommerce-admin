var list = [{cod: 1, description: "Pedido de Placares", category: "Electrodméstico", fPublication: "12-03-19", state: "CERRADO"},
    {cod: 2, description: "Pedido de Bicicletas", category: "Deportes", fPublication: "11-05-19", state: "ABIERTO"},
    {cod: 3, description: "Pedido de Bicicletas", category: "Deportes", fPublication: "11-05-19", state: "CERRADO"},
    {cod: 4, description: "Pedido de Bicicletas", category: "Deportes", fPublication: "11-05-19", state: "CREADO"},
    {cod: 5, description: "Pedido de Bicicletas", category: "Deportes", fPublication: "11-05-19", state: "FINALIZADO"},
    {cod: 6, description: "Pedido de Bicicletas", category: "Deportes", fPublication: "11-05-19", state: "CREADO"},
    {cod: 7, description: "Pedido de Bicicletas", category: "Deportes", fPublication: "11-05-19", state: "ABIERTO"},
    {cod: 8, description: "Pedido de Bicicletas", category: "Deportes", fPublication: "11-05-19", state: "FINALIZADO"},
    {cod: 9, description: "Pedido de Bicicletas", category: "Deportes", fPublication: "11-05-19", state: "ABIERTO"},
    {cod: 10, description: "Pedido de Bicicletas", category: "Deportes", fPublication: "11-05-19", state: "CREADO"},
    {cod: 11, description: "Pedido de Bicicletas", category: "Deportes", fPublication: "11-05-19", state: "ABIERTO"}]

var vmExchange = new Vue({
    el: '#exchange-index',
    data: {
        listExchange: list,
        valuePages: [],
        itemPerPages: 10,
        currentPage: 1
    },
    created() {
        this.filterItems();
    },
    computed: {
        getLength() {
            return this.listExchange.length;
        },
        countPages() {
            return Math.ceil(this.getLength / this.itemPerPages);
        },
        pages() {
            let pages = [];
            for (let index = 0; index < this.countPages; index++) {
                pages.push(index + 1);
            }
            return pages;
        }
    },

    watch: {
        currentPage(pageValue) {
            this.goToPage(pageValue);
        }
    },
    methods: {
        goToPage: function (page) {
            this.currentPage = page;
            this.filterItems();
        },

        filterItems: function () {
            var indexTo = this.currentPage * this.itemPerPages;
            var indexFrom = indexTo - this.itemPerPages;
            this.valuePages = [];
            for (var index = indexFrom; index < indexTo; index++) {
                if (index < this.getLength) {
                    if (this.listExchange[index] != undefined) {
                        this.valuePages.push(this.listExchange[index]);
                    }
                }
            }
        },

        classState: function (state) {
            switch (state.toUpperCase()) {
                case "CERRADO":
                    return "label-danger";
                case "ABIERTO":
                    return "label-primary";
                case "CREADO":
                    return "label-info";
                case "FINALIZADO":
                    return "label-success";
                default:
                    return "";
            }
        }
    }
});
