package es2_groupbf.segmentation.scores;

import es2_groupbf.entities.Client;

import java.util.List;

public class IndividualScore extends Score implements IScore {
    @Override
    public void calculateScore(List<Client> clients) {
        for (Client client : clients) {
            client.setGeneralScore(client.getMonetizationScore() + client.getRegularityScore() + client.getTotalPurchasesScore());
        }
    }
}
