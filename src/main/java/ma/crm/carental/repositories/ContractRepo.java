package ma.crm.carental.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ma.crm.carental.entities.Contract;
import ma.crm.carental.repositories.interfaces.ContractInterface;


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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateContractsInBatch'");
    }

    @Override
    public List<Contract> contractsWithPagination(int page, int pageSize) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'contractsWithPagination'");
    }

    @Override
    public Contract find(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'find'");
    }

    @Override
    public Long count() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'count'");
    }
    
}
