var list = [{rs: "3 V - INGENIERIA S.A.", ruc: "80019709-1", persona: "ADOLFO VERA ALDERETE - JUAN MANUEL VERA CAMPUZANO.", direccion: "R. I. 5 GRAL. DÍAZ N° 351 C/ CAMPO CERVERA.", email: "elvio@108.com", telefono: "021 665 177"},
    {rs: "3 V - INGENIERIA S.A.", ruc: "80019709-2", persona: "ADOLFO VERA ALDERETE - JUAN MANUEL VERA CAMPUZANO.", direccion: "R. I. 5 GRAL. DÍAZ N° 351 C/ CAMPO CERVERA.", email: "elvio@108.com", telefono: "021 665 177"},
{rs: "3 V - INGENIERIA S.A.", ruc: "80019709-3", persona: "ADOLFO VERA ALDERETE - JUAN MANUEL VERA CAMPUZANO.", direccion: "R. I. 5 GRAL. DÍAZ N° 351 C/ CAMPO CERVERA.", email: "elvio@108.com", telefono: "021 665 177"},
{rs: "3 V - INGENIERIA S.A.", ruc: "80019709-4", persona: "ADOLFO VERA ALDERETE - JUAN MANUEL VERA CAMPUZANO.", direccion: "R. I. 5 GRAL. DÍAZ N° 351 C/ CAMPO CERVERA.", email: "elvio@108.com", telefono: "021 665 177"},
{rs: "3 V - INGENIERIA S.A.", ruc: "80019709-5", persona: "ADOLFO VERA ALDERETE - JUAN MANUEL VERA CAMPUZANO.", direccion: "R. I. 5 GRAL. DÍAZ N° 351 C/ CAMPO CERVERA.", email: "elvio@108.com", telefono: "021 665 177"},
{rs: "3 V - INGENIERIA S.A.", ruc: "80019709-6", persona: "ADOLFO VERA ALDERETE - JUAN MANUEL VERA CAMPUZANO.", direccion: "R. I. 5 GRAL. DÍAZ N° 351 C/ CAMPO CERVERA.", email: "elvio@108.com", telefono: "021 665 177"},
{rs: "3 V - INGENIERIA S.A.", ruc: "80019709-7", persona: "ADOLFO VERA ALDERETE - JUAN MANUEL VERA CAMPUZANO.", direccion: "R. I. 5 GRAL. DÍAZ N° 351 C/ CAMPO CERVERA.", email: "elvio@108.com", telefono: "021 665 177"},
{rs: "3 V - INGENIERIA S.A.", ruc: "80019709-8", persona: "ADOLFO VERA ALDERETE - JUAN MANUEL VERA CAMPUZANO.", direccion: "R. I. 5 GRAL. DÍAZ N° 351 C/ CAMPO CERVERA.", email: "elvio@108.com", telefono: "021 665 177"},
{rs: "3 V - INGENIERIA S.A.", ruc: "80019709-9", persona: "ADOLFO VERA ALDERETE - JUAN MANUEL VERA CAMPUZANO.", direccion: "R. I. 5 GRAL. DÍAZ N° 351 C/ CAMPO CERVERA.", email: "elvio@108.com", telefono: "021 665 177"},
{rs: "3 V - INGENIERIA S.A.", ruc: "80019709-0", persona: "ADOLFO VERA ALDERETE - JUAN MANUEL VERA CAMPUZANO.", direccion: "R. I. 5 GRAL. DÍAZ N° 351 C/ CAMPO CERVERA.", email: "elvio@108.com", telefono: "021 665 177"},
{rs: "3 V - INGENIERIA S.A.", ruc: "80019709-1", persona: "ADOLFO VERA ALDERETE - JUAN MANUEL VERA CAMPUZANO.", direccion: "R. I. 5 GRAL. DÍAZ N° 351 C/ CAMPO CERVERA.", email: "elvio@108.com", telefono: "021 665 177"}]

var vmProvider = new Vue({
    el: '#provider-index',
    data: {
        listProvider: list,
        valuePages: [],
        itemPerPages: 10,
        currentPage: 1,
    },
    created() {
        this.filterItems();
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
                    'Accept': 'application/json',
                },
                success: function (data) {
                    console.log("success");
                    console.log(data);
                }.bind(this),
                error: function (data) {
                    console.log("error");
                    console.log(data);
                }.bind(this)
            });
        }

    }
})
