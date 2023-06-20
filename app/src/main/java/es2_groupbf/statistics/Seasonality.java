package es2_groupbf.statistics;

import es2_groupbf.entities.Client;
import es2_groupbf.entities.Transaction;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Seasonality {
    private static String getSeason(LocalDate date) {
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();

        return switch (month) {
            case 3 -> day >= 21 ? "Spring" : "Winter";
            case 4, 5 -> "Spring";
            case 6 -> day >= 21 ? "Summer" : "Spring";
            case 7, 8 -> "Summer";
            case 9 -> day >= 21 ? "Autumn" : "Summer";
            case 10, 11 -> "Autumn";
            case 12 -> day >= 21 ? "Winter" : "Autumn";
            case 1, 2 -> "Winter";
            default -> "Invalid date";
        };
    }

    private static void countSeasons(List<Transaction> transactions, Map<String, Integer> seasonCount) {
        for (Transaction transaction : transactions) {
            LocalDate date = transaction.getDate();
            String season = getSeason(date);

            if (seasonCount.containsKey(season)) {
                seasonCount.put(season, seasonCount.get(season) + 1);
            } else {
                seasonCount.put(season, 1);
            }
        }
    }

    private static String getMostInterestingSeason(Map<String, Integer> seasonCount) {
        Map.Entry<String, Integer> maxEntry = Collections.max(seasonCount.entrySet(), Map.Entry.comparingByValue());
        return maxEntry.getKey();
    }

    public static String getMostInterestingSeasonPerClient(Client client) {
        Map<String, Integer> seasonCount = new HashMap<>();

        countSeasons(client.getTransactions(), seasonCount);

        return getMostInterestingSeason(seasonCount);
    }

    public static String getMostInterestingSeasonInGeneral(List<Client> clients) {
        Map<String, Integer> seasonCount = new HashMap<>();

        for (Client client : clients) {
            countSeasons(client.getTransactions(), seasonCount);
        }

        return getMostInterestingSeason(seasonCount);
    }
}
