package es2_groupbf;

import es2_groupbf.entities.Client;
import es2_groupbf.entities.Statistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static es2_groupbf.statistics.ClientScore.*;
import static es2_groupbf.statistics.CommunicationChannels.getMostUsedCommunicationChannel;
import static es2_groupbf.statistics.PaymentMethod.getPredominantPaymentMethodInGeneral;
import static es2_groupbf.statistics.PaymentMethod.getPredominantPaymentMethodPerClient;
import static es2_groupbf.statistics.Purchases.*;
import static es2_groupbf.statistics.Seasonality.getMostInterestingSeasonInGeneral;
import static es2_groupbf.statistics.Seasonality.getMostInterestingSeasonPerClient;

public class StatisticsBuilder {
    private List<Client> clients = new ArrayList<>();
    private final Statistics statistics = new Statistics();

    public StatisticsBuilder() {
    }

    public StatisticsBuilder createStatistics(List<Client> clients) {
        this.clients = clients;
        return this;
    }

    public StatisticsBuilder setSeasonalityStatistics() {
        Map<String, String> clientSeasonalityMap = new HashMap<>();

        for (Client client : clients) {
            clientSeasonalityMap.put(client.getDocIdHash(), getMostInterestingSeasonPerClient(client));
        }
        statistics.setMostInterestingSeasonPerClient(clientSeasonalityMap);

        statistics.setMostInterestingSeasonInGeneral(getMostInterestingSeasonInGeneral(clients));

        return this;
    }

    public StatisticsBuilder setCommunicationChannelStatistics() {
        statistics.setMostUsedCommunicationChannel(getMostUsedCommunicationChannel(clients));

        return this;
    }

    public StatisticsBuilder setPurchasesStatistics() {
        Map<String, Double> clientPurchasesMap = getAverageTimeIntervalBetweenPurchasesPerClient(clients);

        statistics.setMinimumPurchaseValue(getMinimumPurchaseValue(clients));
        statistics.setMaximumPurchaseValue(getMaximumPurchaseValue(clients));
        statistics.setAveragePurchasesValue(getAveragePurchasesValue(clients));
        statistics.setAverageTimeIntervalBetweenPurchasesPerClient(clientPurchasesMap);
        statistics.setMinimumTimeIntervalBetweenPurchasesInGeneral(getMinimumTimeIntervalBetweenPurchasesInGeneral(clients));
        statistics.setMaximumTimeIntervalBetweenPurchasesInGeneral(getMaximumTimeIntervalBetweenPurchasesInGeneral(clients));
        statistics.setAverageTimeIntervalBetweenPurchasesInGeneral(getAverageTimeIntervalBetweenPurchasesInGeneral(clients));

        return this;
    }

    public StatisticsBuilder setPaymentMethodStatistics() {
        Map<String, String> clientPaymentMethodMap = new HashMap<>();

        for (Client client : clients) {
            clientPaymentMethodMap.put(client.getDocIdHash(), getPredominantPaymentMethodPerClient(client));
        }
        statistics.setPredominantPaymentMethodPerClient(clientPaymentMethodMap);

        statistics.setPredominantPaymentMethodInGeneral(getPredominantPaymentMethodInGeneral(clients));

        return this;
    }

    public StatisticsBuilder setClientScoreStatistics() {
        statistics.setClientWithMinimumOfEachScore(getClientWithMinimumOfEachScore(clients));
        statistics.setClientWithMaximumOfEachScore(getClientWithMaximumOfEachScore(clients));
        statistics.setClientWithWorstAverageScore(getClientWithWorstAverageScore(clients));
        statistics.setClientWithBestAverageScore(getClientWithBestAverageScore(clients));

        return this;
    }

    public Statistics build() {
        return statistics;
    }
}
