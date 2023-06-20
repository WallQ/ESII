package es2_groupbf.segmentation.scores;

import es2_groupbf.entities.Client;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public abstract class Score {
    protected List<Client> sortClients(List<Client> clients, Comparator<Client> comparator) {
        return clients.stream().sorted(comparator).collect(Collectors.toList());
    }

    protected void calculateQuartileScore(List<Client> clients, Comparator<Client> comparator, BiConsumer<Client, Integer> scoreSetter) {
        List<Client> sortedClients = sortClients(clients, comparator);

        int numberClients = sortedClients.size();
        int q1 = numberClients / 4 - 1;
        int q2 = numberClients / 2 - 1;
        int q3 = q1 + q2 + 1;
        int q4 = numberClients - 1;

        for (int i = 0; i <= numberClients; i++) {
            if (i <= q1) {
                scoreSetter.accept(sortedClients.get(i), 1);
            } else if (i <= q2) {
                scoreSetter.accept(sortedClients.get(i), 2);
            } else if (i <= q3) {
                scoreSetter.accept(sortedClients.get(i), 3);
            } else if (i <= q4) {
                scoreSetter.accept(sortedClients.get(i), 4);
            }
        }
    }
}
