package es2_groupbf.segmentation.indicators;

import es2_groupbf.entities.Transaction;

import java.util.List;

public interface IIndicator<T> {
    T calculateIndicator(List<Transaction> transactions);
}
