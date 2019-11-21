var vmBook = new Vue({
    el:'#book-index',
    data:{
        listBook:undefined,
        from:"",
        to:""
    },
    created(){
        
    },
    computed: {
        
    },
    methods: {
        toDay(){
            let date = new Date();
            return date.getFullYear().toString().concat("-").concat((date.getMonth()+1).toString()).concat("-").concat(date.getDate().toString());
        }
    
    }
});