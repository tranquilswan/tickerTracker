package Model;

import static Model.TransactionType.BUY;

public class Transaction {

    private int transactionID;
    private int numberOfShares;
    //removing individual 'buyPrice' and 'sellPrice' variables and replacing them with one transPrice.
    //doesn't make sense to have buy and sell price for every transaction. and we already have transType enum
    private double transactionPricePerShare;
    private String symbol;
    private double priceTargetPerShare;
    private double commission;
    private TransactionType transactionType;
    private String comments;
    //Transaction total would have to be a calculated field
    private double transactionTotal;

    /*
        What would be the ideal datatype for this?
        -TransactionType Object?
        -enum?  => enum mane...
        -boolean? 1:BUY, 0:SELL
    */


    public Transaction(){}

    public Transaction(int transID, TransactionType transType, int numOfShares, double transPrice, String symbol, double commission, String comments){
        this.transactionID = transID;
        this.transactionType = transType;
        this.numberOfShares = numOfShares;
        this.transactionPricePerShare = transPrice;
        this.symbol = symbol;
        this.commission = commission;
        this.comments = comments;
    }

    public int getTransactionID(){
        return transactionID;
    }

    public int getNumberOfShares() {
        return numberOfShares;
    }

    public void setNumberOfShares(int numberOfShares) {
        this.numberOfShares = numberOfShares;
    }

    public double getTransactionPrice() {
        return transactionPricePerShare;
    }

    public void setTransactionPrice(double transactionPrice) {
        this.transactionPricePerShare = transactionPrice;
    }

    public String getSymbol() {
        return symbol;
    }

    //symbols can't be be changed. well, they can be revised, but i dunno if we wanna have that functionality in yet.
    //thats an edge case...for now...until CGC goes to WEED
//    public void setSymbol(String symbol) {
//        this.symbol = symbol;
//    }

    public double getPriceTarget() {
        return priceTargetPerShare;
    }

    public void setPriceTarget(double priceTarget) {
        this.priceTargetPerShare = priceTarget;
    }

    public double getCommission() {
        return commission;
    }

    //can't really set commission. Might go up or down, but its fixed at time of transaction.
    //jesus, can you imagine? variable commission? fukdat.gif
//    public void setCommission(double commission) {
//        this.commission = commission;
//    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    //can't change a transaction type. have to make another transaction of opposite type.
//    public void setTransactionType(TransactionType transactionType) {
//        this.transactionType = transactionType;
//    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
