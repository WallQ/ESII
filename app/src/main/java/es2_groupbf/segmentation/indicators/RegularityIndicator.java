package es2_groupbf.segmentation.indicators;

import es2_groupbf.entities.Transaction;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class RegularityIndicator implements IIndicator<Integer> {
    @Override
    public Integer calculateIndicator(List<Transaction> transactions) {
        Date currentDate = new Date();
        Date latestTransactionDate = null;

        for (Transaction transaction : transactions) {
            if (latestTransactionDate == null || transaction.getPurchaseDate().after(latestTransactionDate)) {
                latestTransactionDate = transaction.getPurchaseDate();
            }
        }

        if (Objects.isNull(latestTransactionDate)) return Integer.MAX_VALUE;

        long diffInMilliseconds = currentDate.getTime() - latestTransactionDate.getTime();

        return (int) (diffInMilliseconds / (24 * 60 * 60 * 1000));
    }
}
