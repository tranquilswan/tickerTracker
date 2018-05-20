package Controller;

import Model.TransactionMockedData;
import Model.Transaction;
import Model.TransactionType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class TransactionRestController {

    public TransactionMockedData transMD = TransactionMockedData.getTransMD();

    @GetMapping("/transactions")
    public ArrayList<Transaction> index(){
        return transMD.fetchAllTransactions();
    }

    @GetMapping("/transactions/{id}")
    public Transaction getTransactionById(@PathVariable String id){
        return transMD.fetchTransactionById(Integer.parseInt(id));
    }

    //With post, what happens if the access modifiers are
    //changed from void to return the transaction object?
    @PostMapping("/transactions")
    public Transaction addTransaction(@PathVariable Map<String, String> newTransaction){
//        if(newTransaction.size() == 7){
            int transID = Integer.parseInt(newTransaction.get("id"));
            System.out.println("THE ID IS: " + transID);
            TransactionType transType;
            if(newTransaction.get("type").toUpperCase().equals("BUY")){
                transType = TransactionType.BUY;
            }else if(newTransaction.get("type").toUpperCase().equals("SELL")){
                transType = TransactionType.SELL;
            }else{
                transType = null;
            }

            int transNumOfShares = Integer.parseInt(newTransaction.get("numOfShares"));

            double transPricePerShare = Double.parseDouble(newTransaction.get("pricePerShare"));

            String transTickerSymbol = newTransaction.get("ticker").toUpperCase();

            double transCommission = Double.parseDouble(newTransaction.get("commission"));

            String transComments = newTransaction.get("comments");

            return transMD.recordNewTransaction(new Transaction(transID,transType,transNumOfShares,
                    transPricePerShare,transTickerSymbol,transCommission,transComments));
//        }

//        transMD.fetchTransactionById(newTransaction.get("id"))
    }

    //With post, what happens if the access modifiers are
    //changed from void to return the transaction object?
    @PostMapping("/transactions/search")
    public void searchTransaction(@PathVariable Map<String, String> transactionToSearch){

    }

//    //need the put to update transaction elements like price target
//    @PutMapping("/transaction/")
//    public Transaction updateTransaction(@PathVariable Map<String, String> transactionToUpdate){
//        return transMD.updateTransaction(transactionToUpdate);
//    }
}
