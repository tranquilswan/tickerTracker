package ControllerTests;

import Controller.TransactionRestController;
import Model.Transaction;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TransactionCtrlTests {


    //test for get, post, put
    TransactionRestController restCtrl = new TransactionRestController();
    Transaction returnedTransaction;
    Map<String, String> passedTransaction = new HashMap<>();

    @Test
    public void getAllTrans() throws Exception {
        ArrayList<Transaction> returnedArray = restCtrl.index();
        assertEquals(4, returnedArray.size());
    }

    @Test
    public void getTransById() throws Exception{
        returnedTransaction = restCtrl.getTransactionById("2");
        assertEquals("APPL", returnedTransaction.getSymbol());
    }

    @Test
    public void makeNewTransaction() throws Exception{

        passedTransaction.put("type", "BUY");
        passedTransaction.put("numOfShares", "300");
        passedTransaction.put("pricePerShare", "37.23");
        passedTransaction.put("ticker", "XIV");
        passedTransaction.put("commission", "4.95");
        passedTransaction.put("comments","Testing Testing 1 2 3");

        Transaction newTrans = restCtrl.addTransaction(passedTransaction);
        assertEquals("XIV", newTrans.getSymbol());
    }

    @Test
    public void searchTransaction() throws Exception{
        passedTransaction.put("textToSearch", "INTC");
        ArrayList<Transaction> foundTrans = restCtrl.searchTransaction(passedTransaction);
        assertEquals(1, foundTrans.size());
    }

    @Test
    public void updateTransaction() throws Exception{
//        passedTransaction.put("id", "4");
        passedTransaction.put("priceTarget", "37.98");
        passedTransaction.put("ticker", "ACB");
        passedTransaction.put("comments", "updated comments");
        ArrayList<Transaction> listOfMatchingTransaction =
                restCtrl.updateTransaction(passedTransaction);
        for(Transaction trans : listOfMatchingTransaction){
            assertEquals("37.98", Double.toString(trans.getPriceTarget()));
            assertEquals("updated comments", trans.getComments());
        }
//        assertEquals("37.98", Double.toString(returnedTransaction.getPriceTarget()));
//        assertEquals("updated comments", returnedTransaction.getComments());
    }
}