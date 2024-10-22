package ma.crm.carental.repositories.interfaces;

import java.util.List;

import ma.crm.carental.entities.Client;

public interface ClientRepoInterface {

    List<Client> insertClientInBatch(List<Client> clients) ;
    int deleteClients(List<Long> clintesIds ) ;
    
}
