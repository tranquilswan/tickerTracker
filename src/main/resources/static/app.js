var url = "http://localhost:8080/transactions";

var formFunction = new Vue({
    el: "#transEntryForm",
    data: {
        trans : {
            ticker              :   "CAT",
            type                :   "BUY",
            pricePerShare       :   "0.00",
            numOfShares         :   "000",
            commission          :   "4.95",
            comments            :   "Test"
        }
    },
    methods: {
        submitTransaction : function(){
            console.log(this.trans);
            axios.post(url,this.trans)
            .then((response) =>{
                console.log("Data Posted");
                displayForm.retrieveTransaction();
                this.trans = {};
            })
            .catch(e => {
                console.log("there was an error" + e);
            })
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
                console.log("get complete");
            })
            console.log("trans: " + this.$data.transactions)
        }
    },
    beforeMount(){
        this.retrieveTransaction();
    }
});