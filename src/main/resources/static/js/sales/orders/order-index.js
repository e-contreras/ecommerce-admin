var list =[{cod:1,description:"Pedido de Placares",category:"Electrodméstico", fPublication:"12-03-19",state:"CERRADO"},
    {cod:2,description:"Pedido de Bicicletas",category:"Deportes", fPublication:"11-05-19",state:"ABIERTO"}];

var vmSalesOrders = new Vue({
    el: '#order-index',
    data: {
        listOrdersSales: list,
        valuePages: [],
        itemPerPages: 10,
        currentPage: 1
    },
    created(){
        this.filterItems();
    },
    computed: {
        getLength() {
            return this.listOrdersSales.length;
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
                    if (this.listOrdersSales[index] != undefined) {
                        this.valuePages.push(this.listOrdersSales[index]);
                    }
                }
            }
        },
        
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