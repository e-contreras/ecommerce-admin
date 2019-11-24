var vmDebitForm = new Vue({
	el:'#debit-note-form',
	data:{
		form:{
			comentario: "",
			numDocRelacionado: "",
			numDocumento: "",
			remitente: 0
		},
		id: document.getElementById("debit-note-data").value === "" ? 0 : document.getElementById("debit-note-data").value,
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
            let uri = "http://localhost:8080/debit_note/" + this.id;
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
                num_documento: formData.numDocumento,
                num_doc_relacionado: formData.numDocRelacionado,
                id:parseInt(this.id),
                remitente: parseInt(formData.remitente)
            };
            console.log('putRequest: ', putRequest);
            $.ajax({
                type: "PUT",
                url: "http://localhost:8080/debit_note",
                contentType: "application/json;charset=utf-8",
                headers: {
                    accept: 'application/json',
                },
                data: putRequest,
                success: function (data) {
                    window.location.href = "../debit-note";
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
                num_documento: formData.numDocumento,
                num_doc_relacionado: formData.numDocRelacionado,
                remitente: parseInt(formData.remitente)
            };
            console.log('postRequest: ', postRequest);
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/debit_note",
                contentType: "application/json;charset=utf-8",
                dataType: "json",
                crossDomain: 'true',
                headers: {
                    Accept: 'application/json',
                },
                data: JSON.stringify(postRequest),
                success: function (data) {
                    console.log(data);
                    window.location.href = "../debit-note";
                }.bind(this),
                error: function (data) {
                    console.log("error", data);
                }.bind(this)
            });
        },
		setModelForm(data){
			this.form.comentario = data.comentario;
			this.form.numDocRelacionado = data.num_doc_relacionado;
			this.form.numDocumento = data.numDocumento;
			this.form.remitente = data.remitente;
			this.id = data.id;
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