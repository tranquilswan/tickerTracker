var url = "http://localhost:8080/transactions";

var formFunction = new Vue({
    el: "#transEntryForm",
    data: {
        trans : {
            ticker  :   "CAT",
            type    :   "BUY",
            transactionPrice    :   "0.00",
            numOfShares :   "000",
            commission  :   "4.95",
            comments    :   "Test"
        }
    },
    methods: {
        submitTransaction : function(){
            console.log(this.$data.trans);
            axios.post(url,this.trans).then(
                function(response){
                  console.log(response)
                }
            )
//      and this function should call the retrieveFunction in displayForm VUE.
        }
    }
});

var displayForm = new Vue({
    el: "#displayForm",
    data : {
        transactions : []
    },
    methods:{
        retrieveTransaction : function(){
            console.log("in retTrans");
            axios.get(url).then((response) => {
                console.log(response.data);
                this.transactions = response.data;
                console.log("done...?");
            })
            console.log("trans: " + this.$data.transactions)
        }
    }
});