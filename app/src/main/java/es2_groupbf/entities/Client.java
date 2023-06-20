package es2_groupbf.entities;

import com.google.gson.annotations.Expose;

import java.util.List;

public class Client {
    @Expose
    private String docIdHash;
    private List<Transaction> transactions;
    private Double monetization;
    private Integer regularity;
    private Integer totalPurchases;
    @Expose
    private Integer monetizationScore;
    @Expose
    private Integer regularityScore;
    @Expose
    private Integer totalPurchasesScore;
    private Integer generalScore;

    public Client() {
    }

    public Client(String docIdHash, List<Transaction> transactions) {
        this.docIdHash = docIdHash;
        this.transactions = transactions;
    }

    public String getDocIdHash() {
        return docIdHash;
    }

    public void setDocIdHash(String docIdHash) {
        this.docIdHash = docIdHash;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Double getMonetization() {
        return monetization;
    }

    public void setMonetization(Double monetization) {
        this.monetization = monetization;
    }

    public Integer getRegularity() {
        return regularity;
    }

    public void setRegularity(Integer regularity) {
        this.regularity = regularity;
    }

    public Integer getTotalPurchases() {
        return totalPurchases;
    }

    public void setTotalPurchases(Integer totalPurchases) {
        this.totalPurchases = totalPurchases;
    }

    public Integer getMonetizationScore() {
        return monetizationScore;
    }

    public void setMonetizationScore(Integer monetizationScore) {
        this.monetizationScore = monetizationScore;
    }

    public Integer getRegularityScore() {
        return regularityScore;
    }

    public void setRegularityScore(Integer regularityScore) {
        this.regularityScore = regularityScore;
    }

    public Integer getTotalPurchasesScore() {
        return totalPurchasesScore;
    }

    public void setTotalPurchasesScore(Integer totalPurchasesScore) {
        this.totalPurchasesScore = totalPurchasesScore;
    }

    public Integer getGeneralScore() {
        return generalScore;
    }

    public void setGeneralScore(Integer generalScore) {
        this.generalScore = generalScore;
    }
}
