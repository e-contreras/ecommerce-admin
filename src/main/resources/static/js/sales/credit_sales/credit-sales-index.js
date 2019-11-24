var list = [{cod: 1, client: "BALA", ruc: "80019709-7", comment: "Cualquiera", reception_date: "12/10/20018 08:59"},
    {cod: 2, client: "ELVIO", ruc: "80075055-1", comment: "Ninguno", reception_date: "12/10/20018 08:59"}]

var vmCreditSales = new Vue({
    el: '#credit-sales-index',
    data: {
        listCreditSales: list,
        valuePages: [],
        itemPerPages: 10,
        currentPage: 1
    },
    created() {
        this.filterItems();
    },
    computed: {
        getLength() {
            return this.listCreditSales.length;
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
                    if (this.listCreditSales[index] != undefined) {
                        this.valuePages.push(this.listCreditSales[index]);
                    }
                }
            }
        },
        generatePDF(id) {
            window.open("http://localhost:8081/sales/credit-note/pdf/" + id, "Nota de crédito", "fullscreen=yes");
        }
    }
});