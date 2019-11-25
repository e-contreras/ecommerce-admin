var vmAssignPacking = new Vue({
    el: '#assign-packing-index',
    data: {
        listSolicitudes: [],
        driverList:[],
        valuePages: [],
        vehiclesList:[],
        itemPerPages: 3,
        currentPage: 1,
        modalMessage: "",
        idAssingPacking: 0

    },
    created() {
        this.getDriverAndVehicles();
        this.getSolicitudes();
    },
    computed: {
        getLength() {
            return this.listSolicitudes.length;
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
    methods: {
        getSolicitudes(){
            $.ajax({
                url: "http://localhost:8080/solicitude",
                cache: false,
                headers: {
                    'Accept': 'application/json',
                },
                success: function (res) {
                    this.listSolicitudes = res;
                    this.filterItems();
                }.bind(this),
                error: function (data) {
                    console.log("error", data);
                }.bind(this)
            });
        },
        getDriverAndVehicles(){
            $.ajax({
                url: "http://localhost:8080/driver",
                cache: false,
                headers: {
                    'Accept': 'application/json',
                },
                success: function (res) {
                    this.driverList = res;
                }.bind(this),
                error: function (data) {
                    console.log("error", data);
                }.bind(this)
            });
            $.ajax({
                url: "http://localhost:8080/vehicles",
                cache: false,
                headers: {
                    'Accept': 'application/json',
                },
                success: function (res) {
                    this.vehiclesList = res;
                }.bind(this),
                error: function (data) {
                    console.log("error", data);
                }.bind(this)
            });
        },
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
                    if (this.listSolicitudes[index] !== undefined) {
                        this.valuePages.push(this.listSolicitudes[index]);
                    }
                }
            }
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
        toDate(date){
            let dateOld = new Date(date);
            const separate = "-";
            const space = " ";
            let day;
            let month;
            let year;
            let time = new Date().toLocaleTimeString();
            if(dateOld === undefined){
            	dateOld = new Date();
        	}
            if (dateOld instanceof Date) {
                day = dateOld.getDate().toString();
                month = (dateOld.getMonth() + 1).toString();
                year = dateOld.getFullYear().toString();
            } else {

                let date = dateOld.toLocaleString().split("/");
                day = date[2];
                month = date[1];
                year = date[0];
            }
            return year.concat(separate).concat(month).concat(separate).concat(day).concat(space).concat(time);
        },

        remove() {
            let uri = "http://localhost:8080/solicitude/" + this.idAssingPacking;
            $.ajax({
                type: 'DELETE',
                url: uri,
                success: function (res) {
                    window.setTimeout(function () {
                        window.location.href = "./assign-packing";
                    }, 1000);
                }.bind(this),
                error: function (data) {
                    console.log("error", data);
                }.bind(this)
            });
        }
    }
});