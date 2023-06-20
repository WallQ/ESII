package es2_groupbf.statistics;

import es2_groupbf.entities.Client;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientScore {
    private static Map<String, Client> findClientWithScore(List<Client> clients, boolean isMinimum) {
        Map<String, Client> result = new HashMap<>();
        List<Client> sortedClients;

        sortedClients = clients.stream().sorted(Comparator.comparing(Client::getMonetization).reversed()).toList();
        Client bestOrWorstClientOfMonetizationScore = null;
        int minOrMaxMonetizationScore = isMinimum ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        for (Client client : sortedClients) {
            int monetizationScore = client.getMonetizationScore();
            if (isMinimum ? monetizationScore < minOrMaxMonetizationScore : monetizationScore > minOrMaxMonetizationScore) {
                bestOrWorstClientOfMonetizationScore = client;
                minOrMaxMonetizationScore = monetizationScore;
            }
        }
        result.put("monetizationScore", bestOrWorstClientOfMonetizationScore);

        sortedClients = clients.stream().sorted(Comparator.comparing(Client::getRegularity)).toList();
        Client bestOrWorstClientOfRegularityScore = null;
        int minOrMaxRegularityScore = isMinimum ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        for (Client client : sortedClients) {
            int regularityScore = client.getRegularityScore();
            if (isMinimum ? regularityScore < minOrMaxRegularityScore : regularityScore > minOrMaxRegularityScore) {
                bestOrWorstClientOfRegularityScore = client;
                minOrMaxRegularityScore = regularityScore;
            }
        }
        result.put("regularityScore", bestOrWorstClientOfRegularityScore);

        sortedClients = clients.stream().sorted(Comparator.comparing(Client::getTotalPurchases).reversed()).toList();
        Client bestOrWorstClientOfTotalPurchasesScore = null;
        int minOrMaxTotalPurchasesScore = isMinimum ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        for (Client client : sortedClients) {
            int totalPurchasesScore = client.getTotalPurchasesScore();
            if (isMinimum ? totalPurchasesScore < minOrMaxTotalPurchasesScore : totalPurchasesScore > minOrMaxTotalPurchasesScore) {
                bestOrWorstClientOfTotalPurchasesScore = client;
                minOrMaxTotalPurchasesScore = totalPurchasesScore;
            }
        }
        result.put("totalPurchasesScore", bestOrWorstClientOfTotalPurchasesScore);

        return result;
    }

    public static Map<String, Client> getClientWithMinimumOfEachScore(List<Client> clients) {
        return findClientWithScore(clients, true);
    }

    public static Map<String, Client> getClientWithMaximumOfEachScore(List<Client> clients) {
        return findClientWithScore(clients, false);
    }

    private static Client findClientWithAverageScore(List<Client> clients, boolean isWorst) {
        Client worstOrBestAverageScoreClient = null;
        int worstOrBestAverageScore = isWorst ? Integer.MAX_VALUE : Integer.MIN_VALUE;

        List<Client> sortedClients = clients.stream().sorted(Comparator.comparing(Client::getGeneralScore).reversed()).toList();
        for (Client client : sortedClients) {
            int averageScore = (client.getMonetizationScore() + client.getRegularityScore() + client.getTotalPurchasesScore()) / 3;

            if (isWorst ? averageScore < worstOrBestAverageScore : averageScore > worstOrBestAverageScore) {
                worstOrBestAverageScoreClient = client;
                worstOrBestAverageScore = averageScore;
            }
        }

        return worstOrBestAverageScoreClient;
    }

    public static Client getClientWithWorstAverageScore(List<Client> clients) {
        return findClientWithAverageScore(clients, true);
    }

    public static Client getClientWithBestAverageScore(List<Client> clients) {
        return findClientWithAverageScore(clients, false);
    }
}
