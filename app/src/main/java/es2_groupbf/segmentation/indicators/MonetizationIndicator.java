package es2_groupbf.segmentation.indicators;

import es2_groupbf.entities.Transaction;

import java.util.List;

public class MonetizationIndicator implements IIndicator<Double> {
    @Override
    public Double calculateIndicator(List<Transaction> transactions) {
        double sum = 0;

        for (Transaction transaction : transactions) {
            sum += transaction.getLodgingRevenue() + transaction.getOtherRevenue();
        }

        return sum;
    }
}
