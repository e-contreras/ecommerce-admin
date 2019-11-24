var list =[{cod:1,provider:"3 V - INGENIERIA S.A.",ruc:"80019709-7",comment:"Cualqiera", reception_date:"12/10/20018 08:59"},
    {cod:2,provider:"4 HERMANOS S.R.L",ruc:"80075055-1", comment:"Ninguna", reception_date:"12/10/20018 08:59"}]

var vmCreditNote = new Vue({
    el:'#credit-note-index',
    data:{
        listCreditNote:list,
        valuePages: [],
        itemPerPages: 10,
        currentPage: 1,
        modalMessage:"",
        idNoteCredit:0
    },
    created(){
    	this.getCredits();
    },
    computed: {
        getLength() {
            return this.listCreditNote.length;
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
                    if (this.listCreditNote[index] !== undefined) {
                        this.valuePages.push(this.listCreditNote[index]);
                    }
                }
            }
        },
        getCredits(){
        	let uri = "http://localhost:8080/credit_note";
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
                    this.listCreditNote = data;
                    this.filterItems();
                }.bind(this),
                error: function (data) {
                    console.log("error", data);
                }.bind(this)
            });
        },
        toEdit(id){
            window.location = "./credit-note/form-edit/" + id;
        },
        
        setIdRemove(noteData) {
            this.idNoteCredit = noteData.id;
            this.modalMessage = "a este destinatario";
        },
        
        remove() {
            let uri = "http://localhost:8080/credit_note/" + this.idNoteCredit;
            $.ajax({
                type: 'DELETE',
                url: uri,
                success: function (res) {
                    window.setTimeout(function () {
                        window.location.href = "./credit-note";
                    }, 1000);
                }.bind(this),
                error: function (data) {
                    console.log("error", data);
                }.bind(this)
            });
        },
    }
});