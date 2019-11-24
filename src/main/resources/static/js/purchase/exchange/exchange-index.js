var vmExchange = new Vue({
    el: '#exchange-index',
    data: {
        listExchange: [],
        valuePages: [],
        itemPerPages: 10,
        currentPage: 1,
        modalMessage:"",
        idExchange:0
    },
    created() {
        
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
                    if (this.listExchange[index] !== undefined) {
                        this.valuePages.push(this.listExchange[index]);
                    }
                }
            }
        },
        getBugetSolicitude(){
        	let uri = "http://localhost:8080/buget/solicitude";
        	$.ajax({
                url: uri,
                headers: {
                    'Accept': 'application/json',
                    'Access-Control-Allow-Origin': 'http://localhost:8081',
                    'Access-Control-Allow-Methods': 'OPTIONS,POST,GET',
                    'Access-Control-Request-Method': 'GET',
                    'Access-Control-Allow-Credentials': 'true',
                    'Access-Control-Allow-Headers': 'X-PINGOTHER'
                },
                success: function (data) {
                    this.listExchange = data;
                    this.filterItems();
                }.bind(this),
                error: function (data) {
                    console.log("error", data);
                }.bind(this)
            });
        	
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
        },
        
        toEdit(id){
            window.location = "./exchange/form-edit/" + id;
        },
        
        setIdRemove(exchange) {
            this.idExchange = exchange.id;
            this.modalMessage = "el " + exchange.description;
        },
        
        remove() {
            let uri = "http://localhost:8080/buget/solicitude/" + this.idExchange;
            $.ajax({
                type: 'DELETE',
                url: uri,
                success: function (res) {
                    window.setTimeout(function () {
                        window.location.href = "./exchange";
                    }, 1000);
                }.bind(this),
                error: function (data) {
                    console.log("error", data);
                }.bind(this)
            });
        }
    }
});
