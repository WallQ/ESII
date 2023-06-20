package es2_groupbf;

import es2_groupbf.entities.Client;
import es2_groupbf.entities.Statistics;
import es2_groupbf.entities.Transaction;

import java.util.List;

import static es2_groupbf.Gson.exportData;

public class AppContext implements IContext {
    private final List<Client> clients;
    private final Statistics statistics;

    public AppContext(List<Client> clients, Statistics statistics) {
        this.clients = clients;
        this.statistics = statistics;
    }

    @Override
    public List<Client> getClients() {
        return clients;
    }

    @Override
    public Statistics getStatistics() {
        return statistics;
    }

    public static IContext init() throws Exception {
        List<Transaction> transactions = OpenCSV.loadData("dataset.csv");

        if (transactions.isEmpty()) {
            throw new Exception("The file provided with data it's probably empty, please provide a file with data!");
        }

        List<Client> clients = new ClientsBuilder()
                .createClients(transactions)
                .setIndicators()
                .setScores()
                .build();

        Statistics statistics = new StatisticsBuilder()
                .createStatistics(clients)
                .setSeasonalityStatistics()
                .setCommunicationChannelStatistics()
                .setPurchasesStatistics()
                .setPaymentMethodStatistics()
                .setClientScoreStatistics()
                .build();

        exportData(clients, "clients.json");
        exportData(statistics, "statistics.json");

        return new AppContext(clients, statistics);
    }
}
