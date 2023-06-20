package es2_groupbf.entities;

import com.google.gson.annotations.Expose;

import java.util.Map;

public class Statistics {
    @Expose
    private Map<String, String> mostInterestingSeasonPerClient;
    @Expose
    private String mostInterestingSeasonInGeneral;
    @Expose
    private String mostUsedCommunicationChannel;
    @Expose
    private Double minimumPurchaseValue;
    @Expose
    private Double maximumPurchaseValue;
    @Expose
    private Double averagePurchasesValue;
    @Expose
    private Map<String, Double> averageTimeIntervalBetweenPurchasesPerClient;
    @Expose
    private Double minimumTimeIntervalBetweenPurchasesInGeneral;
    @Expose
    private Double maximumTimeIntervalBetweenPurchasesInGeneral;
    @Expose
    private Double averageTimeIntervalBetweenPurchasesInGeneral;
    @Expose
    private Map<String, String> predominantPaymentMethodPerClient;
    @Expose
    private String predominantPaymentMethodInGeneral;
    @Expose
    private Map<String, Client> clientWithMinimumOfEachScore;
    @Expose
    private Map<String, Client> clientWithMaximumOfEachScore;
    @Expose
    private Client clientWithWorstAverageScore;
    @Expose
    private Client clientWithBestAverageScore;

    public Statistics() {
    }

    public Statistics(Map<String, String> mostInterestingSeasonPerClient, String mostInterestingSeasonInGeneral, String mostUsedCommunicationChannel, Double minimumPurchaseValue, Double maximumPurchaseValue, Double averagePurchasesValue, Map<String, Double> averageTimeIntervalBetweenPurchasesPerClient, Double minimumTimeIntervalBetweenPurchasesInGeneral, Double maximumTimeIntervalBetweenPurchasesInGeneral, Double averageTimeIntervalBetweenPurchasesInGeneral, Map<String, String> predominantPaymentMethodPerClient, String predominantPaymentMethodInGeneral, Map<String, Client> clientWithMinimumOfEachScore, Map<String, Client> clientWithMaximumOfEachScore, Client clientWithWorstAverageScore, Client clientWithBestAverageScore) {
        this.mostInterestingSeasonPerClient = mostInterestingSeasonPerClient;
        this.mostInterestingSeasonInGeneral = mostInterestingSeasonInGeneral;
        this.mostUsedCommunicationChannel = mostUsedCommunicationChannel;
        this.minimumPurchaseValue = minimumPurchaseValue;
        this.maximumPurchaseValue = maximumPurchaseValue;
        this.averagePurchasesValue = averagePurchasesValue;
        this.averageTimeIntervalBetweenPurchasesPerClient = averageTimeIntervalBetweenPurchasesPerClient;
        this.minimumTimeIntervalBetweenPurchasesInGeneral = minimumTimeIntervalBetweenPurchasesInGeneral;
        this.maximumTimeIntervalBetweenPurchasesInGeneral = maximumTimeIntervalBetweenPurchasesInGeneral;
        this.averageTimeIntervalBetweenPurchasesInGeneral = averageTimeIntervalBetweenPurchasesInGeneral;
        this.predominantPaymentMethodPerClient = predominantPaymentMethodPerClient;
        this.predominantPaymentMethodInGeneral = predominantPaymentMethodInGeneral;
        this.clientWithMinimumOfEachScore = clientWithMinimumOfEachScore;
        this.clientWithMaximumOfEachScore = clientWithMaximumOfEachScore;
        this.clientWithWorstAverageScore = clientWithWorstAverageScore;
        this.clientWithBestAverageScore = clientWithBestAverageScore;
    }

    public Map<String, String> getMostInterestingSeasonPerClient() {
        return mostInterestingSeasonPerClient;
    }

    public void setMostInterestingSeasonPerClient(Map<String, String> mostInterestingSeasonPerClient) {
        this.mostInterestingSeasonPerClient = mostInterestingSeasonPerClient;
    }

    public String getMostInterestingSeasonInGeneral() {
        return mostInterestingSeasonInGeneral;
    }

    public void setMostInterestingSeasonInGeneral(String mostInterestingSeasonInGeneral) {
        this.mostInterestingSeasonInGeneral = mostInterestingSeasonInGeneral;
    }

    public String getMostUsedCommunicationChannel() {
        return mostUsedCommunicationChannel;
    }

    public void setMostUsedCommunicationChannel(String mostUsedCommunicationChannel) {
        this.mostUsedCommunicationChannel = mostUsedCommunicationChannel;
    }

    public Double getMinimumPurchaseValue() {
        return minimumPurchaseValue;
    }

    public void setMinimumPurchaseValue(Double minimumPurchaseValue) {
        this.minimumPurchaseValue = minimumPurchaseValue;
    }

    public Double getMaximumPurchaseValue() {
        return maximumPurchaseValue;
    }

    public void setMaximumPurchaseValue(Double maximumPurchaseValue) {
        this.maximumPurchaseValue = maximumPurchaseValue;
    }

    public Double getAveragePurchasesValue() {
        return averagePurchasesValue;
    }

    public void setAveragePurchasesValue(Double averagePurchasesValue) {
        this.averagePurchasesValue = averagePurchasesValue;
    }

    public Map<String, Double> getAverageTimeIntervalBetweenPurchasesPerClient() {
        return averageTimeIntervalBetweenPurchasesPerClient;
    }

    public void setAverageTimeIntervalBetweenPurchasesPerClient(Map<String, Double> averageTimeIntervalBetweenPurchasesPerClient) {
        this.averageTimeIntervalBetweenPurchasesPerClient = averageTimeIntervalBetweenPurchasesPerClient;
    }

    public Double getMinimumTimeIntervalBetweenPurchasesInGeneral() {
        return minimumTimeIntervalBetweenPurchasesInGeneral;
    }

    public void setMinimumTimeIntervalBetweenPurchasesInGeneral(Double minimumTimeIntervalBetweenPurchasesInGeneral) {
        this.minimumTimeIntervalBetweenPurchasesInGeneral = minimumTimeIntervalBetweenPurchasesInGeneral;
    }

    public Double getMaximumTimeIntervalBetweenPurchasesInGeneral() {
        return maximumTimeIntervalBetweenPurchasesInGeneral;
    }

    public void setMaximumTimeIntervalBetweenPurchasesInGeneral(Double maximumTimeIntervalBetweenPurchasesInGeneral) {
        this.maximumTimeIntervalBetweenPurchasesInGeneral = maximumTimeIntervalBetweenPurchasesInGeneral;
    }

    public Double getAverageTimeIntervalBetweenPurchasesInGeneral() {
        return averageTimeIntervalBetweenPurchasesInGeneral;
    }

    public void setAverageTimeIntervalBetweenPurchasesInGeneral(Double averageTimeIntervalBetweenPurchasesInGeneral) {
        this.averageTimeIntervalBetweenPurchasesInGeneral = averageTimeIntervalBetweenPurchasesInGeneral;
    }

    public Map<String, String> getPredominantPaymentMethodPerClient() {
        return predominantPaymentMethodPerClient;
    }

    public void setPredominantPaymentMethodPerClient(Map<String, String> predominantPaymentMethodPerClient) {
        this.predominantPaymentMethodPerClient = predominantPaymentMethodPerClient;
    }

    public String getPredominantPaymentMethodInGeneral() {
        return predominantPaymentMethodInGeneral;
    }

    public void setPredominantPaymentMethodInGeneral(String predominantPaymentMethodInGeneral) {
        this.predominantPaymentMethodInGeneral = predominantPaymentMethodInGeneral;
    }

    public Map<String, Client> getClientWithMinimumOfEachScore() {
        return clientWithMinimumOfEachScore;
    }

    public void setClientWithMinimumOfEachScore(Map<String, Client> clientWithMinimumOfEachScore) {
        this.clientWithMinimumOfEachScore = clientWithMinimumOfEachScore;
    }

    public Map<String, Client> getClientWithMaximumOfEachScore() {
        return clientWithMaximumOfEachScore;
    }

    public void setClientWithMaximumOfEachScore(Map<String, Client> clientWithMaximumOfEachScore) {
        this.clientWithMaximumOfEachScore = clientWithMaximumOfEachScore;
    }

    public Client getClientWithWorstAverageScore() {
        return clientWithWorstAverageScore;
    }

    public void setClientWithWorstAverageScore(Client clientWithWorstAverageScore) {
        this.clientWithWorstAverageScore = clientWithWorstAverageScore;
    }

    public Client getClientWithBestAverageScore() {
        return clientWithBestAverageScore;
    }

    public void setClientWithBestAverageScore(Client clientWithBestAverageScore) {
        this.clientWithBestAverageScore = clientWithBestAverageScore;
    }
}
