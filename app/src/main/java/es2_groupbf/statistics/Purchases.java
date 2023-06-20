package es2_groupbf.statistics;

import es2_groupbf.entities.Client;
import es2_groupbf.entities.Transaction;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Purchases {
    private static Double calculatePurchaseValue(List<Client> clients, boolean isMinimum) {
        double purchaseValue = isMinimum ? Double.MAX_VALUE : Double.MIN_VALUE;

        for (Client client : clients) {
            for (Transaction transaction : client.getTransactions()) {
                double value = transaction.getLodgingRevenue() + transaction.getOtherRevenue();

                if (isMinimum ? value < purchaseValue : value > purchaseValue) {
                    purchaseValue = value;
                }
            }
        }

        return purchaseValue;
    }

    public static Double getMinimumPurchaseValue(List<Client> clients) {

        return calculatePurchaseValue(clients, true);
    }

    public static Double getMaximumPurchaseValue(List<Client> clients) {
        return calculatePurchaseValue(clients, false);
    }

    public static Double getAveragePurchasesValue(List<Client> clients) {
        double total = 0;
        int count = 0;

        if (clients.isEmpty()) {
            return 0.0;
        }

        for (Client client : clients) {
            for (Transaction transaction : client.getTransactions()) {
                total += transaction.getLodgingRevenue() + transaction.getOtherRevenue();
                count++;
            }
        }

        return total / count;
    }

    public static Map<String, Double> getAverageTimeIntervalBetweenPurchasesPerClient(List<Client> clients) {
        Map<String, Double> result = new HashMap<>();

        for (Client client : clients) {
            List<Transaction> transactions = client.getTransactions();

            if (transactions.size() < 2) {
                continue;
            }

            transactions.sort(Comparator.comparing(Transaction::getDate));

            long totalTimeInterval = 0;
            for (int i = 1; i < transactions.size(); i++) {
                long timeInterval = transactions.get(i).getDate().toEpochDay() - transactions.get(i - 1).getDate().toEpochDay();
                totalTimeInterval += timeInterval;
            }

            double averageTimeInterval = (double) totalTimeInterval / (transactions.size() - 1);

            result.put(client.getDocIdHash(), averageTimeInterval);
        }

        return result;
    }

    private static Double calculateTimeInterval(List<Client> clients, boolean isMinimum) {
        long timeInterval = isMinimum ? Long.MAX_VALUE : Long.MIN_VALUE;

        for (Client client : clients) {
            List<Transaction> transactions = client.getTransactions();

            if (transactions.size() < 2) {
                continue;
            }

            transactions.sort(Comparator.comparing(Transaction::getDate));

            for (int i = 1; i < transactions.size(); i++) {
                long interval = transactions.get(i).getDate().toEpochDay() - transactions.get(i - 1).getDate().toEpochDay();

                if (isMinimum ? interval < timeInterval : interval > timeInterval) {
                    timeInterval = interval;
                }
            }
        }

        return (double) timeInterval;
    }

    public static Double getMinimumTimeIntervalBetweenPurchasesInGeneral(List<Client> clients) {
        return calculateTimeInterval(clients, true);
    }

    public static Double getMaximumTimeIntervalBetweenPurchasesInGeneral(List<Client> clients) {
        return calculateTimeInterval(clients, false);
    }

    public static Double getAverageTimeIntervalBetweenPurchasesInGeneral(List<Client> clients) {
        long totalTimeInterval = 0;
        int totalTransactions = 0;

        if (clients.isEmpty()) {
            return 0.0;
        }

        for (Client client : clients) {
            List<Transaction> transactions = client.getTransactions();

            if (transactions.size() < 2) {
                continue;
            }

            transactions.sort(Comparator.comparing(Transaction::getDate));

            totalTransactions += transactions.size() - 1;
            for (int i = 1; i < transactions.size(); i++) {
                long timeInterval = transactions.get(i).getDate().toEpochDay() - transactions.get(i - 1).getDate().toEpochDay();
                totalTimeInterval += timeInterval;
            }
        }

        return (double) totalTimeInterval / totalTransactions;
    }
}
