package ma.crm.carental.repositories;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import ma.crm.carental.entities.Client;
import ma.crm.carental.repositories.interfaces.ClientRepoInterface;
import ma.crm.carental.tenantfilter.TenantContext;


@Slf4j
@Repository
public class ClientRepo implements ClientRepoInterface{

    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private  int batchSize = 10;

    @PersistenceContext
    private EntityManager em ;

    @Override
    public List<Client> insertClientInBatch(List<Client> clients) {

        log.debug("batch size is : {} 🔖\n" , batchSize);

        if (clients == null || clients.isEmpty()) {
            return clients ;
        }

        for (int i = 0; i < clients.size(); i++) {
            
            em.persist(clients.get(i));

            if (i > 0 && i % batchSize == 0) {
                em.flush();
                em.clear();
            }
        }
        
        return clients ;
    }
    
    @Override
    public int deleteClients(List<Long> clintesIds) {

        if (clintesIds == null || clintesIds.isEmpty()) {
            return 0; // No records to delete
        }

        String jpql = "DELETE FROM Client c WHERE c.id IN :ids" ;

        return em.createQuery(jpql)
                    .setParameter("ids", clintesIds)
                    .executeUpdate() ;
    }
}
