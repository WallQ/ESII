package es2_groupbf.statistics;

import es2_groupbf.entities.Client;
import es2_groupbf.entities.Transaction;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentMethod {
    private static String getPaymentMethod(Integer paymentMethod) {
        return switch (paymentMethod) {
            case 1 -> "Payment Provider";
            case 2 -> "Cash";
            case 3 -> "Credit Card";
            case 4 -> "Bank Transfer";
            default -> "Invalid payment method";
        };
    }

    private static void countPaymentMethod(List<Transaction> transactions, Map<Integer, Integer> paymentMethodCount) {
        for (Transaction transaction : transactions) {
            Integer method = transaction.getPaymentMethod();

            if (paymentMethodCount.containsKey(method)) {
                paymentMethodCount.put(method, paymentMethodCount.get(method) + 1);
            } else {
                paymentMethodCount.put(method, 1);
            }
        }
    }

    private static Integer getPredominantPaymentMethod(Map<Integer, Integer> paymentMethodCount) {
        Map.Entry<Integer, Integer> maxEntry = Collections.max(paymentMethodCount.entrySet(), Map.Entry.comparingByValue());
        return maxEntry.getKey();
    }

    public static String getPredominantPaymentMethodPerClient(Client client) {
        Map<Integer, Integer> paymentMethodCount = new HashMap<>();

        countPaymentMethod(client.getTransactions(), paymentMethodCount);

        Integer paymentMethod = getPredominantPaymentMethod(paymentMethodCount);

        return getPaymentMethod(paymentMethod);
    }

    public static String getPredominantPaymentMethodInGeneral(List<Client> clients) {
        Map<Integer, Integer> paymentMethodCount = new HashMap<>();

        for (Client client : clients) {
            countPaymentMethod(client.getTransactions(), paymentMethodCount);
        }

        Integer paymentMethod = getPredominantPaymentMethod(paymentMethodCount);

        return getPaymentMethod(paymentMethod);
    }
}
