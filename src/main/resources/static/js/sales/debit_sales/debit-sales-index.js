var vmDebitSales = new Vue({
    el:'#debit-sales-index',
    data:{
        listDebitSales: [],
        valuePages: [],
        itemPerPages: 10,
        currentPage: 1
    },
    created(){
        this.getDebitSales();
    },
    computed: {
        getLength() {
            return this.listDebitSales.length;
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
                    if (this.listDebitSales[index] != undefined) {
                        this.valuePages.push(this.listDebitSales[index]);
                    }
                }
            }
        },
        getDebitSales() {
            $.ajax({
                url: "http://localhost:8080/debit_note",
                cache: false,
                headers: {
                    'Accept': 'application/json',
                },
                success: function (data) {
                    this.listDebitSales = data;
                    console.log(data);
                    this.filterItems();
                }.bind(this),
                error: function (data) {
                    console.log("error", data);
                }.bind(this)
            });
        },
        
        generatePDF(id) {
            window.open("http://localhost:8081/sales/debit-note/pdf/" + id, "Nota de crédito", "fullscreen=yes");
        }        
    }
});