var vmProduactApp = new Vue({
    el: '#product-app-index',
    data: {
        listProduct: [],
        valuePages: [],
        itemPerPages: 10,
        currentPage: 1
    },
    created() {
        this.getProducts();
    },
    computed: {
        getLength() {
            return this.listProduct.length;
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
                    if (this.listProduct[index] != undefined) {
                        this.valuePages.push(this.listProduct[index]);
                    }
                }
            }
        },
        getProducts() {
            $.ajax({
                url: "http://localhost:8080/products",
                cache: false,
                headers: {
                    'Accept': 'application/json',
                },
                success: function (data) {
                    this.listProduct = data;
                    this.filterItems();
                }.bind(this),
                error: function (data) {
                    console.log("error", data);
                }.bind(this)
            });
        },
        toEdit(id){
            window.location = "./products/form-edit/".concat(id.toString());
        }
        
    }
});