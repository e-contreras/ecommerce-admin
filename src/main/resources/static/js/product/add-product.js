//function readURL(input) {
//    if (input.files && input.files[0]) {
//        var reader = new FileReader();
//
//        reader.onload = function(e) {
//            if($('#imagePreview img').length){
//                $('#imagePreview img').attr("src", e.target.result);
//            }else{
//                $('#imagePreview').append($("<img>",{
//                    src: e.target.result,
//                    class: "img-responsive pad"
//                }));
//            }
//
//        }
//
//        reader.readAsDataURL(input.files[0]);
//    }
//}
//
//$("#imgupload").change(function() {
//    readURL(this);
//});
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
    },
    created() {
        this.getCategory();
        this.getBrand();
    },
    computed: {
        imageLength() {
            return this.imageList.length;
        },
    },
    methods: {
        saveProduct() {
            let formData = this.form;
            let postRequest = {
                bar_code: formData.brand,
                category_id: formData.category,
                code: formData.code,
                description: formData.comment,
                eraser: 0,
                model: formData.model,
                pictures: this.toListBytes(),
                product_name: formData.product,
                user_id: null
            };
            console.log('postRequest: ', postRequest);
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/products",
                headers: {
                    'Accept': 'application/json',
                },
                data: postRequest,
                success: function (data) {
                    window.location.href = "../products";
                }.bind(this),
                error: function (data) {
                    console.log("error", data);
                }.bind(this)
            });
        },
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
        }
    }
});