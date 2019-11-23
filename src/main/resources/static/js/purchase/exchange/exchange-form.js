var vmExchange = new Vue({
    el:'#exchange-form',
    data:{
        form: {
            category: 0,
            startDate: undefined,
            endDate: undefined,
            status: 0,
            description:""
        },
        categoryList:[],
        profuctDataList:[],
        productList:[],
        stateList:[{key:1, value:"ABIERTO"},{key:2, value:"CREADO"},{key:3, value:"FINALIZADO"},{key:4, value:"CERRADO"}],
        id: document.getElementById("exchange-data").value === "" ? 0 : document.getElementById("exchange-data").value,
        mode: document.getElementById("mode-form").value !== null && document.getElementById("mode-form").value !== undefined ? document.getElementById('mode-form').value : ""
    },
    created() {
        if (this.mode === "edit") {
            this.callServiceById();
        }
        this.getProductList();
        this.getCategory();
    },
    methods : {
    	getCategory(){
    		$.ajax({
                url: "http://localhost:8080/categories",
                cache: true,
                headers: {
                    'Accept': 'application/json',
                },
                success: function (data) {
                    this.categoryList = data;
                }.bind(this),
                error: function (data) {
                    console.log("error", data);
                }.bind(this)
            });
    	},
    	getProductList(){
    		$.ajax({
                url: "http://localhost:8080/products",
                cache: true,
                headers: {
                    'Accept': 'application/json',
                },
                success: function (data) {
                	if(data !== undefined && data!==null){
                		this.profuctDataList = data; 
                		this.productList = this.productConverter(data);
                	}
                }.bind(this),
                error: function (data) {
                    console.log("error", data);
                }.bind(this)
            });
    	},
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
        		category: formData.category,
        	    description: formData.description,
        	    endDate: this.buildDate(formData.endDate),
        	    initDate: this.buildDate(formData.startDate),
        	    status: formData.status,
        	    products: this.loadProductForSave()
            };
            console.log('putRequest: ', putRequest);
            $.ajax({
                type: "PUT",
                url: "http://localhost:8080/buget/solicitude",
                contentType: "application/json",
                headers: {
                    accept: 'application/json',
                },
                data: putRequest,
                success: function (data) {
                	this.setData(data);
                    window.location.href = "../exchange";
                }.bind(this),
                error: function (data) {
                    console.log("error", data);
                }.bind(this)
            });
        },
        saveMode() {
            let formData = this.form; 
            let postRequest = {
        		category: formData.category,
        	    description: formData.description,
        	    endDate: this.buildDate(formData.endDate),
        	    initDate: this.buildDate(formData.startDate),
        	    status: formData.status,
        	    products: this.loadProductForSave()
            };
            console.log('postRequest: ', postRequest);
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/buget/solicitude",
                contentType: "application/json;charset=utf-8",
                dataType: "json",
                crossDomain: 'true',
                headers: {
                    Accept: 'application/json',
                },
                data: JSON.stringify(postRequest),
                success: function (data) {
                    console.log(data);
                    window.location.href = "../exchange";
                }.bind(this),
                error: function (data) {
                    console.log("error", data);
                }.bind(this)
            });
        },
        productConverter(data){
        	let newProductList = [];
        	data.forEach(e => {
        		let img = {id:e.id, name:e.product_name, img:e.imagenes.length > 0 && e.imagenes!==undefined?window.atob(e.imagenes[0]):undefined, select:false};
        		if(e.estado===0){
        			newProductList.push(img);
        		}
        	});
        	return newProductList;
        },
        loadProductForSave(){
        	let productlistRequest = [];
        	this.productList.forEach( e => {
        		if(e.select){
        			this.profuctDataList.forEach(p => {
        				if(p.id===e.id){
        					productlistRequest.push(p);
            			}
        			});
        		}
        	});
        	return productlistRequest;
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
        },
        setData(data){
        	data.products.forEach(e => {
        		this.productList.forEach)(p => {
        			if(e.id === p.id){
        				p.select = true;
        			}
        		});
        	});
			this.form.category: data.category,
			this.form.description: data.description,
			this.form.endDate: data.endDate,
			this.form.startDate: data.initDate,
			this.form.status: data.status,
        }
    }
});