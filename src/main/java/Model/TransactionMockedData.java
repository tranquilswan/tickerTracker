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

        for(Transaction trans : transData){
            if(trans.getTransactionID() == id){
                return trans;
            }
        }
        return null;
    }

    public Transaction recordNewTransaction(Map<String, String> newTrans){
        //include logic to make sure that the user can't add the same id multiple time
        //ID should be auto-incrementing, not userProvided

        int transID = transData.size() + 1;

        TransactionType transType;
        if(newTrans.get("type").toUpperCase().equals("BUY")){
            transType = TransactionType.BUY;
        }else if(newTrans.get("type").toUpperCase().equals("SELL")){
            transType = TransactionType.SELL;
        }else{
            transType = null;
        }

        int transNumOfShares = Integer.parseInt(newTrans.get("numOfShares"));

        double transPricePerShare = Double.parseDouble(newTrans.get("pricePerShare"));

        String transTickerSymbol = newTrans.get("ticker").toUpperCase();

        double transCommission = Double.parseDouble(newTrans.get("commission"));

        String transComments = newTrans.get("comments");

        if(newTrans.containsKey("priceTarget")){
            double transPriceTarget = Double.parseDouble(newTrans.get("priceTarget"));
            transData.add(new Transaction(transID,transType,transNumOfShares,
                    transPricePerShare,transTickerSymbol,transCommission,transComments,transPriceTarget));
        }else{
            transData.add(new Transaction(transID,transType,transNumOfShares,
                    transPricePerShare,transTickerSymbol,transCommission,transComments));
        }

        //if the size of the list is 5, the largest index is 4.
        //kinda shitty, but...
        return transData.get(transData.size()-1);
    }

    public ArrayList<Transaction> searchTransaction(String searchTerm){
        ArrayList<Transaction> matchedTransaction = new ArrayList<>();
        for (Transaction trans : transData){
            if(trans.getSymbol().contains(searchTerm) ||
                    trans.getComments().contains(searchTerm)){
                matchedTransaction.add(trans);
            }
        }
        return matchedTransaction;
    }

    public ArrayList<Transaction> updateTransaction(Map<String,String> newTransParams){
        //the transaction has to have the ID supplied. and then just the price target
        //should also be able to update comments.
        //I suppose comments should be in some sort of arrayList, to retain a comment history
        String comments = null;
        String ticker = null;
        double priceTarget;

        ticker = newTransParams.get("ticker");

        ArrayList<Transaction> listOfUpdatedTrans = this.searchTransaction(ticker);

        for(Transaction trans : listOfUpdatedTrans){
            if(newTransParams.containsKey("priceTarget")){
                priceTarget = Double.parseDouble(newTransParams.get("priceTarget"));
                trans.setPriceTarget(priceTarget);
            }

            if(newTransParams.containsKey("comments")){
                comments = newTransParams.get("comments");
                trans.setComments(comments);
            }
        }

        return listOfUpdatedTrans;
    }

}