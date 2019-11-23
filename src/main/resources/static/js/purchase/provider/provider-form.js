var vmProviderForm = new Vue({
    el: '#provider-form',
    data: {
        form: {
            firsName: "",
            lastName: "",
            businessName: "",
            docType: 0,
            birthDate: undefined,
            homeAddress: "",
            email: "",
            workAddress: "",
            telephone: 0,
        },
        documentList: [],
        id: document.getElementById("provider-data").value,
        mode: document.getElementById("mode-form").value !== null && document.getElementById("mode-form").value !== undefined ? document.getElementById('mode-form').value : ""
    },
    created() {
        if (this.mode === "edit") {
            this.callServiceById();
        }
        this.getDocumentList();
    },
    methods: {
        getSelectImage(e) {
            let file = e.target.files;
            if (file.length > 0) {
                var r = new FileReader();
                r.readAsDataURL(file[0]);
                r.onload = async function () {
                    this.image = await r.result;
                };
            }
        },

        getDocumentList() {
            $.ajax({
                url: "http://localhost:8080/document_types",
                headers: {
                    'Accept': 'application/json',
                    'Access-Control-Allow-Origin': 'http://localhost:8081',
                    'Access-Control-Allow-Credentials': true
                },
                success: function (data) {
                    this.documentList = data;
                }.bind(this),
                error: function (data) {
                    console.log("error", data);
                }.bind(this)
            });
        },
        buildDate: function (dateOld) {
            const separate = "-";
            const formatT = "T";
            const formatZ = "Z";
            let day;
            let month;
            let year;
            let time = new Date().toLocaleTimeString();
            if (dateOld instanceof Date) {
                day = dateOld.getDate().toString();
                month = (dateOld.getMonth() + 1).toString();
                year = dateOld.getFullYear().toString();
            } else {
                let date = dateOld.split("-");
                day = date[2];
                month = date[1];
                year = date[0];
            }
            return year.concat(separate).concat(month).concat(separate).concat(day).concat(formatT).concat(time).concat(formatZ);
        },
        callServiceById() {
            let uri = "http://localhost:8080/providers/" + this.id;
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
                    this.setModelForm(data);
                }.bind(this),
                error: function (data) {
                    console.log("error", data);
                }.bind(this)
            });
        },
        setModelForm(data) {
            this.form.firsName = data.name;
            this.form.lastName = data.lastname;
            this.form.businessName = data.business_name;
//            this.form.docType = data.;
            this.form.birthDate = data.birthday;
//            this.form.homeAddress = data.;
            this.form.email = data.email;
//            this.form.workAddress = data.;
            this.form.telephone = data.cellphone !== undefined ? data.cellphone : 0;
        },
        accordingToMode() {
            if (this.mode === "edit") {
                this.editMode();
            } else {
                this.saveMode();
            }
        },
        editMode() {
            let formData = this.form;
            let putRequest = {
                birthday: "2019-11-22T03:13:00.522Z",
                business_name: "string",
                cellphone: "string",
                document_number: "string",
                document_type_id: 0,
                email: "string",
                id: this.id,
                lastname: "string",
                name: "string",
                person: {
                    birthday: "2019-11-22T03:13:00.522Z",
                    business_name: "string",
                    cellphone: "string",
                    document_number: "string",
                    document_type_id: 0,
                    email: "string",
                    lastname: "string",
                    name: "string",
                    person_id: 0,
                    person_type_id: 0,
                    phone: "string"
                },
                person_id: 0,
                person_type_id: 0,
                phone: "string"
            };
            console.log('putRequest: ', putRequest);
            $.ajax({
                type: "PUT",
                url: "http://localhost:8080/products",
                contentType: "application/json",
                headers: {
                    accept: 'application/json',
                },
                data: putRequest,
                success: function (data) {
                    console.log(data);
//                    window.location.href = "../provider";
                }.bind(this),
                error: function (data) {
                    console.log("error", data);
                }.bind(this)
            });
        },
        saveMode() {
            let formData = this.form;
            let postRequest = {
                birthday: "2019-11-22T03:13:00.522Z",
                business_name: "string",
                cellphone: "string",
                document_number: "string",
                document_type_id: 0,
                email: "string",
                id: 0,
                lastname: "string",
                name: "string",
                person: {
                    birthday: "2019-11-22T03:13:00.522Z",
                    business_name: "string",
                    cellphone: "string",
                    document_number: "string",
                    document_type_id: 0,
                    email: "string",
                    lastname: "string",
                    name: "string",
                    person_id: 0,
                    person_type_id: 0,
                    phone: "string"
                },
                person_id: 0,
                person_type_id: 0,
                phone: "string"
            };
            console.log('postRequest: ', postRequest);
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/products",
                contentType: "application/json charset=utf-8",
                dataType: "json",
                crossDomain: true,
                headers: {
                    Accept: 'application/json',
                },
                data: JSON.stringify(postRequest),
                success: function (data) {
                    console.log(data);
//                    window.location.href = "../products";
                }.bind(this),
                error: function (data) {
                    console.log("error", data);
                }.bind(this)
            });
        }

    }
});