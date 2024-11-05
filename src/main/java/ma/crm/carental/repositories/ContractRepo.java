package ma.crm.carental.repositories;


import jakarta.persistence.Entity;
import java.lang.reflect.Field;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import ma.crm.carental.entities.Contract;
import ma.crm.carental.entities.DeliveryGuy;
import ma.crm.carental.repositories.interfaces.ContractInterface;
import ma.crm.carental.utils.DBUtiles;


@Slf4j
@Repository
public class ContractRepo implements ContractInterface{


    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private  int batchSize = 10;


    @PersistenceContext
    private EntityManager em ;


    @Override
    public List<Contract> insertContractInBatch(List<Contract> contracts) {

        if (contracts == null || contracts.isEmpty()) {
            return contracts ;
        }

        for (int i = 0; i < contracts.size(); i++) {
            
            em.persist(contracts.get(i));

            if (i > 0 && i % batchSize == 0) {
                em.flush();
                em.clear();
            }
        }

        return contracts ;
    }

    @Override
    public int deleteContracts(List<Long> contractIds) {

        if (contractIds == null || contractIds.isEmpty()) {
            return 0; // No records to delete
        }

        String jpql = "DELETE FROM Contract c WHERE c.id IN :ids" ;

        return em.createQuery(jpql)
                    .setParameter("ids", contractIds)
                    .executeUpdate() ;
    }

    @Override
    public int updateContractsInBatch(List<Long> contractIds, Contract contract) {

        int totalUpdatedRecords = 0;

        /**
         * @ get field value map from an object
         */
        Map<String, Object> fieldsToUpdate = DBUtiles.convertToMap(contract);

        //@ Check if there are any fields to update before entering the loop
        if (fieldsToUpdate.isEmpty()) {
            throw new IllegalArgumentException("No fields to update");
        }

        //@ Validate field names to prevent injection and errors
        Set<String> validFieldNames = DBUtiles.getValidateFieldNames(Contract.class.getDeclaredFields()) ;
        

        log.info("fildes to udate {} : 〽️" , fieldsToUpdate);

        Query query = DBUtiles.buildJPQLQueryDynamicallyForUpdate(fieldsToUpdate, validFieldNames, em) ;
        
        
        
        /**
         * $ @apiNote plz update { ClientRepo.updateClientsInBatch} 
         * 
         * */
        for (int i = 0; i < contractIds.size(); i += batchSize) {
            List<Long> batch = contractIds.subList(i, Math.min(i + batchSize, contractIds.size()));
    
            
            // Set the client IDs for the current batch
            query.setParameter("contractIds", batch);

            // Execute the update
            int updatedRecords = query.executeUpdate();
            totalUpdatedRecords += updatedRecords;
    
            em.flush();
            em.clear();
        }
        

        return totalUpdatedRecords ;
    }

    @Override
    public List<Contract> contractsWithPagination(int page, int pageSize) {

        String jpql = "SELECT c FROM Contract c ORDER BY c.id";

        TypedQuery<Contract> query = em.createQuery(jpql, Contract.class);

        query.setFirstResult(page * pageSize);
        query.setMaxResults(pageSize);

        return query.getResultList() ;
    }

    @Override
    public Contract find(long id) throws NoResultException{

        String hql = "FROM Contract c WHERE c.id = :id";
            
        return (Contract) em.createQuery(hql)
                                    .setParameter("id", id)
                                    .getSingleResult() ;
    }

    @Override
    public Long count() {

        String jpql = "SELECT COUNT(c) FROM Contract c";

        TypedQuery<Long> query = em.createQuery(jpql, Long.class);
        
        return query.getSingleResult() ;
    }

    

    private boolean isEntityWithNullId(Object obj) {
        // if (obj == null) return true;
    
        try {
            // Check if the class is annotated as an entity (assuming a JPA Entity in jakarta package)
            if (obj.getClass().isAnnotationPresent(Entity.class)) {
                log.info("Object is not an entity: {}", obj.getClass().getName());
                

                Field idField = obj.getClass().getDeclaredField("id");
                idField.setAccessible(true);
                
                Object idValue = idField.get(obj);
                
                if (idValue != null) {
                    return false;  // `id` field is non-null, so it's a valid entity with a valid ID
                } else {
                    log.info("Entity {} has a null id", obj.getClass().getName());
                }
                return true;
            }
    
            // Check for `id` field and validate if it is non-null
            
            
            
            
        } catch (NoSuchFieldException e) {
            log.warn("No id field found in entity {}", obj.getClass().getName(), e);
        } catch (IllegalAccessException e) {
            log.warn("Unable to access id field for entity {}", obj.getClass().getName(), e);
        }
    
        return false; // Either `id` is null or entity check failed
    }    

}
