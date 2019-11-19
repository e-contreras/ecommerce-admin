var list =[{order:1,client:"Bala",ruc:"5416252-2", orderDate:"12/10/20018 08:59",deliveryDateAgreed:"12/10/20018 08:59", driver:"Alfred Pennynworth", vehicle:"Mercedes"},
    {order:2,client:"Enrrique",ruc:"5416255-2", orderDate:"12/10/20018 08:59",deliveryDateAgreed:"12/10/20018 08:59", driver:"Alfred Pennynworth", vehicle:"BMW"}];

var vmReferralNote = new Vue({
    el: '#referral-note-index',
    data: {
        listReferralNote: list
    },
    created() {

    },
    computed: {

    },
    methods: {
    }
});