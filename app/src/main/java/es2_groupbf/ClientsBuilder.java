package es2_groupbf;

import es2_groupbf.entities.Client;
import es2_groupbf.entities.Transaction;
import es2_groupbf.segmentation.indicators.MonetizationIndicator;
import es2_groupbf.segmentation.indicators.RegularityIndicator;
import es2_groupbf.segmentation.indicators.TotalPurchasesIndicator;
import es2_groupbf.segmentation.scores.IndividualScore;
import es2_groupbf.segmentation.scores.MonetizationScore;
import es2_groupbf.segmentation.scores.RegularityScore;
import es2_groupbf.segmentation.scores.TotalPurchasesScore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientsBuilder {
    private final List<Client> clients = new ArrayList<>();

    public ClientsBuilder() {
    }

    public ClientsBuilder createClients(List<Transaction> transactions) {
        HashMap<String, List<Transaction>> clientsMap = new HashMap<>();

        for (Transaction transaction : transactions) {
            clientsMap.putIfAbsent(transaction.getDocIdHash(), new ArrayList<>());
            clientsMap.get(transaction.getDocIdHash()).add(transaction);
        }

        for (Map.Entry<String, List<Transaction>> entry : clientsMap.entrySet()) {
            this.clients.add(new Client(entry.getKey(), entry.getValue()));
        }

        return this;
    }

    public ClientsBuilder setIndicators() {
        for (Client client : this.clients) {
            client.setMonetization(new MonetizationIndicator().calculateIndicator(client.getTransactions()));
            client.setRegularity(new RegularityIndicator().calculateIndicator(client.getTransactions()));
            client.setTotalPurchases(new TotalPurchasesIndicator().calculateIndicator(client.getTransactions()));
        }

        return this;
    }

    public ClientsBuilder setScores() {
        new MonetizationScore().calculateScore(this.clients);
        new RegularityScore().calculateScore(this.clients);
        new TotalPurchasesScore().calculateScore(this.clients);
        new IndividualScore().calculateScore(this.clients);

        return this;
    }

    public List<Client> build() {
        return clients;
    }
}
