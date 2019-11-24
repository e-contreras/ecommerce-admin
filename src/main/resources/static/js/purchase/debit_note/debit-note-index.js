var vmDebitNote = new Vue({
    el:'#debit-note-index',
    data:{
        listDebitNote:[],
        valuePages: [],
        itemPerPages: 10,
        currentPage: 1,
        idNoteDebit:0,
        modalMessage:""
    },
    created(){
        this.getDebits();
    },
    computed: {
        getLength() {
            return this.listDebitNote.length;
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
                    if (this.listDebitNote[index] != undefined) {
                        this.valuePages.push(this.listDebitNote[index]);
                    }
                }
            }
        },
        getDebits() {
            let uri = "http://localhost:8080/debit_note";
            $.ajax({
                url: uri,
                headers: {
                    'Accept': 'application/json',
                    'Access-Control-Allow-Origin': 'http://localhost:8081',
                    'Access-Control-Allow-Methods': 'OPTIONS,POST,GET',
                    'Access-Control-Allow-Credentials': 'true',
                    'Access-Control-Allow-Headers': 'X-PINGOTHER'
                },
                success: function (data) {
                    if(data!==undefined){
                        this.listDebitNote = data;
                        this.filterItems();
                    }else{
                        this.listDebitNote.push({comentario: "string",id: 0,num_doc_relacionado: "string",num_documento: "string",remitente: 0});
                        this.filterItems();
                    }
                }.bind(this),
                error: function (data) {
                    console.log("error", data);
                }.bind(this)
            });
        },
        toEdit(id) {
            window.location = "./debit-note/form-edit/" + id;
        },

        setIdRemove(debitData) {
            this.modalMessage = "este registro";
            this.idNoteDebit = debitData.id;
        },

        remove() {
            let uri = "http://localhost:8080/debit_note/" + this.idNoteDebit;
            $.ajax({
                type: 'DELETE',
                url: uri,
                success: function (res) {
                    if(res){
                        window.setTimeout(function () {
                            window.location.href = "./debit-note";
                        }, 1000);
                    }
                }.bind(this),
                error: function (data) {
                    console.log("error", data);
                }.bind(this)
            });
        }
    }
});