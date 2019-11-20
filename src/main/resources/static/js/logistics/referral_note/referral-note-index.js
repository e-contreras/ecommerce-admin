var list =[{order:1,client:"Bala",ruc:"5416252-2", orderDate:"12/10/20018 08:59",deliveryDateAgreed:"12/10/20018 08:59", driver:"Alfred Pennynworth", vehicle:"Mercedes"},
    {order:2,client:"Enrrique",ruc:"5416255-2", orderDate:"12/10/20018 08:59",deliveryDateAgreed:"12/10/20018 08:59", driver:"Alfred Pennynworth", vehicle:"BMW"}];

var vmLogisticsReferralNote = new Vue({
    el: '#referral-note-index',
    data: {
        listReferralNote: list,
        valuePages: [],
        itemPerPages: 10,
        currentPage: 1
    },
    created(){
        this.filterItems();
    },
    computed: {
        getLength() {
            return this.listReferralNote.length;
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
                    if (this.listReferralNote[index] != undefined) {
                        this.valuePages.push(this.listReferralNote[index]);
                    }
                }
            }
        }
    }
});