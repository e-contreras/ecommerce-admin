var vmAssignPacking = new Vue({
    el: '#assign-packing-index',
    data: {
        listSolicitudes: []
    },
    created() {
        this.getSolicitudes();
    },
    computed: {
        
    },
    methods: {
        getSolicitudes(){
            $.ajax({
                url: "http://localhost:8080/solicitude",
                cache: false,
                headers: {
                    'Accept': 'application/json',
                },
                success: function (res) {
                    this.listSolicitudes = res.data;
                    console.log(listSolicitudes);
                    this.filterItems();
                }.bind(this),
                error: function (data) {
                    console.log("error", data);
                }.bind(this)
            });
        },
        filterItems(){
            console.log(this.listSolicitudes)
        }
    }
});