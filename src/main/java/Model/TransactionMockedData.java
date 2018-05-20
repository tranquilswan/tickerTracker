package Model;

import java.util.ArrayList;
import java.util.Map;

public class TransactionMockedData {

    ArrayList<Transaction> transData;
    //create a singleton
    private static TransactionMockedData instance = null;
    public static TransactionMockedData getTransMD() {
        if(instance == null){
            return instance = new TransactionMockedData();
        }else{
            return instance;
        }
    }

    //create a constructor preloaded with mocked transaction
    public TransactionMockedData(){
        transData = new ArrayList<>();
        transData.add(new Transaction(1,TransactionType.BUY, 100,
                50.12,"INTC",4.95,"Yuge upside"));
        transData.add(new Transaction(2, TransactionType.BUY, 1000,
                177.19, "APPL", 4.95,"Good for Buffet, good for me"));
        transData.add(new Transaction(3,TransactionType.BUY,1000,
                30.43,"XSP",4.95,"Best decision"));
        transData.add(new Transaction(4,TransactionType.SELL,100,
                10.32,"ACB",4.95,"short ACB"));
    }

    //the functions to add,retrieve,search transactions
    public ArrayList<Transaction> fetchAllTransactions(){
        return transData;
    }

    public Transaction fetchTransactionById(int id){
//        transData.forEach(trans->{
//            if(trans.getTransactionID() == id){
//                return trans;
//            }
//        });
        for(Transaction trans : transData){
            if(trans.getTransactionID() == id){
                return trans;
            }
        }
        return null;
    }

    public Transaction recordNewTransaction(Transaction newTrans){
        transData.add(newTrans);
        return newTrans;
    }

//    public Transaction updateTransaction(Map<String,String> updateTrans){
//        //the transaction has to have the ID supplied. and then just the price target
//        //should also be able to update comments. I suppose comments should be in some sort of
//        //array list, to retain a comment history
//        int id = Integer.parseInt(updateTrans.get("id"));
//        double priceTarget = Double.parseDouble(updateTrans.get("priceTarget"));
//        Transaction updatedTrans = this.fetchTransactionById(id);
//        updatedTrans.setPriceTarget(priceTarget);
//        return updatedTrans;
//    }

}