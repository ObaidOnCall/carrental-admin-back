package ma.crm.carental.repositories.interfaces;

import java.util.List;
import java.util.Optional;

import ma.crm.carental.entities.Client;
import ma.crm.carental.entities.ClientDocs;

public interface ClientRepoInterface {

    List<Client> insertClientInBatch(List<Client> clients) ;

    int deleteClients(List<Long> clintesIds ) ;

    int updateClientsInBatch(List<Long> clintesIds , Client client) ;

    List<Client> clientsWithPagination(int page, int pageSize) ;

    Client find(long id) ;

    Long count() ;

    List<ClientDocs> insertClientDocs(List<ClientDocs> clientDocs) ;
    
}
