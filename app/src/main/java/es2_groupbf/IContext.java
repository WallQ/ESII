package es2_groupbf;

import es2_groupbf.entities.Client;
import es2_groupbf.entities.Statistics;

import java.util.List;

public interface IContext {
    List<Client> getClients();

    Statistics getStatistics();
}
