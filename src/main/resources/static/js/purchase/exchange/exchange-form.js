var vmExchange = new Vue({
    el:'#',
    data:{
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
        id: document.getElementById("exchange-data").value,
        mode: document.getElementById("mode-form").value !== null && document.getElementById("mode-form").value !== undefined ? document.getElementById('mode-form').value : ""
    },
    created() {
        if (this.mode === "edit") {
            this.callServiceById();
        }
    },
    methods : {
        accordingToMode() {
            if (this.mode === "edit") {
                this.editMode();
            } else {
                this.saveMode();
            }
        },
        callServiceById() {
            let uri = "http://localhost:8080/excange/" + this.id;
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
        editMode() {
            let formData = this.form;
            let putRequest = {
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
            console.log('putRequest: ', putRequest);
            $.ajax({
                type: "PUT",
                url: "http://localhost:8080/exchange",
                contentType: "application/json",
                headers: {
                    accept: 'application/json',
                },
                data: putRequest,
                success: function (data) {
                    console.log(data);
//                    window.location.href = "../exchange";
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
                url: "http://localhost:8080/exchange",
                contentType: "application/json charset=utf-8",
                dataType: "json",
                crossDomain: 'true',
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