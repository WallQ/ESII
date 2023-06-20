package es2_groupbf.segmentation.indicators;

import es2_groupbf.entities.Transaction;

import java.util.List;

public class TotalPurchasesIndicator implements IIndicator<Integer> {
    @Override
    public Integer calculateIndicator(List<Transaction> transactions) {
        return transactions.size();
    }
}
