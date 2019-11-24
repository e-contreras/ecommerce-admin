var vmCreditForm = new Vue({
	el:'#credit-note-form',
	data :{
        form:{
            comentario: "",
            destinatario: 0,
            fecEmision: undefined,
            numDocRelacionado: "",
            timbrado: undefined
        },
        id: document.getElementById("credit-note-data").value === "" ? 0 : document.getElementById("credit-note-data").value,
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
            let uri = "http://localhost:8080/credit_note/" + this.id;
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
        		comentario: formData.comentario,
                destinatario: parseInt(formData.destinatario),
                fec_emision: this.buildDate(formData.fecEmision),
                num_doc_relacionado: formData.numDocRelacionado,
                timbrado: parseInt(formData.timbrado)
            };
            console.log('putRequest: ', putRequest);
            $.ajax({
                type: "PUT",
                url: "http://localhost:8080/credit_note",
                contentType: "application/json",
                headers: {
                    accept: 'application/json',
                },
                data: putRequest,
                success: function (data) {
                    window.location.href = "../credit-note";
                }.bind(this),
                error: function (data) {
                    console.log("error", data);
                }.bind(this)
            });
        },
        saveMode() {
            let formData = this.form; 
            let postRequest = {
        		comentario: formData.comentario,
                destinatario: parseInt(formData.destinatario),
                fec_emision: this.buildDate(formData.fecEmision),
                num_doc_relacionado: formData.numDocRelacionado,
                timbrado: parseInt(formData.timbrado)
            };
            console.log('postRequest: ', postRequest);
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/credit_note",
                contentType: "application/json;charset=utf-8",
                dataType: "json",
                crossDomain: 'true',
                headers: {
                    Accept: 'application/json',
                },
                data: JSON.stringify(postRequest),
                success: function (data) {
                    console.log(data);
                    window.location.href = "../credit-note";
                }.bind(this),
                error: function (data) {
                    console.log("error", data);
                }.bind(this)
            });
        },
        setModelForm(data){
			this.form.comentario = data.comentario;
			this.form.destinatario = data.destinatario;
			this.form.fecEmision = data.fec_emision;
			this.form.numDocRelacionado = data.num_doc_relacionado;
			this.form.timbrado = data.timbrado;
        },
        buildDate:function(dateOld) {
            const separate = "-";
            const formatT = "T";
            const formatZ = "Z";
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
                let date = dateOld.split("-");
                day = date[2];
                month = date[1];
                year = date[0];
            }
            return year.concat(separate).concat(month).concat(separate).concat(day).concat(formatT).concat(time).concat(formatZ);
        }
    	
    }
});