var vmProductForm = new Vue({
    el: '#product-form',
    data: {
        form: {
            code: "",
            product: "",
            brand: 0,
            model: "",
            category: 0,
            comment: ""
        },
        imageList: [],
        categoryList: [],
        brandList: [],
        id: document.getElementById("product-data").value,
        mode: document.getElementById("mode-form").value !== null && document.getElementById("mode-form").value !== undefined ? document.getElementById('mode-form').value : "",
    },
    created() {
        this.getCategory();
        this.getBrand();
        if(this.mode==="edit"){
            this.callServiceById();
        }
    },
    computed: {
        imageLength() {
            return this.imageList.length;
        }
    },
    methods: {
        selectImage(e) {
            let file = e.target.files;
            if (file !== undefined && file.length > 0) {
                var pathImg = [];
                for (let i = 0; i < file.length; i++) {
                    var reader = new FileReader();
                    reader.readAsDataURL(file[i]);
                    reader.onload = async function (e) {
                        pathImg.push(await e.currentTarget.result);
                    };
                    reader.onerror = function (error) {
                        console.log('Error: ', error);
                    };
                }
                this.imageList = pathImg;
            }
        },

        getCategory() {
            $.ajax({
                url: "http://localhost:8080/categories",
                cache: false,
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
        getBrand() {
            $.ajax({
                url: "http://localhost:8080/brands",
                cache: false,
                headers: {
                    'Accept': 'application/json',
                },
                success: function (data) {
                    this.brandList = data;
                }.bind(this),
                error: function (data) {
                    console.log("error", data);
                }.bind(this)
            });
        },
        toListBytes() {
            let list = this.imageList;
            let newImgList = [];
            list.forEach(i => {
                newImgList.push(window.btoa(i));
            });
            return newImgList;
        },
        toImageData(imageList){
          let images = [];
          imageList.forEach(e => {
              images.push(window.atob(e));
          });
          return images;
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
                brand_id: formData.brand,
                category_id: formData.category,
                code: formData.code,
                description: formData.comment,
                eraser: 0,
                model: formData.model,
                pictures: window.btoa(this.imageList[0]),
                product_name: formData.product,
                user_id: 1
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
//                    window.location.href = "../products";
                }.bind(this),
                error: function (data) {
                    console.log("error", data);
                }.bind(this)
            });
        },
        saveMode() {
            let formData = this.form;
            let postRequest = {
                brand_id: formData.brand,
                category_id: formData.category,
                code: formData.code,
                description: formData.comment,
                eraser: 0,
                impuesto:10,
                model: formData.model,
                pictures: this.toListBytes(),
                product_name: formData.product,
                user_id: 1
            };
            console.log('postRequest: ', postRequest);
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/products",
                contentType: "application/json; charset=utf-8",
                dataType:"json",
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
        },
        callServiceById(){
            let uri = "http://localhost:8080/products/" + this.id;
            $.ajax({
                type:"GET",
                url: uri,
                headers: { 'Access-Control-Allow-Origin': "http://localhost:8081" },
                contentType: "application/json",
                success: function (data) {
                    this.setModelForm(data);
                }.bind(this),
                error: function (data) {
                    console.log("error", data);
                }.bind(this)
            });
        },
        setModelForm(rs){
            this.form.code = rs.code;
            this.form.product = rs.product_name;
            this.form.comment = rs.descripcion;
            this.form.brand = rs.marca.id;
            this.form.category = rs.categoria.id;
            this.imageList = this.toImageData(rs.imagenes);
        }
    }
});