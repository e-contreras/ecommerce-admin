var vmProviderForm = new Vue({
    el: '#provider-form',
    data: {
        form: {
            firsName: "",
            lastName: "",
            businessName: "",
            docType: "",
            birthDate: undefined,
            homeAddress: "",
            email: "",
            workAddress: "",
            telephone: 0,
            image: undefined
        },
        image: undefined
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

        saveProvider(){
            
        }

    }
});