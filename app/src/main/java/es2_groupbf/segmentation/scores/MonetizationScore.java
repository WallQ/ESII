package es2_groupbf.segmentation.scores;

import es2_groupbf.entities.Client;

import java.util.Comparator;
import java.util.List;

public class MonetizationScore extends Score implements IScore {
    @Override
    public void calculateScore(List<Client> clients) {
        calculateQuartileScore(clients, Comparator.comparing(Client::getMonetization), Client::setMonetizationScore);
    }
}
