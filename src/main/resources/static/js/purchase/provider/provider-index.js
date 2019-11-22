var vmProvider = new Vue({
    el: '#provider-index',
    data: {
        listProvider: [],
        valuePages: [],
        itemPerPages: 10,
        currentPage: 1,
        modalMessage:"",
        idProduct:0
    },
    created() {
        this.getProvider();
    },
    computed: {
        getLength() {
            return this.listProvider.length;
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
                    if (this.listProvider[index] != undefined) {
                        this.valuePages.push(this.listProvider[index]);
                    }
                }
            }
        },

        getProvider() {
            $.ajax({
                url: "http://localhost:8080/providers",
                cache: false,
                headers: {
                    Accept: 'application/json',
                },
                success: function (data) {
                    this.listProvider = data;
                    this.filterItems();
                    console.log(data);
                }.bind(this),
                error: function (data) {
                    console.log("error");
                    console.log(data);
                }.bind(this)
            });
        },
        toEdit(id){
            window.location = "./provider/form-edit/" + id;
        },
        setIdRemove(provider) {
            this.idProduct = provider;
            this.modalMessage = "a " + provider.name+" "+provider.lastname;
        },
        remove() {
            let uri = "http://localhost:8080/providers/" + this.idProduct.id;
            $.ajax({
                type: 'DELETE',
                url: uri,
                success: function (res) {
                    window.setTimeout(function () {
                        window.location.href = "./provider";
                    }, 1000);
                }.bind(this),
                error: function (data) {
                    console.log("error", data);
                }.bind(this)
            });
        },

    }
});