package Controller;

import Model.TransactionMockedData;
import Model.Transaction;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public Transaction addTransaction(@RequestBody Map<String, String> newTransaction){
        return transMD.recordNewTransaction(newTransaction);
    }

    //Should be able to search with other term
    //id doesn't make sense here. How would one remember the ID for a specific transaction?
    //and we already have a get by ID method.
    @PostMapping("/transactions/search")
    public ArrayList<Transaction> searchTransaction(@RequestBody Map<String, String> searchTerm){
        return transMD.searchTransaction(searchTerm.get("textToSearch"));
    }

    //need the put to update transaction elements like price target
    @PutMapping("/transactions")
    public ArrayList<Transaction> updateTransaction(@RequestBody Map<String, String> transactionToUpdate){
        return transMD.updateTransaction(transactionToUpdate);
    }
}
